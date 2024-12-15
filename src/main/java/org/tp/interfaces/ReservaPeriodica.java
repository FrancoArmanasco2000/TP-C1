package org.tp.interfaces;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReservaPeriodica extends JFrame {
    private JTextField inputCantidadAlumnos;
    private JTextField inputNombreApellido;
    private JTextField inputAsignatura;
    private JTextField inputCorreo;
    private JComboBox<String> periodoComboBox;
    private JComboBox<String> tipoAulaComboBox;
    private JButton agregarDiaButton;
    private JTable tablaDiasReserva;
    private JButton confirmarButton;
    private JButton cancelarButton;
    private JPanel reservaPeriodicaPanel;


    public ReservaPeriodica() {
        this.setTitle("Reserva Periodica");
        this.setContentPane(this.reservaPeriodicaPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(500, 600);
        this.setResizable(false); // NO MODIFICA LA PESTAÑA
        this.setLocationRelativeTo(null); // APARECE EN EL MEDIO
        this.setVisible(true);
        String[] periodos = {"1C 2024", "2C 2024", "ANUAL 2024", "1C 2025", "2C 2025", "ANUAL 2025"};
        for (String periodo : periodos) {
            periodoComboBox.addItem(periodo);
        }
        String[] tipos = {"Multimedios", "Informatica", "Sin Recursos Adicionales"};
        for (String tipo : tipos) {
            tipoAulaComboBox.addItem(tipo);
        }
        agregarDiaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AgregarDia ad = new AgregarDia(ReservaPeriodica.this, tablaDiasReserva);
                if (!(inputCantidadAlumnos.getText().equals("") || inputNombreApellido.getText().equals("") || inputAsignatura.getText().equals("") || inputCorreo.getText().equals("")) && validarDatos()) {
                    //Como los datos de la reserva estan completos, se bloquean los campos para evitar que se modifiquen a la hora de agregar el siguiente día
                    periodoComboBox.setEnabled(false);
                    tipoAulaComboBox.setEnabled(false);
                    inputCantidadAlumnos.setEditable(false);
                    inputNombreApellido.setEditable(false);
                    inputAsignatura.setEditable(false);
                    inputCorreo.setEditable(false);

                    AgregarDia ad = new AgregarDia(ReservaPeriodica.this, tablaDiasReserva);
                    ad.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Debe completar todos los campos antes de agregar un día.");
                }
            }
        });
        String[] columnas = {"Dia", "Horario Inicio", "Horario Fin"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaDiasReserva.setModel(modeloTabla);


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
