package org.tp.interfaces;

import javax.swing.*;

public class MensajeDeError extends JFrame {
    private JButton aceptarButton;
    private JPanel panelError;
    private JTextArea mensajeError;

    public MensajeDeError(String texto) {
        mensajeError.setText(texto);
        mensajeError.setWrapStyleWord(true);
        mensajeError.setLineWrap(true);
        mensajeError.setEditable(false);
        mensajeError.setOpaque(false);
        mensajeError.setFocusable(false);
        mensajeError.setCursor(null);

        int ancho = Math.min(Math.max(300, texto.length() * 7), 500);
        int alto = Math.min(200 + texto.split("\n").length * 20, 400);

        this.setContentPane(this.panelError);
        this.setSize(ancho, alto);
        this.setTitle("⚠\uFE0F ERROR ⚠\uFE0F");
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        aceptarButton.addActionListener(e -> MensajeDeError.this.dispose());
    }
}
