package org.tp.dao;

import org.tp.entity.Periodo;

public interface PeriodoDAOImpl {

    Periodo getPeriodoByNombre (String nombrePeriodo);

    Periodo getPeriodoById (Long idPeriodo);

}
