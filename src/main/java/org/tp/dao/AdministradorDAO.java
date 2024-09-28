package org.tp.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.tp.entity.Administrador;

import java.util.List;

public class AdministradorDAO {

    private static EntityManager manager; //Gestor de la base de datos
    private static EntityManagerFactory factory;

    public void guardarAdministrador(Administrador admin) {
        factory = Persistence.createEntityManagerFactory("Aplicacion");
        manager = factory.createEntityManager();
        try {
            manager.getTransaction().begin();
            manager.persist(admin);
            manager.getTransaction().commit();
        }catch(Exception e) {
            if(manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
        }
    }

    public void eliminarAdministrador(Administrador admin) {
        factory = Persistence.createEntityManagerFactory("Aplicacion");
        manager = factory.createEntityManager();
        try {
            manager.getTransaction().begin();
            manager.remove(admin);
            manager.getTransaction().commit();
        }catch (Exception e) {
            if(manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
        }
    }

    public Administrador traerAdministrador(int id) {
        factory = Persistence.createEntityManagerFactory("Aplicacion");
        manager = factory.createEntityManager();
        try {
            manager.getTransaction().begin();
            Administrador admin = manager.find(Administrador.class, id);
            manager.getTransaction().commit();

            return admin;
        }catch (Exception e) {
            if(manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
                System.out.println(e.getMessage());
                return null;
            }
            return null;
        }
    }

    public List<Administrador> traerAdministradores() {
        factory = Persistence.createEntityManagerFactory("Aplicacion");
        manager = factory.createEntityManager();
        try {
            manager.getTransaction().begin();

            String hql = "FROM Administrador";
            Query query = manager.createQuery(hql);

            List<Administrador> administradores = query.getResultList();
            for(Administrador adm : administradores) {
               System.out.println("ID: " + adm.getIdUsuario() + ", Name: "+ adm.getUsuario());
            }
            manager.getTransaction().commit();

            return administradores;
        }catch (Exception e) {
            if(manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
                return null;
            }
            return null;
        }
    }

    public void actualizarAdministrador(Administrador adminNuevo) {
        factory = Persistence.createEntityManagerFactory("Aplicacion");
        manager = factory.createEntityManager();

        try {
            manager.getTransaction().begin();
            Administrador admin = manager.find(Administrador.class, 1);
            admin.setUsuario(adminNuevo.getUsuario());
            admin.setContrasena(adminNuevo.getContrasena());

            manager.merge(admin);
            manager.getTransaction().commit();
        }catch (Exception e) {
            if(manager.getTransaction().isActive()) {}
        }
    }





}
