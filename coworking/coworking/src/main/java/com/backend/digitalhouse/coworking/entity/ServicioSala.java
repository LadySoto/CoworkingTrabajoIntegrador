package com.backend.digitalhouse.coworking.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "SEVICIOS_SALAS")
public class ServicioSala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SERVICIO_SALA")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="servicios_id_servicio")
    private Servicio servicio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="salas_id_sala")
    private Sala sala;

    public ServicioSala() {
    }

    public ServicioSala(Servicio servicio, Sala sala) {
        this.servicio = servicio;
        this.sala = sala;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public String toString() {
        return "ServicioSala{" +
                "id=" + id +
                ", servicio=" + servicio +
                ", sala=" + sala +
                '}';
    }
}
