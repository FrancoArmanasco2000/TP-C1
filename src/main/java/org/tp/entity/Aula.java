package org.tp.entity;

import jakarta.persistence.*;
import org.tp.utils.TipoAula;

@Entity
@Table (name="Aula")
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idAula;
    @Column
    private String nombre;
    @Column
    private String ubicacion;
    @Column
    private Integer capacidad;
    @Column
    private TipoAula tipo;
    @Column
    private Integer nro_aula;
    @Column
    private String piso;
    @Column
    private Integer cantidad_pcs;
    @Column
    private String tipo_pizarron;
    @Column
    private Boolean canion;
    @Column
    private Boolean aire_acondicionado;
    @Column
    private Boolean ventiladores;

    public Aula() {
    }

    public Aula(Boolean aire_acondicionado, Boolean ventiladores, String ubicacion, String tipo_pizarron, TipoAula tipo, String piso, Integer nro_aula, String nombre, Long idAula, Integer cantidad_pcs, Boolean canion, Integer capacidad) {
        this.aire_acondicionado = aire_acondicionado;
        this.ventiladores = ventiladores;
        this.ubicacion = ubicacion;
        this.tipo_pizarron = tipo_pizarron;
        this.tipo = tipo;
        this.piso = piso;
        this.nro_aula = nro_aula;
        this.nombre = nombre;
        this.idAula = idAula;
        this.cantidad_pcs = cantidad_pcs;
        this.canion = canion;
        this.capacidad = capacidad;
    }

    public Boolean getAire_acondicionado() {
        return aire_acondicionado;
    }

    public void setAire_acondicionado(Boolean aire_acondicionado) {
        this.aire_acondicionado = aire_acondicionado;
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

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public Long getIdAula() {
        return idAula;
    }

    public void setIdAula(Long idAula) {
        this.idAula = idAula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNro_aula() {
        return nro_aula;
    }

    public void setNro_aula(Integer nro_aula) {
        this.nro_aula = nro_aula;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public TipoAula getTipo() {
        return tipo;
    }

    public void setTipo(TipoAula tipo) {
        this.tipo = tipo;
    }

    public String getTipo_pizarron() {
        return tipo_pizarron;
    }

    public void setTipo_pizarron(String tipo_pizarron) {
        this.tipo_pizarron = tipo_pizarron;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Boolean getVentiladores() {
        return ventiladores;
    }

    public void setVentiladores(Boolean ventiladores) {
        this.ventiladores = ventiladores;
    }

    @Override
    public String toString() {
        return "Aula{" +
                "aire_acondicionado=" + aire_acondicionado +
                ", idAula=" + idAula +
                ", nombre='" + nombre + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", capacidad=" + capacidad +
                ", tipo=" + tipo +
                ", nro_aula=" + nro_aula +
                ", piso='" + piso + '\'' +
                ", cantidad_pcs=" + cantidad_pcs +
                ", tipo_pizarron='" + tipo_pizarron + '\'' +
                ", canion=" + canion +
                ", ventiladores=" + ventiladores +
                '}';
    }
}
