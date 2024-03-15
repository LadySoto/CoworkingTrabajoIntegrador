package com.backend.digitalhouse.coworking.dto.entrada.servicioSala;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.validation.constraints.NotNull;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ServicioSalaEntradaDto {

    @NotNull(message = "Este campo no puede ser nulo")
    private List<Long> idServicio;

    @NotNull(message = "Este campo no puede ser nulo")
    private long idSala;

    public ServicioSalaEntradaDto() {
    }

    public ServicioSalaEntradaDto(List<Long> idServicio, long idSala) {
        this.idServicio = idServicio;
        this.idSala = idSala;
    }

    public List<Long> getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(List<Long> idServicio) {
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
