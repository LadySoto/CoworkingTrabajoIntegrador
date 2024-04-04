package com.backend.digitalhouse.coworking.dto.entrada.reservaEspacio;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReservaEspacioEntradaDto {
    @NotNull(message = "Este campo no puede ser nulo")
    private Long idUsuario;
    @NotNull(message = "Este campo no puede ser nulo")
    private Long idSala;
    @FutureOrPresent(message = "La fecha no puede ser anterior al dia de hoy")
    @NotNull(message = "Este campo no puede ser nulo, debe especificar fecha y hora de la reserva")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime fechaHoraInicio;
    @FutureOrPresent(message = "La fecha no puede ser anterior al dia de hoy")
    @NotNull(message = "Este campo no puede ser nulo, debe especificar fecha y hora de la reserva")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime fechaHoraFin;
    private int cantidadPersonas;
    private int calificacion;

    public ReservaEspacioEntradaDto() {
    }

    public ReservaEspacioEntradaDto(Long idUsuario, Long idSala, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin, int cantidadPersonas, int calificacion) {
        this.idUsuario = idUsuario;
        this.idSala = idSala;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.cantidadPersonas = cantidadPersonas;
        this.calificacion = calificacion;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdSala() {
        return idSala;
    }

    public void setIdSala(Long idSala) {
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

    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(int cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
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
                ", cantidadPersonas=" + cantidadPersonas +
                ", calificacion=" + calificacion +
                '}';
    }
}
