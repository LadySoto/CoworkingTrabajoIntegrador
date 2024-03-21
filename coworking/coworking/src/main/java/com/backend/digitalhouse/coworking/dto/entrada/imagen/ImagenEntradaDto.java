package com.backend.digitalhouse.coworking.dto.entrada.imagen;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ImagenEntradaDto {

    @Size(max = 100, message = "El nombre de la imagen debe tener hasta 100 caracteres")
    @NotNull(message = "El nombre de la imagen no puede ser nulo")
    @NotBlank(message = "Debe especificarse el nombre de la imagen")
    private String nombre;

    @Size(max = 100, message = "La ruta la imagen debe tener hasta 100 caracteres")
    @NotNull(message = "Este campo no puede ser nulo")
    @NotBlank(message = "Debe especificarse la ruta de la imagen")
    private String imagen;

    @NotNull(message = "Este campo no puede ser nulo")
    private Long idSala;

    public ImagenEntradaDto() {
    }

    public ImagenEntradaDto(String nombre, String imagen) {
        this.nombre = nombre;
        this.imagen = imagen;
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

    @Override
    public String toString() {
        return "ImagenEntradaDto{" +
                "nombre='" + nombre + '\'' +
                ", imagen='" + imagen + '\'' +
                '}';
    }
}
