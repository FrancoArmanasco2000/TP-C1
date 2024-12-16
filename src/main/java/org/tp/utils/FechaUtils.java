package org.tp.utils;

import org.tp.dto.FechaDTO;
import org.tp.entity.Fecha;

import java.util.ArrayList;
import java.util.List;

public class FechaUtils {


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

    }

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
