package org.tp.interfaces;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.tp.dto.FechaDTO;
import org.tp.dto.ReservaDTO;
import org.tp.utils.FechaUtils;

public class AgregarFecha extends JFrame {
    private JButton asignarAulaButton;
    private JButton cancelarButton;
    private JPanel agregarFechaPanel;
    private JFormattedTextField formattedTextFieldHoraInicio;
    private JFormattedTextField formattedTextFieldHoraFin;
    private JFormattedTextField formattedFecha;

    public AgregarFecha(JFrame parentFrame, DefaultTableModel modeloTablaDiasReserva, ReservaDTO reservaDTO) {
        this.setTitle("Agregar Fecha");
        this.setContentPane(this.agregarFechaPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400, 300);
        this.setResizable(false);
        this.setLocationRelativeTo(parentFrame);
        this.setVisible(true);

        formattedTextFieldHoraInicio.setColumns(5);
        formattedTextFieldHoraFin.setColumns(5);
        formattedFecha.setColumns(10);

        this.configurarFormatoHorario(formattedTextFieldHoraInicio);
        this.configurarFormatoHorario(formattedTextFieldHoraFin);

        try {
            MaskFormatter mascara = new MaskFormatter("##/##/####");
            mascara.setPlaceholderCharacter('_');
            formattedFecha.setFormatterFactory(new DefaultFormatterFactory(mascara));
        } catch (ParseException e) {
            throw new RuntimeException("Error al configurar el formato de fecha: " + e.getMessage(), e);
        }

        asignarAulaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fecha = formattedFecha.getText();
                String horarioInicio = formattedTextFieldHoraInicio.getText();
                String horarioFin = formattedTextFieldHoraFin.getText();

                if (fecha.isBlank() || !horarioInicio.matches("\\d{2}:\\d{2}") || horarioInicio.equals("__:__") || horarioFin.equals("__:__") || !horarioFin.matches("\\d{2}:\\d{2}")) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Por favor, complete todos los campos obligatorios.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate fechaFormateada = LocalDate.parse(fecha, formatter);

                FechaDTO fechaDTO = new FechaDTO();
                fechaDTO.setFecha(fechaFormateada);
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