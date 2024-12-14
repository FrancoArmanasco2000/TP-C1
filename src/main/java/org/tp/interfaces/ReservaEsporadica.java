package org.tp.interfaces;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReservaEsporadica extends JFrame {
    private JTextField inputCantidadAlumnos;
    private JTextField inputNombreApellido;
    private JTextField inputAsignatura;
    private JTextField inputCorreo;
    private JComboBox tipoAulaComboBox;
    private JButton agregarFechaButton;
    private JTable tablaDiasReserva;
    private JButton confirmarButton;
    private JButton cancelarButton;
    private JPanel reservaEsporadicaPanel;


    public ReservaEsporadica() {
        this.setTitle("Reserva esporadica");
        this.setContentPane(this.reservaEsporadicaPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(500,600);
        this.setResizable(false); // NO MODIFICA LA PESTAÑA
        this.setLocationRelativeTo(null); // APARECE EN EL MEDIO
        this.setVisible(true);

        /*agregarFechaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AgregarFecha(ReservaEsporadica.this, tablaDiasReserva);
            }
        });*/

        tipoAulaComboBox.setModel(new DefaultComboBoxModel<>(new String[]{
                "Seleccionar", "Multimedios", "Informatica", "Sin adicionales"
        }));

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReservaEsporadica.this.dispose();
            }
        });
    }
}