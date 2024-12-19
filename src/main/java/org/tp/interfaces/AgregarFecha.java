package org.tp.interfaces;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import org.tp.dto.FechaDTO;
import org.tp.dto.ReservaDTO;
import org.tp.utils.FechaUtils;
import org.tp.utils.HorarioUtils;

public class AgregarFecha extends JFrame {
    private JButton asignarAulaButton;
    private JButton cancelarButton;
    private JPanel agregarFechaPanel;
    private JFormattedTextField formattedTextFieldHoraInicio;
    private JFormattedTextField formattedTextFieldHoraFin;
    private JFormattedTextField formattedFecha;

    public AgregarFecha(JFrame parentFrame, JTable tablaFechasReserva, ReservaDTO reservaDTO) {
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

        HorarioUtils.configurarFormatoHorario(formattedTextFieldHoraInicio);
        HorarioUtils.configurarFormatoHorario(formattedTextFieldHoraFin);
        FechaUtils.configurarFormatoFecha(formattedFecha);

        asignarAulaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fechaTexto = formattedFecha.getText();
                String horarioInicio = formattedTextFieldHoraInicio.getText();
                String horarioFin = formattedTextFieldHoraFin.getText();

                if (fechaTexto.equals("__/__/__") || horarioInicio.equals("__:__") || horarioFin.equals("__:__")) {
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

                if (!FechaUtils.esFechaValida(fechaTexto)) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Por favor, seleccione una fecha válida (formato dd/MM/yyyy).",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                LocalDate fecha = FechaUtils.convertirTextoALocalDate(fechaTexto);

                FechaDTO fechaDTO = new FechaDTO(fecha, horarioInicio, HorarioUtils.calcularDuracion(horarioInicio,horarioFin), FechaUtils.obtenerDiaDeLaSemana(fecha));

                new AsignarAula(parentFrame, tablaFechasReserva,reservaDTO,fechaDTO);

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