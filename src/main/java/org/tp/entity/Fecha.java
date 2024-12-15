package org.tp.entity;

import jakarta.persistence.*;
import org.tp.entity.PKSCompuestas.PkFecha;
import org.tp.utils.FechaInterface;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table (name = "fecha")
@IdClass(PkFecha.class)
public class Fecha implements FechaInterface{

    @Id
    private LocalDate fecha;
    @Id
    private String horarioInicio;
    @Id
    @ManyToOne
    @JoinColumn(name="idReserva")
    private Reserva reserva;
    @Id
    @ManyToOne
    @JoinColumn(name="idAula")
    private Aula aula;
    private String dia;
    private Integer duracion;

    public Fecha() {
    }

    public Fecha(Aula aula, LocalDate fecha, String horarioInicio, Reserva reserva, String dia, Integer duracion) {
        this.aula = aula;
        this.fecha = fecha;
        this.horarioInicio = horarioInicio;
        this.reserva = reserva;
        this.dia = dia;
        this.duracion = duracion;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    @Override
    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    @Override
    public String getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(String horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }


}
