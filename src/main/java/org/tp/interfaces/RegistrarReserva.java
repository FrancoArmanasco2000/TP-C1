package org.tp.interfaces;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class RegistrarReserva extends JFrame {

    private JRadioButton periodicaRadioButton;
    private JRadioButton esporadicaRadioButton;
    private JButton aceptarButton;
    private JPanel seleccionarTipoReserva;

    public RegistrarReserva() {

        this.setContentPane(this.seleccionarTipoReserva);
        this.setBounds(0, 0, 350, 450);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        ButtonGroup grupoReservas = new ButtonGroup();
        grupoReservas.add(periodicaRadioButton);
        grupoReservas.add(esporadicaRadioButton);

        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (periodicaRadioButton.isSelected()) {
                    RegistrarReserva.super.dispose();
                    ReservaPeriodica rp = new ReservaPeriodica();
                } else if (esporadicaRadioButton.isSelected()) {

                }
            }
        });
    }

}
