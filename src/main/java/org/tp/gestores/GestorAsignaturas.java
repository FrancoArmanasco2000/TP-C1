package org.tp.gestores;

import java.util.ArrayList;
import java.util.List;

public class GestorAsignaturas {

    public List<String> getAsignaturas() {
        List<String> asignaturas = new ArrayList<String>();
        asignaturas.add("Diseño de sistemas");
        asignaturas.add("Base de datos");
        asignaturas.add("Desarrollo de software");
        asignaturas.add("Redes de datos");

        return asignaturas;
    }

}
