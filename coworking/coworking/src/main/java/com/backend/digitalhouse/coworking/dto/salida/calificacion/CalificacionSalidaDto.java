package com.backend.digitalhouse.coworking.dto.salida.calificacion;

public class CalificacionSalidaDto {
    private Long id;
    private int puntuacion;

    public CalificacionSalidaDto(){
    }

    public CalificacionSalidaDto(Long id, int puntuacion) {
        this.id = id;
        this.puntuacion = puntuacion;
    }

    public Long getId() { return id;}
    public void setId(Long id) {this.id = id;}

    public int getPuntuacion() {return puntuacion;}
    public void setPuntuacion(int puntuacion) {this.puntuacion = puntuacion;}

    @Override
    public String toString() {
        return "Id: " + id + " - Puntuacion: " + puntuacion;
    }
}
