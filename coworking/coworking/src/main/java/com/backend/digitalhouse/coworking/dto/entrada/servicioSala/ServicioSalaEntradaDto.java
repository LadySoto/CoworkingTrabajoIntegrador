package com.backend.digitalhouse.coworking.dto.entrada.servicioSala;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ServicioSalaEntradaDto {
    @NotNull(message = "Este campo no puede ser nulo")
    private long idServicio;

    @NotNull(message = "Este campo no puede ser nulo")
    private long idSala;

    public ServicioSalaEntradaDto() {
    }

    public ServicioSalaEntradaDto(long idServicio, long idSala) {
        this.idServicio = idServicio;
        this.idSala = idSala;
    }

    public long getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(long idServicio) {
        this.idServicio = idServicio;
    }

    public long getIdSala() {
        return idSala;
    }

    public void setIdSala(long idSala) {
        this.idSala = idSala;
    }

    @Override
    public String toString() {
        return "ServicioSalaEntradaDto{" +
                "idServicio=" + idServicio +
                ", idSala=" + idSala +
                '}';
    }
}
