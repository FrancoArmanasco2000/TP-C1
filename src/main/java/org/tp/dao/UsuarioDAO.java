package org.tp.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.tp.entity.Administrador;
import org.tp.entity.Bedel;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements UsuarioDAOImpl{

    private static EntityManager manager; //Gestor de la base de datos
    private static EntityManagerFactory factory;

    public UsuarioDAO() {

    }

    @Override
    public void crearUsuario(Bedel bedel) { //Esto tendria que ser un dto
        factory = Persistence.createEntityManagerFactory("Aplicacion");
        manager = factory.createEntityManager();
        try {
            manager.getTransaction().begin();
            manager.persist(bedel);
            manager.getTransaction().commit();
        }catch(Exception e) {
            if(manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
        }
    }

    public List<String> listaNombreUsuarios() {
        factory = Persistence.createEntityManagerFactory("Aplicacion");
        manager = factory.createEntityManager();
        try {
            manager.getTransaction().begin();

            String hql = "SELECT usuario FROM Bedel";
            Query query = manager.createQuery(hql);

            List<String> nombreUsuarios = query.getResultList();
            manager.getTransaction().commit();
            return nombreUsuarios;
        }catch (Exception e) {
            if(manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
                return null;
            }
        }
        return null;
    }
}
