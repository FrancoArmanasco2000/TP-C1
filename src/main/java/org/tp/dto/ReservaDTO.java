package org.tp.dto;

import org.tp.utils.TipoAula;

import java.time.LocalDate;
import java.util.List;

public class ReservaDTO {
    private Long idReserva;
    private Long idPeriodo;
    private int cantAlumnos;
    private TipoAula tipoAula;
    private String nombreDocente;
    private String asignatura;
    private String correoContacto;
    private List<FechaDTO> listaFechasDTO;
    private Long idUsuario;
    private Long idAula;
    private LocalDate fecha;
    private String horarioInicio;
    private int duracion;
    private int horasSolapadas;
    private String nombreUsuario;

    public ReservaDTO() {};


    public ReservaDTO(Long idPeriodo, int cantAlumnos, TipoAula tipoAula, String nombreDocente, String asignatura, String correoContacto, String nombreUsuario, List<FechaDTO> listaFechasDTO) {
        this.idPeriodo = idPeriodo;
        this.cantAlumnos = cantAlumnos;
        this.tipoAula = tipoAula;
        this.asignatura = asignatura;
        this.nombreDocente = nombreDocente;
        this.correoContacto = correoContacto;
        this.nombreUsuario = nombreUsuario;
        this.listaFechasDTO = listaFechasDTO;
    }

    public ReservaDTO(Long idPeriodo, int cantAlumnos, TipoAula tipoAula, String nombreDocente, String asignatura, String correoContacto, String nombreUsuario) {
        this.idPeriodo = idPeriodo;
        this.cantAlumnos = cantAlumnos;
        this.tipoAula = tipoAula;
        this.asignatura = asignatura;
        this.nombreDocente = nombreDocente;
        this.correoContacto = correoContacto;
        this.nombreUsuario = nombreUsuario;
    }

    public ReservaDTO(int cantAlumnos, TipoAula tipoAula, String nombreDocente, String asignatura, String correoContacto, String nombreUsuario) {
        this.cantAlumnos = cantAlumnos;
        this.tipoAula = tipoAula;
        this.nombreDocente = nombreDocente;
        this.asignatura = asignatura;
        this.correoContacto = correoContacto;
        this.nombreUsuario = nombreUsuario;
    }

    @Override
    public String toString() {
        return "ReservaDTO{" +
                "idReserva=" + idReserva +
                ", idPeriodo=" + idPeriodo +
                ", cantAlumnos=" + cantAlumnos +
                ", tipoAula=" + tipoAula +
                ", nombreDocente=" + nombreDocente +
                ", idCurso=" + asignatura +
                ", correoContacto='" + correoContacto + '\'' +
                ", asignatura='" + asignatura + '\'' +
                ", listaFechasDTO=" + listaFechasDTO +
                ", idUsuario=" + idUsuario +
                ", idAula=" + idAula +
                ", fecha=" + fecha +
                ", horarioInicio='" + horarioInicio + '\'' +
                ", duracion=" + duracion +
                '}';
    }


    public ReservaDTO(Long idReserva, int cantAlumnos, String correoContacto, String horarioInicio, int duracion, Long idAula, LocalDate fecha) {
        this.idReserva = idReserva;
        this.cantAlumnos = cantAlumnos;
        this.correoContacto = correoContacto;
        this.horarioInicio = horarioInicio;
        this.duracion = duracion;
        this.idAula = idAula;
        this.fecha = fecha;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCorreoContacto() {
        return correoContacto;
    }

    public void setCorreoContacto(String correoContacto) {
        this.correoContacto = correoContacto;
    }

    public Long getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Long idReserva) {this.idReserva = idReserva;}
    public List<FechaDTO> getListaFechasDTO() {
        return listaFechasDTO;
    }

    public void setListaFechasDTO(List<FechaDTO> listaFechasDTO) {
        this.listaFechasDTO = listaFechasDTO;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public String getNombreDocente() {
        return nombreDocente;
    }

    public void setNombreDocente(String nombreDocente) {
        this.nombreDocente = nombreDocente;
    }

    public int getCantAlumnos() {
        return cantAlumnos;
    }

    public void setCantAlumnos(int cantAlumnos) {
        this.cantAlumnos = cantAlumnos;
    }

    public TipoAula getTipoAula() {
        return tipoAula;
    }

    public void setTipoAula(TipoAula tipoAula) {
        this.tipoAula = tipoAula;
    }

    public Long getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Long idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public Long getIdAula() {return idAula;}

    public void setIdAula(Long idAula) {this.idAula = idAula;}

    public LocalDate getFecha() {return fecha;}

    public void setFecha(LocalDate fecha) {this.fecha = fecha;}

    public String getHorarioInicio() {return horarioInicio;}

    public void setHorarioInicio(String horarioInicio) {this.horarioInicio = horarioInicio;}

    public Integer getDuracion() {return duracion;}

    public void setDuracion(Integer duracion) {this.duracion = duracion;}

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int getHorasSolapadas(){ return horasSolapadas;}
    public String getHoraA(){ return horarioInicio;}
    public String getHoraB(){
        int horaInicioF = Integer.parseInt(horarioInicio.substring(0, 2));
        int minutosInicioF = Integer.parseInt(horarioInicio.substring(horarioInicio.length() - 2));
        int horarioInicioEnMinutos = horaInicioF * 60 + minutosInicioF;
        int horarioFinF = (horarioInicioEnMinutos + duracion)/60;
        return String.valueOf(horarioFinF);
    }
}
