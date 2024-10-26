package org.tp.gestores;

public class GestorPoliticas {

    public GestorPoliticas() {}

    public boolean comprobarTODO(String password) {
        return comprobarLongitud(password) && comprobarCaracteres(password) && comprobarDigitos(password) && comprobarMayusculas(password);
    }

    public boolean comprobarLongitud(String password) {
        return password.length() >= 8 && password.length() <= 24;
    }

    public boolean comprobarCaracteres(String password) {
        return password.matches(".*[@#$%&*].*");
    }

    public boolean comprobarMayusculas(String password) {
        return password.matches(".*[A-Z].*");
    }

    public boolean comprobarDigitos(String password) {
        return password.matches(".*[0-9].*");
    }
}
