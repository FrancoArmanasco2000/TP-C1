package org.tp.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jdk.jfr.Timestamp;
import org.tp.dto.FechaDTO;
import org.tp.entity.Aula;
import org.tp.entity.Fecha;
import org.tp.entity.Periodo;
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

            List<Integer> horariosA = FechaUtils.convertirHoras(fechas.get(0).getHorarioInicio(), fechas.get(0));

            List<LocalDate> fechasLista = fechas.stream()
                    .map(FechaDTO::getFecha)
                    .collect(Collectors.toList());
            List<Long> idsAulas = aulasFiltradas.stream()
                    .map(Aula::getIdAula)
                    .collect(Collectors.toList());

            manager.getTransaction().begin();
            String hql = "SELECT f FROM Fecha f WHERE f.fecha IN :fechas AND f.aula.idAula IN :idsAulas";
            Query query = manager.createQuery(hql);
            query.setParameter("fechas", fechasLista);
            query.setParameter("idsAulas", idsAulas);
            List<Fecha> fechasReservas = query.getResultList();

            Map<Long, List<Fecha>> fechasPorAula = new HashMap<>();

            for(Fecha f : fechasReservas) {
                if(fechasPorAula.containsKey(f.getAula().getIdAula())) {
                    fechasPorAula.get(f.getAula().getIdAula()).add(f);
                }else {
                    fechasPorAula.put(f.getAula().getIdAula(), new ArrayList<>());
                    fechasPorAula.get(f.getAula().getIdAula()).add(f);
                }
            }

            for(Aula a: aulasFiltradas) {
                if(!fechasPorAula.containsKey(a.getIdAula())) {
                    aulasDefinitivas.add(a);
                }
            }

            for(int i = 0; i<fechasPorAula.keySet().size(); i++) { //RECORREMOS TODAS LAS LLAVES DEL MAP (OSEA AULAS)
                boolean flag = true;
                List<Fecha> fechasAula = fechasPorAula.get(fechasPorAula.keySet().toArray()[i]); //AGARRAMOS LA LISTA DE FECHAS QUE TIENE CADA LLAVA (AULA) EN EL MAP
                for(Fecha f : fechasAula) { //RECORREMOS TODAS LAS FEHCAS
                    List<Integer> horariosB = FechaUtils.convertirHoras(f.getHorarioInicio(),f);
                    if(horariosA.get(0) > horariosB.get(0) && horariosA.get(0) < horariosB.get(1)
                        || horariosA.get(1) > horariosB.get(0) && horariosA.get(1) < horariosB.get(1)) {
                        flag = false;
                   }
                   if(!flag) {
                       break;
                    }
                }
                if(flag) {
                    aulasDefinitivas.add(aulasFiltradas.get(i));
                }
            }

            return aulasDefinitivas;

        } catch (Exception e){
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            manager.close();
            factory.close();
        }
    }
}
