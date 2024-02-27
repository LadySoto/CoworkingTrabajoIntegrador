package com.backend.digitalhouse.coworking.dto.salida.imagenProducto;

public class SalaProductoSalidaDto {

    private String nombre;
    private String descripcion;

    public SalaProductoSalidaDto() {
    }

    public SalaProductoSalidaDto(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
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
        return "Nombre: " + nombre + ",Descripción: " + descripcion;
    }
}
