package org.tp.dao;

import org.tp.dto.AulaDTO;
import org.tp.entity.Aula;

import java.util.List;

public interface AulaDAOImpl {

    public Aula getAulaByNroAula(Integer nroAula);

    public List<Aula> getAulasByCapacidad(Integer capacidad);

}
