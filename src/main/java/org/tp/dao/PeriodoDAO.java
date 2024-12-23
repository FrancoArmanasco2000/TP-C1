package org.tp.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.tp.entity.Periodo;

public class PeriodoDAO{

    private static EntityManager manager;
    private static EntityManagerFactory factory;

    public PeriodoDAO () {}


    public Periodo getPeriodoByNombre (String nombrePeriodo) {
        factory = Persistence.createEntityManagerFactory("Aplicacion");
        manager = factory.createEntityManager();

        try {
            manager.getTransaction().begin();
            String hql = "SELECT p FROM Periodo p WHERE p.nombre = :nombrePeriodo";
            Query query = manager.createQuery(hql);
            query.setParameter("nombrePeriodo", nombrePeriodo);

            Periodo periodoByIdUsuario = (Periodo) query.getSingleResult();
            manager.getTransaction().commit();
            return periodoByIdUsuario;
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


    public Periodo getPeriodo (Long idPeriodo) {
        factory = Persistence.createEntityManagerFactory("Aplicacion");
        manager = factory.createEntityManager();

        try {
            manager.getTransaction().begin();
            String hql = "SELECT p FROM Periodo p WHERE p.idPeriodo = :idPeriodo";
            Query query = manager.createQuery(hql);
            query.setParameter("idPeriodo", idPeriodo);
            Periodo periodo = (Periodo) query.getSingleResult();
            manager.getTransaction().commit();
            System.out.println(periodo);
            return periodo;
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
