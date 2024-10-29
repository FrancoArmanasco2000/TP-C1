package org.tp.interfaces;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MensajeDeError extends JFrame {
    private JButton aceptarButton;
    private JPanel panelError;
    private JTextArea mensajeError;

    public MensajeDeError(String texto){
        mensajeError.setText(texto);
        mensajeError.setWrapStyleWord(true);
        mensajeError.setLineWrap(true);
        mensajeError.setEditable(false);
        mensajeError.setOpaque(false);
        mensajeError.setFocusable(false);
        mensajeError.setCursor(null);

        this.setContentPane(this.panelError);
        this.setSize(350,200);
        this.setTitle("⚠\uFE0F ERROR ⚠\uFE0F");
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MensajeDeError.this.dispose();
            }
        });
    }
}
