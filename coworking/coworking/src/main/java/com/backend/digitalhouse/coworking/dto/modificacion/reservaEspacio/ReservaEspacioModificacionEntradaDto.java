package com.backend.digitalhouse.coworking.dto.modificacion.reservaEspacio;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReservaEspacioModificacionEntradaDto {

    private long idUsuario;
    private long idReserva;
    private long idSala;
    private long idServicio;

    public ReservaEspacioModificacionEntradaDto() {
    }

    public ReservaEspacioModificacionEntradaDto(long idUsuario, long idReserva, long idSala, long idServicio) {
        this.idUsuario = idUsuario;
        this.idReserva = idReserva;
        this.idSala = idSala;
        this.idServicio = idServicio;
    }

    public long getIdUsuario() {return idUsuario;}
    public void setIdUsuario(long idUsuario) {this.idUsuario = idUsuario;}

    public long getIdReserva() {return idReserva;}
    public void setIdReserva(long idReserva) {this.idReserva = idReserva;}

    public long getIdSala() {return idSala;}
    public void setIdSala(long idSala) {this.idSala = idSala;}

    public long getIdServicio() {return idServicio;}
    public void setIdServicio(long idServicio) {this.idServicio = idServicio;}
}
