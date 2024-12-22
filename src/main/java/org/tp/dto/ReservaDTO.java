package org.tp.dto;

import org.tp.utils.TipoAula;

import java.time.LocalDate;
import java.util.List;

public class ReservaDTO {
    private Long idReserva;
    private Long idPeriodo;
    private Integer cantAlumnos;
    private String tipoAula;
    private Integer idDocente;
    private Integer idCurso;
    private String correoContacto;
    private List<FechaDTO> listaFechasDTO;
    private Long idUsuario;
    private Long idAula;
    private LocalDate fecha;
    private String horarioInicio;
    private int duracion;
    private String nombreUsuario;

    public ReservaDTO() {}

    public ReservaDTO(String nombreUsuario, Long idReserva, int duracion, String horarioInicio, LocalDate fecha, Long idAula, Long idUsuario, List<FechaDTO> listaFechasDTO, String correoContacto, Integer idCurso, Integer idDocente, String tipoAula, Integer cantAlumnos, Long idPeriodo) {
        this.nombreUsuario = nombreUsuario;
        this.idReserva = idReserva;
        this.duracion = duracion;
        this.horarioInicio = horarioInicio;
        this.fecha = fecha;
        this.idAula = idAula;
        this.idUsuario = idUsuario;
        this.listaFechasDTO = listaFechasDTO;
        this.correoContacto = correoContacto;
        this.idCurso = idCurso;
        this.idDocente = idDocente;
        this.tipoAula = tipoAula;
        this.cantAlumnos = cantAlumnos;
        this.idPeriodo = idPeriodo;
    }

    public ReservaDTO (Long idReserva, Integer cantAlumnos, String correoContacto, String horarioInicio, Integer duracion, Long idAula,  LocalDate fecha) {
        this.idReserva = idReserva;
        this.cantAlumnos = cantAlumnos;
        this.horarioInicio = horarioInicio;
        this.duracion = duracion;
        this.idAula = idAula;
        this.fecha = fecha;
        this.correoContacto = correoContacto;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }


    public Long getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Long idReserva) {
        this.idReserva = idReserva;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
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

    public Long getIdAula() {
        return idAula;
    }

    public void setIdAula(Long idAula) {
        this.idAula = idAula;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<FechaDTO> getListaFechasDTO() {
        return listaFechasDTO;
    }

    public void setListaFechasDTO(List<FechaDTO> listaFechasDTO) {
        this.listaFechasDTO = listaFechasDTO;
    }

    public String getCorreoContacto() {
        return correoContacto;
    }

    public void setCorreoContacto(String correoContacto) {
        this.correoContacto = correoContacto;
    }

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setAsignatura(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public Integer getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(Integer idDocente) {
        this.idDocente = idDocente;
    }

    public String getTipoAula() {
        return tipoAula;
    }

    public void setTipoAula(String tipoAula) {
        this.tipoAula = tipoAula;
    }

    public Integer getCantAlumnos() {
        return cantAlumnos;
    }

    public void setCantAlumnos(Integer cantAlumnos) {
        this.cantAlumnos = cantAlumnos;
    }

    public Long getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Long idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    @Override
    public String toString() {
        return "ReservaDTO{" +
                "idReserva=" + idReserva +
                ", idPeriodo=" + idPeriodo +
                ", cantAlumnos=" + cantAlumnos +
                ", tipoAula='" + tipoAula + '\'' +
                ", idDocente=" + idDocente +
                ", idCurso=" + idCurso +
                ", correoContacto='" + correoContacto + '\'' +
                ", listaFechasDTO=" + listaFechasDTO +
                ", idUsuario=" + idUsuario +
                ", idAula=" + idAula +
                ", fecha=" + fecha +
                ", horarioInicio='" + horarioInicio + '\'' +
                ", duracion=" + duracion +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                '}';
    }

    public String getHorarioFin(){
        int horaInicioF = Integer.parseInt(horarioInicio.substring(0, 2));
        int minutosInicioF = Integer.parseInt(horarioInicio.substring(horarioInicio.length() - 2));
        int horarioInicioEnMinutos = horaInicioF * 60 + minutosInicioF;
        int horarioFinEnMinutos = horarioInicioEnMinutos + duracion;
        int horaFin = (horarioFinEnMinutos / 60) % 24; // Asegurarse de que las horas no excedan 24
        int minutosFin = horarioFinEnMinutos % 60;

        return String.format("%02d:%02d", horaFin, minutosFin);
    }
}
