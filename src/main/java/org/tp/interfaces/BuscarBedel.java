package org.tp.interfaces;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import org.tp.entity.Bedel;
import org.tp.gestores.GestorUsuario;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

    public BuscarBedel() {
        this.setTitle("Buscar Bedel");
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setSize(800, 600);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setContentPane(buscarBedel);
        gestorUsuario = new GestorUsuario();

        ButtonGroup searchGroup = new ButtonGroup();
        searchGroup.add(nombreRadioButton);
        searchGroup.add(turnoRadioButton);

        String[] columnas = {"ID", "Nombre", "Apellido", "Turno", "Usuario"};
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

        cargarDatosBedeles();

        buscarButton.addActionListener(e -> buscarBedeles());
        limpiarButton.addActionListener(e -> limpiarBusqueda());
        modificarButton.addActionListener(e -> {
            int filaSeleccionada = tablaBedeles.getSelectedRow();

            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(this, "Por favor, selecciona un bedel para modificar.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Long idBedel = (Long) modeloTabla.getValueAt(filaSeleccionada, 0);

            ModificarBedel modificarBedel = new ModificarBedel(idBedel);
            modificarBedel.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    cargarDatosBedeles();
                }
            });

            modificarBedel.setVisible(true);
        });
        eliminarButton.addActionListener(e -> eliminarBedel());
        cancelarButton.addActionListener(e -> dispose());
    }

    private void cargarDatosBedeles() {
        modeloTabla.setRowCount(0);
        List<Bedel> bedeles = gestorUsuario.obtenerTodosLosBedeles();

        if (bedeles == null || bedeles.isEmpty()) {
            System.out.println("No se encontraron bedeles.");
            return;
        }

        for (Bedel bedel : bedeles) {
            modeloTabla.addRow(new Object[]{
                    bedel.getIdUsuario(),
                    bedel.getNombre(),
                    bedel.getApellido(),
                    bedel.getTurno(),
                    bedel.getUsuario()
            });
        }
    }

    private void buscarBedeles() {
        String criterio = buscadorTextField.getText();
        if (criterio.isEmpty()) {
            cargarDatosBedeles();
            return;
        }

        List<Bedel> resultados;
        if (nombreRadioButton.isSelected()) {
            resultados = gestorUsuario.buscarBedelesPorNombre(criterio);
        } else if (turnoRadioButton.isSelected()) {
            resultados = gestorUsuario.buscarBedelesPorTurno(criterio);
        } else {
            resultados = gestorUsuario.obtenerTodosLosBedeles();
        }

        modeloTabla.setRowCount(0);
        for (Bedel bedel : resultados) {
            modeloTabla.addRow(new Object[]{
                    bedel.getIdUsuario(),
                    bedel.getNombre(),
                    bedel.getApellido(),
                    bedel.getTurno(),
                    bedel.getUsuario()
            });
        }
    }

    private void limpiarBusqueda() {
        buscadorTextField.setText("");
        cargarDatosBedeles();
    }

    private void eliminarBedel() {
        int filaSeleccionada = tablaBedeles.getSelectedRow(); // Obtener la fila seleccionada

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un bedel para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Long idUsuario = (Long) modeloTabla.getValueAt(filaSeleccionada, 0);

        // Confirmación antes de eliminar
        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Está seguro de que desea eliminar este bedel?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            gestorUsuario.eliminarBedel(idUsuario);

            cargarDatosBedeles();
            JOptionPane.showMessageDialog(this, "Bedel eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}