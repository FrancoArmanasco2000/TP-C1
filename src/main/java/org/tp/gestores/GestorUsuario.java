package org.tp.gestores;

import org.tp.dao.UsuarioDAO;
import org.tp.dto.BedelDTO;
import org.tp.entity.Bedel;
import org.tp.excepciones.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GestorUsuario {

    private static UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final GestorPoliticas gestorPoliticas;

    public GestorUsuario (){ this.gestorPoliticas = new GestorPoliticas(); usuarioDAO = new UsuarioDAO();}

    public void registrarBedel (BedelDTO bedelDTO) throws UsuarioYaRegistradoException, ContraseniaInvalidaException, ContraseniasNoCoincidenException, IllegalArgumentException {

       validarUsuario(bedelDTO);

        Bedel b = new Bedel();
        b.setNombre(bedelDTO.getNombre());
        b.setApellido(bedelDTO.getApellido());
        b.setTurno(bedelDTO.getTurno());
        b.setUsuario(bedelDTO.getUsuario());
        b.setContrasena(bedelDTO.getContrasenia());
        b.setBorrado(bedelDTO.getBorrado());
        b.setReservas(new ArrayList<>());

        usuarioDAO.crearUsuario(b);
    }

    public void eliminarBedel (Long idUsuario) {

        Bedel bedel = usuarioDAO.getUsuarioById(idUsuario);
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

    public List<BedelDTO> buscarBedel (String apellido, String turno) throws BedelPorApellidoNoEncontradoException, BedelPorTurnoNoEncontradoException {
        List<Bedel> bedeles;

        if(apellido.isBlank()) {
            bedeles = usuarioDAO.getBedeles("", turno);
        } else {
            bedeles = usuarioDAO.getBedeles(apellido, "");
        }

        if(bedeles.isEmpty() && turno.isBlank()) {
            throw new BedelPorApellidoNoEncontradoException();
        }

        if(bedeles.isEmpty() && apellido.isBlank()) {
            throw new BedelPorTurnoNoEncontradoException();
        }

        List<BedelDTO> bedelesDTO = new ArrayList<>();

        for(Bedel b: bedeles){
            if(!b.getBorrado()) {
                BedelDTO bedelDTO = new BedelDTO();
                bedelDTO.setIdUsuario(b.getIdUsuario());
                bedelDTO.setUsuario(b.getUsuario());
                bedelDTO.setApellido(b.getApellido());
                bedelDTO.setNombre(b.getNombre());
                bedelDTO.setTurno(b.getTurno());
                bedelDTO.setContrasenia(b.getContrasena());
                bedelesDTO.add(bedelDTO);
            }
        }

        return bedelesDTO;
    }

    private void validarUsuario(BedelDTO bedelDTO) throws IllegalArgumentException, ContraseniaInvalidaException, UsuarioYaRegistradoException {

        if(!gestorPoliticas.validarContrasenia(bedelDTO.getContrasenia()).isEmpty()) {
            throw new ContraseniaInvalidaException(gestorPoliticas.validarContrasenia(bedelDTO.getContrasenia()));
        }

        if (bedelDTO.getUsuario() == null || bedelDTO.getUsuario().length() >= 20) {
            throw new IllegalArgumentException("El usuario no puede estar vacío y debe tener como máximo 20 caracteres.");
        }

        Bedel bedel = usuarioDAO.getUsuario(bedelDTO.getUsuario());

        if(bedel != null) {
            throw new UsuarioYaRegistradoException();
        }
    }

    public boolean validarSesion(String usuario, String contrasenia) {
        System.out.println(usuarioDAO.getUsuario(usuario).toString());
        Bedel bedel = usuarioDAO.getUsuario(usuario);
        System.out.println(bedel);
        return Objects.equals(bedel.getUsuario(), usuario) && bedel.getContrasena().equals(contrasenia);
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