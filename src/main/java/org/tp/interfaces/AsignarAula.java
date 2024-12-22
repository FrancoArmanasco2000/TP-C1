package org.tp.interfaces;

import org.tp.dto.*;
import org.tp.entity.Reserva;
import org.tp.gestores.GestorAula;
import org.tp.utils.FechaUtils;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class AsignarAula extends JFrame{
    private JPanel asignarAulaPanel;
    private JTable tablaAulas;
    private JButton asignarButton;
    private JButton cancelarButton;
    private static GestorAula gestorAula = new GestorAula();

    public AsignarAula(JFrame parentFrame, DefaultTableModel modeloTablaDiasReserva, ReservaDTO reservaDTO , FechaDTO fechaDTO) {
        this.setContentPane(asignarAulaPanel);
        this.setTitle("Asignar Aula");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(700, 500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        String[] columnas = {"Nombre","Piso", "Capacidad", "Ventilador", "Aire Acondicionado", "Cañon", "Tipo Pizarron"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaAulas.setModel(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaAulas);
        asignarAulaPanel.add(scrollPane, BorderLayout.CENTER);


        ResultadoDTO resultadoDTO = gestorAula.obtenerDisponibilidad(reservaDTO,fechaDTO);
        List<AulaDTO> listaAulasDisponibles = new ArrayList<>();

        if(!(resultadoDTO.getListaAulasDisponibles().isEmpty())){
           for(AulaDTO aulaDTO: resultadoDTO.getListaAulasDisponibles()) {
               modeloTabla.addRow(new Object[]{
                       aulaDTO.getNombre(),
                       aulaDTO.getPiso(),
                       aulaDTO.getCapacidad(),
                       retornarSiONo(aulaDTO.getVentiladores()),
                       retornarSiONo(aulaDTO.getAire_acondicionado()),
                       retornarSiONo(aulaDTO.getCanion()),
                       aulaDTO.getTipoPizarron()
               });
               listaAulasDisponibles.add(aulaDTO);
           }
        } else {


            List<ReservaDTO> reservasSolapada = resultadoDTO.getReservasSolapadas();
            double horasSolapadas = resultadoDTO.getMinimaCantidadSolapada();
            dispose();

            Map<ReservaDTO, List<String>> listaReservasSolapadas = new HashMap<>();


            for (ReservaDTO reservaDTOSolapada : reservasSolapada) {
                if(!listaReservasSolapadas.containsKey(reservaDTOSolapada)){
                    listaReservasSolapadas.put(reservaDTOSolapada,new ArrayList<>());
                    listaReservasSolapadas.get(reservaDTOSolapada).add(reservaDTOSolapada.getListaFechasDTO().get(0).getDia());
                    SwingUtilities.invokeLater(() -> {
                        ReservasSolapadas dialog = new ReservasSolapadas(reservaDTOSolapada, horasSolapadas);
                        dialog.setModal(true);
                        dialog.setVisible(true);
                    });
                }else if(!listaReservasSolapadas.get(reservaDTOSolapada).contains(reservaDTOSolapada.getListaFechasDTO().get(0).getDia())){
                    listaReservasSolapadas.get(reservaDTOSolapada).add(reservaDTOSolapada.getListaFechasDTO().get(0).getDia());
                    SwingUtilities.invokeLater(() -> {
                        ReservasSolapadas dialog = new ReservasSolapadas(reservaDTOSolapada, horasSolapadas);
                        dialog.setModal(true);
                        dialog.setVisible(true);
                    });
                }
            }

            dispose();
        }

        asignarButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               int filaSeleccionada = tablaAulas.getSelectedRow();

               if (filaSeleccionada == -1) {
                   JOptionPane.showMessageDialog(null, "Por favor, selecciona un Aula para asignar.", "Error", JOptionPane.ERROR_MESSAGE);
                   return;
               }

               Long idAula = listaAulasDisponibles.get(filaSeleccionada).getIdAula();
               List<FechaDTO> listaFechas = gestorAula.agregarIdAula(resultadoDTO.getFechasDTO(), idAula);

               reservaDTO.setListaFechasDTO(listaFechas);

               modeloTablaDiasReserva.addRow(new Object[]{
                       fechaDTO.getDia(),
                       fechaDTO.getHorarioInicio(),
                       fechaDTO.getHorarioFin()
               });

               dispose();

           }
        });

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(
                        null,
                        "Se ha cancelado la operación.",
                        "Cancelación",
                        JOptionPane.WARNING_MESSAGE
                );
                dispose();
            }
        });

    }



    public static String retornarSiONo(boolean bool){
        return bool?"SI":"NO";
    }

}
