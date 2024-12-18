package org.tp.gestores;

import org.tp.dao.AulaDAO;
import org.tp.dao.ReservaDAO;
import org.tp.dto.AulaDTO;
import org.tp.dto.FechaDTO;
import org.tp.dto.ReservaDTO;
import org.tp.dto.ResultadoDTO;
import org.tp.entity.Aula;
import org.tp.entity.Reserva;
import org.tp.utils.FechaUtils;
import org.tp.utils.TipoAula;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.tp.dto.ResultadoDTO.*;

public class GestorAula {
    private static AulaDAO aulaDAO = new AulaDAO();
    private static ReservaDAO reservaDAO = new ReservaDAO();
    private static ResultadoDTO resultadoDTO = new ResultadoDTO();

    /** listaFechaDTO lista de fechas para verificar disponibilidad.
     * reservaDTO se almacenarán las aulas disponibles.
     * Se retorna ResultadoDTO actualizado con aulas disponibles.
     */
    public ResultadoDTO obtenerDisponibilidadAulas(ReservaDTO reservaDTO, FechaDTO fechaDTO) {

        List<FechaDTO> listaFechasDTO = FechaUtils.crearListaFechas(reservaDTO, fechaDTO);

        //Obtener todas las aulas que cumplen con la capacidad y el tipo del ReservaDTO
        List<Aula> aulasFiltradas = getAulas(reservaDTO.getTipoAula(), reservaDTO.getCantAlumnos());

        //Filtrar aulas que ya están reservadas en las fechas especificadas
        List<Aula> aulas = filtrarAulasReservadas(aulasFiltradas, listaFechasDTO);

        if (aulas.isEmpty()) {
            resultadoDTO = reservaDAO.menosSolapadas(aulasFiltradas, listaFechasDTO);
        } else {
            List<AulaDTO> aulasDTODefinitivas = convertirAulasADTO(aulas);
            resultadoDTO.setListaAulasDisponibles(aulasDTODefinitivas);
        }
        return resultadoDTO;
    }

    public List<Aula> getAulas(TipoAula tipo, Integer capacidad) {
        return aulaDAO.getAulasByCapacidadYTipo(capacidad, tipo);
    }

    public List<Aula> filtrarAulasReservadas(List<Aula> aulasFiltradas, List<FechaDTO> listaFechasDTO) {
        return reservaDAO.obtenerAulasDisponibles(aulasFiltradas, listaFechasDTO);
    }

    private List<AulaDTO> convertirAulasADTO(List<Aula> aulas) {
        return aulas.stream()
                .map(aula -> {
                    AulaDTO aulaDTO = new AulaDTO();
                    aulaDTO.setNombre(aula.getNombre());
                    aulaDTO.setUbicacion(aula.getUbicacion());
                    aulaDTO.setCapacidad(aula.getCapacidad());
                    aulaDTO.setTipo(aula.getTipo());
                    aulaDTO.setNro_aula(aula.getNro_aula());
                    aulaDTO.setPiso(aula.getPiso());
                    aulaDTO.setCantidad_pcs(aula.getCantidad_pcs());
                    aulaDTO.setCanion(aula.getCanion());
                    aulaDTO.setAire_acondicionado(aula.getAire_acondicionado());
                    aulaDTO.setVentiladores(aula.getVentiladores());
                    return aulaDTO;
                })
                .collect(Collectors.toList());
    }

    public AulaDTO getAulaByNombre(String nombreAula) {
        Aula a = aulaDAO.getAulaByNombreAula(nombreAula);
        AulaDTO aulaDTO = new AulaDTO(
                a.getIdAula(),
                a.getNombre(),
                a.getUbicacion(),
                a.getCapacidad(),
                a.getTipo(),
                a.getNro_aula(),
                a.getPiso(),
                a.getCantidad_pcs(),
                a.getCanion(),
                a.getAire_acondicionado(),
                a.getVentiladores(),
                a.getTipo_pizarron()
        );
        return aulaDTO;
    }

    public void asignarAulaAFechasDelPeriodo(ReservaDTO reserva,FechaDTO fechaDTO,Long idAula) {
        List<FechaDTO> fechasDelPeriodo = FechaUtils.crearListaFechas(reserva,fechaDTO);
        for(FechaDTO f : fechasDelPeriodo) {
            f.setIdAula(idAula);
        }
        if(reserva.getListaFechasDTO() == null){
            reserva.setListaFechasDTO(fechasDelPeriodo);
        }else {
            reserva.getListaFechasDTO().addAll(fechasDelPeriodo);
        }
    }
}

