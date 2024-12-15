package org.tp.interfaces;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReservaEsporadica extends JFrame {
    private JPanel reservaEsporadicaPanel;
    private JTextField inputCantidadAlumnos;
    private JComboBox tipoAulaComboBox;
    private JTextField inputNombreApellido;
    private JTextField inputAsignatura;
    private JTextField inputCorreo;
    private JButton agregarFechaButton;
    private JTable tablaFechasReserva;
    private JButton confirmarButton;
    private JButton cancelarButton;

    public ReservaEsporadica(){
        this.setTitle("Reserva esporadica");
        this.setContentPane(this.reservaEsporadicaPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(500,600);
        this.setResizable(false); // NO MODIFICA LA PESTAÑA
        this.setLocationRelativeTo(null); // APARECE EN EL MEDIO
        this.setVisible(true);

        String[] tipos = {"Multimedios", "Informatica", "Sin Recursos Adicionales"};
        for(String tipo: tipos) {
            tipoAulaComboBox.addItem(tipo);
        }
        agregarFechaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AgregarDia ad = new AgregarDia(ReservaEsporadica.this, tablaFechasReserva);
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
        tablaFechasReserva.setModel(modeloTabla);

    }

}
