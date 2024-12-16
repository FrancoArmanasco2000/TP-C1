package org.tp.utils;

import org.tp.dao.PeriodoDAO;
import org.tp.dto.FechaDTO;
import org.tp.dto.ReservaDTO;
import org.tp.entity.Fecha;
import org.tp.entity.Periodo;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FechaUtils {

    public static List<FechaDTO> crearListaFechas(ReservaDTO reserva, FechaDTO fecha) {  //Crea una lista de fechasDTO segun el dia de fecha y el periodo de reserva

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

    private static DayOfWeek convertirTextoADayOfWeek(String diaTexto) {
        return switch (diaTexto) {
            case "Lunes" -> DayOfWeek.MONDAY;
            case "Martes" -> DayOfWeek.TUESDAY;
            case "Miércoles" -> DayOfWeek.WEDNESDAY;
            case "Jueves" -> DayOfWeek.THURSDAY;
            case "Viernes" -> DayOfWeek.FRIDAY;
            default -> throw new IllegalArgumentException("El día " + diaTexto + " no es válido");
        };
    }


    public static List<Integer> convertirHoras(String horarioInicio, int duracion) {
        ArrayList<Integer> valores = new ArrayList<>();
        Integer horaInicioF = Integer.parseInt(horarioInicio.substring(0, 2));
        Integer minutosInicioF = Integer.parseInt(horarioInicio.substring(horarioInicio.length() - 2));
        Integer horarioInicioEnMinutos = horaInicioF * 60 + minutosInicioF;

        Integer horarioFinF = horarioInicioEnMinutos + duracion;

        valores.add(horarioInicioEnMinutos);
        valores.add(horarioFinF);

        return valores;
    }

    /* Viejo
    public static List<Integer> convertirHoras (String horarioInicio, FechaInterface f) {

        ArrayList<Integer> valores = new ArrayList<>();

        Integer horaInicioF = Integer.parseInt(f.getHorarioInicio().substring(0,2));
        Integer minutosInicioF = Integer.parseInt(f.getHorarioInicio().substring(f.getHorarioInicio().length()-2));
        Integer horarioInicioF = horaInicioF*60 + minutosInicioF;
        Integer duracionF = f.getDuracion();
        Integer horarioFinF = duracionF + horarioInicioF;

        valores.add(horarioInicioF);
        valores.add(horarioFinF);

        return valores;

    }*/

    public static double calcularSolapamiento(List<Integer> horario1, List<Integer> horario2) {
        int inicioSolapamiento = Math.max(horario1.getFirst(), horario2.get(0));
        int finSolapamiento = Math.min(horario1.getFirst(), horario2.get(1));

        if (inicioSolapamiento < finSolapamiento) {
            return finSolapamiento - inicioSolapamiento;
        } else {
            return 0.0;
        }
    }

    public static boolean solapa(List<Integer> horariosA, List<Integer> horariosB) {
        return horariosA.get(0) < horariosB.get(1) && horariosA.get(1) > horariosB.get(0);
    }

}
