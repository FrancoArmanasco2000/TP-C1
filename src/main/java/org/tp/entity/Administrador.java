package org.tp.entity;

import jakarta.persistence.*;

@Entity //HIBERNATE RECONOCE QUE ESTO VA A SER UNA TABLA
@Table(name = "ADMINISTRADOR") //NOMBRE QUE VA A TENER LA TABLA EN LA BASE DE DATOS
public class Administrador {

    @Id //DEJAS EN CLARO CUAL VA A SER EL ATRIBUTO QUE REPRESENTA EL ID
    @GeneratedValue(strategy = GenerationType.SEQUENCE) //EL ID SE GENERA AUTOMATICAMENTE DE MANERA SECUENCIAL
    private Long idUsuario;
    @Column //DEMAS COLUMNAS DE LA TABLA
    private String usuario;
    @Column
    private String contrasena;

    //SIEMPRE GENERAR UN CONSTRUCTOR VACIO
    public Administrador() {}

    //LUEGO GENEREN UNO CON LOS ATRIBUTOS
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
