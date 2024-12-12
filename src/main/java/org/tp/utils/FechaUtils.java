package org.tp.utils;

import org.tp.dto.FechaDTO;
import org.tp.entity.Fecha;

import java.util.ArrayList;
import java.util.List;

public class FechaUtils {

    public static List<Integer> convertirHoras (String horarioInicio, Fecha f) {

        ArrayList<Integer> valores = new ArrayList<>();

        Integer horaInicioF = Integer.parseInt(f.getHorarioInicio().substring(0,2));
        Integer minutosInicioF = Integer.parseInt(f.getHorarioInicio().substring(f.getHorarioInicio().length()-2));
        Integer horarioInicioF = horaInicioF*60 + minutosInicioF;
        Integer duracionF = f.getDuracion();
        Integer horarioFinF = duracionF + horarioInicioF;

        valores.add(horarioInicioF);
        valores.add(horarioFinF);

        return valores;
    }

    public static List<Integer> convertirHoras (String horarioInicio, FechaDTO f) {

        ArrayList<Integer> valores = new ArrayList<>();

        Integer horaInicioF = Integer.parseInt(f.getHorarioInicio().substring(0,2));
        Integer minutosInicioF = Integer.parseInt(f.getHorarioInicio().substring(f.getHorarioInicio().length()-2));
        Integer horarioInicioF = horaInicioF*60 + minutosInicioF;
        Integer duracionF = f.getDuracion();
        Integer horarioFinF = duracionF + horarioInicioF;

        valores.add(horarioInicioF);
        valores.add(horarioFinF);

        return valores;
    }

}
