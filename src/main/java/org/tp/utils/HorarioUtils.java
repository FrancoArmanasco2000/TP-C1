package org.tp.utils;

public class HorarioUtils {

    public static boolean noEsHorarioValido(String horario) {
        if (horario == null || !horario.matches("\\d{2}:\\d{2}")) {
            return true;
        }

        String[] partes = horario.split(":");
        int horas = Integer.parseInt(partes[0]);
        int minutos = Integer.parseInt(partes[1]);

        return horas < 0 || horas > 23 || minutos < 0 || minutos > 59;
    }

    public static boolean noEsDuracionValida(String horario) {
        String[] partes = horario.split(":");
        int horas = Integer.parseInt(partes[0]);
        int minutos = Integer.parseInt(partes[1]);

        return !(minutos % 30 == 0);
    }

    public static Integer calcularDuracion (String horarioInicio, String horarioFin) {
        Integer horaInicio = Integer.parseInt(horarioInicio.substring(0,2));
        Integer horaFin = Integer.parseInt(horarioFin.substring(0,2));
        Integer minutosInicio = Integer.parseInt(horarioInicio.substring(3,5));
        Integer minutosFin = Integer.parseInt(horarioFin.substring(3,5));
        return (horaFin - horaInicio) * 60 + (minutosFin - minutosInicio);
    }
}
