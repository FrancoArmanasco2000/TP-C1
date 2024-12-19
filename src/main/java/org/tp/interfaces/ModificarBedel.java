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
    private final Bedel bedel;

    private static Boolean mostrar;
    private static Boolean mostrar2;

    public ModificarBedel(Long idBedel) {
        mostrar = true;
        mostrar2 = true;
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
        seleccionarTurno.setSelectedItem(bedel.getTurno().substring(0, 1).toUpperCase() + bedel.getTurno().substring(1));
        inputUsuario.setText(bedel.getUsuario());
        inputContrasenia.setText(bedel.getContrasenia());
        confirmarContrasenia.setText(bedel.getContrasenia());

        cancelarButton.addActionListener(e -> {
           new MensajeDeAlerta("¿Estas seguro que deseas cancelar la modificación del registro?", ModificarBedel.this);
        });
        confirmarButton.addActionListener(e -> {
            if (!(inputApellido.getText().isBlank() || inputNombre.getText().isBlank() || inputContrasenia.getText().isBlank() || confirmarContrasenia.getText().isBlank() || inputUsuario.getText().isBlank() || seleccionarTurno.getSelectedIndex() == 0)) {
                try {
                    String nombre = inputNombre.getText().substring(0,1).toUpperCase() + inputNombre.getText().substring(1).toLowerCase();
                    String turno = seleccionarTurno.getSelectedItem().toString().substring(0,1).toUpperCase() + seleccionarTurno.getSelectedItem().toString().substring(1).toLowerCase();
                    String apellido = inputApellido.getText().substring(0,1).toUpperCase() + inputApellido.getText().substring(1).toLowerCase();

                    BedelDTO bedel = new BedelDTO(nombre, apellido, inputUsuario.getText(), inputContrasenia.getText(), turno);
                    Bedel bedelActual = modificarBedel(bedel);
                    bedelActual.setIdUsuario(idBedel);
                    actualizarBedel(bedelActual);
                    dispose();

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

    private void actualizarBedel(Bedel bedel) {
        this.gestorUsuario.actualizarBedel(bedel);
    }


    private Bedel modificarBedel(BedelDTO bedel) throws ContraseniaInvalidaException, ContraseniasNoCoincidenException, IllegalArgumentException {
        if (!confirmarContrasenia.getText().equals(inputContrasenia.getText())) {
            throw new ContraseniasNoCoincidenException();
        }
        return this.gestorUsuario.modificarBedel(bedel);
    }

    private Bedel getUsuarioById(Long idBedel) {
        return this.gestorUsuario.getUsuarioById(idBedel);
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
