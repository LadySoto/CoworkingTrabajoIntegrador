package com.backend.digitalhouse.coworking.dto.modificacion.reservaEspacio;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

public class ReservaEspacioCalificadaModificacionEntradaDto {
    @NotNull(message = "El campo no puede ser nulo")
    private Long id;

    @NotNull(message = "El campo no puede ser nulo")
    private Long idUsuario;

    @NotNull(message = "El campo no puede ser nulo")
    private Long idSala;

    @NotNull(message = "El campo no puede ser nulo")
    private int calificacion;

    public ReservaEspacioCalificadaModificacionEntradaDto() {
    }

    public ReservaEspacioCalificadaModificacionEntradaDto(Long id, Long idUsuario, Long idSala, int calificacion) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idSala = idSala;
        this.calificacion = calificacion;
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

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    @Override
    public String toString() {
        return "ReservaEspacio{" +
                "id=" + id +
                ", idUsuario=" + idUsuario +
                ", idSala=" + idSala +
                ", calificación=" + calificacion +
                '}';
    }
}
