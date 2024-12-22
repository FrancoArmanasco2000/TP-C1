package org.tp.excepciones;

import java.time.LocalDate;
import java.util.ArrayList;

import static java.util.stream.Collectors.toList;

public class FechaException extends Exception{

    public FechaException(String fechas) {
        super("Estos dias ya pasaron " + "\n" + fechas);
    }

}
