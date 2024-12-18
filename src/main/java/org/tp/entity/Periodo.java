package org.tp.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "periodo")
public class Periodo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private Long idPeriodo;
    @Column
    private String nombre;
    @Column
    private LocalDate fechaInicio;
    @Column
    private LocalDate fechaFin;
    @Column
    private Integer anio;

    public Periodo() {
    }

    public Periodo(Integer anio, String nombre, Long idPeriodo, LocalDate fechaInicio, LocalDate fechaFin) {
        this.anio = anio;
        this.nombre = nombre;
        this.idPeriodo = idPeriodo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    @Override
    public String toString() {
        return "Periodo{" +
                "anio=" + anio +
                ", idPeriodo=" + idPeriodo +
                ", nombre='" + nombre + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                '}';
    }
}


