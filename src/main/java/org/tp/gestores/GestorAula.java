package org.tp.gestores;

import org.tp.dao.AulaDAO;
import org.tp.dao.ReservaDAO;
import org.tp.dto.AulaDTO;
import org.tp.dto.FechaDTO;
import org.tp.dto.ReservaDTO;
import org.tp.dto.ResultadoDTO;
import org.tp.entity.Aula;
import org.tp.entity.Reserva;
import org.tp.utils.TipoAula;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GestorAula {
    private static AulaDAO aulaDAO = new AulaDAO();
    private static ReservaDAO reservaDAO = new ReservaDAO();
    private static ResultadoDTO resultadoDTO = new ResultadoDTO();

    /** listaFechaDTO lista de fechas para verificar disponibilidad.
     * reservaDTO se almacenarán las aulas disponibles.
     * Se retorna ResultadoDTO actualizado con aulas disponibles.
     */
    public ResultadoDTO obtenerDisponibilidadAulas(List<FechaDTO> listaFechaDTO, ReservaDTO reservaDTO) {
        //Obtener todas las aulas que cumplen con la capacidad y el tipo del ReservaDTO
        List<Aula> aulasFiltradas = getAulas(reservaDTO.getTipoAula(), reservaDTO.getCantAlumnos());

        //Filtrar aulas que ya están reservadas en las fechas especificadas
        List<Aula> aulas = filtrarAulasReservadas(aulasFiltradas, listaFechaDTO);
        if(aulas.isEmpty()) {
            ResultadoDTO reservasMenosSolapadas = reservaDAO.menosSolapadas(aulasFiltradas, listaFechaDTO);
        }
        else{
            List<AulaDTO> aulasDTOFiltradas = convertirAulasADTO(aulasFiltradas);
            ResultadoDTO.setListaAulasDisponibles(aulasDTOFiltradas);
        }

        //Mapear aulas disponibles a AulaDTO y asignarlas a ReservaDTO
        List<AulaDTO> aulasDTO = new ArrayList<>();
        for (Aula aula : aulasFiltradas) {
            AulaDTO aulaDTO = new AulaDTO();
            aulaDTO.setNro_aula(aula.getNro_aula());
            aulaDTO.setNombre(aula.getNombre());
            aulaDTO.setUbicacion(aula.getUbicacion());
            aulaDTO.setCapacidad(aula.getCapacidad());
            aulaDTO.setTipo(aula.getTipo());
            aulaDTO.setAire_acondicionado(aula.getAire_acondicionado());
            aulaDTO.setCanion(aula.getCanion());
            aulaDTO.setCantidad_pcs(aula.getCantidad_pcs());
            aulasDTO.add(aulaDTO);
        }
        if(aulas.isEmpty()) {}
        //Actualizar ResultadoDTO con las aulas disponibles
        resultadoDTO.setListaAulasDisponibles(aulasDTO);
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
}
