package org.tp.interfaces;

import javax.swing.*;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import org.tp.gestores.GestorUsuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InicioSesion extends JFrame {
    private JPanel ventana;
    private JLabel titulo;
    private JLabel usuario;
    private JTextField usuarioInput;
    private JLabel contrasenia;
    private JPasswordField contraseniaInput;
    private JButton botonMostrarContrasenia;
    private JButton botonIniciarSesion;
    private static Boolean mostrar=true;
    private final GestorUsuario gestorUsuario;

    public InicioSesion() {
        this.setVisible(true);
        this.setContentPane(this.ventana);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setSize(500, 600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.gestorUsuario = new GestorUsuario();
        contraseniaInput.setEchoChar('*');


        botonIniciarSesion.addActionListener(e -> {
            if(contraseniaInput.getText().equals("Admin") && usuarioInput.getText().equals("Admin")) {
                dispose();
                MenuAdministrador mu = new MenuAdministrador();
            }else if(gestorUsuario.validarSesion(usuarioInput.getText(), contraseniaInput.getText())) {
                dispose();
                MenuUsuario mu = new MenuUsuario(usuarioInput.getText());
            } else{
                JOptionPane.showMessageDialog(null, "Usuario o contraseña ingresados incorrectos.");
            }
        });
        botonMostrarContrasenia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiar(contraseniaInput);
            }
        });

    }
    public static void cambiar (JPasswordField password) {
        if(mostrar){
            password.setEchoChar((char) 0);
        } else {
            password.setEchoChar('*');
        }
        mostrar = !mostrar;
    }

    public static void main(String[] args) {
        FlatMacDarkLaf.setup();
        new InicioSesion();
    }
}
