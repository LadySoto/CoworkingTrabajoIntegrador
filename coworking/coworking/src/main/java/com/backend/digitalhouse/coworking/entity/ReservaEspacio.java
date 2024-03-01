package com.backend.digitalhouse.coworking.entity;

import javax.persistence.*;

@Entity
@Table(name = "RESERVAS_ESPACIOS")
public class ReservaEspacio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_REVERVA_ESPACIO")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="usuarios_id_usuario")
    private Usuario usuario;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="reservas_id_reserva")
    private Reserva reserva;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="salas_id_sala")
    private Sala sala;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="servicios_id_servicio")
    private Servicio servicio;

    public ReservaEspacio() {
    }

    public ReservaEspacio(Usuario usuario, Reserva reserva, Sala sala, Servicio servicio) {
        this.usuario = usuario;
        this.reserva = reserva;
        this.sala = sala;
        this.servicio = servicio;
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

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    @Override
    public String toString() {
        return "ReservaEspacio{" +
                "id=" + id +
                ", usuario=" + usuario +
                ", reserva=" + reserva +
                ", sala=" + sala +
                ", servicio=" + servicio +
                '}';
    }
}
