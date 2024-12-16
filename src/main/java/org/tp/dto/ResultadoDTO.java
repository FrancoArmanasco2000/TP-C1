package org.tp.dto;

import java.util.List;

public class ResultadoDTO {
    public List<AulaDTO> listaAulasDisponibles;
    public List<ReservaDTO> reservasSolapadas;
    public double cantidadSolapada;

    public double getMinimaCantidadSolapada() {
        return minimaCantidadSolapada;
    }

    public void setMinimaCantidadSolapada(double minimaCantidadSolapada) {
        this.minimaCantidadSolapada = minimaCantidadSolapada;
    }

    private double minimaCantidadSolapada;

    public List<AulaDTO> getListaAulasDisponibles() {
        return listaAulasDisponibles;
    }
    public void setListaAulasDisponibles(List<AulaDTO> listaAulasDisponibles) {
        this.listaAulasDisponibles = listaAulasDisponibles;
    }
    public List<ReservaDTO> getReservasSolapadas() {
        return reservasSolapadas;
    }
    public void setReservasSolapadas(List<ReservaDTO> reservasSolapadas) {
        this.reservasSolapadas = reservasSolapadas;
    }

}
