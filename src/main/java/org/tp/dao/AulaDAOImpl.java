package org.tp.dao;


import org.tp.entity.Aula;
import org.tp.utils.TipoAula;

import java.util.List;

public interface AulaDAOImpl {

    Aula getAulaByNombreAula(String nombreAula);

    List<Aula> getAulasByCapacidadYTipo(Integer capacidad, TipoAula tipo);

    Aula getAulaById (Long idAula);

}
