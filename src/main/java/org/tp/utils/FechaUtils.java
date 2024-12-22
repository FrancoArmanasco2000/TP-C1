package org.tp.utils;

import org.tp.dao.PeriodoDAO;
import org.tp.dto.FechaDTO;
import org.tp.dto.ReservaDTO;
import org.tp.entity.Periodo;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FechaUtils {

   public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");






    public static double calcularSolapamiento(List<Integer> horariosA, List<Integer> horariosB) {
        if (horariosA.isEmpty() || horariosB.isEmpty()) {
            return 0;
        }

        int inicioSolapamiento = Math.max(horariosA.get(0), horariosB.get(0));
        int finSolapamiento = Math.min(horariosA.get(horariosA.size() - 1), horariosB.get(horariosB.size() - 1));

        int duracionSolapada = finSolapamiento - inicioSolapamiento;

        if (duracionSolapada <= 0) {
            return 0;
        }
        return duracionSolapada;
    }



    public static void configurarFormatoFecha(JFormattedTextField fechaTextField) {
        try {
            MaskFormatter mascara = new MaskFormatter("##/##/####");
            mascara.setPlaceholderCharacter('_');
            fechaTextField.setFormatterFactory(new DefaultFormatterFactory(mascara));
        } catch (ParseException e) {
            throw new RuntimeException("Error al configurar el formato de fecha: " + e.getMessage(), e);
        }
    }

    public static boolean esFechaValida(String textoFecha) {

        try {
            LocalDate.parse(textoFecha, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static LocalDate convertirTextoALocalDate(String textoFecha) {
        try {
            return LocalDate.parse(textoFecha, formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public static String obtenerDiaDeLaSemana(LocalDate fecha) {
        if(fecha != null) {
            String dia = fecha.getDayOfWeek().getDisplayName(java.time.format.TextStyle.FULL, new Locale("es", "ES"));
            return dia.substring(0, 1).toUpperCase() + dia.substring(1);
        }else {
            return "Desconocido";
        }

    }

    public static String convertirLocalDateATexto(LocalDate fecha) {
        return fecha.format(formatter);
    }


}
