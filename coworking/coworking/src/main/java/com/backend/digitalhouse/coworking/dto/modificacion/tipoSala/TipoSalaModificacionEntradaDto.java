package com.backend.digitalhouse.coworking.dto.modificacion.tipoSala;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TipoSalaModificacionEntradaDto {
    @NotNull(message = "El campo no puede ser nulo")
    private Long id;
    @Size(max = 100, message = "El nombre debe tener hasta 100 caracteres")
    private String nombre;

    public TipoSalaModificacionEntradaDto() {
    }

    public TipoSalaModificacionEntradaDto(String nombre) {
        this.nombre = nombre;
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

    @Override
    public String toString() {
        return "TipoSalaModificacionEntradaDto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
