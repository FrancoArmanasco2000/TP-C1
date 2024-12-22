package org.tp.dao;

import jakarta.persistence.*;
import org.tp.entity.Bedel;

import java.util.List;

public class UsuarioDAO implements UsuarioDAOImpl{

    private static EntityManager manager;
    private static EntityManagerFactory factory;

    public UsuarioDAO() {
    }

    public Bedel getUsuario(String usuario) {
        factory = Persistence.createEntityManagerFactory("Aplicacion");
        manager = factory.createEntityManager();
        try {
            manager.getTransaction().begin();
            String hql = "SELECT b FROM Bedel b WHERE b.usuario = :usuario";
            Query query = manager.createQuery(hql);
            query.setParameter("usuario", usuario);

            Bedel bedel = (Bedel) query.getSingleResult();
            manager.getTransaction().commit();
            return bedel;
        } catch (Exception e) {
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
                e.printStackTrace();
                return null;
            }
        } finally {
            manager.close();
        }
        return null;
    }

    public void crearUsuario(Bedel bedel) {
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
        } finally {
            manager.close();
            factory.close();
        }
    }

    public Bedel getUsuarioById(Long idUsuario) {
        factory = Persistence.createEntityManagerFactory("Aplicacion");
        manager = factory.createEntityManager();
        try {
            manager.getTransaction().begin();
            // Corregir la consulta HQL para usar un parámetro nombrado
            String hql = "SELECT b FROM Bedel b WHERE b.idUsuario = :idUsuario";
            Query query = manager.createQuery(hql);
            query.setParameter("idUsuario", idUsuario);

            // Usar getSingleResult en lugar de getResult para obtener un solo objeto
            Bedel bedelByIdUsuario = (Bedel) query.getSingleResult();
            manager.getTransaction().commit();
            return bedelByIdUsuario;
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
            return null;
        } finally {
            if (manager != null) {
                manager.close();
            }
            factory.close();
        }
    }

    public void actualizarUsuario(Bedel bedel) {
        factory = Persistence.createEntityManagerFactory("Aplicacion");
        manager = factory.createEntityManager();
        try {
            manager.getTransaction().begin();
            manager.merge(bedel);
            manager.getTransaction().commit();
        } catch (Exception e) {
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
        } finally {
            manager.close();
        }
    }

    public List<Bedel> obtenerTodosLosUsuarios() {
        factory = Persistence.createEntityManagerFactory("Aplicacion");
        manager = factory.createEntityManager();
        List<Bedel> bedeles = null;

        try {
            manager.getTransaction().begin();
            TypedQuery<Bedel> query = manager.createQuery("SELECT b FROM Bedel b WHERE b.borrado = false", Bedel.class);
            bedeles = query.getResultList();
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            manager.close();
        }

        return bedeles;
    }

    public List<Bedel> getBedeles (String apellido , String turno) {
        factory = Persistence.createEntityManagerFactory("Aplicacion");
        manager = factory.createEntityManager();

        try {
            manager.getTransaction().begin();
            String hql;
            Query query;
            if(apellido.isBlank()) {
                hql = "SELECT b FROM Bedel b WHERE b.turno = :turno";
                query = manager.createQuery(hql);
                query.setParameter("turno", turno);
            } else {
                hql = "SELECT b FROM Bedel b WHERE b.apellido = :apellido";
                query = manager.createQuery(hql);
                query.setParameter("apellido", apellido);
            }

            List<Bedel> bedeles = query.getResultList();
            manager.getTransaction().commit();
            return bedeles;
        } catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
            return null;
        } finally {
            manager.close();
        }
    }


}