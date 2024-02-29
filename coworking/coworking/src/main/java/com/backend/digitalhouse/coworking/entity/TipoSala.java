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

    public TipoSala() {
    }

    public TipoSala(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
