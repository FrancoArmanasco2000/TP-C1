package org.tp.excepciones;

public class DuracionException extends Exception {

    public DuracionException(String msg) {
        super(msg + "\n" + "Debe ser multiplo de 30.");
    }


}
