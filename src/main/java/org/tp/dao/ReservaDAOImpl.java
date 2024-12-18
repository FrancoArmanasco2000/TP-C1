package org.tp.dao;

import org.tp.dto.FechaDTO;
import org.tp.entity.Aula;
import org.tp.entity.Reserva;

import java.util.List;

public interface ReservaDAOImpl {

    void crearReserva(Reserva reserva);

    List<Aula> obtenerAulasDisponibles(List<Aula> aulasFiltradas, List<FechaDTO> fechas);
}
