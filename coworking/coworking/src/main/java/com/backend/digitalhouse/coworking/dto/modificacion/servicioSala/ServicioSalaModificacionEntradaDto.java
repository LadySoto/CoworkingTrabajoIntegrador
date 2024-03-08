package com.backend.digitalhouse.coworking.dto.modificacion.servicioSala;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ServicioSalaModificacionEntradaDto {
    @NotNull(message = "Este campo no puede ser nulo")
    private Long id;
    private long idServicio;
    private long idSala;

    public ServicioSalaModificacionEntradaDto() {
    }

    public ServicioSalaModificacionEntradaDto(Long id, long idServicio, long idSala) {
        this.id = id;
        this.idServicio = idServicio;
        this.idSala = idSala;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return "ServicioSalaModificacionEntradaDto{" +
                "id=" + id +
                ", idServicio=" + idServicio +
                ", idSala=" + idSala +
                '}';
    }
}
