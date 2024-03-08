package com.backend.digitalhouse.coworking.entity;

import javax.persistence.*;

@Entity
@Table(name = "CALIFICACIONES")
public class Calificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CALIFICACION")
    private Long id;
    @Column(name = "PUNTUACION")
    private int puntuacion;

    public Calificacion() {
    }

    public Calificacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public int getPuntuacion() {
        return puntuacion;
    }
    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    @Override
    public String toString() {
        return "Calificacion{" +
                "id=" + id +
                ", puntuacion=" + puntuacion +
                '}';
    }
}
