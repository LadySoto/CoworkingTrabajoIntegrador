package com.backend.digitalhouse.coworking.entity;

import javax.persistence.*;

@Entity
@Table(name = "USUARIOS")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private Long id;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "CORREO")
    private String correo;
    @Column(name = "CONTRASENA")
    private String contrasena;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_identificaciones_id_identificacion")
    private TipoIdentificacion tipoIdentificacion;
    @Column(name = "NUMERO_IDENTIFICACION")
    private String numeroIdentificacion;
    @Column(name = "ESTADO")
    private int estado;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roles_id_rol")
    private Rol rol;

    public Usuario() {
    }

    public Usuario(String nombre, String correo, String contasena, TipoIdentificacion tipoIdentificacion, String numeroIdentificacion, int estado, Rol rol) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contasena;
        this.tipoIdentificacion = tipoIdentificacion;
        this.numeroIdentificacion = numeroIdentificacion;
        this.estado = estado;
        this.rol = rol;
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

    public TipoIdentificacion getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
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

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", tipoIdentificacion=" + tipoIdentificacion +
                ", numeroIdentificacion='" + numeroIdentificacion + '\'' +
                ", estado=" + estado +
                ", rol=" + rol +
                '}';
    }
}
