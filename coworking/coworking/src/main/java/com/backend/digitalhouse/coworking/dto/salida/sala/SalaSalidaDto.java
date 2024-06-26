package com.backend.digitalhouse.coworking.dto.salida.sala;

import com.backend.digitalhouse.coworking.dto.salida.tipoSala.TipoSalaSalidaDto;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SalaSalidaDto {
    private Long id;
    private String nombre;
    private String descripcion;
    private int capacidad;
    private int disponible;
    private int estado;
    private double promedioCalificacion;
    private TipoSalaSalidaDto tipoSala;
    private List<Map<Long, String>> imagenes;
    private List<Map<Long, String>> servicios;

    public SalaSalidaDto() {
    }

    public SalaSalidaDto(Long id, String nombre, String descripcion, int capacidad, int disponible, int estado, double promedioCalificacion, TipoSalaSalidaDto tipoSala, List<Map<Long, String>> imagenes, List<Map<Long, String>> servicios) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.capacidad = capacidad;
        this.disponible = disponible;
        this.estado = estado;
        this.promedioCalificacion = promedioCalificacion;
        this.tipoSala = tipoSala;
        this.imagenes = imagenes;
        this.servicios = servicios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalaSalidaDto that = (SalaSalidaDto) o;
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

    public int getDisponible() {
        return disponible;
    }

    public void setDisponible(int disponible) {
        this.disponible = disponible;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public double getPromedioCalificacion() {
        return promedioCalificacion;
    }

    public void setPromedioCalificacion(double promedioCalificacion) {
        this.promedioCalificacion = promedioCalificacion;
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
        return "SalaSalidaDto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", capacidad=" + capacidad +
                ", disponible=" + disponible +
                ", estado=" + estado +
                ", promedioCalificacion=" + promedioCalificacion +
                ", tipoSala=" + tipoSala +
                ", imagenes=" + imagenes +
                ", servicios=" + servicios +
                '}';
    }
}
