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

    public ImagenSalaModificacionEntradaDto(long idImagen, long idSala) {
        this.idImagenes = idImagen;
        this.idSala = idSala;
    }

    public long getIdImagen() {
        return idImagenes;
    }
    public void setIdImagen(long idImagen) {
        this.idImagenes = idImagen;
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
                "idImagenes=" + idImagenes +
                ", idSala=" + idSala +
                '}';
    }
}