package com.backend.digitalhouse.coworking.dto.salida.sala;

public class TipoSalaSalidaDto {

    private Long id;
    private String nombre;

    public TipoSalaSalidaDto() {
    }

    public TipoSalaSalidaDto(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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

    @Override
    public String toString() {
        return "Id: " + id + " - Nombre: " + nombre;
    }
}
