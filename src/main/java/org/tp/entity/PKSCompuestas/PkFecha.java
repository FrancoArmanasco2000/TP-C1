package org.tp.entity.PKSCompuestas;

import org.tp.entity.Aula;
import org.tp.entity.Reserva;

import java.io.Serializable;
import java.time.LocalDate;

public class PkFecha implements Serializable {

    private LocalDate fecha;
    private String horarioInicio;
    private Aula aula;
    private Reserva reserva;

}
