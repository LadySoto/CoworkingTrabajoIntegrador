package com.backend.digitalhouse.coworking.dto.salida.imagenSala;

import com.backend.digitalhouse.coworking.dto.salida.imagen.ImagenSalidaDto;
import com.backend.digitalhouse.coworking.dto.salida.sala.SalaSalidaDto;

public class ImagenTipoSalaSalidaDto {
    private Long id;
    private ImagenSalidaDto imagenSalidaDto;
    private TipoSalaSalidaDto tipoSalaSalidaDto;

    public ImagenTipoSalaSalidaDto() {
    }

    public ImagenTipoSalaSalidaDto(Long id, ImagenSalidaDto imagenSalidaDto, TipoSalaSalidaDto tipoSalaSalidaDto) {
        this.id = id;
        this.imagenSalidaDto = imagenSalidaDto;
        this.tipoSalaSalidaDto = tipoSalaSalidaDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ImagenSalidaDto getImagenSalidaDto() {
        return imagenSalidaDto;
    }

    public void setImagenSalidaDto(ImagenSalidaDto imagenSalidaDto) {
        this.imagenSalidaDto = imagenSalidaDto;
    }

    public TipoSalaSalidaDto getTipoSalaSalidaDto() {
        return tipoSalaSalidaDto;
    }

    public void setTipoSalaSalidaDto(TipoSalaSalidaDto tipoSalaSalidaDto) {
        this.tipoSalaSalidaDto = tipoSalaSalidaDto;
    }

    @Override
    public String toString() {
        return "ImagenTipoSalaSalidaDto{" +
                "id=" + id +
                ", imagen=" + imagenSalidaDto +
                ", tipo sala=" + tipoSalaSalidaDto +
                '}';
    }
}