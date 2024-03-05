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

    public TipoSalaEntradaDto() {
    }

    public TipoSalaEntradaDto(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "TipoSalaEntradaDto{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
