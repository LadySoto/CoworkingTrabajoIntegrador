package com.backend.digitalhouse.coworking.dto.salida.servicioSala;

import com.backend.digitalhouse.coworking.dto.salida.sala.SalaSalidaDto;
import com.backend.digitalhouse.coworking.dto.salida.servicio.ServicioSalidaDto;

public class ServicioSalaSalidaDto {
    private Long id;
    private ServicioSalidaDto servicio;
    private SalaSalidaDto sala;

    public ServicioSalaSalidaDto() {
    }

    public ServicioSalaSalidaDto(Long id, ServicioSalidaDto servicio, SalaSalidaDto sala) {
        this.id = id;
        this.servicio = servicio;
        this.sala = sala;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ServicioSalidaDto getServicio() {
        return servicio;
    }

    public void setServicio(ServicioSalidaDto servicio) {
        this.servicio = servicio;
    }

    public SalaSalidaDto getSala() {
        return sala;
    }

    public void setSala(SalaSalidaDto sala) {
        this.sala = sala;
    }

    @Override
    public String toString() {
        return "ServicioSalaSalidaDto{" +
                "id=" + id +
                ", servicio=" + servicio +
                ", sala=" + sala +
                '}';
    }
}
