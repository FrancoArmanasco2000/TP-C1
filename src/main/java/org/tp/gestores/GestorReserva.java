package org.tp.gestores;

import org.tp.dao.PeriodoDAO;
import org.tp.dto.FechaDTO;
import org.tp.dto.ReservaDTO;
import org.tp.entity.Periodo;
import org.tp.entity.Reserva;

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

    public List<FechaDTO> calcularFechasDelPeriodo(ReservaDTO reserva, Periodo periodo) {
        List<FechaDTO> fechasDelPeriodo = new ArrayList<>();
        return fechasDelPeriodo;
    }

    public void RegistrarReserva(ReservaDTO reservaDTO) {

        Reserva r = new Reserva();
        r.setCantidadAlumnos(reservaDTO.getCantAlumnos());
        r.setTipoAula(reservaDTO.getTipoAula());
        r.setIdCurso(reservaDTO.getIdCurso());
        r.setIdDocente(reservaDTO.getIdDocente());
        r.setCorreoContacto(reservaDTO.getCorreoContacto());
        if(reservaDTO.getIdPeriodo() != 0){  //opt EsPeriodica
            PeriodoDAO periodoDAO = new PeriodoDAO();
            Periodo p = periodoDAO.getPeriodoById(reservaDTO.getIdPeriodo());
            r.setIdPeriodo(p);

        }


    }
}
