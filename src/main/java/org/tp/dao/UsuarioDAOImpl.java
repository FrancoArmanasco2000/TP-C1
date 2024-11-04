package org.tp.dao;

import org.tp.entity.Bedel;

import java.util.List;

public interface UsuarioDAOImpl {

    public void crearUsuario(Bedel bedel);

    public List<String> listaNombreUsuarios();

    public Bedel getBedelByUsuario(String usuario);
}
