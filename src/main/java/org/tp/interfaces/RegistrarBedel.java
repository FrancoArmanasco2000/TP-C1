package org.tp.interfaces;

import org.tp.dto.BedelDTO;
import org.tp.gestores.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RegistrarBedel extends JFrame{
    private JPanel registrarBedel;
    private JPasswordField confirmarContrasenia;
    private JPasswordField inputContrasenia;
    private JButton confirmarButton;
    private JButton cancelarButton;
    private JTextField inputNombre;
    private JComboBox seleccionarTurno;
    private JTextField inputUsuario;
    private JTextField inputApellido;
    private JButton ojoButton2;
    private JButton ojoButton;
    private GestorUsuario gestorUsuario;
    private GestorPoliticas gestorPoliticas;
    private static Boolean mostrar;
    private static Boolean mostrar2;

    public RegistrarBedel() {

        this.mostrar = true;
        this.mostrar2 = true;
        this.gestorUsuario = new GestorUsuario();
        this.gestorPoliticas = new GestorPoliticas();
        this.setContentPane(this.registrarBedel);
        this.setSize(800,400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        this.inputContrasenia.setEchoChar('*');
        this.confirmarContrasenia.setEchoChar('*');

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MensajeDeAlerta ma = new MensajeDeAlerta("¿Estas seguro que deseas cancelar el registro?", RegistrarBedel.this);
            }
        });

        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setRegistrarBedel();
            }
        });

        ojoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiar(inputContrasenia);
            }
        });
        ojoButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiar2(confirmarContrasenia);
            }
        });
    }

    private boolean verificarUsuarioExiste(String usuario) {
        return this.gestorUsuario.listarUsuarios().contains(usuario);
    }

    private void setRegistrarBedel() {

        if(this.inputApellido.getText().isBlank() || this.inputNombre.getText().isBlank() || this.inputContrasenia.getText().isBlank() || this.inputUsuario.getText().isBlank() || this.seleccionarTurno.getSelectedIndex() == 0) {
            System.out.println("HOLA"+this.seleccionarTurno.getSelectedIndex());
        } else {
            if (verificarUsuarioExiste(this.inputUsuario.getText())) {
                MensajeDeError me = new MensajeDeError("El idUsuario ya se encuentra registrado.");
            } else if(!this.gestorPoliticas.comprobarTODO(this.inputContrasenia.getText()).equals("")){
                MensajeDeError me = new MensajeDeError(this.gestorPoliticas.comprobarTODO(this.inputContrasenia.getText()));
            } else if (!this.inputContrasenia.getText().equals(this.confirmarContrasenia.getText())) {
                MensajeDeError me = new MensajeDeError("Las contraseñas no coinciden.");
            } else {
                registrarBedel(this.inputNombre.getText(),this.inputApellido.getText(),this.inputUsuario.getText(),this.inputContrasenia.getText(), (String) this.seleccionarTurno.getSelectedItem());
                dispose();
            }
        }


    }

    private void registrarBedel(String nombre, String apellido, String usuario, String contrasenia, String turno){
        BedelDTO bedelDTO = new BedelDTO(nombre, apellido, usuario, contrasenia, turno);
        this.gestorUsuario.registrarBedel(bedelDTO);
    }

    public static void cambiar (JPasswordField password) {
        if(mostrar){
            password.setEchoChar((char) 0);
        } else {
            password.setEchoChar('*');
        }
        mostrar = !mostrar;
    }

    public static void cambiar2 (JPasswordField password) {
        if(mostrar2){
            password.setEchoChar((char) 0);
        } else {
            password.setEchoChar('*');
        }
        mostrar2 = !mostrar2;
    }

}
