package com.backend.digitalhouse.coworking.dto.modificacion.calificacion;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CalificacionModificacionEntradaDto {
    @NotNull(message = "El campo no puede ser nulo")
    private Long id;
    @Pattern(regexp = "^[1-5]$", message = "La calificacion debe tener un numero de 1 a 5")
    private long puntuacion;

    public CalificacionModificacionEntradaDto() {
    }

    public CalificacionModificacionEntradaDto(long puntuacion) {
        this.puntuacion = puntuacion;
    }

    public long getPuntuacion() {return puntuacion;}
    public void setPuntuacion(long puntuacion) {this.puntuacion = puntuacion;}

    @Override
    public String toString() {
        return "CalificacionModificacionEntradaDto{" +
                "puntuacion=" + puntuacion +
                '}';
    }
}