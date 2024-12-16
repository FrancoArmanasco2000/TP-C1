package org.tp.dao;

import org.tp.dto.FechaDTO;
import org.tp.dto.ReservaDTO;
import org.tp.entity.Aula;
import org.tp.entity.Reserva;

import java.util.List;

public interface ReservaDAOImpl {

    public void crearReserva(Reserva reserva);

    public List<Aula> obtenerAulasDisponibles(List<Aula> aulasFiltradas, List<FechaDTO> fechas);
}
