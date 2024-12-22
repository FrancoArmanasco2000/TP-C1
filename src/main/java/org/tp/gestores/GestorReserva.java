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
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class GestorReserva {

    public void validarHorario(ReservaDTO reservaDTO) throws HorarioException, DuracionException {
        String[] partes;
        int horas;
        int minutos;
        List<LocalDate> fechasNoValidasPorHorario = new ArrayList<>();
        List<LocalDate> fechasNoValidasPorDuracion = new ArrayList<>();

        for(FechaDTO f : reservaDTO.getListaFechasDTO()) {

            //validamos horario inicio
            partes = f.getHorarioInicio().split(":");
            horas = Integer.parseInt(partes[0]);
            minutos = Integer.parseInt(partes[1]);

            if(horas < 0 || horas > 23 || minutos < 0 || minutos > 59) {
                fechasNoValidasPorHorario.add(f.getFecha());
            }

            //validamos horario fin
            partes = f.getHorarioFin().split(":");
            horas = Integer.parseInt(partes[0]);
            minutos = Integer.parseInt(partes[1]);

            if(horas < 0 || horas > 23 || minutos < 0 || minutos > 59) {
                fechasNoValidasPorHorario.add(f.getFecha());
            }

            //validamos duracion
            if( f.getDuracion() % 30 != 0){
                fechasNoValidasPorDuracion.add(f.getFecha());
            };
        }

        if(fechasNoValidasPorHorario.size() > 0) {
            String fechasStr = "";
            for(LocalDate fecha : fechasNoValidasPorHorario) {
                fechasStr += fecha.toString() + "\n";
            }
            throw new HorarioException(fechasStr);
        }

        if(fechasNoValidasPorDuracion.size() > 0) {
            String fechasStr = "";
            for(LocalDate fecha : fechasNoValidasPorDuracion) {
                fechasStr += fecha.toString() + "\n";
            }
            throw new HorarioException(fechasStr);
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

    public void validarDatos(ReservaDTO reservaDTO) throws DatosException {

        // Cant alumnos es un número
        if (!reservaDTO.getCantAlumnos().toString().matches("\\d+")) {
            throw new DatosException("La cantidad de alumnos debe ser un número entero.");
        }
        // Formato de email
        if (!reservaDTO.getCorreoContacto().matches("[A-ZÑÁÉÍÓÚa-zñáéíóú0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}")) {
            throw new DatosException("El correo electrónico debe tener un formato válido, como ejemplo@dominio.com.");
        }

    }


    public void registrarReserva(ReservaDTO reservaDTO) throws DuracionException, HorarioException, FechaException, DatosException {

        PeriodoDAO periodoDAO = new PeriodoDAO();

        validarHorario(reservaDTO);
        validarDia(reservaDTO);
        validarDatos(reservaDTO);
        //verificarDiponibilidad(reservaDTO);

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


    private DayOfWeek convertirTextoADayOfWeek(String diaTexto) {
        return switch (diaTexto) {
            case "Lunes" -> DayOfWeek.MONDAY;
            case "Martes" -> DayOfWeek.TUESDAY;
            case "Miércoles" -> DayOfWeek.WEDNESDAY;
            case "Jueves" -> DayOfWeek.THURSDAY;
            case "Viernes" -> DayOfWeek.FRIDAY;
            default -> throw new IllegalArgumentException("El día " + diaTexto + " no es válido");
        };
    }

}
