package org.tp.interfaces;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import javax.swing.*;

public class MensajeDeAlerta extends JFrame{
    private JButton siButton;
    private JTextArea mensajeAlerta;
    private JButton noButton;
    private JPanel panelAlerta;

    public MensajeDeAlerta(String texto){
        mensajeAlerta.setText(texto);
        mensajeAlerta.setWrapStyleWord(true);
        mensajeAlerta.setLineWrap(true);
        mensajeAlerta.setEditable(false);
        mensajeAlerta.setOpaque(false);
        mensajeAlerta.setFocusable(false);
        mensajeAlerta.setCursor(null);
    }

    public static void main(String[] args) {
        FlatMacDarkLaf.setup();
        MensajeDeAlerta me =  new MensajeDeAlerta("¿Estas seguro de cancelar?");
        me.setContentPane(me.panelAlerta);
        me.setSize(250,200);
        me.setTitle("ALERTA");
        me.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        me.setResizable(false);
        me.setLocationRelativeTo(null);
        me.setVisible(true);
    }
}
