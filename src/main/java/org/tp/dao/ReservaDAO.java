package org.tp.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.tp.dto.FechaDTO;
import org.tp.entity.Aula;
import org.tp.entity.Fecha;
import org.tp.entity.Reserva;
import org.tp.utils.FechaUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReservaDAO implements ReservaDAOImpl{

    private static EntityManager manager;
    private static EntityManagerFactory factory;

    public ReservaDAO () {}

    @Override
    public void crearReserva(Reserva reserva) {
        factory = Persistence.createEntityManagerFactory("Aplicacion");
        manager = factory.createEntityManager();
        try {
            manager.getTransaction().begin();
            manager.persist(reserva);
            manager.getTransaction().commit();
        }catch(Exception e) {
            if(manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
        } finally {
            manager.close();
            factory.close();
        }
    }

    @Override
    public List<Aula> obtenerAulasDisponibles(List<Aula> aulasFiltradas, List<FechaDTO> fechas) {

        factory = Persistence.createEntityManagerFactory("Aplicacion");
        manager = factory.createEntityManager();

        try {

            List<Aula> aulasDefinitivas = new ArrayList<>();
            List<Integer> horariosA = FechaUtils.convertirHoras(fechas.getFirst().getHorarioInicio(), fechas.getFirst()); //Arreglo de horarioInicio y horarioFin para cada fecha dentro de la lista listaFechasDTO

            List<LocalDate> fechasLista = fechas.stream()
                    .map(FechaDTO::getFecha)
                    .collect(Collectors.toList());
            List<Long> idsAulas = aulasFiltradas.stream()
                    .map(Aula::getIdAula)
                    .collect(Collectors.toList());

            String hql = "SELECT f FROM Fecha f WHERE f.fecha IN :fechas AND f.aula.idAula IN :idsAulas"; //Devuelve todas las fechas que tengan asignadas las aulas filtradas
            Query query = manager.createQuery(hql);
            query.setParameter("fechas", fechasLista);
            query.setParameter("idsAulas", idsAulas);
            List<Fecha> fechasReservadas = query.getResultList();

            Map<Long, List<Fecha>> fechasPorAula = new HashMap<>();

            for(Fecha f : fechasReservadas) {  //Agrega al map cada par key-value usando como key el idAula y como value la lista de fechas
                if(fechasPorAula.containsKey(f.getAula().getIdAula())) {
                    fechasPorAula.get(f.getAula().getIdAula()).add(f);
                }else {
                    fechasPorAula.put(f.getAula().getIdAula(), new ArrayList<>());
                    fechasPorAula.get(f.getAula().getIdAula()).add(f);
                }
            }

            for(Aula a: aulasFiltradas) {   //Las aulas que no tengan asignadas ninguna fecha coincidente estan disponibles: las agregamos a la lista final
                if(!fechasPorAula.containsKey(a.getIdAula())) {
                    aulasDefinitivas.add(a);
                }
            }

            for(Long idAula: fechasPorAula.keySet()) { //Recorremos todas las llaves del map (todos los idAulas)
                boolean flagDisponible = true;
                List<Fecha> fechasAula = fechasPorAula.get(idAula); //Guardamos la lista de fechas que tiene cada llave (aula) en el map
                for(Fecha f : fechasAula) { //Recorremos las fechas de cada aula y comparamos horarios
                    List<Integer> horariosB = FechaUtils.convertirHoras(f.getHorarioInicio(),f);
                    if(FechaUtils.solapa(horariosA,horariosB)) {
                        flagDisponible = false;
                        break;
                   }
                }
                if(flagDisponible) {
                    aulasDefinitivas.add(aulasFiltradas.stream()
                            .filter(a -> a.getIdAula().equals(idAula))
                            .findFirst()
                            .orElse(null));
                }
            }

            return aulasDefinitivas;
        }
        catch (Exception e){
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
            throw new RuntimeException("Error al obtener las aulas disponibles", e);
        } finally {
            manager.close();
            factory.close();
        }
    }



}
