package com.backend.digitalhouse.coworking.dto.modificacion.tipoIdentificacion;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TipoIdentificacionModificacionEntradaDto {
    @Size(max = 45, message = "El nombre debe tener hasta 45 caracteres")
    private String nombre;

    @Digits(integer = 1, fraction = 0, message = "El numero debe tener como maximo 1 dígito")
    private int estado;

    public TipoIdentificacionModificacionEntradaDto() {
    }

    public TipoIdentificacionModificacionEntradaDto(String nombre, int estado) {
        this.nombre = nombre;
        this.estado = estado;
    }

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public int getEstado() {return estado;}
    public void setEstado(int estado) {this.estado = estado;}

    @Override
    public String toString() {
        return "TipoIdentificacionModificacionEntradaDto{" +
                "nombre='" + nombre + '\'' +
                ", estado=" + estado +
                '}';
    }
}
