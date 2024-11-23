package org.tp.interfaces;

import org.tp.dto.BedelDTO;
import org.tp.entity.Bedel;
import org.tp.excepciones.ContraseniaInvalidaException;
import org.tp.excepciones.ContraseniasNoCoincidenException;
import org.tp.excepciones.UsuarioYaRegistradoException;
import org.tp.gestores.GestorUsuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ModificarBedel extends JFrame {
    private JPanel modificarBedel;
    private JPasswordField confirmarContrasenia;
    private JPasswordField inputContrasenia;
    private JTextField inputNombre;
    private JButton cancelarButton;
    private JButton confirmarButton;
    private JTextField inputApellido;
    private JComboBox seleccionarTurno;
    private JTextField inputUsuario;
    private JButton ojoButton2;
    private JButton ojoButton;
    private GestorUsuario gestorUsuario;
    private Bedel bedel;

    private static Boolean mostrar;
    private static Boolean mostrar2;

    public ModificarBedel(Long idBedel){
        this.mostrar = true;
        this.mostrar2 = true;
        this.gestorUsuario = new GestorUsuario();
        this.setContentPane(this.modificarBedel);
        this.setSize(800, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        inputUsuario.setEditable(false);

        bedel = getUsuarioById(idBedel);

        inputNombre.setText(bedel.getNombre());
        inputApellido.setText(bedel.getApellido());
        seleccionarTurno.setSelectedItem(bedel.getTurno());
        inputUsuario.setText(bedel.getUsuario());
        inputContrasenia.setText(bedel.getContrasenia());
        confirmarContrasenia.setText(bedel.getContrasenia());

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MensajeDeAlerta ma = new MensajeDeAlerta("¿Estas seguro que deseas cancelar la modificación del registro?", ModificarBedel.this);
            }
        });
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(inputApellido.getText().isBlank() || inputNombre.getText().isBlank() || inputContrasenia.getText().isBlank() || confirmarContrasenia.getText().isBlank() || inputUsuario.getText().isBlank() || seleccionarTurno.getSelectedIndex() == 0)) {
                    try{
                        BedelDTO bedel = new BedelDTO( inputNombre.getText(), inputApellido.getText(), inputUsuario.getText(), inputContrasenia.getText(), seleccionarTurno.getSelectedItem().toString() );
                        Bedel bedelActual=modificarBedel(bedel);
                        bedelActual.setIdUsuario(idBedel);
                        actualizarBedel(bedelActual);
                        dispose();
                    } catch (ContraseniaInvalidaException | ContraseniasNoCoincidenException ex) {
                        MensajeDeError me = new MensajeDeError(ex.getMessage());
                    }
                }
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

    private void actualizarBedel(Bedel bedel){
        this.gestorUsuario.actualizarBedel(bedel);
    }


    private Bedel modificarBedel(BedelDTO bedel) throws ContraseniaInvalidaException, ContraseniasNoCoincidenException {
        if(!confirmarContrasenia.getText().equals(inputContrasenia.getText())){
            throw new ContraseniasNoCoincidenException();
        }
        return this.gestorUsuario.modificarBedel(bedel);
    }

    private Bedel getUsuarioById(Long idBedel){
        Bedel bedel= this.gestorUsuario.getUsuarioById(idBedel);
        return bedel;
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
