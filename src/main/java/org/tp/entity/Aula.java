package org.tp.entity;

import jakarta.persistence.*;
import org.tp.utils.TipoAula;

import java.util.List;

@Entity
public class Aula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAula;

    private String nombre;
    private String ubicacion;
    private Integer capacidad;
    private String tipo;
    private Integer piso;
    private Boolean aireAcondicionado;
    private Boolean utilidades;
    private Integer nro_aula;
    private Integer cantidad_pcs;
    private Boolean canion;
    private Boolean ventiladores;
    private String tipoPizarron;
    @OneToMany(mappedBy = "aula")
    private List<Fecha> fechas;



    public Long getIdAula() {
        return idAula;
    }

    public void setIdAula(Long idAula) {
        this.idAula = idAula;
    }

    public List<Fecha> getFechas() {
        return fechas;
    }

    public void setFechas(List<Fecha> fechas) {
        this.fechas = fechas;
    }

    public Boolean getUtilidades() {
        return utilidades;
    }

    public void setUtilidades(Boolean utilidades) {
        this.utilidades = utilidades;
    }

    public Integer getPiso() {
        return piso;
    }

    public void setPiso(Integer piso) {
        this.piso = piso;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getAireAcondicionado() {
        return aireAcondicionado;
    }

    public void setAireAcondicionado(Boolean aireAcondicionado) {
        this.aireAcondicionado = aireAcondicionado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getNro_aula() {
        return nro_aula;
    }

    public void setNro_aula(Integer nro_aula) {
        this.nro_aula = nro_aula;
    }

    public String getTipoPizarron() {
        return tipoPizarron;
    }

    public void setTipoPizarron(String tipoPizarron) {
        this.tipoPizarron = tipoPizarron;
    }

    public Boolean getVentiladores() {
        return ventiladores;
    }

    public void setVentiladores(Boolean ventiladores) {
        this.ventiladores = ventiladores;
    }

    public Boolean getCanion() {
        return canion;
    }

    public void setCanion(Boolean canion) {
        this.canion = canion;
    }

    public Integer getCantidad_pcs() {
        return cantidad_pcs;
    }

    public void setCantidad_pcs(Integer cantidad_pcs) {
        this.cantidad_pcs = cantidad_pcs;
    }
}
