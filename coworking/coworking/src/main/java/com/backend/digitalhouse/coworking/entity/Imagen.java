package com.backend.digitalhouse.coworking.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "IMAGENES")
public class Imagen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_IMAGEN")
    private Long id;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "IMAGEN")
    private String imagen;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="salas_id_sala")
    private Sala sala;

    /*@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="salas_id_sala")
    private Long idSala;*/

    public Imagen() {
    }

    public Imagen(String nombre, String imagen, Sala sala) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.sala = sala;
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    @Override
    public String toString() {
        return "Imagen{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", imagen='" + imagen + '\'' +
                ", sala='" + sala +
                '}';
    }
}
