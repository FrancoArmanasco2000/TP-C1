package org.tp.interfaces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuscarBedel extends JFrame {
    private JPanel buscarBedel;
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
        this.setContentPane(buscarBedel);

        ButtonGroup searchGroup = new ButtonGroup();
        searchGroup.add(nombreRadioButton);
        searchGroup.add(turnoRadioButton);

        //Crear la tabla de bedeles
        String[] columnas = {"Nombre", "Apellido", "Turno", "Usuario"};
        Object[][] datos = {};
        JTable tablaBedeles = new JTable(datos, columnas);
        JScrollPane scrollPane = new JScrollPane(tablaBedeles);

        // Añadir tabla al CENTRO del BorderLayout
        buscarBedel.add(scrollPane, BorderLayout.CENTER);


        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ModificarBedel mb = new ModificarBedel(1L);//Se modifica solo el de id 1 para testear, luego se deberá pasar el id del bedel de la fila seleccionada
            }
        });

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
