package com.backend.digitalhouse.coworking.dto.salida.servicioSala;

import com.backend.digitalhouse.coworking.dto.salida.sala.SalaSalidaDto;
import com.backend.digitalhouse.coworking.dto.salida.servicio.ServicioSalidaDto;

public class ServicioSalaSalidaDto {

    private Long id;
    private ServicioSalidaDto idServicio;
    private SalaSalidaDto idSala;

    public ServicioSalaSalidaDto() {
    }

    public ServicioSalaSalidaDto(Long id, ServicioSalidaDto idServicio, SalaSalidaDto idSala) {
        this.id = id;
        this.idServicio = idServicio;
        this.idSala = idSala;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ServicioSalidaDto getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(ServicioSalidaDto idServicio) {
        this.idServicio = idServicio;
    }

    public SalaSalidaDto getIdSala() {
        return idSala;
    }

    public void setIdSala(SalaSalidaDto idSala) {
        this.idSala = idSala;
    }

    @Override
    public String toString() {
        return "ServicioSalaSalidaDto{" +
                "id=" + id +
                ", idServicio=" + idServicio +
                ", idSala=" + idSala +
                '}';
    }
}
