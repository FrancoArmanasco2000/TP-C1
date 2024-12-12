package org.tp.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.tp.entity.Aula;
import org.tp.entity.Periodo;
import org.tp.utils.TipoAula;

import java.util.List;

public class AulaDAO implements AulaDAOImpl {

    private static EntityManager manager;
    private static EntityManagerFactory factory;

    public AulaDAO () {};

    @Override
    public Aula getAulaByNroAula (Integer nroAula) {
        factory = Persistence.createEntityManagerFactory("Aplicacion");
        manager = factory.createEntityManager();

        try {
            manager.getTransaction().begin();
            String hql = "SELECT a FROM Aula a WHERE a.nro_aula = :nroAula";
            Query query = manager.createQuery(hql);
            query.setParameter("nroAula", nroAula);

            Aula aulaByNro = (Aula) query.getSingleResult();
            manager.getTransaction().commit();
            return aulaByNro;
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

    @Override
    public List<Aula> getAulasByCapacidadYTipo (Integer capacidad, TipoAula tipo) {
        factory = Persistence.createEntityManagerFactory("Aplicacion");
        manager = factory.createEntityManager();

        try {
            manager.getTransaction().begin();
            String hql = "SELECT a FROM Aula a WHERE a.capacidad >= :capacidad AND a.tipo == :tipo";
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
