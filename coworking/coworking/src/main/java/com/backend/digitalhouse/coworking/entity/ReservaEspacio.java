package com.backend.digitalhouse.coworking.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "RESERVAS_ESPACIOS")
public class ReservaEspacio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_RESERVA_ESPACIO")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="usuarios_id_usuario")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="salas_id_sala")
    private Sala sala;

    @Column(name = "FECHA_HORA_INICIO")
    private LocalDateTime fechaHoraInicio;

    @Column(name = "FECHA_HORA_FIN")
    private LocalDateTime fechaHoraFin;

    @Column(name="CANTIDAD_PERSONAS")
    private int cantidadPersonas;

    @Column(name = "CALIFICACION")
    private int calificacion;

    public ReservaEspacio() {
    }

    public ReservaEspacio(Long id, Usuario usuario, Sala sala, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin, int cantidadPersonas, int calificacion) {
        this.id = id;
        this.usuario = usuario;
        this.sala = sala;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.cantidadPersonas = cantidadPersonas;
        this.calificacion = calificacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public LocalDateTime getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(int cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    @Override
    public String toString() {
        return "ReservaEspacio{" +
                "id=" + id +
                ", usuario=" + usuario +
                ", sala=" + sala +
                ", fechaHoraInicio=" + fechaHoraInicio +
                ", fechaHoraFin=" + fechaHoraFin +
                ", cantidadPersonas=" + cantidadPersonas +
                ", calificacion=" + calificacion +
                '}';
    }
}
