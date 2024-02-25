package com.backend.digitalhouse.coworking.dto.entrada.sala;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SalaEntradaDto {

    @Size(max = 100, message = "El nombre debe tener hasta 100 caracteres")
    @NotNull(message = "El nombre no puede ser nulo")
    @NotBlank(message = "Debe especificarse el nombre de la sala")
    private String nombre;

    @Size(max = 500, message = "La descripción debe tener hasta 500 caracteres")
    @NotNull(message = "La descripción no puede ser nulo")
    @NotBlank(message = "Debe especificarse la descripición de la sala")
    private String descripcion;

    @NotNull(message = "El campo no puede ser nulo")
    @Digits(integer = 2, fraction = 0, message = "El numero debe tener como maximo 2 dígitos")
    private int capacidad;

    @NotNull(message = "El campono puede ser nulo")
    @Digits(integer = 1, fraction = 0, message = "El numero debe tener como maximo 1 dígito")
    private int disponible;

    @NotNull(message = "El campo no puede ser nulo")
    @Digits(integer = 1, fraction = 0, message = "El numero debe tener como maximo 1 dígito")
    private int estado;

    @NotNull(message = "El campo no puede ser nulo")
    @Digits(integer = 3, fraction = 2, message = "El número debe tener como máximo 3 dígitos y 2 decimales")
    private BigDecimal promedioCalificacion;

    @NotNull(message = "El tipo de categoría no puede ser nulo")
    private long idTipoSala;

    public SalaEntradaDto() {
    }

    public SalaEntradaDto(String nombre, String descripcion, int capacidad, int disponible, int estado, BigDecimal promedioCalificacion, long idTipoSala) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.capacidad = capacidad;
        this.disponible = disponible;
        this.estado = estado;
        this.promedioCalificacion = promedioCalificacion;
        this.idTipoSala = idTipoSala;
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

    public BigDecimal getPromedioCalificacion() {
        return promedioCalificacion;
    }

    public void setPromedioCalificacion(BigDecimal promedioCalificacion) {
        this.promedioCalificacion = promedioCalificacion;
    }

    public long getIdTipoSala() {
        return idTipoSala;
    }

    public void setIdTipoSala(long idTipoSala) {
        this.idTipoSala = idTipoSala;
    }
}
