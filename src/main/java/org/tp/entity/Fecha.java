package org.tp.entity;

import jakarta.persistence.*;
import org.tp.utils.FechaInterface;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class Fecha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFecha;
    private Date fecha;
    private Integer horario;
    private Integer duracion;
    @ManyToOne
    @JoinColumn(name = "idReserva")
    private Reserva reserva;
    @ManyToOne
    @JoinColumn(name = "idAula")
    private Aula aula;




    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }

    public Long getIdFecha() {
        return idFecha;
    }

    public void setIdFecha(Long idFecha) {
        this.idFecha = idFecha;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public Integer getHorario() {
        return horario;
    }

    public void setHorario(Integer horario) {
        this.horario = horario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
