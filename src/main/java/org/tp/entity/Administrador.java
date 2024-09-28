package org.tp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ADMINISTRADOR")
public class Administrador {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idUsuario;
    @Column
    private String usuario;
    @Column
    private String contrasena;


    public Administrador() {}

    public Administrador(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Administrador{" +
                "contrasena='" + contrasena + '\'' +
                ", idUsuario=" + idUsuario +
                ", usuario='" + usuario + '\'' +
                '}';
    }
}
