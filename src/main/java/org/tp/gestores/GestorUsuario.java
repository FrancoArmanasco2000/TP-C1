package org.tp.gestores;

import org.tp.dao.UsuarioDAO;
import org.tp.dto.BedelDTO;
import org.tp.entity.Bedel;

import java.util.ArrayList;
import java.util.List;

public class GestorUsuario {

    private static UsuarioDAO usuarioDAO = new UsuarioDAO();

    public GestorUsuario (){}

    public void registrarBedel (BedelDTO bedel) {
        Bedel bedelEntity = new Bedel(
            bedel.getNombre(),
            bedel.getApellido(),
            bedel.getUsuario(),
            bedel.getContrasenia(),
            bedel.getBorrado(),
            bedel.getTurno()
        );
        usuarioDAO.crearUsuario(bedelEntity);
    }

    public Bedel getBedelByUsuario (String usuario) {
       return  usuarioDAO.getBedelByUsuario(usuario);
    }
}
