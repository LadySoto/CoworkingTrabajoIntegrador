package com.backend.digitalhouse.coworking.dto.modificacion.reservaEspacio;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReservaEspacioModificacionEntradaDto {
    @NotNull(message = "El campo no puede ser nulo")
    private Long id;

    @NotNull(message = "El campo no puede ser nulo")
    private Long idUsuario;

    @NotNull(message = "El campo no puede ser nulo")
    private Long idSala;

    @NotNull(message = "El campo no puede ser nulo")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private int fechaHoraInicio;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private int fechaHoraFin;

    @NotNull(message = "El campo no puede ser nulo")
    private int cantidadHora;

    public ReservaEspacioModificacionEntradaDto() {
    }

    public ReservaEspacioModificacionEntradaDto(Long id, Long idUsuario, Long idSala, int fechaHoraInicio, int fechaHoraFin, int cantidadHora) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idSala = idSala;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.cantidadHora = cantidadHora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(int fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public int getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(int fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public int getCantidadHora() {
        return cantidadHora;
    }

    public void setCantidadHora(int cantidadHora) {
        this.cantidadHora = cantidadHora;
    }

    @Override
    public String toString() {
        return "ReservaEspacio{" +
                "id=" + id +
                ", idUsuario=" + idUsuario +
                ", idSala=" + idSala +
                ", fecha y hora inicial=" + fechaHoraInicio +
                ", fecha y hora final=" + fechaHoraFin +
                ", cantidad de horas=" + cantidadHora +
                '}';
    }
}


