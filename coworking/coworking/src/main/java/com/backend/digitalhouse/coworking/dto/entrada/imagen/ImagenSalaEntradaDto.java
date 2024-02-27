package com.backend.digitalhouse.coworking.dto.entrada.imagen;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ImagenSalaEntradaDto {

    @NotNull(message = "Este campo no puede ser nulo")
    private long idImagen;

    @NotNull(message = "Este campo no puede ser nulo")
    private long idSala;

    public ImagenSalaEntradaDto() {
    }

    public ImagenSalaEntradaDto(long idImagen, long idSala) {
        this.idImagen = idImagen;
        this.idSala = idSala;
    }

    public long getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(long idImagen) {
        this.idImagen = idImagen;
    }

    public long getIdSala() {
        return idSala;
    }

    public void setIdSala(long idSala) {
        this.idSala = idSala;
    }
}
