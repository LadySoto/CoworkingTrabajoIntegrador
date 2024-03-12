package com.backend.digitalhouse.coworking.entity;

import javax.persistence.*;

@Entity
@Table(name="TIPO_SALAS")
public class TipoSala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIPO_SALA")
    private Long id;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    public TipoSala() {
    }

    public TipoSala(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "TipoSala{" +
                "id=" + id +
                ", nombre= " + nombre + '\'' +
                " , descripción= " + descripcion +
                '}';
    }
}
