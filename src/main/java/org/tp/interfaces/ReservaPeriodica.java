package org.tp.interfaces;

import org.tp.dto.ReservaDTO;
import org.tp.excepciones.DatosException;
import org.tp.excepciones.DuracionException;
import org.tp.excepciones.FechaException;
import org.tp.excepciones.HorarioException;
import org.tp.gestores.GestorAsignaturas;
import org.tp.gestores.GestorDocente;
import org.tp.gestores.GestorReserva;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;
import java.util.List;

public class ReservaPeriodica extends JFrame {
    private JTextField inputCantidadAlumnos;
    private JComboBox inputAsignatura;
    private JTextField inputCorreo;
    private JComboBox<String> periodoComboBox;
    private JComboBox<String> tipoAulaComboBox;
    private JButton agregarDiaButton;
    private JTable tablaDiasReserva;
    private JButton confirmarButton;
    private JButton cancelarButton;
    private JPanel reservaPeriodicaPanel;
    private JComboBox inputDocente;
    private ReservaDTO reservaDTO;

    public ReservaPeriodica(String usuario) {
        this.setTitle("Reserva Periódica");
        this.setContentPane(this.reservaPeriodicaPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(500, 600);
        this.setResizable(false); // NO MODIFICA LA PESTAÑA
        this.setLocationRelativeTo(null); // APARECE EN EL MEDIO
        this.setVisible(true);
        reservaDTO = new ReservaDTO();
        reservaDTO.setListaFechasDTO(new ArrayList<>());

        String[] periodos = {"ANUAL 2024", "1C 2024", "2C 2024", "ANUAL 2025", "1C 2025", "2C 2025", "ANUAL 2026", "1C 2026" , "2C 2026" };
        for (String periodo : periodos) {
            periodoComboBox.addItem(periodo);
        }
        String[] tipos = {"Multimedios", "Informatica", "Sin Recursos Adicionales"};
        for (String tipo : tipos) {
            tipoAulaComboBox.addItem(tipo);
        }

        GestorAsignaturas gestorAsignaturas = new GestorAsignaturas();
        List<String> asignaturas = gestorAsignaturas.getAsignaturas();
        for (String asignatura : asignaturas) {
            inputAsignatura.addItem(asignatura);
        }

        GestorDocente gestorDocente = new GestorDocente();
        List<String> docentes = gestorDocente.getDocentes();
        for (String docente : docentes) {
            inputDocente.addItem(docente);
        }

        String[] columnas = {"Dia", "Horario Inicio", "Horario Fin"};
        DefaultTableModel modeloTablaDiasReserva = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        agregarDiaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!inputCantidadAlumnos.getText().equals("") && !inputCorreo.getText().equals("")) {
                    try {
                        validarDatos();

                        reservaDTO.setCantAlumnos(Integer.parseInt(inputCantidadAlumnos.getText()));
                        reservaDTO.setAsignatura(inputAsignatura.getSelectedIndex());
                        reservaDTO.setIdDocente(inputDocente.getSelectedIndex());
                        reservaDTO.setIdPeriodo(periodoComboBox.getSelectedIndex() + 1L);
                        reservaDTO.setTipoAula(retornarTipoAula());
                        reservaDTO.setCorreoContacto(inputCorreo.getText());
                        reservaDTO.setNombreUsuario(usuario);


                        AgregarDia ad = new AgregarDia(ReservaPeriodica.this, modeloTablaDiasReserva, reservaDTO);
                        ad.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosed(java.awt.event.WindowEvent windowEvent) {

                            }
                        });
                    }catch (DatosException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error de Validación", JOptionPane.ERROR_MESSAGE);
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "Complete todos los campos, por favor.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
                }
            }

        });

        tablaDiasReserva.setModel(modeloTablaDiasReserva);

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
                try {

                    gr.registrarReserva(reservaDTO);

                    JOptionPane.showMessageDialog(ReservaPeriodica.super.rootPane, "Reserva creada con exito", "Exito", JOptionPane.INFORMATION_MESSAGE);
                    dispose();

                } catch (HorarioException | DuracionException | FechaException | DatosException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error de Validación", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private String retornarTipoAula(){
        Integer index = tipoAulaComboBox.getSelectedIndex();
        return index.toString();
    };

    public void validarDatos() throws DatosException {

        // Cant alumnos es un número
        if (!inputCantidadAlumnos.getText().matches("\\d+")) {
            throw new DatosException("La cantidad de alumnos debe ser un número entero.");
        }
        // Formato de email
        if (!inputCorreo.getText().matches("[A-ZÑÁÉÍÓÚa-zñáéíóú0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}")) {
            throw new DatosException("El correo electrónico debe tener un formato válido, como ejemplo@dominio.com.");
        }

    }


}
