package com.backend.digitalhouse.coworking.dto.salida.reserva;

import com.backend.digitalhouse.coworking.dto.salida.calificacion.CalificacionSalidaDto;
import java.time.LocalDateTime;

public class ReservaSalidaDto {
    private Long id;
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
    private CalificacionSalidaDto calificacion;
    private int cantidadHora;

    public ReservaSalidaDto() {
    }

    public ReservaSalidaDto(Long id, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin, CalificacionSalidaDto calificacion, int cantidadHora) {
        this.id = id;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.calificacion = calificacion;
        this.cantidadHora = cantidadHora;
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public LocalDateTime getFechaHoraInicio() {return fechaHoraInicio;}
    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {this.fechaHoraInicio = fechaHoraInicio;}

    public LocalDateTime getFechaHoraFin() {return fechaHoraFin;}
    public void setFechaHoraFin(LocalDateTime fechaHoraFin) {this.fechaHoraFin = fechaHoraFin;}

    public CalificacionSalidaDto getCalificacion() {return calificacion;}
    public void setCalificacion(CalificacionSalidaDto calificacion) {this.calificacion = calificacion;}

    public int getCantidadHora() {return cantidadHora;}
    public void setCantidadHora(int cantidadHora) {this.cantidadHora = cantidadHora;}

    @Override
    public String toString() {
        return "ReservaSalidaDto{" +
                "id=" + id +
                ", fechaHoraInicio=" + fechaHoraInicio +
                ", fechaHoraFin=" + fechaHoraFin +
                ", calificacion=" + calificacion +
                ", cantidadHora=" + cantidadHora +
                '}';
    }
}
