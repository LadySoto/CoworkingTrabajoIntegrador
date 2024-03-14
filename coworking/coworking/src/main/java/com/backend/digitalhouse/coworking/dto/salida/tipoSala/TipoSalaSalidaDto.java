package com.backend.digitalhouse.coworking.dto.salida.tipoSala;

import java.util.List;

public class TipoSalaSalidaDto {

    private Long id;
    private String nombre;

    private String descripcion;
    private List<String> imagenes;

    public TipoSalaSalidaDto() {
    }

    public TipoSalaSalidaDto(Long id, String nombre, String descripcion, List<String> imagenes) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagenes = imagenes;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<String> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<String> imagenes) {
        this.imagenes = imagenes;
    }

    @Override
    public String toString() {
        return "TipoSalaSalidaDto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion= " + descripcion +
                ", imagenes= " + imagenes +
                '}';
    }
}
