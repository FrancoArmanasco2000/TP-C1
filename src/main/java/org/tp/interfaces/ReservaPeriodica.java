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


    public ReservaPeriodica(){
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
        agregarDiaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AgregarDia ad = new AgregarDia(ReservaPeriodica.this, tablaDiasReserva);
                ad.setVisible(true);
            }
        });
        String[] columnas = {"Dia", "Horario Inicio", "Horario Fin"};
        DefaultTableModel modeloTabla= new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaDiasReserva.setModel(modeloTabla);

    }

}
