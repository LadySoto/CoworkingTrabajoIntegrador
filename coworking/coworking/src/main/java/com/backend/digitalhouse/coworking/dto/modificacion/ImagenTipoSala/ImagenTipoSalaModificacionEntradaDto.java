package com.backend.digitalhouse.coworking.dto.modificacion.ImagenTipoSala;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.validation.constraints.NotNull;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ImagenTipoSalaModificacionEntradaDto {
    @NotNull(message = "El campo no puede ser nulo")
    private Long id;
    private List<Long> idImagen;
    private long idTipoSala;

    public ImagenTipoSalaModificacionEntradaDto() {
    }

    public ImagenTipoSalaModificacionEntradaDto(Long id, List<Long> idImagen, long idTipoSala) {
        this.id = id;
        this.idImagen = idImagen;
        this.idTipoSala = idTipoSala;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return "ImagenTipoSalaModificacionEntradaDto{" +
                "id=" + id +
                ", idImagen=" + idImagen +
                ", idTipoSala=" + idTipoSala +
                '}';
    }
}
