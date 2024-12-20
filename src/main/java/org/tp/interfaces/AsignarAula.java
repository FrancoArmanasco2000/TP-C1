package org.tp.interfaces;

import org.tp.dto.AulaDTO;
import org.tp.dto.ReservaDTO;
import org.tp.dto.FechaDTO;
import org.tp.dto.ResultadoDTO;
import org.tp.gestores.GestorAula;
import org.tp.utils.FechaUtils;
import org.tp.utils.HorarioUtils;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class AsignarAula extends JFrame{
    private JPanel asignarAulaPanel;
    private JTable tablaAulas;
    private JButton asignarButton;
    private JButton cancelarButton;
//    private static GestorAula gestorAula = new GestorAula();
//
//    public AsignarAula(JFrame parentFrame, JTable tablaDiasReserva, ReservaDTO reservaDTO, FechaDTO fechaDTO) {
//        this.setContentPane(asignarAulaPanel);
//        this.setTitle("Asignar Aula");
//        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        this.setSize(700, 500);
//        this.setLocationRelativeTo(null);
//        this.setVisible(true);
//
//        String[] columnas = {"Nombre","Piso", "Capacidad", "Ventilador", "Aire Acondicionado", "Cañon", "Tipo Pizarron"};
//        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
//            @Override
//            public boolean isCellEditable(int row, int column) {
//                return false;
//            }
//        };
//        tablaAulas.setModel(modeloTabla);
//        JScrollPane scrollPane = new JScrollPane(tablaAulas);
//        asignarAulaPanel.add(scrollPane, BorderLayout.CENTER);
//
//
//        ResultadoDTO resultadoDTO = gestorAula.obtenerDisponibilidadAulas(reservaDTO,fechaDTO);
//
//        if(!(resultadoDTO.getListaAulasDisponibles() ==  null)){
//           for(AulaDTO aulaDTO: resultadoDTO.getListaAulasDisponibles()) {
//               modeloTabla.addRow(new Object[]{
//                       aulaDTO.getNombre(),
//                       aulaDTO.getPiso(),
//                       aulaDTO.getCapacidad(),
//                       retornarSiONo(aulaDTO.getVentiladores()),
//                       retornarSiONo(aulaDTO.getAire_acondicionado()),
//                       retornarSiONo(aulaDTO.getCanion()),
//                       aulaDTO.getTipoPizarron()
//               });
//           }
//        } else {
//            List<ReservaDTO> reservasSolapada = resultadoDTO.getReservasSolapadas();
//            double horasSolapadas = resultadoDTO.getMinimaCantidadSolapada();
//            dispose();
//            Set<Long> idsReservasProcesadas = new HashSet<>();
//
//            for (ReservaDTO reserva : reservasSolapada) {
//                /*reserva.setAsignatura(reservasSolapada.getAsignatura());
//                reserva.setNombreDocente(reservasSolapada.getNombreDocente());*/// Acá hay un error
//                if (!idsReservasProcesadas.contains(reserva.getIdReserva())) {
//                    idsReservasProcesadas.add(reserva.getIdReserva()); // Marca el id como procesado
//                    // Crear y mostrar el diálogo para esta reserva
//                    SwingUtilities.invokeLater(() -> {
//                        ReservasSolapadas dialog = new ReservasSolapadas(reserva, horasSolapadas);
//                        dialog.setModal(true); // Hace que el JDialog sea modal
//                        dialog.setVisible(true); // Bloquea hasta que el usuario cierre la ventana
//                    });
//                }
//            }
//
//            dispose();
//        }
//
//        asignarButton.addActionListener(new ActionListener() {
//           @Override
//           public void actionPerformed(ActionEvent e) {
//               int filaSeleccionada = tablaAulas.getSelectedRow();
//
//               if (filaSeleccionada == -1) {
//                   JOptionPane.showMessageDialog(null, "Por favor, selecciona un Aula para asignar.", "Error", JOptionPane.ERROR_MESSAGE);
//                   return;
//               }
//
//               String nombreAula = (String) modeloTabla.getValueAt(filaSeleccionada, 0);
//
//               Long idAula = gestorAula.getAulaByNombre(nombreAula).getIdAula();
//
//               if(reservaDTO.getIdPeriodo() != null) { //Si es periodica
//                   gestorAula.asignarAulaAFechasDelPeriodo(reservaDTO,fechaDTO,idAula);
//               } else {
//                   gestorAula.asignarAulaAFecha(reservaDTO,fechaDTO,idAula);
//               }
//
//               DefaultTableModel model = (DefaultTableModel) tablaDiasReserva.getModel();
//
//               if(reservaDTO.getIdPeriodo() == null) {
//                   String fecha = FechaUtils.convertirLocalDateATexto(fechaDTO.getFecha());
//                   String horarioInicio = fechaDTO.getHorarioInicio();
//                   String horarioFin = HorarioUtils.getHorarioFin(horarioInicio,fechaDTO.getDuracion());
//                   model.addRow(new Object[]{fecha, horarioInicio, horarioFin});
//               } else{
//                   String dia = fechaDTO.getDia();
//                   String horarioInicio = fechaDTO.getHorarioInicio();
//                   String horarioFin = HorarioUtils.getHorarioFin(horarioInicio,fechaDTO.getDuracion());
//                   model.addRow(new Object[]{dia, horarioInicio, horarioFin});
//               }
//
//               dispose();
//
//           }
//        });
//
//        cancelarButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(
//                        null,
//                        "Se ha cancelado la operación.",
//                        "Cancelación",
//                        JOptionPane.WARNING_MESSAGE
//                );
//                dispose();
//            }
//        });
//
//    }
//    public static String retornarSiONo(boolean bool){
//        return bool?"SI":"NO";
//    }

}
