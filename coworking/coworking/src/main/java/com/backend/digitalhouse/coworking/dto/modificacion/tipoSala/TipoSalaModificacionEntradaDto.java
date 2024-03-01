package com.backend.digitalhouse.coworking.dto.modificacion.tipoSala;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TipoSalaModificacionEntradaDto {

    @Size(max = 100, message = "El nombre debe tener hasta 100 caracteres")
    private String nombre;

    public TipoSalaModificacionEntradaDto() {
    }

    public TipoSalaModificacionEntradaDto(String nombre) {
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
        return "TipoSalaModificacionEntradaDto{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
