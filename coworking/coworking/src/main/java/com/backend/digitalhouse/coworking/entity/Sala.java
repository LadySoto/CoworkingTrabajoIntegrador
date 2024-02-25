package com.backend.digitalhouse.coworking.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "SALAS")
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SALA")
    private Long id;

    private String nombre;
    private String descripcion;
    private int capacidad;
    private int disponible;
    private int estado;

    @Column(name="PROMEDIO_CALIFICACION")
    private BigDecimal promedioCalificacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tipo_salas_id_tipo_sala")
    private TipoSala tipoSala;

    public Sala() {
    }

    public Sala(String nombre, String descripcion, int capacidad, int disponible, int estado, BigDecimal promedioCalificacion, TipoSala tipoSala) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.capacidad = capacidad;
        this.disponible = disponible;
        this.estado = estado;
        this.promedioCalificacion = promedioCalificacion;
        this.tipoSala = tipoSala;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getDisponible() {
        return disponible;
    }

    public void setDisponible(int disponible) {
        this.disponible = disponible;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public BigDecimal getPromedioCalificacion() {
        return promedioCalificacion;
    }

    public void setPromedioCalificacion(BigDecimal promedioCalificacion) {
        this.promedioCalificacion = promedioCalificacion;
    }

    public TipoSala getTipoSala() {
        return tipoSala;
    }

    public void setTipoSala(TipoSala tipoSala) {
        this.tipoSala = tipoSala;
    }
}
