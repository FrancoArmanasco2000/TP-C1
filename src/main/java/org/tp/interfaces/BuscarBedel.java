package org.tp.interfaces;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import org.tp.dto.BedelDTO;
import org.tp.entity.Bedel;
import org.tp.excepciones.BedelPorApellidoNoEncontradoException;
import org.tp.excepciones.BedelPorTurnoNoEncontradoException;
import org.tp.gestores.GestorUsuario;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

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

    private final JTable tablaBedeles;
    private final DefaultTableModel modeloTabla;
    private final GestorUsuario gestorUsuario;
    private  List<BedelDTO> bedelesDTO;

    public BuscarBedel() {
        this.setTitle("Buscar Bedel");
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setSize(800, 600);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setContentPane(buscarBedel);
        this.nombreRadioButton.setSelected(true);
        gestorUsuario = new GestorUsuario();

        ButtonGroup searchGroup = new ButtonGroup();
        searchGroup.add(nombreRadioButton);
        searchGroup.add(turnoRadioButton);

        String[] columnas = {"Apellido","Nombre","Turno","Usuario"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Hacer que todas las celdas no sean editables
                return false;
            }
        };

        tablaBedeles = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaBedeles);
        buscarBedel.add(scrollPane, BorderLayout.CENTER);

        this.cargarDatosBedeles();

        buscarButton.addActionListener(e -> {
            try {

                bedelesDTO = new ArrayList<>();
                String textoBuscador = buscadorTextField.getText().substring(0,1).toUpperCase() + buscadorTextField.getText().substring(1).toLowerCase();
                bedelesDTO = nombreRadioButton.isSelected() ? gestorUsuario.buscarBedel(textoBuscador,"") : gestorUsuario.buscarBedel("",textoBuscador);

                modeloTabla.setRowCount(0);
                for (BedelDTO bedelDTO : bedelesDTO) {
                    modeloTabla.addRow(new Object[]{
                            bedelDTO.getApellido(),
                            bedelDTO.getNombre(),
                            bedelDTO.getTurno(),
                            bedelDTO.getUsuario()
                    });
                }

            } catch (BedelPorApellidoNoEncontradoException | BedelPorTurnoNoEncontradoException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        limpiarButton.addActionListener(e -> limpiarBusqueda());
        modificarButton.addActionListener(e -> {
            int filaSeleccionada = tablaBedeles.getSelectedRow();

            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(this, "Por favor, selecciona un bedel para modificar.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            ModificarBedel modificarBedel = new ModificarBedel(bedelesDTO.get(filaSeleccionada));

            modificarBedel.setVisible(true);
            dispose();
        });
        eliminarButton.addActionListener(e -> {
            int filaSeleccionada = tablaBedeles.getSelectedRow(); // Obtener la fila seleccionada

            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione un bedel para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            gestorUsuario.eliminarBedel(bedelesDTO.get(filaSeleccionada).getIdUsuario());

            // Confirmación antes de eliminar
            int confirm = JOptionPane.showConfirmDialog(this,
                    "¿Está seguro de que desea eliminar este bedel?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                //gestorUsuario.eliminarBedel(idUsuario);

                cargarDatosBedeles();
                JOptionPane.showMessageDialog(this, "Bedel eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
            dispose();
        });
        cancelarButton.addActionListener(e -> dispose());
    }

    private void cargarDatosBedeles() {
        modeloTabla.setRowCount(0);
        List<BedelDTO> bedeles = gestorUsuario.obtenerTodosLosUsuarios();

        if (bedeles == null || bedeles.isEmpty()) {
            System.out.println("No se encontraron bedeles.");
            return;
        }

        this.bedelesDTO = bedeles;
        for (BedelDTO bedelDTO : bedeles) {
            modeloTabla.addRow(new Object[]{
                    bedelDTO.getApellido(),
                    bedelDTO.getNombre(),
                    bedelDTO.getTurno(),
                    bedelDTO.getUsuario()
            });
        }
    }

    private void limpiarBusqueda() {
        buscadorTextField.setText("");
        cargarDatosBedeles();
    }

}