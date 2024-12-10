package org.tp.interfaces;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReservaPeriodica extends JFrame {
    private JTextField inputCantidadAlumnos;
    private JTextField inputNombreApellido;
    private JTextField inputAsignatura;
    private JTextField inputCorreo;
    private JComboBox<String> periodoComboBox;
    private JComboBox<String> tipoAulaComboBox;
    private JButton agregarDiaButton;
    private JTable tablaDiasReserva;
    private JButton confirmarButton;
    private JButton cancelarButton;
    private JPanel reservaPeriodicaPanel;


    public ReservaPeriodica() {
        this.setTitle("Reserva periodica");
        this.setContentPane(this.reservaPeriodicaPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(500,600);
        this.setResizable(false); // NO MODIFICA LA PESTAÑA
        this.setLocationRelativeTo(null); // APARECE EN EL MEDIO
        this.setVisible(true);
        String[] periodos = {"1C 2024", "2C 2024", "ANUAL 2024", "1C 2025", "2C 2025", "ANUAL 2025"};
        for(String periodo: periodos) {
            periodoComboBox.addItem(periodo);
        }
        String[] tipos = {"Multimedios", "Informatica", "Sin Recursos Adicionales"};
        for(String tipo: tipos) {
            tipoAulaComboBox.addItem(tipo);
        }



        //no valide si las longitudes son correctas, es necesario? lo dice el word
        periodoComboBox.setModel(new DefaultComboBoxModel<>(new String[]{
                "Seleccionar", "Anual", "1er Cuatrimestre", "2do Cuatrimestre"
        }));

        tipoAulaComboBox.setModel(new DefaultComboBoxModel<>(new String[]{
                "Seleccionar", "Multimedios", "Informatica", "Sin adicionales"
        }));

        agregarDiaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AgregarDia(ReservaPeriodica.this, tablaDiasReserva);
            }
        });

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReservaPeriodica.this.dispose();
            }
        });

        tablaDiasReserva.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Día", "Horario Inicio", "Horario Fin"}
        ));
    }
}