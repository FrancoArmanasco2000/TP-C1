package org.tp.interfaces.BuscarBedel;

import javax.swing.*;
import java.awt.*;

public class BuscarBedel extends JFrame {
    private JPanel mainPanel;
    private JPanel headerPanel;
    private JRadioButton nombreRadioButton;
    private JRadioButton turnoRadioButton;
    private JTextField buscadorTextField;
    private JButton buscarButton;
    private JButton limpiarButton;
    private JPanel footerPanel;
    private JButton modificarButton;
    private JButton eliminarButton;
    private JButton cancelarButton;

    public BuscarBedel() {
        this.setTitle("Buscar Bedel");
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setSize(800,600);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setContentPane(mainPanel);



        ButtonGroup searchGroup = new ButtonGroup();
        searchGroup.add(nombreRadioButton);
        searchGroup.add(turnoRadioButton);

        //Crear la tabla de bedeles
        String[] columnas = {"Nombre", "Apellido", "Turno", "Usuario"};
        Object[][] datos = {};
        JTable tablaBedeles = new JTable(datos, columnas);
        JScrollPane scrollPane = new JScrollPane(tablaBedeles);




        // Añadir tabla al CENTRO del BorderLayout
        mainPanel.add(scrollPane, BorderLayout.CENTER);



    }
}
