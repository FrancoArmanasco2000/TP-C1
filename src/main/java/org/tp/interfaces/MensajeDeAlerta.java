package org.tp.interfaces;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class MensajeDeAlerta extends JFrame {
    private JButton siButton;
    private JTextArea mensajeAlerta;
    private JButton noButton;
    private JPanel panelAlerta;


    public MensajeDeAlerta(String texto, JFrame ventana) {
        mensajeAlerta.setText(texto);
        mensajeAlerta.setWrapStyleWord(true);
        mensajeAlerta.setLineWrap(true);
        mensajeAlerta.setEditable(false);
        mensajeAlerta.setOpaque(false);
        mensajeAlerta.setFocusable(false);
        mensajeAlerta.setCursor(null);

        this.setContentPane(this.panelAlerta);
        this.setTitle("ALERTA");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(250, 200);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MensajeDeAlerta.this.dispose();
            }
        });
        siButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventana.dispose();
                dispose();
            }
        });
    }

}
