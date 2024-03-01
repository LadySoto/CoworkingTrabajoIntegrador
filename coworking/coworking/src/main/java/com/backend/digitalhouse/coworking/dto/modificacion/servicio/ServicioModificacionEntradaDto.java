package com.backend.digitalhouse.coworking.dto.modificacion.servicio;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ServicioModificacionEntradaDto {
    @NotNull(message = "El campo no puede ser nulo")
    private Long id;
    @Size(max = 100, message = "El nombre debe tener hasta 100 caracteres")
    private String nombre;

    @Digits(integer = 1, fraction = 0, message = "El numero debe tener como maximo 1 dígito")
    private int estado;

    public ServicioModificacionEntradaDto() {
    }

    public ServicioModificacionEntradaDto(String nombre, int estado) {
        this.nombre = nombre;
        this.estado = estado;
    }

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public int getEstado() {return estado;}
    public void setEstado(int estado) {this.estado = estado;}

    @Override
    public String toString() {
        return "ServicioModificacionEntradaDto{" +
                "nombre='" + nombre + '\'' +
                ", estado=" + estado +
                '}';
    }
}
