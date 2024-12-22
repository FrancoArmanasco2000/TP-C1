package org.tp.dto;


public class AulaDTO {
    private Long idAula;
    private String nombre;
    private String ubicacion;
    private Integer capacidad;
    private String tipo;
    private Integer nro_aula;
    private Integer piso;
    private Integer cantidad_pcs;
    private Boolean canion;
    private Boolean aire_acondicionado;
    private Boolean ventiladores;
    private String tipoPizarron;


    public AulaDTO(){}

    public AulaDTO(Long idAula, String nombre, String ubicacion, Integer capacidad, String tipo, Integer nro_aula, Integer piso, Integer cantidad_pcs, Boolean canion, Boolean aire_acondicionado, Boolean ventiladores, String tipoPizarron) {
        this.idAula = idAula;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.capacidad = capacidad;
        this.tipo = tipo;
        this.nro_aula = nro_aula;
        this.piso = piso;
        this.cantidad_pcs = cantidad_pcs;
        this.canion = canion;
        this.aire_acondicionado = aire_acondicionado;
        this.ventiladores = ventiladores;
        this.tipoPizarron = tipoPizarron;
    }

    public void setTipoPizarron(String tipoPizarron) {
        this.tipoPizarron = tipoPizarron;
    }

    public Long getIdAula() {
        return idAula;
    }

    public String getTipoPizarron() {
        return tipoPizarron;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public String getTipo() {
        return tipo;
    }

    public Integer getPiso() {
        return piso;
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

    public void setIdAula(Long idAula) {this.idAula = idAula;}

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public void setNro_aula(Integer nro_aula) {
        this.nro_aula = nro_aula;
    }

    public void setPiso(Integer piso) {
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
