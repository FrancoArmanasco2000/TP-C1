package org.tp.entity;

import jakarta.persistence.*;
import org.tp.utils.TipoAula;

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
    private TipoAula tipoAula;
    @Column
    private Long idDocente;
    @Column
    private Long idCurso;
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

    public Reserva(Integer cantidadAlumnos, TipoAula tipoAula, Bedel idUsuario, Long idReserva, Periodo idPeriodo, Long idDocente, Long idCurso, String correoContacto) {
        this.cantidadAlumnos = cantidadAlumnos;
        this.tipoAula = tipoAula;
        this.idUsuario = idUsuario;
        this.idReserva = idReserva;
        this.idPeriodo = idPeriodo;
        this.idDocente = idDocente;
        this.idCurso = idCurso;
        this.correoContacto = correoContacto;
    }

    public void setCorreoContacto(String correoContacto) {
        this.correoContacto = correoContacto;
    }

    public void setCantidadAlumnos(Integer cantidadAlumnos) {
        this.cantidadAlumnos = cantidadAlumnos;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }

    public void setIdDocente(Long idDocente) {
        this.idDocente = idDocente;
    }

    public void setIdPeriodo(Periodo idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public Long getIdReserva() {
        return idReserva;
    }

    public Bedel getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Bedel idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setTipoAula(TipoAula tipoAula) {
        this.tipoAula = tipoAula;
    }

}
