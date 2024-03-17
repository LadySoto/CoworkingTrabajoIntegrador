package com.backend.digitalhouse.coworking.dto.salida.servicioSala;
import com.backend.digitalhouse.coworking.dto.salida.sala.SalaSalidaDto;

public class ServicioSalaSalidaDto {

    private Long id;
    private ServicioSalaSalidaDto servicioSalaSalidaDto;
    private SalaSalidaDto salaSalidaDto;

    public ServicioSalaSalidaDto() {
    }

    public ServicioSalaSalidaDto(Long id, ServicioSalaSalidaDto servicioSalaSalidaDto, SalaSalidaDto salaSalidaDto) {
        this.id = id;
        this.servicioSalaSalidaDto = servicioSalaSalidaDto;
        this.salaSalidaDto = salaSalidaDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ServicioSalaSalidaDto getServicioSalaSalidaDto() {
        return servicioSalaSalidaDto;
    }

    public void setServicioSalaSalidaDto(ServicioSalaSalidaDto servicioSalaSalidaDto) {
        this.servicioSalaSalidaDto = servicioSalaSalidaDto;
    }

    public SalaSalidaDto getSalaSalidaDto() {
        return salaSalidaDto;
    }

    public void setSalaSalidaDto(SalaSalidaDto salaSalidaDto) {
        this.salaSalidaDto = salaSalidaDto;
    }

    @Override
    public String toString() {
        return "ServicioSalaSalidaDto{" +
                "id=" + id +
                ", servicio=" + servicioSalaSalidaDto +
                ", sala=" + salaSalidaDto +
                '}';
    }
}
