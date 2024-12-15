package org.tp.interfaces;

import org.tp.dto.BedelDTO;
import org.tp.excepciones.ContraseniaInvalidaException;
import org.tp.excepciones.ContraseniasNoCoincidenException;
import org.tp.excepciones.UsuarioYaRegistradoException;
import org.tp.gestores.*;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.util.Locale;


public class RegistrarBedel extends JFrame {
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
    private final GestorUsuario gestorUsuario;

    private static Boolean mostrar;
    private static Boolean mostrar2;

    public RegistrarBedel() {

        mostrar = true;
        mostrar2 = true;
        this.gestorUsuario = new GestorUsuario();
        //this.gestorPoliticas = new GestorPoliticas();
        this.setContentPane(this.registrarBedel);
        this.setSize(800, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        this.inputContrasenia.setEchoChar('*');
        this.confirmarContrasenia.setEchoChar('*');

        cancelarButton.addActionListener(e -> {
            MensajeDeAlerta ma = new MensajeDeAlerta("¿Estas seguro que deseas cancelar el registro?", RegistrarBedel.this);
        });

        confirmarButton.addActionListener(e -> {
            if (!(inputApellido.getText().isBlank() || inputNombre.getText().isBlank() || inputContrasenia.getText().isBlank() || confirmarContrasenia.getText().isBlank() || inputUsuario.getText().isBlank() || seleccionarTurno.getSelectedIndex() == 0)) {
                try {
                    String nombre = inputNombre.getText().substring(0,1).toUpperCase() + inputNombre.getText().substring(1).toLowerCase();
                    String turno = seleccionarTurno.getSelectedItem().toString().substring(0,1).toUpperCase() + seleccionarTurno.getSelectedItem().toString().substring(1).toLowerCase();
                    String apellido = inputApellido.getText().substring(0,1).toUpperCase() + inputApellido.getText().substring(1).toLowerCase();
                    registrarBedel(nombre, apellido, inputUsuario.getText(), inputContrasenia.getText(), confirmarContrasenia.getText(), turno);
                    dispose();
                } catch (UsuarioYaRegistradoException | ContraseniaInvalidaException |
                         ContraseniasNoCoincidenException | IllegalArgumentException ex) {
                    MensajeDeError me = new MensajeDeError(ex.getMessage());
                }
            }
        });

        ojoButton.addActionListener(e -> cambiar(inputContrasenia));
        ojoButton2.addActionListener(e -> cambiar2(confirmarContrasenia));
    }

    private void registrarBedel(String nombre, String apellido, String usuario, String contrasenia, String confirmarcontrasenia, String turno) throws ContraseniasNoCoincidenException, UsuarioYaRegistradoException, ContraseniaInvalidaException {
        BedelDTO bedelDTO = new BedelDTO(nombre, apellido, usuario, contrasenia, turno);
        this.gestorUsuario.registrarBedel(bedelDTO, confirmarcontrasenia);
    }

    public static void cambiar(JPasswordField password) {
        if (mostrar) {
            password.setEchoChar((char) 0);
        } else {
            password.setEchoChar('*');
        }
        mostrar = !mostrar;
    }

    public static void cambiar2(JPasswordField password) {
        if (mostrar2) {
            password.setEchoChar((char) 0);
        } else {
            password.setEchoChar('*');
        }
        mostrar2 = !mostrar2;
    }

}

