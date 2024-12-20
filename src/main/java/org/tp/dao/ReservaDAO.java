package org.tp.dao;

import jakarta.persistence.*;
import org.tp.dto.FechaDTO;
import org.tp.dto.ReservaDTO;
import org.tp.dto.ResultadoDTO;
import org.tp.entity.*;
import org.tp.utils.FechaUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReservaDAO {

//    private static EntityManager manager;
//    private static EntityManagerFactory factory;
//
//    public ReservaDAO() {
//    }
//
//    @Override
//    public void crearReserva(Reserva reserva) {
//        factory = Persistence.createEntityManagerFactory("Aplicacion");
//        manager = factory.createEntityManager();
//        try {
//            manager.getTransaction().begin();
//            manager.persist(reserva);
//            manager.getTransaction().commit();
//        } catch (Exception e) {
//            if (manager.getTransaction().isActive()) {
//                manager.getTransaction().rollback();
//            }
//        } finally {
//            manager.close();
//            factory.close();
//        }
//    }
//
//    @Override
//    public List<Aula> obtenerAulasDisponibles(List<Aula> aulasFiltradas, List<FechaDTO> fechas) {
//
//        factory = Persistence.createEntityManagerFactory("Aplicacion");
//        manager = factory.createEntityManager();
//
//        try {
//
//            List<Aula> aulasDefinitivas = new ArrayList<>();
//            //Arreglo de horarioInicio y horarioFin para cada fecha dentro de la lista listaFechasDTO
//            List<Integer> horariosA = FechaUtils.convertirHoras(fechas.getFirst().getHorarioInicio(), fechas.getFirst().getDuracion());
//
//            List<LocalDate> fechasLista = fechas.stream()
//                    .map(FechaDTO::getFecha)
//                    .collect(Collectors.toList());
//            List<Long> idsAulas = aulasFiltradas.stream()
//                    .map(Aula::getIdAula)
//                    .collect(Collectors.toList());
//
//            String hql = "SELECT f FROM Fecha f WHERE f.fecha IN :fechas AND f.aula.idAula IN :idsAulas"; //Devuelve todas las fechas que tengan asignadas las aulas filtradas
//            Query query = manager.createQuery(hql);
//            query.setParameter("fechas", fechasLista);
//            query.setParameter("idsAulas", idsAulas);
//            List<Fecha> fechasReservadas = query.getResultList();
//
//            Map<Long, List<Fecha>> fechasPorAula = new HashMap<>();
//
//            for (Fecha f : fechasReservadas) {  //Agrega al map cada par key-value usando como key el idAula y como value la lista de fechas
//                if (fechasPorAula.containsKey(f.getAula().getIdAula())) {
//                    fechasPorAula.get(f.getAula().getIdAula()).add(f);
//                } else {
//                    fechasPorAula.put(f.getAula().getIdAula(), new ArrayList<>());
//                    fechasPorAula.get(f.getAula().getIdAula()).add(f);
//                }
//            }
//
//            for (Aula a : aulasFiltradas) {   //Las aulas que no tengan asignadas ninguna fecha coincidente estan disponibles: las agregamos a la lista final
//                if (!fechasPorAula.containsKey(a.getIdAula())) {
//                    aulasDefinitivas.add(a);
//                }
//            }
//
//            for (Long idAula : fechasPorAula.keySet()) { //Recorremos todas las llaves del map (todos los idAulas)
//                boolean flagDisponible = true;
//                List<Fecha> fechasAula = fechasPorAula.get(idAula); //Guardamos la lista de fechas que tiene cada llave (aula) en el map
//                for (Fecha f : fechasAula) { //Recorremos las fechas de cada aula y comparamos horarios
//                    List<Integer> horariosB = FechaUtils.convertirHoras(f.getHorarioInicio(), f.getDuracion());
//                    if (FechaUtils.solapa(horariosA, horariosB)) {
//                        flagDisponible = false;
//                        break;
//                    }
//                }
//                if (flagDisponible) {
//                    aulasDefinitivas.add(aulasFiltradas.stream()
//                            .filter(a -> a.getIdAula().equals(idAula))
//                            .findFirst()
//                            .orElse(null));
//                }
//            }
//
//            return aulasDefinitivas;
//        } catch (Exception e) {
//            if (manager.getTransaction().isActive()) {
//                manager.getTransaction().rollback();
//            }
//            throw new RuntimeException("Error al obtener las aulas disponibles", e);
//        } finally {
//            manager.close();
//            factory.close();
//        }
//    }
//
//    public ResultadoDTO menosSolapadas(List<Aula> aulasFiltradas, List<FechaDTO> fechas) {
//        factory = Persistence.createEntityManagerFactory("Aplicacion");
//        manager = factory.createEntityManager();
//
//        List<ReservaDTO> reservasMenosSolapadasDTO = new ArrayList<>();
//        double minimaCantidad = Double.MAX_VALUE;
//
//        // Convertir fechas y horarios
//        List<Integer> horariosA = FechaUtils.convertirHoras(fechas.getFirst().getHorarioInicio(), fechas.getFirst().getDuracion());
//        List<LocalDate> fechasLista = fechas.stream()
//                .map(FechaDTO::getFecha)
//                .collect(Collectors.toList());
//        List<Long> idsAulas = aulasFiltradas.stream()
//                .map(Aula::getIdAula)
//                .collect(Collectors.toList());
//
//        String hql = "SELECT r.idReserva, r.cantidadAlumnos, r.correoContacto, "
//                + "f.horarioInicio, f.duracion, f.aula.idAula, r.idPeriodo.idPeriodo, f.fecha, r.asignatura, r.nombreDocente "
//                + "FROM Reserva r "
//                + "JOIN r.idPeriodo p "
//                + "JOIN Fecha f ON f.aula.idAula IN :idsAulas AND r.idReserva = f.reserva.idReserva "
//                + "WHERE f.fecha IN :fechas";
//
//        Query query = manager.createQuery(hql);
//        query.setParameter("fechas", fechasLista);
//        query.setParameter("idsAulas", idsAulas);
//
//        List<Object[]> resultados = query.getResultList();
//
//        // Estructura para agrupar reservas por aula
//        Map<Long, List<ReservaDTO>> reservasPorAula = new HashMap<>();
//
//        ResultadoDTO resultadoMenosSolapadasDTO = new ResultadoDTO();
//
//        // Procesar las filas devueltas por la consulta
//        for (Object[] row : resultados) {
//            Long idReserva = (Long) row[0];
//            Integer cantidadAlumnos = (Integer) row[1];
//            String correoContacto = (String) row[2];
//            String horarioInicio = (String) row[3];
//            Integer duracion = (Integer) row[4];
//            Long idAula = (Long) row[5];
//            LocalDate fecha = (LocalDate) row[7];
//            String asignatura = (String) row[8];
//            String nombreDocente = (String) row[9];
//
//            // Convertir el horario de la reserva a intervalos de tiempo
//            List<Integer> horariosB = FechaUtils.convertirHoras(horarioInicio, duracion);
//
//            // Verificar si los horarios solapan
//            if (FechaUtils.solapa(horariosA, horariosB)) {
//
//                ReservaDTO dto = new ReservaDTO(idReserva, cantidadAlumnos, correoContacto, horarioInicio, duracion, idAula, fecha);
//                dto.setNombreDocente(nombreDocente);
//                dto.setAsignatura(asignatura);
//                reservasPorAula.computeIfAbsent(idAula, k -> new ArrayList<>()).add(dto);
//            }
//        }
//
//        // Calcular la cantidad de solapamiento para cada aula
//        for (Map.Entry<Long, List<ReservaDTO>> entry : reservasPorAula.entrySet()) {
//            double cantidadSolapada = 0;
//            List<ReservaDTO> reservasSolapadas = entry.getValue();
//
//            for (ReservaDTO reserva : reservasSolapadas) {
//                List<Integer> horariosB = FechaUtils.convertirHoras(reserva.getHorarioInicio(), reserva.getDuracion());
//                cantidadSolapada=FechaUtils.calcularSolapamiento(horariosA, horariosB);
//            }
//
//            // Actualizar las reservas con menor solapamiento
//            if (cantidadSolapada < minimaCantidad) {
//                minimaCantidad = cantidadSolapada;
//                reservasMenosSolapadasDTO = new ArrayList<>(reservasSolapadas);
//
//            } else if (cantidadSolapada == minimaCantidad) {
//                for (ReservaDTO reserva : reservasSolapadas) {
//                    if (!reservasMenosSolapadasDTO.contains(reserva)) {
//                        reservasMenosSolapadasDTO.add(reserva);
//                    }
//                }
//            }
//            resultadoMenosSolapadasDTO.setMinimaCantidadSolapada(minimaCantidad);
//        }
//
//        resultadoMenosSolapadasDTO.setReservasSolapadas(reservasMenosSolapadasDTO);
//
//        return resultadoMenosSolapadasDTO;
//    }


}
