package org.tp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.tp.dao.AulaDAO;
import org.tp.dao.PeriodoDAO;
import org.tp.entity.Administrador;
import org.tp.entity.Aula;

import java.util.GregorianCalendar;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        AulaDAO ad = new AulaDAO();

        System.out.println(ad.getAulasByCapacidad(60));
    }

}