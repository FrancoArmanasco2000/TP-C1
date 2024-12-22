package org.tp.gestores;

import org.tp.dao.AulaDAO;
import org.tp.dao.PeriodoDAO;
import org.tp.dao.ReservaDAO;
import org.tp.dto.AulaDTO;
import org.tp.dto.FechaDTO;
import org.tp.dto.ReservaDTO;
import org.tp.dto.ResultadoDTO;
import org.tp.entity.Aula;
import org.tp.entity.Periodo;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class GestorAula {
    private static AulaDAO aulaDAO = new AulaDAO();
    private static ReservaDAO reservaDAO = new ReservaDAO();
    private static PeriodoDAO periodoDAO = new PeriodoDAO();
    private static ResultadoDTO resultadoDTO = new ResultadoDTO();


    /** listaFechaDTO lista de fechas para verificar disponibilidad.
     * fechaDTO se almacenarán las aulas disponibles.
     * Se retorna ResultadoDTO actualizado con aulas disponibles.
     */
    public ResultadoDTO obtenerDisponibilidad(ReservaDTO reservaDTO, FechaDTO fechaDTO) {

        List<FechaDTO> listaFechasDTO;

        //CHEQUEAMOS SI ES ESPORADICA O PERIODICA
        if (reservaDTO.getIdPeriodo() != null) {
            Periodo periodo = periodoDAO.getPeriodo(reservaDTO.getIdPeriodo());
            listaFechasDTO = crearListaFechas(reservaDTO, fechaDTO, periodo);
        } else {
            listaFechasDTO = crearListaFechas(reservaDTO, fechaDTO, null);
        }

        List<Aula> aulasFiltradas = aulaDAO.getAulas(reservaDTO.getTipoAula(), reservaDTO.getCantAlumnos());

        List<Aula> aulas = filtrarAulasReservadas(aulasFiltradas, listaFechasDTO);

        ResultadoDTO reservasMenosSolapadas;
        if (aulas.isEmpty()) {
            reservasMenosSolapadas = reservaDAO.menosSolapadas(aulasFiltradas, listaFechasDTO);
            return reservasMenosSolapadas;
        } else {

            ResultadoDTO resultadoDTO = new ResultadoDTO();
            resultadoDTO.setListaAulasDisponibles(new ArrayList<>());

            for (Aula aula : aulas) {
                AulaDTO aulaDTO = new AulaDTO();
                aulaDTO.setIdAula(aula.getIdAula());
                aulaDTO.setNombre(aula.getNombre());
                aulaDTO.setUbicacion(aula.getUbicacion());
                aulaDTO.setCapacidad(aula.getCapacidad());
                aulaDTO.setTipo(aula.getTipo());
                aulaDTO.setNro_aula(aula.getNro_aula());
                aulaDTO.setPiso(aula.getPiso());
                aulaDTO.setCantidad_pcs(aula.getCantidad_pcs());
                aulaDTO.setTipoPizarron(aula.getTipoPizarron());
                aulaDTO.setCanion(aula.getCanion());
                aulaDTO.setAire_acondicionado(aula.getAireAcondicionado());
                aulaDTO.setVentiladores(aula.getVentiladores());
                resultadoDTO.getListaAulasDisponibles().add(aulaDTO);
            }

            resultadoDTO.setFechasDTO(listaFechasDTO);
            return resultadoDTO;
        }
    }


    public List<FechaDTO> agregarIdAula (List<FechaDTO> fechasDTO , Long idAula) {
        for(FechaDTO fechaDTO : fechasDTO) {
            fechaDTO.setIdAula(idAula);
        }
        return fechasDTO;
    }

    public List<FechaDTO> crearListaFechas (ReservaDTO reservaDTO, FechaDTO fechaDTO , Periodo periodo) {

        List<FechaDTO> listaFechasDTO = new ArrayList<>();
        if(fechaDTO.getDia() == null) {
            listaFechasDTO.add(fechaDTO);
        }else{
            LocalDate fechaInicio = periodo.getFechaInicio();
            LocalDate fechaFin = periodo.getFechaFin();
            DayOfWeek diaDeLaSemana = convertirTextoADayOfWeek(fechaDTO.getDia());
            LocalDate fechaI = fechaInicio;
            while (!fechaI.isAfter(fechaFin)) {
                if (fechaI.getDayOfWeek() == diaDeLaSemana) {
                    FechaDTO fechaDTONuevo = new FechaDTO();
                    fechaDTONuevo.setFecha(fechaI);
                    fechaDTONuevo.setHorarioInicio(fechaDTO.getHorarioInicio());
                    fechaDTONuevo.setHorarioFin(fechaDTO.getHorarioFin());
                    fechaDTONuevo.setDuracion(fechaDTO.getDuracion());
                    fechaDTONuevo.setDia(fechaDTO.getDia());
                    listaFechasDTO.add(fechaDTONuevo);
                }
                fechaI = fechaI.plusDays(1);
            }
        }
        return listaFechasDTO;
    }

    public List<Aula> filtrarAulasReservadas(List<Aula> aulasFiltradas, List<FechaDTO> listaFechasDTO) {
        return reservaDAO.obtenerAulasDisponibles(aulasFiltradas, listaFechasDTO);
    }

    private static DayOfWeek convertirTextoADayOfWeek(String diaTexto) {
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

