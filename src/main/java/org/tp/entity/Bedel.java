package org.tp.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "bedel")
public class Bedel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusuario", nullable = false)
    private Long idUsuario;
    @Column(name = "nombre", nullable = false, length = 31)
    private String nombre;
    @Column(name = "apellido", nullable = false, length = 31)
    private String apellido;
    @Column(name = "usuario", nullable = false, length = 21, unique = true)
    private String usuario;
    @Column(name = "contrasenia", nullable = false, length = 25)
    private String contrasenia;
    @Column(name = "borrado", nullable = false)
    private Boolean borrado = false;
    @Column(name = "turno", nullable = false, length = 11)
    private String turno;

    public Bedel () {}

    public Bedel (String nombre, String apellido, String usuario, String contrasenia, Boolean borrado, String turno) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.borrado = borrado;
        this.turno = turno;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(Boolean borrado) {
        this.borrado = borrado;
    }
}
