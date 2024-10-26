package org.tp.gestores;

public class GestorPoliticas {

    public GestorPoliticas() {}

    public String comprobarTODO(String password) {
        StringBuilder respuesta = new StringBuilder();

        String longitud = comprobarLongitud(password);
        if (!longitud.isEmpty()) {
            respuesta.append(longitud).append("\n");
        }

        String caracteres = comprobarCaracteres(password);
        if (!caracteres.isEmpty()) {
            respuesta.append(caracteres).append("\n");
        }

        String digitos = comprobarDigitos(password);
        if (!digitos.isEmpty()) {
            respuesta.append(digitos).append("\n");
        }

        String mayusculas = comprobarMayusculas(password);
        if (!mayusculas.isEmpty()) {
            respuesta.append(mayusculas).append("\n");
        }

        // Eliminar el último salto de línea si existe
        if (respuesta.length() > 0 && respuesta.charAt(respuesta.length() - 1) == '\n') {
            respuesta.deleteCharAt(respuesta.length() - 1);
        }

        return respuesta.toString();
    }


    public String comprobarLongitud(String password) {
        if(password.length() < 8)
        return "Longitud mínima 8 caracteres.";
        else{
            return "";
        }
    }

    public String comprobarCaracteres(String password) {
        if(password.matches(".*[@#$%&*].*")){
            return "";
        }
        else{
            return "No incluye caracteres especiales.";
        }
    }

    public String comprobarMayusculas(String password) {
        if(password.matches(".*[A-Z].*")){
            return "";
        }
        else{
            return "No incluye una mayuscula.";
        }

    }

    public String comprobarDigitos(String password) {
        if(password.matches(".*[0-9].*")){
            return "";
        }
        else{
            return "No incluye digitos.";
        }
    }
}
