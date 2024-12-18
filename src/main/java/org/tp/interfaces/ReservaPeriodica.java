package org.tp.interfaces;

import org.tp.dao.ReservaDAO;
import org.tp.dto.FechaDTO;
import org.tp.dto.ReservaDTO;
import org.tp.gestores.GestorReserva;
import org.tp.utils.InterfazUtils;
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
    private ReservaDTO reservaDTO;

    public ReservaPeriodica(String usuario) {
        this.setTitle("Reserva Periódica");
        this.setContentPane(this.reservaPeriodicaPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(500, 600);
        this.setResizable(false); // NO MODIFICA LA PESTAÑA
        this.setLocationRelativeTo(null); // APARECE EN EL MEDIO
        this.setVisible(true);
        String[] periodos = {"ANUAL 2024", "1C 2024", "2C 2024", "ANUAL 2025", "1C 2025", "2C 2025", "ANUAL 2026", "1C 2026" , "2C 2026" };
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
                if (!(inputCantidadAlumnos.getText().isEmpty() || inputNombreApellido.getText().isEmpty() || inputAsignatura.getText().isEmpty() || inputCorreo.getText().isEmpty())
                        && InterfazUtils.validarDatos(inputCantidadAlumnos.getText(),inputNombreApellido.getText(),inputAsignatura.getText(),inputCorreo.getText())) {
                    ReservaPeriodica.this.setEnabled(false);
                    if(reservaDTO == null){
                        reservaDTO = new ReservaDTO(
                                retornarPeriodo(),
                                Integer.parseInt(inputCantidadAlumnos.getText()),
                                InterfazUtils.retornarTipoAula(tipoAulaComboBox.getSelectedIndex()),
                                inputNombreApellido.getText(),
                                inputAsignatura.getText(),
                                inputCorreo.getText(),
                                usuario
                        );
                    }
                    AgregarDia ad = new AgregarDia(ReservaPeriodica.this, tablaDiasReserva, reservaDTO);
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
                //System.out.println(reservaDTO.toString());
                GestorReserva gr = new GestorReserva();
                gr.RegistrarReserva(reservaDTO);
            }
        });
    }



    public Long retornarPeriodo(){
        return periodoComboBox.getSelectedIndex()+1L;
    };




}
