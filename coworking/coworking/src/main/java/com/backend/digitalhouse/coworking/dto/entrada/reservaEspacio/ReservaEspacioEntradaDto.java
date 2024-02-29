package com.backend.digitalhouse.coworking.dto.entrada.reservaEspacio;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReservaEspacioEntradaDto {
    @NotNull(message = "Este campo no puede ser nulo")
    private long idUsuario;
    @NotNull(message = "Este campo no puede ser nulo")
    private long idReserva;
    @NotNull(message = "Este campo no puede ser nulo")
    private long idSala;
    @NotNull(message = "Este campo no puede ser nulo")
    private long idServicio;

    public ReservaEspacioEntradaDto() {
    }

    public ReservaEspacioEntradaDto(long idUsuario, long idReserva, long idSala, long idServicio) {
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
