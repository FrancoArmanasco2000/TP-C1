package org.tp.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import org.tp.entity.Fecha;
import org.tp.entity.Reserva;
import org.tp.entity.Aula;


public class FechaDAO{
//    private static EntityManager manager;
//    private static EntityManagerFactory factory;
//    @Override
//    public void crearFecha(Fecha fecha) {
//        factory = Persistence.createEntityManagerFactory("Aplicacion");
//        manager = factory.createEntityManager();
//        try {
//            if (fecha.getAula() != null) {
//                Aula aulaManaged = manager.find(Aula.class, fecha.getAula().getIdAula());
//                fecha.setAula(aulaManaged);
//            }
//
//
//            if (fecha.getReserva() != null) {
//                Reserva reservaManaged = manager.find(Reserva.class, fecha.getReserva().getIdReserva());
//                fecha.setReserva(reservaManaged);
//            }
//
//            manager.getTransaction().begin();
//            manager.merge(fecha);
//            manager.getTransaction().commit();
//        }catch(Exception e) {
//            if(manager.getTransaction().isActive()) {
//                manager.getTransaction().rollback();
//            }
//        } finally {
//            manager.close();
//            factory.close();
//        }
//    }
}
