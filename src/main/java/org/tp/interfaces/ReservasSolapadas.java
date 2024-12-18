package org.tp.interfaces;

import org.tp.dto.ReservaDTO;

import javax.swing.*;

public class ReservasSolapadas extends JDialog{
    private JPanel reservasSolapadasPanel;
    private JTextField campoAsignatura;
    private JTextField campoCorreo;
    private JTextField campoNombreApellido;
    private JTextField campoHorasSuperpuestas;
    private JTextField campoHoraA;
    private JTextField campoHoraB;

    public ReservasSolapadas(ReservaDTO reserva, double horasSolapadas) {
        this.setTitle("Reserva Solapadas");
        this.setContentPane(this.reservasSolapadasPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400, 400);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        campoAsignatura.setEnabled(false);
        campoCorreo.setEditable(false);
        campoNombreApellido.setEditable(false);
        campoHorasSuperpuestas.setEditable(false);
        campoHoraA.setEditable(false);
        campoHoraB.setEditable(false);

        double horarioFinEnMinutos = horasSolapadas;
        double horaFin = (horarioFinEnMinutos / 60) % 24; // Asegurarse de que las horas no excedan 24
        double minutosFin = horarioFinEnMinutos % 60;

        campoAsignatura.setText(reserva.getAsignatura());
        campoCorreo.setText(reserva.getCorreoContacto());
        campoNombreApellido.setText(reserva.getNombreDocente());
        campoHorasSuperpuestas.setText(String.format("%02d:%02d", (int)horaFin, (int)minutosFin));
        campoHoraA.setText(reserva.getHoraA());
        campoHoraB.setText(reserva.getHoraB());
    }

}
