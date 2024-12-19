package org.tp.dto;

public class BedelDTO {

    private Long idUsuario;
    private String nombre;
    private String apellido;
    private String usuario;
    private String contrasenia;
    private Boolean borrado;
    private String turno;

    public BedelDTO(){}

    public BedelDTO(String nombre, String apellido, String usuario, String contrasenia, String turno) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.turno = turno;
        this.borrado = false;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Boolean getBorrado() {
        return borrado;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
