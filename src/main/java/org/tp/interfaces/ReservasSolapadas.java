package org.tp.interfaces;

import org.tp.dto.ReservaDTO;
import org.tp.dto.ResultadoDTO;

import javax.swing.*;

public class ReservasSolapadas extends JDialog{
    private JPanel reservasSolapadasPanel;
    private JTextField campoAsignatura;
    private JTextField campoCorreo;
    private JTextField campoNombreApellido;
    private JTextField campoHorasSuperpuestas;
    private JTextField campoHoraA;
    private JTextField campoHoraB;

    public ReservasSolapadas(ReservaDTO reserva) {
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

        campoAsignatura.setText(reserva.getAsignatura());
        campoCorreo.setText(reserva.getCorreoContacto());
        campoNombreApellido.setText(reserva.getNombreDocente());
        campoHorasSuperpuestas.setText(String.valueOf(reserva.getHorasSolapadas()));
        campoHoraA.setText(reserva.getHoraA());
        campoHoraB.setText(reserva.getHoraB());
    }

}
