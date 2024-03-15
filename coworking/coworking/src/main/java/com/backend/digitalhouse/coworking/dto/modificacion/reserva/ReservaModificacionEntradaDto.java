package com.backend.digitalhouse.coworking.dto.modificacion.reserva;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReservaModificacionEntradaDto {
    @NotNull(message = "El campo no puede ser nulo")
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private long fechaHoraInicio;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private long fechaHoraFin;
    private long idCalificacion;
    private long cantidadHora;

    public ReservaModificacionEntradaDto() {
    }

    public ReservaModificacionEntradaDto(Long id, long fechaHoraInicio, long fechaHoraFin, long idCalificacion, long cantidadHora) {
        this.id = id;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.idCalificacion = idCalificacion;
        this.cantidadHora = cantidadHora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public long getIdCalificacion() {
        return idCalificacion;
    }

    public void setIdCalificacion(long idCalificacion) {
        this.idCalificacion = idCalificacion;
    }

    public long getCantidadHora() {
        return cantidadHora;
    }

    public void setCantidadHora(long cantidadHora) {
        this.cantidadHora = cantidadHora;
    }

    @Override
    public String toString() {
        return "ReservaModificacionEntradaDto{" +
                "id=" + id +
                ", fechaHoraInicio=" + fechaHoraInicio +
                ", fechaHoraFin=" + fechaHoraFin +
                ", idCalificacion=" + idCalificacion +
                ", cantidadHora=" + cantidadHora +
                '}';
    }
}