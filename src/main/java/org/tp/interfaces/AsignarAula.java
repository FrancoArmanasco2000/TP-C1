package org.tp.interfaces;

import javax.swing.*;

public class AsignarAula extends JFrame{
    private JTable tablaAsignarAula;
    private JPanel asignarAulaPanel;
    private JButton asignarButton;
    private JButton cancelarButton;

    public AsignarAula() {
        this.setContentPane(asignarAulaPanel);

        this.setTitle("Asignar Aula");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        cancelarButton.addActionListener(e -> dispose());
    }
}
