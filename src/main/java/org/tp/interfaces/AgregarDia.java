package org.tp.interfaces;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.tp.dto.FechaDTO;
import org.tp.dto.ReservaDTO;
import org.tp.utils.HorarioUtils;

public class AgregarDia extends JFrame {
    private JComboBox<String> diaComboBox;
    private JButton asignarAulaButton;
    private JButton cancelarButton;
    private JPanel agregarDiaPanel;
    private JFormattedTextField formattedTextFieldHoraInicio;
    private JFormattedTextField formattedTextFieldHoraFin;

    public AgregarDia(JFrame parentFrame, JTable tablaDiasReserva, ReservaDTO reservaDTO) {
        this.setTitle("Agregar Día");
        this.setContentPane(this.agregarDiaPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(450, 300);
        this.setResizable(false);
        this.setLocationRelativeTo(parentFrame);
        this.setVisible(true);

        diaComboBox.setModel(new DefaultComboBoxModel<>(new String[]{
                "Lunes", "Martes", "Miércoles", "Jueves", "Viernes"
        }));
        diaComboBox.setEditable(false);

        formattedTextFieldHoraInicio.setColumns(5);
        formattedTextFieldHoraFin.setColumns(5);
        HorarioUtils.configurarFormatoHorario(formattedTextFieldHoraInicio);
        HorarioUtils.configurarFormatoHorario(formattedTextFieldHoraFin);

        asignarAulaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dia = (String) diaComboBox.getSelectedItem();
                String horarioInicio = formattedTextFieldHoraInicio.getText();
                String horarioFin = formattedTextFieldHoraFin.getText();

                if (dia == null || horarioInicio.equals("__:__") || horarioFin.equals("__:__")) {
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
                            "La duración de la clase debe ser un múltiplo de 30 minutos",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                FechaDTO fechaDTO = new FechaDTO(dia,HorarioUtils.calcularDuracion(horarioInicio,horarioFin),horarioInicio);

                new AsignarAula(parentFrame, tablaDiasReserva,reservaDTO,fechaDTO);
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