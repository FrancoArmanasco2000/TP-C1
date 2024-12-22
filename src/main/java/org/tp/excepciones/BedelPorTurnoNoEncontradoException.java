package org.tp.excepciones;

public class BedelPorTurnoNoEncontradoException extends Exception {

    public BedelPorTurnoNoEncontradoException() {
        super("No existe bedel con ese turno.");
    }

}
