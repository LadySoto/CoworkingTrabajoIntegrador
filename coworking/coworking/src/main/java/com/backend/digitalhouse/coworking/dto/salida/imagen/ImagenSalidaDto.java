package com.backend.digitalhouse.coworking.dto.salida.imagen;

import com.backend.digitalhouse.coworking.dto.salida.sala.SalaSalidaDto;

public class ImagenSalidaDto {
    private Long id;
    private String nombre;
    private String imagen;
    private int estado;
    private SalaSalidaDto sala;

    public ImagenSalidaDto() {
    }

    public ImagenSalidaDto(Long id, String nombre, String imagen, int estado) {
        this.id = id;
        this.nombre = nombre;
        this.imagen = imagen;
        this.estado = estado;
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

    public int getEstado() {
        return estado;
    }
    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "ImagenSalidaDto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", imagen='" + imagen + '\'' +
                ", estado=" + estado +
                '}';
    }
}

