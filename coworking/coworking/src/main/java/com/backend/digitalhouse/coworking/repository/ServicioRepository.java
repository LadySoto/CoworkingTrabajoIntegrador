package com.backend.digitalhouse.coworking.repository;

import com.backend.digitalhouse.coworking.entity.Sala;
import com.backend.digitalhouse.coworking.entity.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long> {
    List<Servicio> findByNombreContaining(String nombre);
}
