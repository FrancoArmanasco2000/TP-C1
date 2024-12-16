package org.tp.gestores;

import org.tp.dao.AulaDAO;
import org.tp.dao.PeriodoDAO;
import org.tp.dto.FechaDTO;
import org.tp.dto.ReservaDTO;
import org.tp.entity.Aula;
import org.tp.entity.Fecha;
import org.tp.entity.Periodo;
import org.tp.entity.Reserva;
import org.tp.utils.FechaUtils;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;

public class GestorReserva {

/*
    public String validarDuracion(ReservaDTO reserva){   //Valida que la duracion de cada dia se multiplo de 30
        boolean duracionValida = false;
        if(reserva.getIdPeriodo() == 0) {
            List<LocalDate> fechasNoValidas = new ArrayList<>();
            for(int i=0;i<reserva.getListaFechasDTO().size();i++){
                if(reserva.getListaFechasDTO().get(i).getDuracion() % 30 != 0) {
                    fechasNoValidas.add(reserva.getListaFechasDTO().get(i).getFecha());
                }
            }
            if(fechasNoValidas.isEmpty()) duracionValida = true;
            if (duracionValida) {
                return "";
            } else {
                return "La duracion no es un multiplo de 30 minutos para los fechas:" + fechasNoValidas;
            }
        }else {
            List<String> diasNoValidos = new ArrayList<String>();
            for (int i = 0; i < reserva.getListaDiasDTO().size(); i++) {
                if (reserva.getListaDiasDTO().get(i).getDuracion() % 30 != 0) {
                    diasNoValidos.add(reserva.getListaDiasDTO().get(i).getDia());
                }
            }
            if (diasNoValidos.isEmpty()) duracionValida = true;
            if (duracionValida) {
                return "";
            } else {
                return "La duracion no es un multiplo de 30 minutos para los dias:" + diasNoValidos;
            }
        }
    }
*/
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

        Reserva r = new Reserva();
        AulaDAO aulaDAO = new AulaDAO();
        r.setCantidadAlumnos(reservaDTO.getCantAlumnos());
        r.setTipoAula(reservaDTO.getTipoAula());
        r.setIdCurso(reservaDTO.getIdCurso());
        r.setIdDocente(reservaDTO.getIdDocente());
        r.setCorreoContacto(reservaDTO.getCorreoContacto());
        if(reservaDTO.getIdPeriodo() != 0){  //opt esPeriodica
            PeriodoDAO periodoDAO = new PeriodoDAO();
            Periodo p = periodoDAO.getPeriodoById(reservaDTO.getIdPeriodo());
            r.setIdPeriodo(p);
            reservaDTO.setListaFechasDTO(calcularFechasDelPeriodo(reservaDTO));
        }
        for(FechaDTO fechaDTO: reservaDTO.getListaFechasDTO()){
            Fecha f = new Fecha();
            f.setFecha(fechaDTO.getFecha());
            f.setHorarioInicio(fechaDTO.getHorarioInicio());
            f.setDuracion(fechaDTO.getDuracion());
            f.setDia(fechaDTO.getDia());
            Aula a = aulaDAO.getAulaById(fechaDTO.getIdAula());
            f.setAula(a);


        }

    }

    public List<FechaDTO> calcularFechasDelPeriodo(ReservaDTO reserva) {
        List<FechaDTO> fechasDelPeriodo = new ArrayList<>();
        for(FechaDTO fecha: reserva.getListaFechasDTO()){
            fechasDelPeriodo.addAll(FechaUtils.crearListaFechas(reserva,fecha));
        }
        return fechasDelPeriodo;
    }


    public Integer calcularDuracion (String horarioInicio, String horarioFin) {
        Integer horaInicio = Integer.parseInt(horarioInicio.substring(0,2));
        Integer horaFin = Integer.parseInt(horarioFin.substring(0,2));
        Integer minutosInicio = Integer.parseInt(horarioInicio.substring(3,5));
        Integer minutosFin = Integer.parseInt(horarioFin.substring(3,5));

        return (horaFin - horaInicio) * 60 + (minutosFin - minutosInicio);
    }

    public FechaDTO generarFechaDTOPeriodica(List<Object> datosFecha) {
        FechaDTO fechaDTO = new FechaDTO();
        fechaDTO.setDia((String) datosFecha.get(0));
        fechaDTO.setHorarioInicio((String) datosFecha.get(1));
        Integer duracion = calcularDuracion((String) datosFecha.get(1), (String) datosFecha.get(2));
        fechaDTO.setDuracion(duracion);
        return fechaDTO;
    }
}
