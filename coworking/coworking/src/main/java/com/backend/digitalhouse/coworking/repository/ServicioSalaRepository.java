package com.backend.digitalhouse.coworking.repository;

import com.backend.digitalhouse.coworking.entity.Sala;
import com.backend.digitalhouse.coworking.entity.Servicio;
import com.backend.digitalhouse.coworking.entity.ServicioSala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ServicioSalaRepository extends JpaRepository<ServicioSala, Long> {
    List<ServicioSala> findByServicioNombreContainingIgnoreCase(String nombre);

    List<ServicioSala> findBySala(Sala sala);

    List<ServicioSala> findByServicio(Servicio servicio);
}