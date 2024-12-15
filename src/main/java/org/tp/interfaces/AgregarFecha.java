package org.tp.interfaces;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.tp.utils.HorarioUtils;

public class AgregarFecha extends JFrame {
    private JComboBox<String> diaComboBox;
    private JButton asignarAulaButton;
    private JButton cancelarButton;
    private JPanel agregarFechaPanel;
    private JFormattedTextField formattedTextFieldHoraInicio;
    private JFormattedTextField formattedTextFieldHoraFin;
    //private JFormattedTextField formattedFecha;
    DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    JFormattedTextField formattedFecha = new JFormattedTextField(format);

    public AgregarFecha(JFrame parentFrame, JTable tablaDiasReserva) {
        this.setTitle("Agregar Día");
        this.setContentPane(this.agregarFechaPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400, 300);
        this.setResizable(false);
        this.setLocationRelativeTo(parentFrame);
        this.setVisible(true);

        formattedTextFieldHoraInicio.setColumns(5);
        formattedTextFieldHoraFin.setColumns(5);
        try{
            MaskFormatter mascara = new MaskFormatter("##:##");
            formattedTextFieldHoraInicio.setFormatterFactory(new DefaultFormatterFactory(mascara));
            formattedTextFieldHoraFin.setFormatterFactory(new DefaultFormatterFactory(mascara));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        formattedFecha.setColumns(10);
        try{
            MaskFormatter mascara = new MaskFormatter("##/##/####");
            formattedFecha.setFormatterFactory(new DefaultFormatterFactory(mascara));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        asignarAulaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dia = (String) diaComboBox.getSelectedItem();
                String horarioInicio = formattedTextFieldHoraInicio.getText();
                String horarioFin = formattedTextFieldHoraFin.getText();

                if (dia == null || horarioInicio.isEmpty() || horarioFin.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Por favor, complete todos los campos obligatorios.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                if(HorarioUtils.noEsHorarioValido(horarioInicio) || HorarioUtils.noEsHorarioValido(horarioFin)) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Por favor, seleccione un horario valido (formato HH:mm)",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                if(HorarioUtils.noEsDuracionValida(horarioInicio) || HorarioUtils.noEsDuracionValida(horarioFin)) {
                    JOptionPane.showMessageDialog(
                            null,
                            "La duracion de la clase debe ser un multiplo de 30 minutos",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                DefaultTableModel model = (DefaultTableModel) tablaDiasReserva.getModel();
                model.addRow(new Object[]{dia, horarioInicio, horarioFin});

                //ACA VA LO DE ASIGNAR EL AULA
                new AsignarAula();

                // Confirmar y cerrar
                JOptionPane.showMessageDialog(
                        null,
                        "Día agregado correctamente.",
                        "Confirmación",
                        JOptionPane.INFORMATION_MESSAGE
                );
                //dispose();
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