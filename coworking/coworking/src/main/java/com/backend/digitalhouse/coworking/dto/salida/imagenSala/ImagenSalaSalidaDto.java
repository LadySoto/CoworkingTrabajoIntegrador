package com.backend.digitalhouse.coworking.dto.salida.imagenSala;

import com.backend.digitalhouse.coworking.dto.salida.imagen.ImagenSalidaDto;
import com.backend.digitalhouse.coworking.dto.salida.sala.SalaSalidaDto;

public class ImagenSalaSalidaDto {
    private Long id;
    private ImagenSalidaDto imagen;
    private SalaSalidaDto sala;

    public ImagenSalaSalidaDto() {
    }

    public ImagenSalaSalidaDto(Long id, ImagenSalidaDto imagen, SalaSalidaDto sala) {
        this.id = id;
        this.imagen = imagen;
        this.sala = sala;
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public ImagenSalidaDto getImagen() {return imagen;}
    public void setImagen(ImagenSalidaDto imagen) {this.imagen = imagen;}

    public SalaSalidaDto getSala() {return sala;}
    public void setSala(SalaSalidaDto sala) {this.sala = sala;}

    @Override
    public String toString() {
        return "Id: " + id + " - Imagen: " + imagen + "- Sala: " + sala;
    }
}