package org.tp.dao;

import org.tp.entity.Periodo;

public interface PeriodoDAOImpl {

    public Periodo getPeriodoByNombre (String nombrePeriodo);

    public Periodo getPeriodoById (Long idPeriodo);

}
