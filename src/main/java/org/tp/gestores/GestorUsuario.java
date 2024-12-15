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
    private final GestorPoliticas gestorPoliticas;

    public GestorUsuario (){ this.gestorPoliticas = new GestorPoliticas(); usuarioDAO = new UsuarioDAO();}

    public void registrarBedel (BedelDTO bedel, String confirmarContrasenia) throws UsuarioYaRegistradoException, ContraseniaInvalidaException, ContraseniasNoCoincidenException, IllegalArgumentException {
            validarDatos(bedel);

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

    public Bedel modificarBedel(BedelDTO bedel) throws ContraseniaInvalidaException, IllegalArgumentException {
        validarDatos(bedel);

        Bedel bedelModificado = new Bedel();
        bedelModificado.setNombre(bedel.getNombre());
        bedelModificado.setApellido(bedel.getApellido());
        bedelModificado.setUsuario(bedel.getUsuario());
        bedelModificado.setContrasenia(bedel.getContrasenia());
        bedelModificado.setTurno(bedel.getTurno());
        bedelModificado.setBorrado(bedel.getBorrado());

        if (!gestorPoliticas.comprobarTODO(bedel.getContrasenia()).isBlank()) {
            throw new ContraseniaInvalidaException(gestorPoliticas.comprobarTODO(bedel.getContrasenia()));
        }

        usuarioDAO.actualizarBedel(bedelModificado);

        return bedelModificado;
    }

    public void actualizarBedel (Bedel bedel){
        usuarioDAO.actualizarBedel(bedel);
    }

    public Bedel getUsuarioById(Long idBedel){
        return usuarioDAO.getBedelByidUsuario(idBedel);
    }

    public List<Bedel> obtenerTodosLosBedeles() {
        return usuarioDAO.obtenerTodosLosBedeles();
    }

    public List<Bedel> buscarBedelesPorNombre(String nombre) {
        return usuarioDAO.buscarBedelesPorNombre(nombre);
    }

    public List<Bedel> buscarBedelesPorTurno(String turno) {
        return usuarioDAO.buscarBedelesPorTurno(turno);
    }

    public void eliminarBedel(Long idUsuario) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Bedel bedel = usuarioDAO.getBedelByidUsuario(idUsuario);

        if (bedel != null) {
            bedel.setBorrado(true);
            usuarioDAO.actualizarBedel(bedel);
        } else {
            System.out.println("No se encontró el Bedel con ID: " + idUsuario);
        }
    }

    private void validarDatos(BedelDTO bedel) throws IllegalArgumentException {
        if (bedel.getNombre() == null || bedel.getNombre().length() >= 30) {
            throw new IllegalArgumentException("El nombre no puede estar vacío y debe tener como máximo 30 caracteres.");
        }

        if (bedel.getApellido() == null || bedel.getApellido().length() >= 30) {
            throw new IllegalArgumentException("El apellido no puede estar vacío y debe tener como máximo 30 caracteres.");
        }

        if (bedel.getUsuario() == null || bedel.getUsuario().length() >= 20) {
            throw new IllegalArgumentException("El usuario no puede estar vacío y debe tener como máximo 20 caracteres.");
        }

        if (bedel.getContrasenia() == null || bedel.getContrasenia().length() >= 24) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía y debe tener como máximo 24 caracteres.");
        }

        if (bedel.getTurno() == null || bedel.getTurno().length() >= 10) {
            throw new IllegalArgumentException("El turno no puede estar vacío y debe tener como máximo 10 caracteres.");
        }
    }

    public boolean validarSesion(String usuario, String contrasenia) {
        return usuarioDAO.getBedelByUsuario(usuario) != null && usuarioDAO.getBedelByUsuario(usuario).getContrasenia().equals(contrasenia);
    }

}