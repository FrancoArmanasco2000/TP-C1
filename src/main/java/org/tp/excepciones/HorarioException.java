package org.tp.excepciones;

public class HorarioException extends Exception {

    public HorarioException(String fechas) {
        super("Fechas con horarios mal establecidos " + "\n" + fechas);
    }

}
