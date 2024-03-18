package com.backend.digitalhouse.coworking.dto.salida.reservaEspacio;

import com.backend.digitalhouse.coworking.dto.salida.sala.SalaSalidaDto;
import com.backend.digitalhouse.coworking.dto.salida.usuario.UsuarioSalidaDto;

public class ReservaEspacioCalificadaSalidaDto {
    private Long id;
    private UsuarioSalidaDto usuario;
    private SalaSalidaDto sala;
    private int calificacion;

    public ReservaEspacioCalificadaSalidaDto() {
    }

    public ReservaEspacioCalificadaSalidaDto(Long id, UsuarioSalidaDto usuario, SalaSalidaDto sala, int calificacion) {
        this.id = id;
        this.usuario = usuario;
        this.sala = sala;
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
                ", Usuario=" + usuario +
                ", Sala=" + sala +
                ", calificación=" + calificacion +
                '}';
    }
}
