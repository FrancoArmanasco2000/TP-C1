package org.tp.interfaces;

import org.tp.dto.ReservaDTO;
import org.tp.gestores.GestorReserva;
import org.tp.utils.InterfazUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

public class ReservaEsporadica extends JFrame {
    private JTextField inputCantidadAlumnos;
    private JTextField inputNombreApellido;
    private JTextField inputAsignatura;
    private JTextField inputCorreo;
    private JComboBox<String> tipoAulaComboBox;
    private JButton agregarFechaButton;
    private JTable tablaFechasReserva;
    private JButton confirmarButton;
    private JButton cancelarButton;
    private JPanel reservaEsporadicaPanel;
//    private ReservaDTO reservaDTO;
//
//
//    public ReservaEsporadica(String usuario) {
//        this.setTitle("Reserva Esporádica");
//        this.setContentPane(this.reservaEsporadicaPanel);
//        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        this.setSize(500,600);
//        this.setResizable(false); // NO MODIFICA LA PESTAÑA
//        this.setLocationRelativeTo(null); // APARECE EN EL MEDIO
//        this.setVisible(true);
//
//        String[] tipos = {"Multimedios", "Informatica", "Sin Recursos Adicionales"};
//        for (String tipo : tipos) {
//            tipoAulaComboBox.addItem(tipo);
//        }
//
//        agregarFechaButton.addActionListener(e -> {
//            if (!(inputCantidadAlumnos.getText().isEmpty() || inputNombreApellido.getText().isEmpty() || inputAsignatura.getText().isEmpty() || inputCorreo.getText().isEmpty())
//                    && InterfazUtils.validarDatos(inputCantidadAlumnos.getText(),inputNombreApellido.getText(),inputAsignatura.getText(),inputCorreo.getText())) {
//
//                ReservaEsporadica.this.setEnabled(false);
//
//                if(reservaDTO == null) {
//                    reservaDTO = new ReservaDTO(
//                            Integer.parseInt(inputCantidadAlumnos.getText()),
//                            InterfazUtils.retornarTipoAula(tipoAulaComboBox.getSelectedIndex()),
//                            inputNombreApellido.getText(),
//                            inputAsignatura.getText(),
//                            inputCorreo.getText(),
//                            usuario
//                    );
//                }
//                AgregarFecha af = new AgregarFecha(ReservaEsporadica.this, tablaFechasReserva, reservaDTO);
//                af.setVisible(true);
//                af.addWindowListener(new WindowAdapter() {
//                    @Override
//                    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
//                        if (tablaFechasReserva.getRowCount() > 0) {
//                            //Como los datos de la reserva estan completos, se bloquean los campos para evitar que se modifiquen a la hora de agregar la fecha
//                            tipoAulaComboBox.setEnabled(false);
//                            inputCantidadAlumnos.setEditable(false);
//                            inputNombreApellido.setEditable(false);
//                            inputAsignatura.setEditable(false);
//                            inputCorreo.setEditable(false);
//                        }
//                        ReservaEsporadica.this.setEnabled(true);
//                    }
//                });
//            } else {
//                JOptionPane.showMessageDialog(null, "Debe completar todos los campos antes de agregar una fecha.");
//            }
//        });
//
//        String[] columnas = {"Fecha", "Horario Inicio", "Horario Fin"};
//        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
//            @Override
//            public boolean isCellEditable(int row, int column) {
//                return false;
//            }
//        };
//
//        tablaFechasReserva.setModel(modeloTabla);
//
//        cancelarButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                ReservaEsporadica.this.dispose();
//            }
//        });
//
//        confirmarButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                GestorReserva gr = new GestorReserva();
//                gr.RegistrarReserva(reservaDTO);
//                dispose();
//            }
//        });
//
//    }
}

