package com.backend.digitalhouse.coworking.entity;

import javax.persistence.*;

@Entity
@Table(name = "IMAGENES_SALA")
public class ImagenSala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_IMAGEN_SALA")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="imagenes_id_imagenes")
    private Imagen imagen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="salas_id_sala")
    private Sala sala;

    public ImagenSala() {
    }

    public ImagenSala(Imagen imagen, Sala sala) {
        this.imagen = imagen;
        this.sala = sala;
    }

    public Long getId() {
        return id;
    }

    public Imagen getImagen() {
        return imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }
}
