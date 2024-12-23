package org.tp.dto;

import java.util.List;

public class ResultadoDTO {
    private List<AulaDTO> listaAulasDisponibles;
    private List<ReservaDTO> reservasSolapadas;
    private List<FechaDTO> fechasDTO;
    private double minimaCantidadSolapada;

    public double getMinimaCantidadSolapada() {
        return minimaCantidadSolapada;
    }

    public void setMinimaCantidadSolapada(double minimaCantidadSolapada) {
        this.minimaCantidadSolapada = minimaCantidadSolapada;
    }

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

    public List<FechaDTO> getFechasDTO() {
        return fechasDTO;
    }
    public void setFechasDTO(List<FechaDTO> fechasDTO) {
        this.fechasDTO = fechasDTO;
    }
}
