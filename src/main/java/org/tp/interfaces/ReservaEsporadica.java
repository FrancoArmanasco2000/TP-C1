package org.tp.interfaces;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReservaEsporadica extends JFrame {
    private JTextField inputCantidadAlumnos;
    private JTextField inputNombreApellido;
    private JTextField inputAsignatura;
    private JTextField inputCorreo;
    private JComboBox tipoAulaComboBox;
    private JButton agregarFechaButton;
    private JTable tablaDiasReserva;
    private JButton confirmarButton;
    private JButton cancelarButton;
    private JPanel reservaEsporadicaPanel;


    public ReservaEsporadica() {
        this.setTitle("Reserva esporadica");
        this.setContentPane(this.reservaEsporadicaPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(500,600);
        this.setResizable(false); // NO MODIFICA LA PESTAÑA
        this.setLocationRelativeTo(null); // APARECE EN EL MEDIO
        this.setVisible(true);

        agregarFechaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(inputCantidadAlumnos.getText().equals("") || inputNombreApellido.getText().equals("") || inputAsignatura.getText().equals("") || inputCorreo.getText().equals("")) && validarDatos()) {
                    //Como los datos de la reserva estan completos, se bloquean los campos para evitar que se modifiquen a la hora de agregar el siguiente día
                    tipoAulaComboBox.setEnabled(false);
                    inputCantidadAlumnos.setEditable(false);
                    inputNombreApellido.setEditable(false);
                    inputAsignatura.setEditable(false);
                    inputCorreo.setEditable(false);

                    AgregarFecha ad = new AgregarFecha(ReservaEsporadica.this, tablaDiasReserva);
                    ad.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Debe completar todos los campos antes de agregar una fecha.");
                }
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