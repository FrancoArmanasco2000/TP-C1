package org.tp.interfaces;

import org.tp.dto.ReservaDTO;
import org.tp.gestores.GestorAsignaturas;
import org.tp.gestores.GestorDocente;

import javax.swing.*;
import java.util.List;

public class ReservasSolapadas extends JDialog{
    private JPanel reservasSolapadasPanel;
    private JTextField campoAsignatura;
    private JTextField campoCorreo;
    private JTextField campoNombreApellido;
    private JTextField campoHorasSuperpuestas;
    private JTextField campoHoraA;
    private JTextField campoHoraB;

    public ReservasSolapadas(ReservaDTO reservaDTO, double horasSolapadas) {
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

        GestorAsignaturas gestorAsignaturas = new GestorAsignaturas();
        List<String> asignaturas = gestorAsignaturas.getAsignaturas();

        GestorDocente gestorDocente = new GestorDocente();
        List<String> docentes = gestorDocente.getDocentes();

        double horarioFinEnMinutos = horasSolapadas;
        double horaFin = (horarioFinEnMinutos / 60) % 24; // Asegurarse de que las horas no excedan 24
        double minutosFin = horarioFinEnMinutos % 60;

        campoAsignatura.setText(asignaturas.get(reservaDTO.getIdCurso()));
        campoCorreo.setText(reservaDTO.getCorreoContacto());
        campoNombreApellido.setText(docentes.get(reservaDTO.getIdDocente()));
        campoHorasSuperpuestas.setText(String.format("%02d:%02d", (int)horaFin, (int)minutosFin));
        campoHoraA.setText(reservaDTO.getHorarioInicio());
        campoHoraB.setText(reservaDTO.getHorarioFin());
    }

}
