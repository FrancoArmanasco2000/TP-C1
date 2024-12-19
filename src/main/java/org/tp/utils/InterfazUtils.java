package org.tp.utils;

import javax.swing.*;

public class InterfazUtils {
    public static boolean validarDatos(String cantidadAlumnosTexto, String nombreApellidoTexto, String asignaturaTexto, String correoTexto) {
        // Cant alumnos es un número

        if (!cantidadAlumnosTexto.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "La cantidad de alumnos debe ser un número entero.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // Nombre y Apellido invalido

        if (!nombreApellidoTexto.matches("[A-ZÑÁÉÍÓÚa-záéíóú]+\\s[A-ZÑÁÉÍÓÚa-záéíóú]+")) {
            JOptionPane.showMessageDialog(null, "El campo Apellido y Nombre debe tener formato: Apellido Nombre.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // Asignatura tiene que ser una sola palabra

        if (!asignaturaTexto.matches("[A-ZÑÁÉÍÓÚa-zñáéíóú]*")) {
            JOptionPane.showMessageDialog(null, "El campo Asignatura debe ser una sola palabra.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // Formato de email

        if (!correoTexto.matches("[A-ZÑÁÉÍÓÚa-zñáéíóú0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}")) {
            JOptionPane.showMessageDialog(null, "El correo electrónico debe tener un formato válido, como ejemplo@dominio.com.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public static TipoAula retornarTipoAula(int tipoAulaIndice) {
        return switch (tipoAulaIndice) {
            case 0 -> TipoAula.MULTIMEDIO;
            case 1 -> TipoAula.INFORMATICA;
            case 2 -> TipoAula.SIN_RECURSOS;
            default -> null;
        };
    };

}
