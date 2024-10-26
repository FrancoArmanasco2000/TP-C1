package org.tp.interfaces;

import javax.swing.*;

public class ReservaPeriodica extends JFrame {
    private JTextField inputCantidadAlumnos;
    private JTextField inputNombreApellido;
    private JTextField inputAsignatura;
    private JTextField inputCorreo;
    private JComboBox periodoComboBox;
    private JComboBox tipoAulaComboBox;
    private JButton agregarDiaButton;
    private JTable tablaDiasReserva;
    private JButton confirmarButton;
    private JButton cancelarButton;
    private JPanel reservaPeriodicaPanel;


    public ReservaPeriodica() {
        this.setTitle("Reserva periodica");
        this.setContentPane(this.reservaPeriodicaPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBounds(0,0,500,600);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
