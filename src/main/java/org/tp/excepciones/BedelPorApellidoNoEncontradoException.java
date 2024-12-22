package org.tp.excepciones;

public class BedelPorApellidoNoEncontradoException extends Exception {

    public BedelPorApellidoNoEncontradoException() {
        super("No existe bedel con ese apellido.");
    }

}
