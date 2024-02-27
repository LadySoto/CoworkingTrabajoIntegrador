package com.backend.digitalhouse.coworking.dto.salida.imagenProducto;

public class ImagenSalaProductoSalidaDto {

    private Long id;
    private ImagenProductoSalidaDto imagenProductoSalidaDto;
    private SalaProductoSalidaDto salaProductoSalidaDto;

    public ImagenSalaProductoSalidaDto() {
    }

    public ImagenSalaProductoSalidaDto(Long id, ImagenProductoSalidaDto imagenProductoSalidaDto, SalaProductoSalidaDto salaProductoSalidaDto) {
        this.id = id;
        this.imagenProductoSalidaDto = imagenProductoSalidaDto;
        this.salaProductoSalidaDto = salaProductoSalidaDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ImagenProductoSalidaDto getImagenProductoSalidaDto() {
        return imagenProductoSalidaDto;
    }

    public void setImagenProductoSalidaDto(ImagenProductoSalidaDto imagenProductoSalidaDto) {
        this.imagenProductoSalidaDto = imagenProductoSalidaDto;
    }

    public SalaProductoSalidaDto getSalaProductoSalidaDto() {
        return salaProductoSalidaDto;
    }

    public void setSalaProductoSalidaDto(SalaProductoSalidaDto salaProductoSalidaDto) {
        this.salaProductoSalidaDto = salaProductoSalidaDto;
    }

    @Override
    public String toString() {
        return "Id: " + id + ", Imagen de la Sala: " + imagenProductoSalidaDto.getImagen() + ", Nombre de la Sala: " + salaProductoSalidaDto.getNombre() + ", Descripción de la Sala: " + salaProductoSalidaDto.getDescripcion();
    }
}
