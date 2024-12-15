package org.tp.gestores;

import org.tp.dao.PeriodoDAO;
import org.tp.dto.FechaDTO;
import org.tp.dto.ReservaDTO;
import org.tp.entity.Periodo;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestorAula {
    List<FechaDTO> crearListaFechas(ReservaDTO reserva, FechaDTO fecha) {
        List<FechaDTO> listaFechasDTO = new ArrayList<>();
        PeriodoDAO periodoDAO = new PeriodoDAO();
        if(fecha.getDia().isEmpty()) {
            listaFechasDTO.add(fecha);
        }else{
            Periodo periodo = periodoDAO.getPeriodoById(reserva.getIdPeriodo());
            LocalDate fechaInicio = periodo.getFechaInicio();
            LocalDate fechaFin = periodo.getFechaFin();
            DayOfWeek diaDeLaSemana = convertirTextoADayOfWeek(fecha.getDia());
            LocalDate fechaI = fechaInicio;
            while (!fechaI.isAfter(fechaFin)) {
                if (fechaI.getDayOfWeek() == diaDeLaSemana) {
                    FechaDTO fechaDTO = new FechaDTO();
                    fechaDTO.setFecha(fechaI);
                    fechaDTO.setHorarioInicio(fecha.getHorarioInicio());
                    fechaDTO.setDuracion(fecha.getDuracion());
                    fechaDTO.setDia(fecha.getDia());
                    listaFechasDTO.add(fechaDTO);
                }
                fechaI = fechaI.plusDays(1);
            }

        }
        return listaFechasDTO;
    }
    private DayOfWeek convertirTextoADayOfWeek(String diaTexto) {
        return switch (diaTexto) {
            case "Lunes" -> DayOfWeek.MONDAY;
            case "Martes" -> DayOfWeek.TUESDAY;
            case "Miércoles" -> DayOfWeek.WEDNESDAY;
            case "Jueves" -> DayOfWeek.THURSDAY;
            case "Viernes" -> DayOfWeek.FRIDAY;
            default -> throw new IllegalArgumentException("El día " + diaTexto + " no es válido");
        };
    }
}
