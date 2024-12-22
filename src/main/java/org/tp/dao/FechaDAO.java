package org.tp.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import org.tp.entity.Fecha;
import org.tp.entity.Reserva;
import org.tp.entity.Aula;


public class FechaDAO{
    private static EntityManager manager;
    private static EntityManagerFactory factory;

    public void crearFecha(Fecha fecha) {
        factory = Persistence.createEntityManagerFactory("Aplicacion");
        manager = factory.createEntityManager();
        try {
            manager.getTransaction().begin();
            manager.persist(fecha);
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
}
