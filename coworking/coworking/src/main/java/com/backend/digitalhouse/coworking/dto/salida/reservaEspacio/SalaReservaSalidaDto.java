package com.backend.digitalhouse.coworking.dto.salida.reservaEspacio;

import com.backend.digitalhouse.coworking.dto.salida.tipoSala.TipoSalaSalidaDto;
import java.util.List;
import java.util.Map;

public class SalaReservaSalidaDto {
    private String nombre;
    private String descripcion;
    private int capacidad;
    private TipoSalaSalidaDto tipoSala;
    private List<Map<Long, String>> imagenes;
    private List<Map<Long, String>> servicios;

    public SalaReservaSalidaDto() {
    }

    public SalaReservaSalidaDto(String nombre, String descripcion, int capacidad, TipoSalaSalidaDto tipoSala, List<Map<Long, String>> imagenes, List<Map<Long, String>> servicios) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.capacidad = capacidad;
        this.tipoSala = tipoSala;
        this.imagenes = imagenes;
        this.servicios = servicios;
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

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public TipoSalaSalidaDto getTipoSala() {
        return tipoSala;
    }

    public void setTipoSala(TipoSalaSalidaDto tipoSala) {
        this.tipoSala = tipoSala;
    }

    public List<Map<Long, String>> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<Map<Long, String>> imagenes) {
        this.imagenes = imagenes;
    }

    public List<Map<Long, String>> getServicios() {
        return servicios;
    }

    public void setServicios(List<Map<Long, String>> servicios) {
        this.servicios = servicios;
    }

    @Override
    public String toString() {
        return "SalaReservaSalidaDto{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", capacidad=" + capacidad +
                ", tipoSala=" + tipoSala +
                ", imagenes=" + imagenes +
                ", servicios=" + servicios +
                '}';
    }
}
