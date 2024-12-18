package org.tp.gestores;

import org.tp.dao.*;
import org.tp.dto.FechaDTO;
import org.tp.dto.ReservaDTO;
import org.tp.entity.*;
import org.tp.utils.FechaUtils;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;

public class GestorReserva {

    public String validarDia(ReservaDTO reserva){ //Valida que los dias especificos sean posteriores a la fecha actual
        LocalDate fechaActual = LocalDate.now();
        List<LocalDate> fechasNoValidas = new ArrayList<>();
        for(int i=0;i<reserva.getListaFechasDTO().size();i++){
            if(reserva.getListaFechasDTO().get(i).getFecha().isBefore(fechaActual)) {
                fechasNoValidas.add(reserva.getListaFechasDTO().get(i).getFecha());
            }
        }
        if(fechasNoValidas.isEmpty()) { return "";} else {
            return "Los siguientes dias ya pasaron: " + fechasNoValidas;
        }
    }

    public void RegistrarReserva(ReservaDTO reservaDTO) {
        FechaDAO fechaDAO = new FechaDAO();
        AulaDAO aulaDAO = new AulaDAO();
        ReservaDAO reservaDAO = new ReservaDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        Reserva r = new Reserva();

        r.setCantidadAlumnos(reservaDTO.getCantAlumnos());
        r.setTipoAula(reservaDTO.getTipoAula());
        r.setCorreoContacto(reservaDTO.getCorreoContacto());
        r.setDocente(reservaDTO.getNombreDocente());
        r.setAsignatura(reservaDTO.getAsignatura());
        Long idUsuario = usuarioDAO.getBedelByUsuario(reservaDTO.getNombreUsuario()).getIdUsuario();
        Bedel b = usuarioDAO.getBedelByidUsuario(idUsuario);
        r.setIdUsuario(b);

        if(reservaDTO.getIdPeriodo() != null){  //opt esPeriodica
            PeriodoDAO periodoDAO = new PeriodoDAO();
            Periodo p = periodoDAO.getPeriodoById(reservaDTO.getIdPeriodo());
            r.setIdPeriodo(p);
        }


        reservaDAO.crearReserva(r);

        for(FechaDTO fechaDTO: reservaDTO.getListaFechasDTO()){
            Fecha f = new Fecha();
            f.setFecha(fechaDTO.getFecha());
            f.setHorarioInicio(fechaDTO.getHorarioInicio());
            f.setDuracion(fechaDTO.getDuracion());
            f.setDia(fechaDTO.getDia());
            Aula a = aulaDAO.getAulaById(fechaDTO.getIdAula());
            f.setAula(a);
            f.setReserva(r);
            fechaDAO.crearFecha(f);
        }


    }

    public List<FechaDTO> calcularFechasDelPeriodo(ReservaDTO reserva) {
        List<FechaDTO> fechasDelPeriodo = new ArrayList<>();
        for(FechaDTO fecha: reserva.getListaFechasDTO()){
            fechasDelPeriodo.addAll(FechaUtils.crearListaFechas(reserva,fecha));
        }
        return fechasDelPeriodo;
    }

}
