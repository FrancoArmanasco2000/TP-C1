package org.tp.interfaces;

import org.tp.dto.ReservaDTO;
import org.tp.dto.FechaDTO;
import org.tp.gestores.GestorAula;
import org.tp.utils.FechaUtils;
import org.tp.utils.TipoAula;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class AsignarAula extends JFrame{
    private JTable tablaAsignarAula;
    private JPanel asignarAulaPanel;
    private JButton asignarButton;
    private JButton cancelarButton;
    private static GestorAula gestorAula = new GestorAula();

    public AsignarAula(/*ReservaDTO reservaDTO, LocalDate fecha*/) {
        this.setContentPane(asignarAulaPanel);
        this.setTitle("Asignar Aula");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        /*FechaDTO fechaDTO = new FechaDTO();
        fechaDTO.setFecha(fecha);
        //if Es Periodica?
        List<FechaDTO> listaFechaDTO = FechaUtils.crearListaFechas(reservaDTO, fecha);
        //else List<FechaDTO> listaFechaDTO = new ArrayList<>;
        listaFechaDTO.add(fechaDTO);



        //Consultar la disponibilidad de la fecha
        List<ReservaDTO> listaReservasSolapadas = gestorAula.obtenerDisponibilidadAulas(listaFechaDTO, reservaDTO);
        for (ReservaDTO reserva : listaReservasSolapadas) {
        SwingUtilities.invokeLater(() -> {
            ReservasSolapadas dialog = new ReservasSolapadas(reserva);
            dialog.setModal(true); // Hace que el JDialog sea modal
            dialog.setVisible(true); // Bloquea hasta que el usuario cierre la ventana
        });
        }*/

        cancelarButton.addActionListener(e -> dispose());
    }
}
