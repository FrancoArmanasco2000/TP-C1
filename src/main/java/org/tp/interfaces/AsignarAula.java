package org.tp.interfaces;

import org.tp.dto.AulaDTO;
import org.tp.dto.ReservaDTO;
import org.tp.dto.FechaDTO;
import org.tp.dto.ResultadoDTO;
import org.tp.gestores.GestorAula;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class AsignarAula extends JFrame{
    private JPanel asignarAulaPanel;
    private JTable tablaAulas;
    private JButton asignarButton;
    private JButton cancelarButton;
    private static GestorAula gestorAula = new GestorAula();

    public AsignarAula(ReservaDTO reservaDTO, FechaDTO fechaDTO) {
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

        GestorAula gestorAula = new GestorAula();
        ResultadoDTO resultadoDTO = gestorAula.obtenerDisponibilidadAulas(reservaDTO,fechaDTO);

        if(!resultadoDTO.listaAulasDisponibles.isEmpty()){
           for(AulaDTO aulaDTO: resultadoDTO.listaAulasDisponibles) {
               modeloTabla.addRow(new Object[]{
                       aulaDTO.getNombre(),
                       aulaDTO.getPiso(),
                       aulaDTO.getCapacidad(),
                       aulaDTO.getVentiladores(),
                       aulaDTO.getAire_acondicionado(),
                       aulaDTO.getCanion(),
                       aulaDTO.getTipoPizarron()
               });
           }
        }


        asignarButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               int filaSeleccionada = tablaAulas.getSelectedRow();

               if (filaSeleccionada == -1) {
                   JOptionPane.showMessageDialog(null, "Por favor, selecciona un Aula para asignar.", "Error", JOptionPane.ERROR_MESSAGE);
                   return;
               }

               String nombreAula = (String) modeloTabla.getValueAt(filaSeleccionada, 0);

               Long idAula = gestorAula.getAulaByNombre(nombreAula).getIdAula();

               if(reservaDTO.getIdPeriodo() != null) { //Si es periodica
                   gestorAula.asignarAulaAFechasDelPeriodo(reservaDTO,fechaDTO,idAula);
               } else {
                   gestorAula.asignarAulaAFecha(reservaDTO,fechaDTO,idAula);
               }

               dispose();

           }
        });

        //Consultar la disponibilidad de la fecha
        /*List<ReservaDTO> listaReservasSolapadas = gestorAula.obtenerDisponibilidadAulas(reservaDTO, listaFechasDTO);
        for (ReservaDTO reserva : listaReservasSolapadas) {
        SwingUtilities.invokeLater(() -> {
            ReservasSolapadas dialog = new ReservasSolapadas(reserva);
            dialog.setModal(true); // Hace que el JDialog sea modal
            dialog.setVisible(true); // Bloquea hasta que el usuario cierre la ventana
        });
        }
        */

        cancelarButton.addActionListener(e -> dispose());
    }
}
