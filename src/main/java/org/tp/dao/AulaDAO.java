package org.tp.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.tp.entity.Aula;
import org.tp.utils.TipoAula;

import java.util.List;

public class AulaDAO {

    private static EntityManager manager;
    private static EntityManagerFactory factory;

    public AulaDAO () {}

    public Aula getAulaByNombreAula (String nombreAula) {
        factory = Persistence.createEntityManagerFactory("Aplicacion");
        manager = factory.createEntityManager();

        try {
            manager.getTransaction().begin();
            String hql = "SELECT a FROM Aula a WHERE a.nombre = :nombreAula";
            Query query = manager.createQuery(hql);
            query.setParameter("nombreAula", nombreAula);

            Aula aulaByNombre = (Aula) query.getSingleResult();
            manager.getTransaction().commit();
            return aulaByNombre;
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

    public Aula getAula (Long idAula) {
        factory = Persistence.createEntityManagerFactory("Aplicacion");
        manager = factory.createEntityManager();

        try {
            manager.getTransaction().begin();
            String hql = "SELECT a FROM Aula a WHERE a.idAula = :idAula";
            Query query = manager.createQuery(hql);
            query.setParameter("idAula", idAula);

            Aula aulaById = (Aula) query.getSingleResult();
            manager.getTransaction().commit();
            return aulaById;
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


    public List<Aula> getAulas (String tipo, Integer capacidad) {
        factory = Persistence.createEntityManagerFactory("Aplicacion");
        manager = factory.createEntityManager();

        try {
            manager.getTransaction().begin();
            String hql = "SELECT a FROM Aula a WHERE a.capacidad >= :capacidad AND a.tipo = :tipo";
            Query query = manager.createQuery(hql);
            query.setParameter("capacidad", capacidad);
            query.setParameter("tipo", tipo);

            List<Aula> aulasByCapacidad =  query.getResultList();
            manager.getTransaction().commit();
            return aulasByCapacidad;
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

