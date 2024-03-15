package com.backend.digitalhouse.coworking.dto.entrada.calificacion;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CalificacionEntradaDto {
    @NotNull(message = "Este campo no puede ser nulo")
    @Digits(integer = 1, fraction = 0, message = "El numero debe tener como maximo 1 dígito de 1 a 5")
    @Min(1)
    @Max(5)
    private int puntuacion;

    public CalificacionEntradaDto() {
    }

    public CalificacionEntradaDto(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    @Override
    public String toString() {
        return "CalificacionEntradaDto{" +
                "puntuacion=" + puntuacion +
                '}';
    }
}

