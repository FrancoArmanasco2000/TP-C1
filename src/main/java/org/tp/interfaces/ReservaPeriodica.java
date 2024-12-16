package org.tp.interfaces;

import org.tp.dto.FechaDTO;
import org.tp.dto.ReservaDTO;
import org.tp.gestores.GestorReserva;
import org.tp.utils.TipoAula;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class ReservaPeriodica extends JFrame {
    private JTextField inputCantidadAlumnos;
    private JTextField inputNombreApellido;
    private JTextField inputAsignatura;
    private JTextField inputCorreo;
    private JComboBox<String> periodoComboBox;
    private JComboBox<String> tipoAulaComboBox;
    private JButton agregarDiaButton;
    private JTable tablaDiasReserva;
    private JButton confirmarButton;
    private JButton cancelarButton;
    private JPanel reservaPeriodicaPanel;


    public ReservaPeriodica() {
        this.setTitle("Reserva Periodica");
        this.setContentPane(this.reservaPeriodicaPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(500, 600);
        this.setResizable(false); // NO MODIFICA LA PESTAÑA
        this.setLocationRelativeTo(null); // APARECE EN EL MEDIO
        this.setVisible(true);
        String[] periodos = {"1C 2024", "2C 2024", "ANUAL 2024", "1C 2025", "2C 2025", "ANUAL 2025"};
        for (String periodo : periodos) {
            periodoComboBox.addItem(periodo);
        }
        String[] tipos = {"Multimedios", "Informatica", "Sin Recursos Adicionales"};
        for (String tipo : tipos) {
            tipoAulaComboBox.addItem(tipo);
        }
        agregarDiaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(inputCantidadAlumnos.getText().isEmpty() || inputNombreApellido.getText().isEmpty() || inputAsignatura.getText().isEmpty() || inputCorreo.getText().isEmpty()) && validarDatos()) {
                    ReservaPeriodica.this.setEnabled(false);
                    AgregarDia ad = new AgregarDia(ReservaPeriodica.this, tablaDiasReserva);
                    ad.setVisible(true);
                    ad.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                            if (tablaDiasReserva.getRowCount() > 0) {
                                //Como los datos de la reserva estan completos, se bloquean los campos para evitar que se modifiquen a la hora de agregar el siguiente día
                                periodoComboBox.setEnabled(false);
                                tipoAulaComboBox.setEnabled(false);
                                inputCantidadAlumnos.setEditable(false);
                                inputNombreApellido.setEditable(false);
                                inputAsignatura.setEditable(false);
                                inputCorreo.setEditable(false);
                            }
                            ReservaPeriodica.this.setEnabled(true);
                        }
                    });
                }else{
                    JOptionPane.showMessageDialog(null, "Debe completar todos los campos antes de agregar un día.");
                }
            }
        });
        String[] columnas = {"Dia", "Horario Inicio", "Horario Fin"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaDiasReserva.setModel(modeloTabla);

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GestorReserva gr = new GestorReserva();
                List<FechaDTO> fechas = new ArrayList<>();
                for (int row = 0; row < tablaDiasReserva.getRowCount(); row++) {
                    List<Object> rowData = new ArrayList<>();
                    for (int col = 0; col < tablaDiasReserva.getColumnCount(); col++) {
                        Object value = tablaDiasReserva.getValueAt(row, col);
                        System.out.println("Dato en fila " + row + ", columna " + col + ": " + value);
                        rowData.add(value);
                    }
                    fechas.add(gr.generarFechaDTOPeriodica(rowData));
                }
            for(FechaDTO fechaDTO : fechas) {
                fechaDTO.setIdAula((long)5);
            }
                
                ReservaDTO reservaDTO = new ReservaDTO(retornarPeriodo(),Integer.parseInt(inputCantidadAlumnos.getText()),retornarTipoAula(), new Random().nextInt(),new Random().nextInt(), inputAsignatura.getText(), inputCorreo.getText(),1L,fechas);
                gr.RegistrarReserva(reservaDTO);
            }
        });

    }

    public Long retornarPeriodo(){
        return periodoComboBox.getSelectedIndex()+1L;
    };

    public TipoAula retornarTipoAula() {
        return switch (tipoAulaComboBox.getSelectedIndex()) {
            case '0' -> TipoAula.MULTIMEDIO;
            case '1' -> TipoAula.INFORMATICA;
            case '2' -> TipoAula.SIN_RECURSOS;
            default -> null;
        };
    };

    public boolean validarDatos() {
        // Cant alumnos es un número
        String cantidadAlumnosTexto = inputCantidadAlumnos.getText();
        if (!cantidadAlumnosTexto.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "La cantidad de alumnos debe ser un número entero.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // Nombre y Apellido invalido
        String nombreApellidoTexto = inputNombreApellido.getText();
        if (!nombreApellidoTexto.matches("[A-Za-z]+\\s[A-Za-z]+")) {
            JOptionPane.showMessageDialog(null, "El campo Apellido y Nombre debe tener formato: Apellido Nombre.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // Asignatura tiene que ser una sola palabra
        String asignaturaTexto = inputAsignatura.getText();
        if (!asignaturaTexto.matches("[A-Zña-zÑ]*")) {
            JOptionPane.showMessageDialog(null, "El campo Asignatura debe ser una sola palabra.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // Formato de email
        String correoTexto = inputCorreo.getText();
        if (!correoTexto.matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}")) {
            JOptionPane.showMessageDialog(null, "El correo electrónico debe tener un formato válido, como ejemplo@dominio.com.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
