package com.backend.digitalhouse.coworking.dto.salida.usuario;

import com.backend.digitalhouse.coworking.dto.salida.rol.RolSalidaDto;
import com.backend.digitalhouse.coworking.dto.salida.tipoIdentificacion.TipoIdentificacionSalidaDto;

public class UsuarioSalidaDto {
    private Long id;
    private String nombre;
    private String correo;
    private String contrasena;
    private TipoIdentificacionSalidaDto tipoIdentificacion;
    private String numeroIdentificacion;
    private int estado;
    private RolSalidaDto rol;

    public UsuarioSalidaDto() {
    }

    public UsuarioSalidaDto(Long id, String nombre, String correo, String contrasena, TipoIdentificacionSalidaDto tipoIdentificacion, String numeroIdentificacion, int estado, RolSalidaDto rol) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.tipoIdentificacion = tipoIdentificacion;
        this.numeroIdentificacion = numeroIdentificacion;
        this.estado = estado;
        this.rol = rol;
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getCorreo() {return correo;}
    public void setCorreo(String correo) {this.correo = correo;}

    public String getContrasena() {return contrasena;}
    public void setContrasena(String contrasena) {this.contrasena = contrasena;}

    public TipoIdentificacionSalidaDto getTipoIdentificacion() {return tipoIdentificacion;}
    public void setTipoIdentificacion(TipoIdentificacionSalidaDto tipoIdentificacion) {this.tipoIdentificacion = tipoIdentificacion;}

    public String getNumeroIdentificacion() {return numeroIdentificacion;}
    public void setNumeroIdentificacion(String numeroIdentificacion) {this.numeroIdentificacion = numeroIdentificacion;}

    public int getEstado() {return estado;}
    public void setEstado(int estado) {this.estado = estado;}

    public RolSalidaDto getRol() {return rol;}
    public void setRol(RolSalidaDto rol) {this.rol = rol;}

    @Override
    public String toString() {
        return "Id: " + id + " - Nombre: " + nombre + " - Correo;: " + correo + " - Contraseña: " + contrasena + " - Tipo de identificacion: " + tipoIdentificacion + " - Numero de identificacion: " + numeroIdentificacion + " - Estado: " + estado + " - Rol: " + rol;
    }
}
