package org.tp.gestores;

import org.tp.dao.UsuarioDAO;
import org.tp.dto.BedelDTO;
import org.tp.entity.Bedel;
import org.tp.excepciones.ContraseniaInvalidaException;
import org.tp.excepciones.ContraseniasNoCoincidenException;
import org.tp.excepciones.UsuarioYaRegistradoException;

import java.util.ArrayList;
import java.util.List;

public class GestorUsuario {

    private static UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final GestorPoliticas gestorPoliticas;

    public GestorUsuario (){ this.gestorPoliticas = new GestorPoliticas(); usuarioDAO = new UsuarioDAO();}

    public void registrarBedel (BedelDTO bedelDTO) throws UsuarioYaRegistradoException, ContraseniaInvalidaException, ContraseniasNoCoincidenException, IllegalArgumentException {

        this.validarUsuario(bedelDTO.getUsuario());

        if(!gestorPoliticas.validarContrasenia(bedelDTO.getContrasenia()).isEmpty()) {
            throw new ContraseniaInvalidaException(gestorPoliticas.validarContrasenia(bedelDTO.getContrasenia()));
        }

        Bedel bedel = usuarioDAO.getUsuario(bedelDTO.getUsuario());

        if(bedel != null) {
            throw new UsuarioYaRegistradoException();
        }

        Bedel b = new Bedel();
        b.setNombre(bedelDTO.getNombre());
        b.setApellido(bedelDTO.getApellido());
        b.setTurno(bedelDTO.getTurno());
        b.setUsuario(bedelDTO.getUsuario());
        b.setContrasena(bedelDTO.getContrasenia());
        b.setBorrado(bedelDTO.getBorrado());

        usuarioDAO.crearUsuario(b);
    }

    public void eliminarBedel (BedelDTO bedelDTO) {

        Bedel bedel = usuarioDAO.getUsuarioById(bedelDTO.getIdUsuario());
        bedel.setBorrado(true);
        usuarioDAO.actualizarUsuario(bedel);

    }

    public void modificarBedel (BedelDTO bedelDTO) throws ContraseniaInvalidaException , ContraseniasNoCoincidenException, IllegalArgumentException{

        this.validarDatos(bedelDTO);

        if (!gestorPoliticas.validarContrasenia(bedelDTO.getContrasenia()).isBlank()) {//Validación de las politicas aka validarContrasenia(BedelDTO.contrasenia)
            throw new ContraseniaInvalidaException(gestorPoliticas.validarContrasenia(bedelDTO.getContrasenia()));
        }

        Bedel bedel = usuarioDAO.getUsuarioById(bedelDTO.getIdUsuario());
        bedel.modificarBedel(bedelDTO);
        usuarioDAO.actualizarUsuario(bedel);

    }



    public List<BedelDTO> obtenerTodosLosUsuarios() {
      List<Bedel> bedeles = usuarioDAO.obtenerTodosLosUsuarios();
      List<BedelDTO> bedelesDTO = new ArrayList<>();
      for(Bedel b: bedeles){
          BedelDTO bedelDTO = new BedelDTO();
          bedelDTO.setIdUsuario(b.getIdUsuario());
          bedelDTO.setUsuario(b.getUsuario());
          bedelDTO.setApellido(b.getApellido());
          bedelDTO.setNombre(b.getNombre());
          bedelDTO.setTurno(b.getTurno());
          bedelDTO.setContrasenia(b.getContrasena());

          bedelesDTO.add(bedelDTO);
      }

      return bedelesDTO;
    }

    public List<Bedel> buscarBedelesPorNombre(String nombre) {
        return usuarioDAO.buscarBedelesPorNombre(nombre);
    }

    public List<Bedel> buscarBedelesPorTurno(String turno) {
        return usuarioDAO.buscarBedelesPorTurno(turno);
    }


    private void validarUsuario(String usuario) throws IllegalArgumentException {
//        if (bedel.getNombre() == null || bedel.getNombre().length() >= 30) {
//            throw new IllegalArgumentException("El nombre no puede estar vacío y debe tener como máximo 30 caracteres.");
//        }
//
//        if (bedel.getApellido() == null || bedel.getApellido().length() >= 30) {
//            throw new IllegalArgumentException("El apellido no puede estar vacío y debe tener como máximo 30 caracteres.");
//        }

        if (usuario == null || usuario.length() >= 20) {
            throw new IllegalArgumentException("El usuario no puede estar vacío y debe tener como máximo 20 caracteres.");
        }

//        if (bedel.getContrasenia() == null || bedel.getContrasenia().length() >= 24) {
//            throw new IllegalArgumentException("La contraseña no puede estar vacía y debe tener como máximo 24 caracteres.");
//        }

//        if (bedel.getTurno() == null || bedel.getTurno().length() >= 10) {
//            throw new IllegalArgumentException("El turno no puede estar vacío y debe tener como máximo 10 caracteres.");
//        }
    }

    public boolean validarSesion(String usuario, String contrasenia) {
        return usuarioDAO.getUsuario(usuario) != null && usuarioDAO.getUsuario(usuario).getContrasena().equals(contrasenia);
    }

    private void validarDatos(BedelDTO bedelDTO) throws IllegalArgumentException {
        if (bedelDTO.getNombre() == null || bedelDTO.getNombre().length() >= 30) {
            throw new IllegalArgumentException("El nombre no puede estar vacío y debe tener como máximo 30 caracteres.");
        }

        if (bedelDTO.getApellido() == null || bedelDTO.getApellido().length() >= 30) {
            throw new IllegalArgumentException("El apellido no puede estar vacío y debe tener como máximo 30 caracteres.");
        }

        if (bedelDTO.getUsuario() == null || bedelDTO.getUsuario().length() >= 20) {
            throw new IllegalArgumentException("El usuario no puede estar vacío y debe tener como máximo 20 caracteres.");
        }

        if (bedelDTO.getContrasenia() == null || bedelDTO.getContrasenia().length() >= 24) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía y debe tener como máximo 24 caracteres.");
        }

        if (bedelDTO.getTurno() == null || bedelDTO.getTurno().length() >= 10) {
            throw new IllegalArgumentException("El turno no puede estar vacío y debe tener como máximo 10 caracteres.");
        }

    }

}