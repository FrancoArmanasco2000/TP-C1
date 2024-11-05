package org.tp.excepciones;

public class ContraseniaInvalidaException extends Exception {
    public ContraseniaInvalidaException(String mensaje) {
        super(mensaje);
    }
}
