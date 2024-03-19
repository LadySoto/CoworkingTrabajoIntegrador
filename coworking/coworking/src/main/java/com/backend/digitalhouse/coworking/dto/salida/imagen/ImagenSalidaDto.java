package com.backend.digitalhouse.coworking.dto.salida.imagen;

import com.backend.digitalhouse.coworking.dto.salida.sala.SalaSalidaDto;

public class ImagenSalidaDto {
    private Long id;
    private String nombre;
    private String imagen;
    private SalaSalidaDto salaSalidaDto;

    public ImagenSalidaDto() {
    }

    public ImagenSalidaDto(Long id, String nombre, String imagen, SalaSalidaDto salaSalidaDto) {
        this.id = id;
        this.nombre = nombre;
        this.imagen = imagen;
        this.salaSalidaDto = salaSalidaDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public SalaSalidaDto getSalaSalidaDto() {
        return salaSalidaDto;
    }

    public void setSalaSalidaDto(SalaSalidaDto salaSalidaDto) {
        this.salaSalidaDto = salaSalidaDto;
    }

    @Override
    public String toString() {
        return "ImagenSalidaDto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", imagen='" + imagen + '\'' +
                ", sala=" + salaSalidaDto +
                '}';
    }
}

