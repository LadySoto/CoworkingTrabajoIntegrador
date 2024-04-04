package com.backend.digitalhouse.coworking.dto.salida.reservaEspacio;

import java.time.LocalDateTime;

public class ReservaEspacioSalidaDto {
    private Long id;
    private UsuarioReservaSalidaDto usuarioReserva;
    private SalaReservaSalidaDto salaReserva;
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
    private int cantidadPersonas;
    private int calificacion;

    public ReservaEspacioSalidaDto() {
    }

    public ReservaEspacioSalidaDto(Long id, UsuarioReservaSalidaDto usuarioReserva, SalaReservaSalidaDto salaReserva, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin, int cantidadPersonas, int calificacion) {
        this.id = id;
        this.usuarioReserva = usuarioReserva;
        this.salaReserva = salaReserva;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.cantidadPersonas = cantidadPersonas;
        this.calificacion = calificacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioReservaSalidaDto getUsuarioReserva() {
        return usuarioReserva;
    }

    public void setUsuarioReserva(UsuarioReservaSalidaDto usuarioReserva) {
        this.usuarioReserva = usuarioReserva;
    }

    public SalaReservaSalidaDto getSalaReserva() {
        return salaReserva;
    }

    public void setSalaReserva(SalaReservaSalidaDto salaReserva) {
        this.salaReserva = salaReserva;
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
        return "ReservaEspacioSalidaDto{" +
                "id=" + id +
                ", usuarioReserva=" + usuarioReserva +
                ", salaReserva=" + salaReserva +
                ", fechaHoraInicio=" + fechaHoraInicio +
                ", fechaHoraFin=" + fechaHoraFin +
                ", cantidadPersonas=" + cantidadPersonas +
                ", calificacion=" + calificacion +
                '}';
    }
}
