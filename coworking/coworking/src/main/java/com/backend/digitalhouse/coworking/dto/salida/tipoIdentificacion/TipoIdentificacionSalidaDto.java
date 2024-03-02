package com.backend.digitalhouse.coworking.dto.salida.tipoIdentificacion;

public class TipoIdentificacionSalidaDto {
    private Long id;
    private String nombre;
    private int estado;

    public TipoIdentificacionSalidaDto() {
    }

    public TipoIdentificacionSalidaDto(Long id, String nombre, int estado) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public int getEstado() {return estado;}
    public void setEstado(int estado) {this.estado = estado;}

    @Override
    public String toString() {
        return "TipoIdentificacionSalidaDto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", estado=" + estado +
                '}';
    }
}
