package org.tp.interfaces;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.util.Locale;

public class MensajeDeExito extends JFrame {
    private JTextArea mensajeExito;
    private JButton aceptarButton;
    private JPanel panelExito;

    public MensajeDeExito(String texto) {
        mensajeExito.setText(texto);
        mensajeExito.setWrapStyleWord(true);
        mensajeExito.setLineWrap(true);
        mensajeExito.setEditable(false);
        mensajeExito.setOpaque(false);
        mensajeExito.setFocusable(false);
        mensajeExito.setCursor(null);
    }

    public static void main(String[] args) {
        FlatMacDarkLaf.setup();
        MensajeDeExito me = new MensajeDeExito("Registro realizado con exito ✔");
        me.setContentPane(me.panelExito);
        me.setSize(250, 200);
        me.setTitle("EXITO");
        me.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        me.setResizable(false);
        me.setLocationRelativeTo(null);
        me.setVisible(true);
    }

}
