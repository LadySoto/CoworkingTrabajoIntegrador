package com.backend.digitalhouse.coworking.dto.salida.reservaEspacio;

import com.backend.digitalhouse.coworking.dto.salida.sala.SalaSalidaDto;
import com.backend.digitalhouse.coworking.dto.salida.tipoSala.TipoSalaSalidaDto;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SalaReservaSalidaDto {

    private Long id;
    private String nombre;
    private String descripcion;
    private int capacidad;
    private TipoSalaSalidaDto tipoSala;
    private List<Map<Long, String>> imagenes;
    private List<Map<Long, String>> servicios;

    public SalaReservaSalidaDto() {
    }

    public SalaReservaSalidaDto(Long id, String nombre, String descripcion, int capacidad, TipoSalaSalidaDto tipoSala, List<Map<Long, String>> imagenes, List<Map<Long, String>> servicios) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.capacidad = capacidad;
        this.tipoSala = tipoSala;
        this.imagenes = imagenes;
        this.servicios = servicios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalaReservaSalidaDto that = (SalaReservaSalidaDto) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", capacidad=" + capacidad +
                ", tipoSala=" + tipoSala +
                ", imagenes=" + imagenes +
                ", servicios=" + servicios +
                '}';
    }
}
