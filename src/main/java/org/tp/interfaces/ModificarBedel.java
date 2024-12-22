package org.tp.interfaces;

import org.tp.dto.BedelDTO;
import org.tp.entity.Bedel;
import org.tp.excepciones.ContraseniaInvalidaException;
import org.tp.excepciones.ContraseniasNoCoincidenException;
import org.tp.gestores.GestorUsuario;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.util.Locale;

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
    private final GestorUsuario gestorUsuario;

    private static Boolean mostrar;
    private static Boolean mostrar2;

    public ModificarBedel(BedelDTO bedelDTOtb) {
        mostrar = true;
        mostrar2 = true;
        this.gestorUsuario = new GestorUsuario();
        this.setContentPane(this.modificarBedel);
        this.setSize(800, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        inputUsuario.setEditable(false);


        inputNombre.setText(bedelDTOtb.getNombre());
        inputApellido.setText(bedelDTOtb.getApellido());
        seleccionarTurno.setSelectedItem(bedelDTOtb.getTurno().substring(0, 1).toUpperCase() + bedelDTOtb.getTurno().substring(1));
        inputUsuario.setText(bedelDTOtb.getUsuario());
        inputContrasenia.setText(bedelDTOtb.getContrasenia());
        confirmarContrasenia.setText(bedelDTOtb.getContrasenia());

        cancelarButton.addActionListener(e -> {
           new MensajeDeAlerta("¿Estas seguro que deseas cancelar la modificación del registro?", ModificarBedel.this);
        });



        confirmarButton.addActionListener(e -> {
            if (!(inputApellido.getText().isBlank() || inputNombre.getText().isBlank() || inputContrasenia.getText().isBlank() || confirmarContrasenia.getText().isBlank() || inputUsuario.getText().isBlank() || seleccionarTurno.getSelectedIndex() == 0)) {

                String nombre = inputNombre.getText().substring(0,1).toUpperCase() + inputNombre.getText().substring(1).toLowerCase();
                String turno = seleccionarTurno.getSelectedItem().toString().substring(0,1).toUpperCase() + seleccionarTurno.getSelectedItem().toString().substring(1).toLowerCase();
                String apellido = inputApellido.getText().substring(0,1).toUpperCase() + inputApellido.getText().substring(1).toLowerCase();


                BedelDTO bedelDTO = new BedelDTO(nombre, apellido, inputUsuario.getText(), inputContrasenia.getText(), turno);
                bedelDTO.setIdUsuario(bedelDTOtb.getIdUsuario());

                try {
                    gestorUsuario.modificarBedel(bedelDTO);
                    dispose();
                    JOptionPane.showMessageDialog(this, "Modificado con exito", "Exito", JOptionPane.INFORMATION_MESSAGE);

                } catch (ContraseniaInvalidaException | ContraseniasNoCoincidenException |
                         IllegalArgumentException ex) {
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
