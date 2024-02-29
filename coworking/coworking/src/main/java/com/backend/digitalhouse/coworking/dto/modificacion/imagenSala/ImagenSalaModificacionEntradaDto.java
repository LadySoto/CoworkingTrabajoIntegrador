package com.backend.digitalhouse.coworking.dto.modificacion.imagenSala;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ImagenSalaModificacionEntradaDto {

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
}