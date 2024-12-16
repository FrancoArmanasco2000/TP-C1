package org.tp.interfaces;

import org.tp.dto.ReservaDTO;
import org.tp.gestores.GestorAula;
import org.tp.utils.TipoAula;

import javax.swing.*;

public class AsignarAula extends JFrame{
    private JTable tablaAsignarAula;
    private JPanel asignarAulaPanel;
    private JButton asignarButton;
    private JButton cancelarButton;
    private static GestorAula gestorAula = new GestorAula();

    public AsignarAula() {
        this.setContentPane(asignarAulaPanel);
        this.setTitle("Asignar Aula");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);



        //gestorAula.getAulas(reservaDTO.getTipoAula(), reservaDTO.getCantAlumnos());

        cancelarButton.addActionListener(e -> dispose());
    }
}
