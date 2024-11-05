package org.tp.excepciones;

public class ContraseniasNoCoincidenException extends Exception {
    public ContraseniasNoCoincidenException() {
        super("Las contraseñas no coinciden.");
    }
}
