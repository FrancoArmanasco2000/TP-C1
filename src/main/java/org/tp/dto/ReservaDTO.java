package org.tp.dto;

import java.util.List;

public class ReservaDTO {
    private int idReserva;
    private int idCliente;
    private int idPeriodo;
    private int cantAlumnos;
    private String tipoAula;
    private int idDocente;
    private int idCurso;
    private String actAcademica;
    private List<FechaDTO> listaFechasDTO;
    private List<DiaDTO> listaDiasDTO;

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public List<DiaDTO> getListaDiasDTO() {
        return listaDiasDTO;
    }

    public void setListaDiasDTO(List<DiaDTO> listaDiasDTO) {
        this.listaDiasDTO = listaDiasDTO;
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

    public String getTipoAula() {
        return tipoAula;
    }

    public void setTipoAula(String tipoAula) {
        this.tipoAula = tipoAula;
    }

    public int getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(int idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
}
