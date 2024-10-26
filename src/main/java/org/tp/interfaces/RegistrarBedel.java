package org.tp.interfaces;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RegistrarBedel extends JFrame{
    private JPanel registrarBedel;
    private JTextField confirmarContrasenia;
    private JTextField inputContrasenia;
    private JButton confirmarButton;
    private JButton cancelarButton;
    private JTextField inputNombre;
    private JComboBox seleccionarTurno;
    private JTextField inputUsuario;
    private JTextField inputApellido;
    private ArrayList<String> idUsuarios = new ArrayList<>();

    public RegistrarBedel() {

        this.setContentPane(this.registrarBedel);
        this.setSize(800,400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    


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
    }

    private boolean verificarUsuarioExiste() {
        return idUsuarios.contains(inputUsuario.getText());
    }

    private void setRegistrarBedel() {
        idUsuarios.add("Cristian");
        if (verificarUsuarioExiste()) {
            MensajeDeError me = new MensajeDeError("El idUsuario ya se encuentra registrado.");
            return;
        }
        //Agregar validacion politicas

        if (!inputContrasenia.getText().equals(confirmarContrasenia.getText())) {
            MensajeDeError me = new MensajeDeError("Las contraseñas no coinciden.");
            return;
        }
        else{
            //Se guarda contraseña
            if(!inputContrasenia.getText().isBlank()) {
                System.out.println("Contraseña guardada.");
            }
            guardarBedel();
            dispose();
        }

    }

    private void guardarBedel() {
    }

//    public static void main(String[] args) {
//        FlatMacDarkLaf.setup();
//        RegistrarBedel rb = new RegistrarBedel();
//        rb.setContentPane(rb.registrarBedel);
//        rb.setSize(800,400);
//        rb.setLocationRelativeTo(null);
//        rb.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//        rb.setVisible(true);
//    }

}
