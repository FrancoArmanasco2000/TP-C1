package org.tp.interfaces;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AgregarDia extends JFrame {
    private JComboBox<String> diaComboBox;
    private JTextField horarioInicioTextField;
    private JTextField horarioFinTextField;
    private JButton asignarAulaButton;
    private JButton cancelarButton;
    private JPanel agregarDiaPanel;

    public AgregarDia(JFrame parentFrame, JTable tablaDiasReserva) {
        this.setTitle("Agregar Día");
        this.setContentPane(this.agregarDiaPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400, 300);
        this.setResizable(false);
        this.setLocationRelativeTo(parentFrame);
        this.setVisible(true);

        // Configurar ComboBox para días
        diaComboBox.setModel(new DefaultComboBoxModel<>(new String[]{
                "Seleccionar", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes"
        }));
        diaComboBox.setEditable(false); // No editable, como indica la tabla

        // Configurar campos de texto para horarios
        horarioInicioTextField.setColumns(5);
        horarioInicioTextField.setToolTipText("Formato: HH:mm");
        horarioFinTextField.setColumns(5);
        horarioFinTextField.setToolTipText("Formato: HH:mm");

        asignarAulaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dia = (String) diaComboBox.getSelectedItem();
                String horarioInicio = horarioInicioTextField.getText();
                String horarioFin = horarioFinTextField.getText();

                // Validar campos obligatorios
                if (dia == null || horarioInicio.isEmpty() || horarioFin.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Por favor, complete todos los campos obligatorios.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                // Validar formato y longitud de los horarios
                if (!horarioInicio.matches("\\d{2}:\\d{2}") || !horarioFin.matches("\\d{2}:\\d{2}")) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Por favor, ingrese el horario en el formato correcto (HH:mm).",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
                if (horarioInicio.length() > 5 || horarioFin.length() > 5) {
                    JOptionPane.showMessageDialog(
                            null,
                            "El horario no puede exceder los 5 caracteres.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                // Agregar fila a la tabla en ReservaPeriódica
                DefaultTableModel model = (DefaultTableModel) tablaDiasReserva.getModel();
                model.addRow(new Object[]{dia, horarioInicio, horarioFin});

                // Confirmar y cerrar
                JOptionPane.showMessageDialog(
                        null,
                        "Día agregado correctamente.",
                        "Confirmación",
                        JOptionPane.INFORMATION_MESSAGE
                );
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
}