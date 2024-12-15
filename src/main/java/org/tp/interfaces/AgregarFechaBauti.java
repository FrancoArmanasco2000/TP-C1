/*package org.tp.interfaces;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;  // Importamos JDateChooser
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AgregarFecha extends JFrame {
    private JDateChooser fechaDateChooser;  // Utilizamos JDateChooser en lugar de JTextField
    private JTextField horaInicioTextField;
    private JTextField horaFinTextField;
    private JButton asignarAulaButton;
    private JButton cancelarButton;
    private JPanel agregarFechaPanel;

    public AgregarFecha(JFrame parentFrame, JTable tablaFechasReserva) {
        this.setTitle("Agregar Fecha");
        this.setContentPane(this.agregarFechaPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400, 300);
        this.setResizable(false);
        this.setLocationRelativeTo(parentFrame);
        this.setVisible(true);

        // Configura el JDateChooser
        fechaDateChooser.setDateFormatString("dd/MM/yyyy");

        // Configuración de los campos de hora
        horaInicioTextField.setColumns(5);
        horaInicioTextField.setToolTipText("Formato: HH:mm");
        horaFinTextField.setColumns(5);
        horaFinTextField.setToolTipText("Formato: HH:mm");

        asignarAulaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date fechaSeleccionada = fechaDateChooser.getDate();  // Obtenemos la fecha seleccionada
                String horaInicio = horaInicioTextField.getText();
                String horaFin = horaFinTextField.getText();

                if (fechaSeleccionada != null && !horaInicio.isEmpty() && !horaFin.isEmpty()) {
                    // Convertimos la fecha seleccionada a formato String
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    String fechaFormateada = sdf.format(fechaSeleccionada);

                    // Agregar la fecha, horario y aula a la tabla de reservas
                    Object[] fila = {fechaFormateada, horaInicio, horaFin};  // Datos a agregar en la fila
                    // Supongo que la tablaFechasReserva es una instancia de JTable de la ventana de "Reserva Esporádica"
                    ((DefaultTableModel) tablaFechasReserva.getModel()).addRow(fila);  // Agregamos la fila

                    // Cerrar la ventana después de agregar la fecha
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(AgregarFecha.this, "Por favor, complete todos los campos.",
                            "Campos incompletos", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();  // Cerramos la ventana sin agregar nada
            }
        });
    }
}*/