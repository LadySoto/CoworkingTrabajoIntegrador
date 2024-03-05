package com.backend.digitalhouse.coworking.dto.entrada.imagenSala;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ImagenSalaEntradaDto {

    @NotNull(message = "Este campo no puede ser nulo")
    private long idImagenes;

    @NotNull(message = "Este campo no puede ser nulo")
    private long idSala;

    public ImagenSalaEntradaDto() {
    }

    public ImagenSalaEntradaDto(long idImagen, long idSala) {
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
        return "ImagenSalaEntradaDto{" +
                "idImagenes=" + idImagenes +
                ", idSala=" + idSala +
                '}';
    }
}
