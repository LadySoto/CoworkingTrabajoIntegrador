package com.backend.digitalhouse.coworking.dto.modificacion.imagen;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ImagenModificacionEntradaDto {
    @NotNull(message = "El campo no puede ser nulo")
    private Long id;
    @Size(max = 100, message = "El nombre de la imagen debe tener hasta 100 caracteres")
    private String nombre;

    @Size(max = 100, message = "La ruta la imagen debe tener hasta 100 caracteres")
    private String imagen;

    private Long idSala;

    public ImagenModificacionEntradaDto() {
    }

    public ImagenModificacionEntradaDto(Long id, String nombre, String imagen) {
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
        return "ImagenModificacionEntradaDto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", imagen='" + imagen + '\'' +
                '}';
    }
}
