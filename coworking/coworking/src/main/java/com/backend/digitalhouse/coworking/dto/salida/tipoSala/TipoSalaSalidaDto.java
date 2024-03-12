package com.backend.digitalhouse.coworking.dto.salida.tipoSala;

public class TipoSalaSalidaDto {

    private Long id;
    private String nombre;

    private String descripcion;

    public TipoSalaSalidaDto() {
    }

    public TipoSalaSalidaDto(Long id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
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

    @Override
    public String toString() {
        return "TipoSalaSalidaDto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion= " + descripcion +
                '}';
    }
}
