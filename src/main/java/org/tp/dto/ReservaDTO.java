package org.tp.dto;

import org.tp.utils.TipoAula;

import java.util.List;

public class ReservaDTO {
    private Long idReserva;
    private Long idPeriodo;
    private int cantAlumnos;
    private TipoAula tipoAula;
    private int idDocente;
    private int idCurso;
    private String correoContacto;
    private String actAcademica;
    private List<FechaDTO> listaFechasDTO;
    private Long idUsuario;

    public ReservaDTO(Long idPeriodo, int cantAlumnos, TipoAula tipoAula, int idCurso, int idDocente, String actAcademica, String correoContacto, Long idUsuario, List<FechaDTO> listaFechasDTO) {
        this.idPeriodo = idPeriodo;
        this.cantAlumnos = cantAlumnos;
        this.tipoAula = tipoAula;
        this.idCurso = idCurso;
        this.idDocente = idDocente;
        this.actAcademica = actAcademica;
        this.correoContacto = correoContacto;
        this.idUsuario = idUsuario;
        this.listaFechasDTO = listaFechasDTO;
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



    public List<FechaDTO> getListaFechasDTO() {
        return listaFechasDTO;
    }

    public void setListaFechasDTO(List<FechaDTO> listaFechasDTO) {
        this.listaFechasDTO = listaFechasDTO;
    }

    public String getActAcademica() {
        return actAcademica;
    }

    public void setActAcademica(String actAcademica) {
        this.actAcademica = actAcademica;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public int getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(int idDocente) {
        this.idDocente = idDocente;
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
}
