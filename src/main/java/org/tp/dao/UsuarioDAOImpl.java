package org.tp.dao;

import org.tp.entity.Bedel;

import java.util.List;

public interface UsuarioDAOImpl {

    void crearUsuario(Bedel bedel);

    List<String> listaNombreUsuarios();

    Bedel getBedelByUsuario(String usuario);

    Bedel getBedelByidUsuario(Long idUsuario);

    void actualizarBedel(Bedel bedel);

    List<Bedel> obtenerTodosLosBedeles();

    List<Bedel> buscarBedelesPorNombre(String nombre);

    List<Bedel> buscarBedelesPorTurno(String turno);

}
