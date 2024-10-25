package org.tp.interfaces;


import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.intellijthemes.FlatMaterialDesignDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatXcodeDarkIJTheme;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;

public class MenuUsuario extends JFrame{

    private JPanel menuUsuario;
    private JButton buscarAulaButton;
    private JButton registrarReservaButton;
    private JButton reservasParaUnDiaButton;
    private JButton reservasParaUnCursoButton;
    private JLabel TituloBienvenida;
    private JLabel SubtituloNombreUsuario;


    public void setSubtituloNombreUsuario(String subtituloNombreUsuario) {
        SubtituloNombreUsuario.setText(subtituloNombreUsuario);
    }

    public MenuUsuario(String usuario){
        this.setSubtituloNombreUsuario(usuario);
        this.setContentPane(this.menuUsuario);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);

        registrarReservaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistrarReserva rr = new RegistrarReserva();
            }
        });
    }

}
