package org.tp.dto;

import java.util.List;

public class ResultadoDTO {
    private List<AulaDTO> listaAulasDisponibles;
    private List<ReservaDTO> reservasSolapadas;

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
