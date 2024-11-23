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

    public GestorUsuario (){ this.gestorPoliticas = new GestorPoliticas();}

    public void registrarBedel (BedelDTO bedel, String confirmarContrasenia) throws UsuarioYaRegistradoException, ContraseniaInvalidaException, ContraseniasNoCoincidenException {
        Bedel bedelEntity = new Bedel(
                bedel.getNombre(),
                bedel.getApellido(),
                bedel.getUsuario(),
                bedel.getContrasenia(),
                bedel.getBorrado(),
                bedel.getTurno()
        );
        if (usuarioDAO.getBedelByUsuario(bedel.getUsuario()) != null) {
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

    public Bedel modificarBedel(BedelDTO bedel) throws ContraseniaInvalidaException, ContraseniasNoCoincidenException{
        Bedel bedelModificado = new Bedel();
        bedelModificado.setNombre(bedel.getNombre());
        bedelModificado.setApellido(bedel.getApellido());
        bedelModificado.setUsuario(bedel.getUsuario());
        bedelModificado.setContrasenia(bedel.getContrasenia());
        bedelModificado.setTurno(bedel.getTurno());
        if (!gestorPoliticas.comprobarTODO(bedel.getContrasenia()).isBlank()) {
            throw new ContraseniaInvalidaException(gestorPoliticas.comprobarTODO(bedel.getContrasenia()));
        }
        return bedelModificado;
    }

    public void actualizarBedel (Bedel bedel){
        usuarioDAO.actualizarBedel(bedel);
    }
    public Bedel getUsuarioById(Long idBedel){
        Bedel bedel = usuarioDAO.getBedelByidUsuario(idBedel);
        return bedel;
    }

}