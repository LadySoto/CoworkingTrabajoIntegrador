package com.backend.digitalhouse.coworking.dto.modificacion.reservaEspacio;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReservaEspacioModificacionEntradaDto {
    @NotNull(message = "El campo no puede ser nulo")
    private Long id;
    private long idUsuario;
    private long idSala;
    @FutureOrPresent(message = "La fecha no puede ser anterior al dia de hoy")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private long fechaHoraInicio;
    @FutureOrPresent(message = "La fecha no puede ser anterior al dia de hoy")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private long fechaHoraFin;
    @Digits(integer = 1, fraction = 0, message = "El numero debe tener como maximo 1 dígito de 1 a 5")
    @Min(1)
    @Max(5)
    private int calificacion;


    public ReservaEspacioModificacionEntradaDto() {
    }

    public ReservaEspacioModificacionEntradaDto(Long id, long idUsuario, long idSala, long fechaHoraInicio, long fechaHoraFin, int calificacion) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idSala = idSala;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.calificacion = calificacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public long getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(long fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public long getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(long fechaHoraFin) {
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
        return "ReservaEspacioModificacionEntradaDto{" +
                "id=" + id +
                ", idUsuario=" + idUsuario +
                ", idSala=" + idSala +
                ", fechaHoraInicio=" + fechaHoraInicio +
                ", fechaHoraFin=" + fechaHoraFin +
                ", calificacion=" + calificacion +
                '}';
    }
}


