package com.backend.digitalhouse.coworking.dto.salida.reservaEspacio;

import com.backend.digitalhouse.coworking.dto.salida.reserva.ReservaSalidaDto;
import com.backend.digitalhouse.coworking.dto.salida.sala.SalaSalidaDto;
import com.backend.digitalhouse.coworking.dto.salida.servicio.ServicioSalidaDto;
import com.backend.digitalhouse.coworking.dto.salida.usuario.UsuarioSalidaDto;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ReservaEspacioSalidaDto {
    private Long id;
    private UsuarioSalidaDto usuario;
    private SalaSalidaDto sala;
    private long fechaHoraInicio;

    private long fechaHoraFin;

    private int cantidadHora;

    public ReservaEspacioSalidaDto() {
    }

    public ReservaEspacioSalidaDto(Long id, UsuarioSalidaDto usuario, SalaSalidaDto sala, long fechaHoraInicio, long fechaHoraFin, int cantidadHora) {
        this.id = id;
        this.usuario = usuario;
        this.sala = sala;
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
                ", usuario=" + usuario +
                ", sala=" + sala +
                ", fecha y hora inicial=" + fechaHoraInicio +
                ", fecha y hora final=" + fechaHoraFin +
                ", cantidad de horas=" + cantidadHora +
                '}';
    }
}
