package com.backend.digitalhouse.coworking.dto.salida.imagenProducto;

public class ImagenProductoSalidaDto {

    private String imagen;

    public ImagenProductoSalidaDto() {
    }

    public ImagenProductoSalidaDto(String imagen) {
        this.imagen = imagen;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Imagen: " + imagen;
    }
}
