package com.backend.digitalhouse.coworking.dto.modificacion.calificacion;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CalificacionModificacionEntradaDto {
    @NotNull(message = "El campo no puede ser nulo")
    private Long id;
    @Pattern(regexp = "^[1-5]$", message = "La calificacion debe tener un numero de 1 a 5")
    private int puntuacion;

    public CalificacionModificacionEntradaDto() {
    }

    public CalificacionModificacionEntradaDto(Long id, int puntuacion) {
        this.id = id;
        this.puntuacion = puntuacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    @Override
    public String toString() {
        return "CalificacionModificacionEntradaDto{" +
                "id=" + id +
                ", puntuacion=" + puntuacion +
                '}';
    }
}