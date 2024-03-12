package com.backend.digitalhouse.coworking.entity;

import javax.persistence.*;

@Entity
@Table(name = "IMAGENES_TIPO_SALAS")
public class ImagenTipoSala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_IMAGENES_TIPO_SALAS")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="imagenes_id_imagenes")
    private Imagen imagen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tipo_salas_id_tipo_sala")
    private TipoSala tipoSala;

    public ImagenTipoSala() {
    }

    public ImagenTipoSala(Imagen imagen, TipoSala tipoSala) {
        this.imagen = imagen;
        this.tipoSala = tipoSala;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "ImagenTipoSala{" +
                "id= " + id +
                ", imagen= " + imagen +
                ", tipo sala= " + tipoSala +
                '}';
    }
}
