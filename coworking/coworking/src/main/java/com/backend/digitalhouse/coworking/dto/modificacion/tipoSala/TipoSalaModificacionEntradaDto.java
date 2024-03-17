package com.backend.digitalhouse.coworking.dto.modificacion.tipoSala;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@JsonIgnoreProperties(ignoreUnknown = true)
public class TipoSalaModificacionEntradaDto {
    @NotNull(message = "El campo no puede ser nulo")
    private Long id;
    @Size(max = 100, message = "El nombre debe tener hasta 100 caracteres")
    private String nombre;

    private String imagen;

    public TipoSalaModificacionEntradaDto() {
    }

    public TipoSalaModificacionEntradaDto(Long id, String nombre, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "TipoSalaModificacionEntradaDto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", imagen='" + imagen + '\'' +
                '}';
    }
}
