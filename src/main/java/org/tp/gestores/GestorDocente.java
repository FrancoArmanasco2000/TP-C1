package org.tp.gestores;

import java.util.ArrayList;
import java.util.List;

public class GestorDocente {

    public List<String> getDocentes () {
        List<String> docentes = new ArrayList<String>();
        docentes.add("Cristian Impini");
        docentes.add("Santiago Marnetto");
        docentes.add("Claudio Bracalenti");
        docentes.add("Juan Puppo");

        return docentes;
    }

}
