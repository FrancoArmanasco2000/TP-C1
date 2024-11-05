package org.tp.gestores;

import org.tp.dao.UsuarioDAO;
import org.tp.dto.BedelDTO;
import org.tp.entity.Bedel;
import org.tp.excepciones.ContraseniaInvalidaException;
import org.tp.excepciones.ContraseniasNoCoincidenException;
import org.tp.excepciones.UsuarioYaRegistradoException;

import java.util.List;

public class GestorUsuario {

    private static UsuarioDAO usuarioDAO = new UsuarioDAO();
    private GestorPoliticas gestorPoliticas;

    public GestorUsuario (){}

    public void registrarBedel (BedelDTO bedel, String confirmarContrasenia) throws UsuarioYaRegistradoException, ContraseniaInvalidaException, ContraseniasNoCoincidenException {
        Bedel bedelEntity = new Bedel(
                bedel.getNombre(),
                bedel.getApellido(),
                bedel.getUsuario(),
                bedel.getContrasenia(),
                bedel.getBorrado(),
                bedel.getTurno()
        );
        if (usuarioDAO.getBedelByUsuario(bedelEntity.getUsuario()) != null) {
            throw new UsuarioYaRegistradoException();
        } else if (!gestorPoliticas.comprobarTODO(bedelEntity.getContrasenia()).isBlank()) {
            throw new ContraseniaInvalidaException(gestorPoliticas.comprobarTODO(bedelEntity.getContrasenia()));
        } else if (!bedelEntity.getContrasenia().equals(confirmarContrasenia)) {
            throw new ContraseniasNoCoincidenException();
        } else {
            usuarioDAO.crearUsuario(bedelEntity);
        }
    }
    public List<String> listarUsuarios() {
        return usuarioDAO.listaNombreUsuarios();
    }
}