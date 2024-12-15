package org.tp;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import org.tp.interfaces.AgregarDia;
import org.tp.interfaces.AsignarAula;
import org.tp.interfaces.ReservaEsporadica;

public class Main {

    public static void main(String[] args) {
        FlatMacDarkLaf.setup();
        new AsignarAula();
    }

}