package com.backend.digitalhouse.coworking.dto.entrada.imagenTipoSala;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.validation.constraints.NotNull;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ImagenTipoSalaEntradaDto {

    @NotNull(message = "Este campo no puede ser nulo")
    private List<Long> idImagen;

    @NotNull(message = "Este campo no puede ser nulo")
    private long idTipoSala;

    public ImagenTipoSalaEntradaDto() {
    }

    public ImagenTipoSalaEntradaDto(List<Long> idImagen, long idTipoSala) {
        this.idImagen = idImagen;
        this.idTipoSala = idTipoSala;
    }

    public List<Long> getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(List<Long> idImagen) {
        this.idImagen = idImagen;
    }

    public long getIdTipoSala() {
        return idTipoSala;
    }

    public void setIdTipoSala(long idTipoSala) {
        this.idTipoSala = idTipoSala;
    }

    @Override
    public String toString() {
        return "ImagenSalaEntradaDto{" +
                "idImagenes=" + idImagen +
                ", idTipoSala=" + idTipoSala +
                '}';
    }
}
