package org.tp.interfaces;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Function;
import java.util.function.Supplier;

public class InicioSesion extends JFrame {

    private static Boolean mostrar;

    public InicioSesion() {

        //CONFIGURACION VENTANA
        JFrame ventana = new JFrame("Inicio Sesion");
        ventana.setSize(500,600);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setLocationRelativeTo(null);
        ventana.setLayout(null);

        //TITULO
        JLabel titulo = new JLabel("S.G.R");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 50 ));
        Dimension sizeTitulo = titulo.getPreferredSize();
        titulo.setBounds(250-(sizeTitulo.width/2)-10,90,sizeTitulo.width,sizeTitulo.height);
        ventana.add(titulo);

        //FORMULARIO INICIAR SESION
        JLabel usuario = new JLabel("Usuario");
        usuario.setFont(new Font("Segoe UI", Font.BOLD, 15 ));
        Dimension sizeUsuario = usuario.getPreferredSize();
        usuario.setBounds(100,200,sizeUsuario.width,sizeUsuario.height);
        ventana.add(usuario);

        JTextField usuarioInput = new JTextField();
        usuarioInput.setFont(new Font("Segoe UI", Font.BOLD, 15 ));
        usuarioInput.setBounds(100,240,280,30);
        ventana.add(usuarioInput);

        JLabel contrasenia = new JLabel("Contraseña");
        contrasenia.setFont(new Font("Segoe UI", Font.BOLD, 15 ));
        Dimension sizeContrasenia = contrasenia.getPreferredSize();
        contrasenia.setBounds(100,300,sizeContrasenia.width,sizeContrasenia.height);
        ventana.add(contrasenia);

        JPasswordField contraseniaInput = new JPasswordField();
        contraseniaInput.setFont(new Font("Segoe UI", Font.BOLD, 15 ));
        contraseniaInput.setBounds(100,340,280,30);
        contraseniaInput.setEchoChar('*');
        ventana.add(contraseniaInput);

        JButton botonMostrarContrasenia = new JButton("\uD83D\uDC41");
        botonMostrarContrasenia.setFont(new Font("Segoe UI", Font.BOLD, 15 ));
        botonMostrarContrasenia.setBounds(380,340,40,40);
        this.mostrar = true;
        ventana.add(botonMostrarContrasenia);

        //BOTON INICIAR SESION
        JButton botonIniciarSesion = new JButton("Iniciar Sesion");
        botonIniciarSesion.setFont(new Font("Segoe UI", Font.BOLD, 15 ));
        botonIniciarSesion.setBounds(250-(100/2)-27,400,130,40);
        botonIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(contraseniaInput.getText().equals("Admin") && usuarioInput.getText().equals("Admin")) {
                    ventana.dispose();
                    MenuAdministrador mu = new MenuAdministrador();
                }else {
                    ventana.dispose();
                    String usuario = usuarioInput.getText();
                    MenuUsuario mu = new MenuUsuario(usuario);
                }
            }
        });

        botonMostrarContrasenia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiar(contraseniaInput);

            }
        });

        //AÑADIMOS EL BOTON Y MOSTRAMOS LA PANTALLA
        ventana.add(botonIniciarSesion);
        ventana.setVisible(true);

    }

    public static void cambiar (JPasswordField password) {
        if(mostrar){
            password.setEchoChar((char) 0);
        } else {
            password.setEchoChar('*');
        }
        mostrar = !mostrar;
    }

    //LA RAIZ PADRE DE TODA LA APLICACION
    public static void main(String[] args) {
        FlatMacDarkLaf.setup();
        new InicioSesion();
    }
}
