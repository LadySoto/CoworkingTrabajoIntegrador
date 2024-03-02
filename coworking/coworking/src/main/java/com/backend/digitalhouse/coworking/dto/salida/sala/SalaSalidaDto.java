package com.backend.digitalhouse.coworking.dto.salida.sala;

import com.backend.digitalhouse.coworking.dto.salida.tipoSala.TipoSalaSalidaDto;

public class SalaSalidaDto {
    private Long id;
    private String nombre;
    private String descripcion;
    private int capacidad;
    private int disponible;
    private int estado;
    private double promedioCalificacion;
    private TipoSalaSalidaDto tipoSala;

    public SalaSalidaDto() {
    }

    public SalaSalidaDto(Long id, String nombre, String descripcion, int capacidad, int disponible, int estado, double promedioCalificacion, TipoSalaSalidaDto tipoSala) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.capacidad = capacidad;
        this.disponible = disponible;
        this.estado = estado;
        this.promedioCalificacion = promedioCalificacion;
        this.tipoSala = tipoSala;
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

    public double getPromedioCalificacion() {return promedioCalificacion; }
    public void setPromedioCalificacion(double promedioCalificacion) {this.promedioCalificacion = promedioCalificacion; }

    public TipoSalaSalidaDto getTipoSala() {return tipoSala; }
    public void setTipoSala(TipoSalaSalidaDto tipoSala) {
        this.tipoSala = tipoSala;
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
                '}';
    }
}
