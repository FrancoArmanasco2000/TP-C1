package org.tp.interfaces;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

public class ReservaEsporadica extends JFrame {
    private JTextField inputCantidadAlumnos;
    private JTextField inputNombreApellido;
    private JTextField inputAsignatura;
    private JTextField inputCorreo;
    private JComboBox tipoAulaComboBox;
    private JButton agregarFechaButton;
    private JTable tablaFechasReserva;
    private JButton confirmarButton;
    private JButton cancelarButton;
    private JPanel reservaEsporadicaPanel;


    public ReservaEsporadica() {
        this.setTitle("Reserva Esporadica");
        this.setContentPane(this.reservaEsporadicaPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(500,600);
        this.setResizable(false); // NO MODIFICA LA PESTAÑA
        this.setLocationRelativeTo(null); // APARECE EN EL MEDIO
        this.setVisible(true);

        agregarFechaButton.addActionListener(e -> {
            if (!(inputCantidadAlumnos.getText().isEmpty() || inputNombreApellido.getText().isEmpty() || inputAsignatura.getText().isEmpty() || inputCorreo.getText().isEmpty()) && validarDatos()) {
                ReservaEsporadica.this.setEnabled(false);
                AgregarFecha af = new AgregarFecha(ReservaEsporadica.this, tablaFechasReserva);
                af.setVisible(true);
                af.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                        if (tablaFechasReserva.getRowCount() > 0) {
                            //Como los datos de la reserva estan completos, se bloquean los campos para evitar que se modifiquen a la hora de agregar la fecha
                            tipoAulaComboBox.setEnabled(false);
                            inputCantidadAlumnos.setEditable(false);
                            inputNombreApellido.setEditable(false);
                            inputAsignatura.setEditable(false);
                            inputCorreo.setEditable(false);
                        }
                        ReservaEsporadica.this.setEnabled(true);
                    }
                });
            } else {
                JOptionPane.showMessageDialog(null, "Debe completar todos los campos antes de agregar una fecha.");
            }
        });

        tipoAulaComboBox.setModel(new DefaultComboBoxModel<>(new String[]{
                "Seleccionar", "Multimedios", "Informatica", "Sin adicionales"
        }));

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReservaEsporadica.this.dispose();
            }
        });
    }

    public boolean validarDatos() {
        // Cant alumnos es un número
        String cantidadAlumnosTexto = inputCantidadAlumnos.getText();
        if (!cantidadAlumnosTexto.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "La cantidad de alumnos debe ser un número entero.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // Nombre y Apellido invalido
        String nombreApellidoTexto = inputNombreApellido.getText();
        if (!nombreApellidoTexto.matches("[A-Za-z]+\\s[A-Za-z]+")) {
            JOptionPane.showMessageDialog(null, "El campo Apellido y Nombre debe tener formato: Apellido Nombre.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // Asignatura tiene que ser una sola palabra
        String asignaturaTexto = inputAsignatura.getText();
        if (!asignaturaTexto.matches("[A-Za-z]+")) {
            JOptionPane.showMessageDialog(null, "El campo Asignatura debe ser una sola palabra.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // Formato de email
        String correoTexto = inputCorreo.getText();
        if (!correoTexto.matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}")) {
            JOptionPane.showMessageDialog(null, "El correo electrónico debe tener un formato válido, como ejemplo@dominio.com.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}