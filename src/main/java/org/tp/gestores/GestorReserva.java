package org.tp.gestores;

import org.tp.dao.*;
import org.tp.dto.FechaDTO;
import org.tp.dto.ReservaDTO;
import org.tp.entity.*;
import org.tp.excepciones.DatosException;
import org.tp.excepciones.DuracionException;
import org.tp.excepciones.FechaException;
import org.tp.excepciones.HorarioException;
import org.tp.utils.FechaUtils;

import javax.swing.*;
import java.time.DayOfWeek;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class GestorReserva {

    public void validarHorario(ReservaDTO reservaDTO) throws HorarioException, DuracionException {

        List<LocalDate> fechasNoValidasPorHorario = new ArrayList<>();
        List<LocalDate> fechasNoValidasPorDuracion = new ArrayList<>();

        for(FechaDTO f : reservaDTO.getListaFechasDTO()) {
            if(f.getDuracion() % 30 != 0) {
                fechasNoValidasPorDuracion.add(f.getFecha());
            }
            if(LocalDate.now().isAfter(f.getFecha())) {
                fechasNoValidasPorHorario.add(f.getFecha());
            }
        }

        if(!fechasNoValidasPorHorario.isEmpty()) {
            String fechasStr = "";
            for(LocalDate fecha : fechasNoValidasPorHorario) {
                fechasStr += fecha.toString() + "\n";
            }
            throw new HorarioException(fechasStr);
        }

        if(!fechasNoValidasPorDuracion.isEmpty()) {
            String fechasStr = "";
            for(LocalDate fecha : fechasNoValidasPorDuracion) {
                fechasStr += fecha.toString() + "\n";
            }
            throw new DuracionException(fechasStr);
        }

    }

    public void validarDia(ReservaDTO reservaDTO) throws FechaException { //Valida que los dias especificos sean posteriores a la fecha actual
        LocalDate fechaActual = LocalDate.now();
        List<LocalDate> fechasNoValidas = new ArrayList<>();

        if(reservaDTO.getIdPeriodo() == null) {
            for(FechaDTO fechaDTO :reservaDTO.getListaFechasDTO()) {
                if(fechaDTO.getFecha().isBefore(fechaActual)){
                    fechasNoValidas.add(fechaDTO.getFecha());
                }
            }
        } else {
            if(reservaDTO.getListaFechasDTO().get(0).getFecha().isBefore(fechaActual)) {
                fechasNoValidas.addAll(reservaDTO.getListaFechasDTO().stream().map(FechaDTO::getFecha).toList());
            }
        }

        if(!fechasNoValidas.isEmpty()) {
            String fechasStr = "";
            for(LocalDate fecha : fechasNoValidas) {
                fechasStr += fecha.toString() + "\n";
            }
            throw new FechaException(fechasStr);
        }
    }


    public void registrarReserva(ReservaDTO reservaDTO) throws DuracionException, HorarioException, FechaException, DatosException {

        PeriodoDAO periodoDAO = new PeriodoDAO();


        validarHorario(reservaDTO);
        validarDia(reservaDTO);



        Reserva reserva = new Reserva();
        reserva.setFechas(new ArrayList<>());
        reserva.setIdDocente(reservaDTO.getIdDocente());
        reserva.setTipoAula(reservaDTO.getTipoAula());
        reserva.setCantidadAlumnos(reservaDTO.getCantAlumnos());
        reserva.setIdCurso(reservaDTO.getIdCurso());
        reserva.setCorreoContacto(reservaDTO.getCorreoContacto());

        if(reservaDTO.getIdPeriodo() != null) {
            Periodo periodo = periodoDAO.getPeriodo(reservaDTO.getIdPeriodo());
            reserva.setPeriodo(periodo);
        }

            List<FechaDTO> listaFechasPeriodo = reservaDTO.getListaFechasDTO();

            AulaDAO aulaDAO = new AulaDAO();

            for(FechaDTO fechaDTO : listaFechasPeriodo) {
                Fecha fecha = new Fecha();
                fecha.setFecha(fechaDTO.getFecha());
                fecha.setHorarioInicio(fechaDTO.getHorarioInicio());
                fecha.setDuracion(fechaDTO.getDuracion());
                Aula aula = aulaDAO.getAula(fechaDTO.getIdAula());
                fecha.setAula(aula);
                reserva.getFechas().add(fecha);
            }

            ReservaDAO reservaDAO = new ReservaDAO();

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Bedel bedel = usuarioDAO.getUsuario(reservaDTO.getNombreUsuario());

            bedel.getReservas().add(reserva);
            usuarioDAO.actualizarUsuario(bedel);

            reserva.setBedel(bedel);
            reservaDAO.crearReserva(reserva);

            FechaDAO fechaDAO = new FechaDAO();
            for(Fecha fecha : reserva.getFechas()) {
                fecha.setReserva(reserva);
                fechaDAO.crearFecha(fecha);
            }
    }

}
