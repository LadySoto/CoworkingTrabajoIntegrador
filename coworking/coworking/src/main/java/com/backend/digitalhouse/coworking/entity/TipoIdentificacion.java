package com.backend.digitalhouse.coworking.entity;

import javax.persistence.*;

@Entity
@Table(name = "TIPO_IDENTIFICACIONES")
public class TipoIdentificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIPO_IDENTIFICACION")
    private Long id;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "ESTADO")
    private int estado;

    public TipoIdentificacion() {
    }

    public TipoIdentificacion(String nombre, int estado) {
        this.nombre = nombre;
        this.estado = estado;
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "TipoIdentificacion{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", estado=" + estado +
                '}';
    }
}
