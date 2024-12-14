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
        List<Integer> horariosA = FechaUtils.convertirHoras(fechas.get(0).getHorarioInicio(), fechas.get(0));

        List<Aula> aulasDefinitivas = new ArrayList<>();

        for(Aula aula: aulasFiltradas) {
            Boolean flag = true;
            Long idAulaAux = aula.getIdAula();
            for(FechaDTO fecha: fechas) {
                LocalDate fechaAux = fecha.getFecha();
                manager.getTransaction().begin();
                String hql = "SELECT f FROM Fecha f WHERE f.fecha == :fechaAux AND f.aula.idAula == :idAulaAux";
                Query query = manager.createQuery(hql);
                query.setParameter("fechaAux", fechaAux);
                query.setParameter("idAulaAux", idAulaAux);
                List<Fecha> reservas  =  query.getResultList();

                for(Fecha f : reservas) {
                    List<Integer> horariosB = FechaUtils.convertirHoras(f.getHorarioInicio(),f);
                    if(horariosA.get(0) > horariosB.get(0) && horariosA.get(0) < horariosB.get(1)
                        || horariosA.get(1) > horariosB.get(0) && horariosA.get(1) < horariosB.get(1)) {
                        flag = false;
                    }
                    if(!flag) {
                        break;
                    }
                }
                if(!flag) {
                    break;
                }
            }
            if(flag) {
                aulasDefinitivas.add(aula);
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
