package org.tp.entity;

import jakarta.persistence.*;
import org.tp.entity.PKSCompuestas.PkFecha;

import java.time.LocalDate;

@Entity
@Table (name = "fecha")
@IdClass(PkFecha.class)
public class Fecha {

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

    public Fecha() {
    }

    public Fecha(Aula aula, LocalDate fecha, String horarioInicio, Reserva reserva) {
        this.aula = aula;
        this.fecha = fecha;
        this.horarioInicio = horarioInicio;
        this.reserva = reserva;
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
