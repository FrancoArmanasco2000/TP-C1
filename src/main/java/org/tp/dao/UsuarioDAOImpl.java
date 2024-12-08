package org.tp.dao;

import org.tp.entity.Bedel;

import java.util.List;

public interface UsuarioDAOImpl {

    public void crearUsuario(Bedel bedel);

    public List<String> listaNombreUsuarios();

    public Bedel getBedelByUsuario(String usuario);

    public Bedel getBedelByidUsuario(Long idUsuario);

    public void actualizarBedel(Bedel bedel);

    public List<Bedel> obtenerTodosLosBedeles();

    public List<Bedel> buscarBedelesPorNombre(String nombre);

    public List<Bedel> buscarBedelesPorTurno(String turno);

}
