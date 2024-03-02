package com.backend.digitalhouse.coworking.dto.salida.reservaEspacio;

import com.backend.digitalhouse.coworking.dto.salida.reserva.ReservaSalidaDto;
import com.backend.digitalhouse.coworking.dto.salida.sala.SalaSalidaDto;
import com.backend.digitalhouse.coworking.dto.salida.servicio.ServicioSalidaDto;
import com.backend.digitalhouse.coworking.dto.salida.usuario.UsuarioSalidaDto;

public class ReservaEspacioSalidaDto {
    private Long id;
    private UsuarioSalidaDto usuario;
    private ReservaSalidaDto reserva;
    private SalaSalidaDto sala;
    private ServicioSalidaDto servicio;

    public ReservaEspacioSalidaDto() {
    }

    public ReservaEspacioSalidaDto(Long id, UsuarioSalidaDto usuario, ReservaSalidaDto reserva, SalaSalidaDto sala, ServicioSalidaDto servicio) {
        this.id = id;
        this.usuario = usuario;
        this.reserva = reserva;
        this.sala = sala;
        this.servicio = servicio;
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public UsuarioSalidaDto getUsuario() {return usuario;}
    public void setUsuario(UsuarioSalidaDto usuario) {this.usuario = usuario;}

    public ReservaSalidaDto getReserva() {return reserva;}
    public void setReserva(ReservaSalidaDto reserva) {this.reserva = reserva;}

    public SalaSalidaDto getSala() {return sala;}
    public void setSala(SalaSalidaDto sala) {this.sala = sala;}

    public ServicioSalidaDto getServicio() {return servicio;}
    public void setServicio(ServicioSalidaDto servicio) {this.servicio = servicio;}

    @Override
    public String toString() {
        return "ReservaEspacioSalidaDto{" +
                "id=" + id +
                ", usuario=" + usuario +
                ", reserva=" + reserva +
                ", sala=" + sala +
                ", servicio=" + servicio +
                '}';
    }
}
