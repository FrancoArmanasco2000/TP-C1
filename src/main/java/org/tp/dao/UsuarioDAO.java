package org.tp.dao;

import jakarta.persistence.*;
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
            //System.out.println(bedelByUsuario);
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

    public Bedel getBedelByidUsuario(Long idUsuario) {
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
            e.printStackTrace();
            return null;
        } finally {
            manager.close();
            factory.close();
        }
    }

    public void actualizarBedel(Bedel bedel) {
        factory = Persistence.createEntityManagerFactory("Aplicacion");
        manager = factory.createEntityManager();
        try {
            manager.getTransaction().begin();

            Bedel bedelExistente = manager.find(Bedel.class, bedel.getIdUsuario());
            if (bedelExistente != null) {
                bedelExistente.setBorrado(bedel.getBorrado());
                manager.merge(bedelExistente);
            } else {
                System.out.println("El Bedel no se encontró para actualizar.");
            }

            manager.getTransaction().commit();
        } catch (Exception e) {
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (manager.isOpen()) {
                manager.close();
            }
            if (factory.isOpen()) {
                factory.close();
            }
        }
    }

    public List<Bedel> obtenerTodosLosBedeles() {
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

    public List<Bedel> buscarBedelesPorNombre(String nombre) {
        factory = Persistence.createEntityManagerFactory("Aplicacion");
        manager = factory.createEntityManager();
        List<Bedel> bedeles = null;

        try {
            manager.getTransaction().begin();
            TypedQuery<Bedel> query = manager.createQuery(
                    "SELECT b FROM Bedel b WHERE b.nombre LIKE :nombre AND b.borrado = false",
                    Bedel.class
            );
            query.setParameter("nombre", "%" + nombre + "%");
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

    public List<Bedel> buscarBedelesPorTurno(String turno) {
        factory = Persistence.createEntityManagerFactory("Aplicacion");
        manager = factory.createEntityManager();
        List<Bedel> bedeles = null;

        try {
            manager.getTransaction().begin();
            TypedQuery<Bedel> query = manager.createQuery(
                    "SELECT b FROM Bedel b WHERE b.turno = :turno AND b.borrado = false",
                    Bedel.class
            );
            query.setParameter("turno", turno);
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
    public void eliminarBedel(Long idUsuario) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Bedel bedel = usuarioDAO.getBedelByidUsuario(idUsuario);

        if (bedel != null) {
            bedel.setBorrado(true);
            usuarioDAO.actualizarBedel(bedel);
        } else {
            System.out.println("No se encontró el Bedel con ID: " + idUsuario);
        }
    }


}