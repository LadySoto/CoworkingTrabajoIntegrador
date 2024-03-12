package com.backend.digitalhouse.coworking.dto.entrada.tipoSala;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    public TipoSalaEntradaDto() {
    }

    public TipoSalaEntradaDto(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
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

    @Override
    public String toString() {
        return "TipoSalaEntradaDto{" +
                "nombre='" + nombre + '\'' +
                ", descripción=" + descripcion +
                '}';
    }
}
