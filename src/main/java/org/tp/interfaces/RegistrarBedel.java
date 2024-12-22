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
import java.util.Objects;


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
    private GestorUsuario gestorUsuario;

    private static Boolean mostrar;
    private static Boolean mostrar2;

    public RegistrarBedel() {

        mostrar = true;
        mostrar2 = true;

        this.setContentPane(this.registrarBedel);
        this.setSize(800, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        this.inputContrasenia.setEchoChar('*');
        this.confirmarContrasenia.setEchoChar('*');

        cancelarButton.addActionListener(e -> {
            RegistrarBedel.this.setEnabled(false);
            MensajeDeAlerta ma = new MensajeDeAlerta("¿Estas seguro que deseas cancelar el registro?", RegistrarBedel.this);
            ma.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    RegistrarBedel.this.setEnabled(true);
                }
            });
        });

        confirmarButton.addActionListener(e -> {
            if (!(inputApellido.getText().isBlank() || inputNombre.getText().isBlank() || inputContrasenia.getText().isBlank() || confirmarContrasenia.getText().isBlank() || inputUsuario.getText().isBlank() || seleccionarTurno.getSelectedIndex() == 0)) {

                String nombre = inputNombre.getText().substring(0,1).toUpperCase() + inputNombre.getText().substring(1).toLowerCase();
                String apellido = inputApellido.getText().substring(0, 1).toUpperCase() + inputApellido.getText().substring(1).toLowerCase();

                BedelDTO bedelDTO = new BedelDTO(nombre, apellido, inputUsuario.getText(), inputContrasenia.getText(), seleccionarTurno.getSelectedItem().toString());

                try {

                    if(!Objects.equals(confirmarContrasenia.getText(), inputContrasenia.getText())) {
                        throw new ContraseniasNoCoincidenException();
                    }

                    gestorUsuario = new GestorUsuario();
                    this.gestorUsuario.registrarBedel(bedelDTO);


                    JOptionPane.showMessageDialog(this, "Bedel creado con exito", "Exito", JOptionPane.INFORMATION_MESSAGE);
                    dispose();

                } catch (UsuarioYaRegistradoException | ContraseniaInvalidaException |
                         ContraseniasNoCoincidenException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        });

        ojoButton.addActionListener(e -> cambiar(inputContrasenia));
        ojoButton2.addActionListener(e -> cambiar2(confirmarContrasenia));
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

