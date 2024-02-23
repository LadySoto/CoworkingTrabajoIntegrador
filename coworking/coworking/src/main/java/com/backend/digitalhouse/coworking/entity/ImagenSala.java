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
    @JoinColumn(name="tipo_sala_id_tipo_sala")
    private TipoSala tipoSala;

    public ImagenSala() {
    }

    public ImagenSala(Imagen imagen, TipoSala tipoSala) {
        this.imagen = imagen;
        this.tipoSala = tipoSala;
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

    public TipoSala getTipoSala() {
        return tipoSala;
    }

    public void setTipoSala(TipoSala tipoSala) {
        this.tipoSala = tipoSala;
    }
}
