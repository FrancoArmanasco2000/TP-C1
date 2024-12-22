package org.tp.interfaces;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import org.tp.dto.FechaDTO;
import org.tp.dto.ReservaDTO;
import org.tp.gestores.GestorReserva;

public class AgregarDia extends JFrame {
    private JComboBox<String> diaComboBox;
    private JButton asignarAulaButton;
    private JButton cancelarButton;
    private JPanel agregarDiaPanel;
    private JFormattedTextField formattedTextFieldHoraInicio;
    private JFormattedTextField formattedTextFieldHoraFin;
    private GestorReserva gestorReserva;

    public AgregarDia(JFrame parentFrame, DefaultTableModel modeloTablaDiasReserva, ReservaDTO reservaDTO) {
        this.setTitle("Agregar Día");
        this.setContentPane(this.agregarDiaPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(450, 300);
        this.setResizable(false);
        this.setLocationRelativeTo(parentFrame);
        this.setVisible(true);
        gestorReserva = new GestorReserva();

        diaComboBox.setModel(new DefaultComboBoxModel<>(new String[]{
                "Lunes", "Martes", "Miércoles", "Jueves", "Viernes"
        }));
        diaComboBox.setEditable(false);

        formattedTextFieldHoraInicio.setColumns(5);
        formattedTextFieldHoraFin.setColumns(5);
        this.configurarFormatoHorario(formattedTextFieldHoraInicio);
        this.configurarFormatoHorario(formattedTextFieldHoraFin);

        asignarAulaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dia = (String) diaComboBox.getSelectedItem();
                String horarioInicio = formattedTextFieldHoraInicio.getText();
                String horarioFin = formattedTextFieldHoraFin.getText();

                if (dia == null || !horarioInicio.matches("\\d{2}:\\d{2}") || horarioInicio.equals("__:__") || horarioFin.equals("__:__") || !horarioFin.matches("\\d{2}:\\d{2}")) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Por favor, complete todos los campos obligatorios.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                FechaDTO fechaDTO = new FechaDTO();
                fechaDTO.setDia(dia);
                fechaDTO.setHorarioInicio(horarioInicio);
                fechaDTO.setHorarioFin(horarioFin);
                fechaDTO.setDuracion(calcularDuracion(horarioInicio, horarioFin));

                new AsignarAula(parentFrame, modeloTablaDiasReserva, reservaDTO ,fechaDTO);

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








    public Integer calcularDuracion (String horarioInicio, String horarioFin) {
        Integer horaInicio = Integer.parseInt(horarioInicio.substring(0,2));
        Integer horaFin = Integer.parseInt(horarioFin.substring(0,2));
        Integer minutosInicio = Integer.parseInt(horarioInicio.substring(3,5));
        Integer minutosFin = Integer.parseInt(horarioFin.substring(3,5));
        return (horaFin - horaInicio) * 60 + (minutosFin - minutosInicio);
    }

    private void configurarFormatoHorario (JFormattedTextField horarioTextField) {
        try {
            MaskFormatter mascara = new MaskFormatter("##:##");
            mascara.setPlaceholderCharacter('_');
            horarioTextField.setFormatterFactory(new DefaultFormatterFactory(mascara));
        } catch (ParseException e) {
            throw new RuntimeException("Error al configurar el formato de horario: " + e.getMessage(), e);
        }
    }
}