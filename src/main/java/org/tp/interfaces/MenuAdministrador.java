package org.tp.interfaces;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import javax.swing.*;

public class MenuAdministrador extends JFrame {

    private JPanel menuAdministrador;
    private JButton buscarBedelButton;
    private JButton registrarBedelButton;
    private JLabel tituloBienvenida;
    private JLabel subtituloNombreUsuario;

    public static void main(String[] args) {
        FlatMacDarkLaf.setup();
        MenuAdministrador mu = new MenuAdministrador();
        mu.setContentPane(new MenuAdministrador().menuAdministrador);
        mu.setSize(500,600);
        mu.setLocationRelativeTo(null);
        mu.setVisible(true);
    }
}
