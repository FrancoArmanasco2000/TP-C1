package org.tp.interfaces;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import javax.swing.*;
import java.awt.*;

public class MensajeDeError extends JFrame {
    private JButton aceptarButton;
    private JPanel panelError;
    private JTextArea mensajeError;

    public MensajeDeError(String texto){
        mensajeError.setText("⚠\uFE0F "+texto);
        mensajeError.setWrapStyleWord(true);
        mensajeError.setLineWrap(true);
        mensajeError.setEditable(false);
        mensajeError.setOpaque(false);
        mensajeError.setFocusable(false);
        mensajeError.setCursor(null);
    }


    public static void main(String[] args) {
        FlatMacDarkLaf.setup();
        MensajeDeError me =  new MensajeDeError("No ingresaste todos los campos");
        me.setContentPane(me.panelError);
        me.setSize(250,200);
        me.setTitle("⚠\uFE0F ERROR ⚠\uFE0F");
        me.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        me.setResizable(false);
        me.setLocationRelativeTo(null);
        me.setVisible(true);
    }
}
