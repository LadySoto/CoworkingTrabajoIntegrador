package com.backend.digitalhouse.coworking.dto.modificacion.imagenSala;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ImagenSalaModificacionEntradaDto {
    @NotNull(message = "El campo no puede ser nulo")
    private Long id;
    private long idImagenes;
    private long idSala;

    public ImagenSalaModificacionEntradaDto() {
    }

    public ImagenSalaModificacionEntradaDto(Long id, long idImagenes, long idSala) {
        this.id = id;
        this.idImagenes = idImagenes;
        this.idSala = idSala;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getIdImagenes() {
        return idImagenes;
    }

    public void setIdImagenes(long idImagenes) {
        this.idImagenes = idImagenes;
    }

    public long getIdSala() {
        return idSala;
    }

    public void setIdSala(long idSala) {
        this.idSala = idSala;
    }

    @Override
    public String toString() {
        return "ImagenSalaModificacionEntradaDto{" +
                "id=" + id +
                ", idImagenes=" + idImagenes +
                ", idSala=" + idSala +
                '}';
    }
}