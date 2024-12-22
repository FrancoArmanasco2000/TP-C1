package org.tp.entity;

import jakarta.persistence.*;
import org.tp.dto.BedelDTO;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Bedel extends Usuario {

    private String nombre;
    private String apellido;
    private String turno;
    private Boolean borrado;

    @OneToMany(mappedBy = "bedel", fetch = FetchType.EAGER)
    private List<Reserva> reservas;

    public Bedel(String usuario, String contrasenia ,String nombre, String apellido, String turno, Boolean borrado) {
        super(usuario, contrasenia);
        this.nombre = nombre;
        this.apellido = apellido;
        this.turno = turno;
        this.borrado = borrado;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
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

    @Override
    public String toString() {
        return "Bedel{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", turno='" + turno + '\'' +
                ", borrado=" + borrado +
                ", reservas=" + reservas +
                '}';
    }

    public void modificarBedel (BedelDTO bedelDTO) {
        this.setContrasena(bedelDTO.getContrasenia());
        this.setNombre(bedelDTO.getNombre());
        this.setApellido(bedelDTO.getApellido());
        this.setTurno(bedelDTO.getTurno());
        this.setUsuario(bedelDTO.getUsuario());
    }

}