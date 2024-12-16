package org.tp.dto;

import org.tp.utils.TipoAula;

public class AulaDTO {
    private String nombre;
    private String ubicacion;
    private Integer capacidad;
    private TipoAula tipo;
    private Integer nro_aula;
    private String piso;
    private Integer cantidad_pcs;
    private Boolean canion;
    private Boolean aire_acondicionado;
    private Boolean ventiladores;


    public String getNombre() {
        return nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public TipoAula getTipo() {
        return tipo;
    }

    public Integer getNro_aula() {
        return nro_aula;
    }

    public String getPiso() {
        return piso;
    }

    public Integer getCantidad_pcs() {
        return cantidad_pcs;
    }

    public Boolean getCanion() {
        return canion;
    }

    public Boolean getAire_acondicionado() {
        return aire_acondicionado;
    }

    public Boolean getVentiladores() {
        return ventiladores;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(TipoAula tipo) {
        this.tipo = tipo;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public void setNro_aula(Integer nro_aula) {
        this.nro_aula = nro_aula;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public void setCantidad_pcs(Integer cantidad_pcs) {
        this.cantidad_pcs = cantidad_pcs;
    }

    public void setCanion(Boolean canion) {
        this.canion = canion;
    }

    public void setAire_acondicionado(Boolean aire_acondicionado) {
        this.aire_acondicionado = aire_acondicionado;
    }

    public void setVentiladores(Boolean ventiladores) {
        this.ventiladores = ventiladores;
    }

}
