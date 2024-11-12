package org.tp.excepciones;

public class UsuarioYaRegistradoException extends Exception {
    public UsuarioYaRegistradoException() {
        super("El idUsuario ya se encuentra registrado.");
    }
}

