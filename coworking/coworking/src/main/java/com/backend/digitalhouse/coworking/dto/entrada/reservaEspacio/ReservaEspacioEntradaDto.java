package com.backend.digitalhouse.coworking.dto.entrada.reservaEspacio;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReservaEspacioEntradaDto {
    @NotNull(message = "Este campo no puede ser nulo")
    private long idUsuario;
    @NotNull(message = "Este campo no puede ser nulo")
    private long idSala;
    @FutureOrPresent(message = "La fecha no puede ser anterior al dia de hoy")
    @NotNull(message = "Este campo no puede ser nulo, debe especificar fecha y hora de la reserva")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime fechaHoraInicio;
    @FutureOrPresent(message = "La fecha no puede ser anterior al dia de hoy")
    @NotNull(message = "Este campo no puede ser nulo, debe especificar fecha y hora de la reserva")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime fechaHoraFin;
    @Digits(integer = 1, fraction = 0, message = "El numero debe tener como maximo 1 dígito de 1 a 5")
    @Min(1)
    @Max(5)
    private int calificacion;

    public ReservaEspacioEntradaDto() {
    }

    public ReservaEspacioEntradaDto(long idUsuario, long idSala, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin, int calificacion) {
        this.idUsuario = idUsuario;
        this.idSala = idSala;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.calificacion = calificacion;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public long getIdSala() {
        return idSala;
    }

    public void setIdSala(long idSala) {
        this.idSala = idSala;
    }

    public LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public LocalDateTime getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    @Override
    public String toString() {
        return "ReservaEspacioEntradaDto{" +
                "idUsuario=" + idUsuario +
                ", idSala=" + idSala +
                ", fechaHoraInicio=" + fechaHoraInicio +
                ", fechaHoraFin=" + fechaHoraFin +
                ", calificacion=" + calificacion +
                '}';
    }
}
