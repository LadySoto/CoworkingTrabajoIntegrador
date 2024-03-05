package com.backend.digitalhouse.coworking.dto.entrada.reserva;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ReservaEntradaDto {
    @NotNull(message = "Este campo no puede ser nulo, debe especificar fecha y hora de la reserva")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private long fechaHoraInicio;
    @NotNull(message = "Este campo no puede ser nulo, debe especificar fecha y hora de la reserva")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private long fechaHoraFin;
    @NotNull(message = "Este campo no puede ser nulo")
    private long idCalificacion;
    private long cantidadHora;

    public ReservaEntradaDto() {
    }

    public ReservaEntradaDto(long fechaHoraInicio, long fechaHoraFin, long idCalificacion, long cantidadHora) {
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.idCalificacion = idCalificacion;
        this.cantidadHora = cantidadHora;
    }

    public long getFechaHoraInicio() {return fechaHoraInicio;}
    public void setFechaHoraInicio(long fechaHoraInicio) {this.fechaHoraInicio = fechaHoraInicio;}

    public long getFechaHoraFin() {return fechaHoraFin;}
    public void setFechaHoraFin(long fechaHoraFin) {this.fechaHoraFin = fechaHoraFin;}

    public long getIdCalificacion() {return idCalificacion;}
    public void setIdCalificacion(long idCalificacion) {this.idCalificacion = idCalificacion;}

    public long getCantidadHora() {return cantidadHora;}
    public void setCantidadHora(long cantidadHora) {this.cantidadHora = cantidadHora;}

    @Override
    public String toString() {
        return "ReservaEntradaDto{" +
                "fechaHoraInicio=" + fechaHoraInicio +
                ", fechaHoraFin=" + fechaHoraFin +
                ", idCalificacion=" + idCalificacion +
                ", cantidadHora=" + cantidadHora +
                '}';
    }
}


