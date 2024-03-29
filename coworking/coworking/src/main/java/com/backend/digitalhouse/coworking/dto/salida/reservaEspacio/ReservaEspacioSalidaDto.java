package com.backend.digitalhouse.coworking.dto.salida.reservaEspacio;

import com.backend.digitalhouse.coworking.dto.salida.sala.SalaSalidaDto;
import com.backend.digitalhouse.coworking.dto.salida.usuario.UsuarioSalidaDto;

import java.time.LocalDateTime;

public class ReservaEspacioSalidaDto {
    private Long id;
    private UsuarioSalidaDto usuario;
    private SalaSalidaDto sala;
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
    private int calificacion;

    public ReservaEspacioSalidaDto() {
    }

    public ReservaEspacioSalidaDto(Long id, UsuarioSalidaDto usuario, SalaSalidaDto sala, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin, int calificacion) {
        this.id = id;
        this.usuario = usuario;
        this.sala = sala;
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

    public UsuarioSalidaDto getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioSalidaDto usuario) {
        this.usuario = usuario;
    }

    public SalaSalidaDto getSala() {
        return sala;
    }

    public void setSala(SalaSalidaDto sala) {
        this.sala = sala;
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
        return "ReservaEspacioSalidaDto{" +
                "id=" + id +
                ", usuario=" + usuario +
                ", sala=" + sala +
                ", fechaHoraInicio=" + fechaHoraInicio +
                ", fechaHoraFin=" + fechaHoraFin +
                ", calificacion=" + calificacion +
                '}';
    }
}
