package com.backend.digitalhouse.coworking.dto.modificacion.sala;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SalaModificacionEntradaDto {
    @NotNull(message = "El campo no puede ser nulo")
    private Long id;
    @Size(max = 100, message = "El nombre debe tener hasta 100 caracteres")
    private String nombre;

    @Size(max = 500, message = "La descripción debe tener hasta 500 caracteres")
    private String descripcion;

    @Digits(integer = 2, fraction = 0, message = "El numero debe tener como máximo 2 dígitos")
    private int capacidad;

    @Digits(integer = 1, fraction = 0, message = "El numero debe tener como máximo 1 dígito")
    private int disponible;

    @Digits(integer = 1, fraction = 0, message = "El numero debe tener como máximo 1 dígito")
    private int estado;

    @Digits(integer = 3, fraction = 2, message = "El número debe tener como máximo 3 dígitos y 2 decimales")
    private double promedioCalificacion;

    private long tipoSala;

    public SalaModificacionEntradaDto() {
    }

    public SalaModificacionEntradaDto(Long id, String nombre, String descripcion, int capacidad, int disponible, int estado, double promedioCalificacion, long tipoSala) {
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

    public double getPromedioCalificacion() {
        return promedioCalificacion;
    }
    public void setPromedioCalificacion(double promedioCalificacion) {
        this.promedioCalificacion = promedioCalificacion;
    }

    public long getTipoSala() {
        return tipoSala;
    }
    public void setTipoSala(long tipoSala) {
        this.tipoSala = tipoSala;
    }

    @Override
    public String toString() {
        return "SalaModificacionEntradaDto{" +
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

