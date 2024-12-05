package org.tp.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Columns;

@Entity
@Table(name="Reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private Long idReserva;
    @Column
    private Integer cantidadAlumnos;
    @Column
    private String tipoAula;
    @Column
    private Integer idDocente;
    @Column
    private Integer idCurso;
    @Column
    private String correoContacto;
    @ManyToOne
    @JoinColumn(name="idPeriodo")
    private Periodo idPeriodo;
    @ManyToOne
    @JoinColumn(name="idUsuario")
    private Bedel idUsuario;

    public Reserva() {
    }

    public Reserva(Integer cantidadAlumnos, String tipoAula, Bedel idUsuario, Long idReserva, Periodo idPeriodo, Integer idDocente, Integer idCurso, String correoContacto) {
        this.cantidadAlumnos = cantidadAlumnos;
        this.tipoAula = tipoAula;
        this.idUsuario = idUsuario;
        this.idReserva = idReserva;
        this.idPeriodo = idPeriodo;
        this.idDocente = idDocente;
        this.idCurso = idCurso;
        this.correoContacto = correoContacto;
    }

    public String getCorreoContacto() {
        return correoContacto;
    }

    public void setCorreoContacto(String correoContacto) {
        this.correoContacto = correoContacto;
    }

    public Integer getCantidadAlumnos() {
        return cantidadAlumnos;
    }

    public void setCantidadAlumnos(Integer cantidadAlumnos) {
        this.cantidadAlumnos = cantidadAlumnos;
    }

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public Integer getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(Integer idDocente) {
        this.idDocente = idDocente;
    }

    public Periodo getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Periodo idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public Long getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Long idReserva) {
        this.idReserva = idReserva;
    }

    public Bedel getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Bedel idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTipoAula() {
        return tipoAula;
    }

    public void setTipoAula(String tipoAula) {
        this.tipoAula = tipoAula;
    }
}
