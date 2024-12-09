package org.tp.interfaces;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class MenuAdministrador extends JFrame {

    private JPanel menuAdministrador;
    private JButton buscarBedelButton;
    private JButton registrarBedelButton;
    private JLabel tituloBienvenida;
    private JLabel subtituloNombreUsuario;

    public MenuAdministrador() {
        this.setContentPane(menuAdministrador);
        this.setTitle("Menu Administrador");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(500, 600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        registrarBedelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistrarBedel rb = new RegistrarBedel();
            }
        });
        buscarBedelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BuscarBedel bd = new BuscarBedel();
            }
        });
    }

}
