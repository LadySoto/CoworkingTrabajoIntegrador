package com.backend.digitalhouse.coworking.dto.salida.usuario;

import com.backend.digitalhouse.coworking.dto.salida.rol.RolSalidaDto;
import com.backend.digitalhouse.coworking.dto.salida.tipoIdentificacion.TipoIdentificacionSalidaDto;

public class UsuarioSalidaDto {

    private Long id;
    private String nombre;
    private String correo;
    private String contrasena;
    private TipoIdentificacionSalidaDto idTipoIdentificacion;
    private String numeroIdentificacion;
    private int estado;
    private RolSalidaDto idRol;

    public UsuarioSalidaDto() {
    }

    public UsuarioSalidaDto(Long id, String nombre, String correo, String contrasena, TipoIdentificacionSalidaDto idTipoIdentificacion, String numeroIdentificacion, int estado, RolSalidaDto idRol) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.idTipoIdentificacion = idTipoIdentificacion;
        this.numeroIdentificacion = numeroIdentificacion;
        this.estado = estado;
        this.idRol = idRol;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public RolSalidaDto getIdRol() {
        return idRol;
    }

    public void setIdRol(RolSalidaDto idRol) {
        this.idRol = idRol;
    }

    @Override
    public String toString() {
        return "UsuarioSalidaDto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", idTipoIdentificacion=" + idTipoIdentificacion +
                ", numeroIdentificacion='" + numeroIdentificacion + '\'' +
                ", estado=" + estado +
                ", idRol=" + idRol +
                '}';
    }
}
