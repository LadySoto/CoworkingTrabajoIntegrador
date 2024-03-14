package com.backend.digitalhouse.coworking.dto.entrada.imagenSala;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.validation.constraints.NotNull;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ImagenSalaEntradaDto {

    @NotNull(message = "Este campo no puede ser nulo")
    private List<Long> idImagen;

    @NotNull(message = "Este campo no puede ser nulo")
    private long idSala;

    public ImagenSalaEntradaDto() {
    }

    public ImagenSalaEntradaDto(List<Long> idImagen, long idSala) {
        this.idImagen = idImagen;
        this.idSala = idSala;
    }

    public List<Long> getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(List<Long> idImagen) {
        this.idImagen = idImagen;
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
                "idImagenes=" + idImagen +
                ", idSala=" + idSala +
                '}';
    }
}
