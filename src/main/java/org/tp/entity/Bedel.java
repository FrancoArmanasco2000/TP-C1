package org.tp.entity;

import jakarta.persistence.*;
import org.tp.dto.BedelDTO;

@Entity
public class Bedel extends Usuario {

    private String nombre;
    private String apellido;
    private String turno;
    private Boolean borrado;

    public Bedel(String usuario, String contrasenia ,String nombre, String apellido, String turno, Boolean borrado) {
        super(usuario, contrasenia);
        this.nombre = nombre;
        this.apellido = apellido;
        this.turno = turno;
        this.borrado = borrado;
    }

    public Bedel(){
        super();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(Boolean borrado) {
        this.borrado = borrado;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void modificarBedel (BedelDTO bedelDTO) {
        this.setContrasena(bedelDTO.getContrasenia());
        this.setNombre(bedelDTO.getNombre());
        this.setApellido(bedelDTO.getApellido());
        this.setTurno(bedelDTO.getTurno());
        this.setUsuario(bedelDTO.getUsuario());
    }

}