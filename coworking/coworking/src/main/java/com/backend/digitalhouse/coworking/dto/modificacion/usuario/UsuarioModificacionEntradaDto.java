package com.backend.digitalhouse.coworking.dto.modificacion.usuario;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UsuarioModificacionEntradaDto {
    @NotNull(message = "El campo no puede ser nulo")
    private Long id;
    @Size(max = 100, message = "El nombre debe tener hasta 100 caracteres")
    @Pattern(regexp = "^[a-zA-ZÀ-ÖØ-öø-ÿ']+(?: [a-zA-ZÀ-ÖØ-öø-ÿ']+)*$", message = "El nombre debe tener solo letras")
    private String nombre;

    @Size(max = 100, message = "El correo debe tener hasta 100 caracteres")
    @Pattern(regexp = "^[^@]+@[^@]+\\.[a-zA-Z]{2,}$", message = "El formato debe ser un correo valido")
    private String correo;

    @Size(max = 20, message = "La contraseña debe tener hasta 20 caracteres")
    private String contrasena;

    private long idTipoIdentificacion;

    @Size(max = 50, message = "El numero de identificacion debe tener hasta 50 caracteres")
    @Pattern(regexp = "^[0-9]*", message = "El numero de identificacion debe tener solo numeros")
    private String numeroIdentificacion;

    @Digits(integer = 1, fraction = 0, message = "El numero debe tener como maximo 1 dígito")
    private int estado;

    private long idRol;

    public UsuarioModificacionEntradaDto(){
    }

    public UsuarioModificacionEntradaDto(Long id, String nombre, String correo, String contrasena, long idTipoIdentificacion, String numeroIdentificacion, int estado, long idRol) {
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

    public long getIdTipoIdentificacion() {
        return idTipoIdentificacion;
    }

    public void setIdTipoIdentificacion(long idTipoIdentificacion) {
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

    public long getIdRol() {
        return idRol;
    }

    public void setIdRol(long idRol) {
        this.idRol = idRol;
    }

    @Override
    public String toString() {
        return "UsuarioModificacionEntradaDto{" +
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

