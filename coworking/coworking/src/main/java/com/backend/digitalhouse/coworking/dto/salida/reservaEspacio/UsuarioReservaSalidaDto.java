package com.backend.digitalhouse.coworking.dto.salida.reservaEspacio;

import com.backend.digitalhouse.coworking.dto.salida.tipoIdentificacion.TipoIdentificacionSalidaDto;

public class UsuarioReservaSalidaDto {
    private String nombre;
    private String correo;
    private TipoIdentificacionSalidaDto idTipoIdentificacion;
    private String numeroIdentificacion;
    public UsuarioReservaSalidaDto() {
    }

    public UsuarioReservaSalidaDto(String nombre, String correo, TipoIdentificacionSalidaDto idTipoIdentificacion, String numeroIdentificacion) {
        this.nombre = nombre;
        this.correo = correo;
        this.idTipoIdentificacion = idTipoIdentificacion;
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public TipoIdentificacionSalidaDto getIdTipoIdentificacion() {
        return idTipoIdentificacion;
    }

    public void setIdTipoIdentificacion(TipoIdentificacionSalidaDto idTipoIdentificacion) {
        this.idTipoIdentificacion = idTipoIdentificacion;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    @Override
    public String toString() {
        return "UsuarioReservaSalidaDto{" +
                "nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", idTipoIdentificacion=" + idTipoIdentificacion +
                ", numeroIdentificacion='" + numeroIdentificacion + '\'' +
                '}';
    }
}
