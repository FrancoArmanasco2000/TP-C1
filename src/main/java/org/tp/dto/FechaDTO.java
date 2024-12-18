package org.tp.dto;

import java.time.LocalDate;
import org.tp.utils.FechaInterface;

public class FechaDTO implements FechaInterface {
    private LocalDate fecha;
    private String horarioInicio;
    private Integer duracion;
    private String dia;
    private Long idAula;

    public FechaDTO(){};

    public FechaDTO(String dia, Integer duracion, String horarioInicio) {
        this.dia = dia;
        this.duracion = duracion;
        this.horarioInicio = horarioInicio;
    }

    public FechaDTO(LocalDate fecha, String horarioInicio, Integer duracion, String dia) {
        this.fecha = fecha;
        this.horarioInicio = horarioInicio;
        this.duracion = duracion;
        this.dia = dia;
    }

    public Long getIdAula() {
        return idAula;
    }

    public void setIdAula(Long idAula) {
        this.idAula = idAula;
    }

    @Override
    public String toString() {
        return "FechaDTO{" +
                "fecha=" + fecha +
                ", horarioInicio='" + horarioInicio + '\'' +
                ", duracion=" + duracion +
                ", dia='" + dia + '\'' +
                ", idAula=" + idAula +
                '}';
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public String getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(String horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    @Override
    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }
}
