package com.backend.digitalhouse.coworking.dto.modificacion.rol;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RolModificacionEntradaDto {
    @NotNull(message = "El campo no puede ser nulo")
    private Long id;
    @Size(max = 50, message = "El nombre debe tener hasta 50 caracteres")
    private String nombre;

    @Digits(integer = 1, fraction = 0, message = "El numero debe tener como maximo 1 dígito")
    private int estado;

    public RolModificacionEntradaDto() {
    }

    public RolModificacionEntradaDto(Long id, String nombre, int estado) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "RolModificacionEntradaDto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", estado=" + estado +
                '}';
    }
}
