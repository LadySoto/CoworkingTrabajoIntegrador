package com.backend.digitalhouse.coworking.dto.entrada.servicioSala;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ServicioSalaEntradaDto {

    @NotNull(message = "Este campo no puede ser nulo")
    private List<Long> idServicios;

    @NotNull(message = "Este campo no puede ser nulo")
    private long idSala;

    public ServicioSalaEntradaDto() {
    }

    public ServicioSalaEntradaDto(List<Long> idServicios, long idSala) {
        this.idServicios = idServicios;
        this.idSala = idSala;
    }

    public List<Long> getIdServicios() {
        return idServicios;
    }

    public void setIdServicios(List<Long> idServicios) {
        this.idServicios = idServicios;
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
                "idServicio=" + idServicios +
                ", idSala=" + idSala +
                '}';
    }
}
