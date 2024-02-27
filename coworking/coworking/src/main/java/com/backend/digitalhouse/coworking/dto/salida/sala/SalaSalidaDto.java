package com.backend.digitalhouse.coworking.dto.salida.sala;

import java.math.BigDecimal;

public class SalaSalidaDto {

    private Long id;
    private String nombre;
    private String descripcion;
    private int capacidad;
    private int disponible;
    private int estado;
    private double promedioCalificacion;
    private TipoSalaSalidaDto tipoSalaSalidaDto;

    public SalaSalidaDto() {
    }

    public SalaSalidaDto(Long id, String nombre, String descripcion, int capacidad, int disponible, int estado, double promedioCalificacion, TipoSalaSalidaDto tipoSalaSalidaDto) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.capacidad = capacidad;
        this.disponible = disponible;
        this.estado = estado;
        this.promedioCalificacion = promedioCalificacion;
        this.tipoSalaSalidaDto = tipoSalaSalidaDto;
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

    public TipoSalaSalidaDto getTipoSalaSalidaDto() {
        return tipoSalaSalidaDto;
    }

    public void setTipoSalaSalidaDto(TipoSalaSalidaDto tipoSalaSalidaDto) {
        this.tipoSalaSalidaDto = tipoSalaSalidaDto;
    }

    @Override
    public String toString() {
        return "Id: " + id + " - Nombre: " + nombre + " - Descripción: " + descripcion + " - Capacidad: " + capacidad + " - Disponible: " + disponible + " - Estado: " + estado + " - Promedio de Calificación: " + promedioCalificacion + " - Categoría a la que pertenece: " + tipoSalaSalidaDto;
    }
}