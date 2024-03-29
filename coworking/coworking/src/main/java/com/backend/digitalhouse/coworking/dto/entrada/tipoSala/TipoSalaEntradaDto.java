package com.backend.digitalhouse.coworking.dto.entrada.tipoSala;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TipoSalaEntradaDto {

    @Size(max = 100, message = "El nombre debe tener hasta 100 caracteres")
    @NotNull(message = "El nombre no puede ser nulo")
    @NotBlank(message = "Debe especificarse el nombre de la categoría de la sala")
    private String nombre;

    @Size(max = 500, message = "La descripción debe tener hasta 500 caracteres")
    @NotNull(message = "La descripción no puede ser nula")
    @NotBlank(message = "Debe especificarse la descripción de la categoría de la sala")
    private String descripcion;
    private String imagen;

    public TipoSalaEntradaDto() {
    }

    public TipoSalaEntradaDto(String nombre, String descripcion, String imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "TipoSalaEntradaDto{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", imagen='" + imagen + '\'' +
                '}';
    }
}
