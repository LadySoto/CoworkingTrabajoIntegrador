package com.backend.digitalhouse.coworking.dto.entrada.imagen;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ImagenEntradaDto {

    @Size(max = 100, message = "El nombre debe tener hasta 100 caracteres")
    @NotNull(message = "El nombre no puede ser nulo")
    @NotBlank(message = "Debe especificarse el nombre de la imagen")
    private String nombre;

    @Size(max = 100, message = "La ruta la imagen debe tener hasta 100 caracteres")
    @NotNull(message = "Este campo no puede ser nulo")
    @NotBlank(message = "Debe especificarse la ruta de la imagen")
    private String imagen;

    @NotNull(message = "El campo no puede ser nulo")
    @Digits(integer = 1, fraction = 0, message = "El numero debe tener como maximo 1 dígito")
    private int estado;

    public ImagenEntradaDto() {
    }

    public ImagenEntradaDto(String nombre, String imagen, int estado) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
