package org.tp.interfaces;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class MenuUsuario extends JFrame {

    private JPanel menuUsuario;
    private JButton buscarAulaButton;
    private JButton registrarReservaButton;
    private JButton reservasParaUnDiaButton;
    private JButton reservasParaUnCursoButton;
    private JLabel TituloBienvenida;
    private JLabel SubtituloNombreUsuario;


    public void setSubtituloNombreUsuario(String subtituloNombreUsuario) {
        SubtituloNombreUsuario.setText(subtituloNombreUsuario);
    }

    public MenuUsuario(String usuario) {
        this.setSubtituloNombreUsuario(usuario);
        this.setContentPane(this.menuUsuario);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setSize(500, 600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        registrarReservaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistrarReserva rr = new RegistrarReserva();
            }
        });
    }

}
