package org.tp.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.tp.entity.Administrador;
import org.tp.entity.Bedel;
import jakarta.persistence.NoResultException;

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

    public Bedel getBedelByUsuario(String usuarioInterfaz) {
        factory = Persistence.createEntityManagerFactory("Aplicacion");
        manager = factory.createEntityManager();
        try {
            manager.getTransaction().begin();
            // Corregir la consulta HQL para usar un parámetro nombrado
            String hql = "SELECT b FROM Bedel b WHERE b.usuario = :usuarioInterfaz";
            Query query = manager.createQuery(hql);
            query.setParameter("usuarioInterfaz", usuarioInterfaz);

            // Usar getSingleResult en lugar de getResult para obtener un solo objeto
            Bedel bedelByUsuario = (Bedel) query.getSingleResult();
            System.out.println(bedelByUsuario);
            manager.getTransaction().commit();
            return bedelByUsuario;
        } catch (NoResultException e) {
            System.out.println("No se encontró un usuario con ese nombre.");
            if (manager != null && manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
            return null;

        } catch (Exception e) {
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
