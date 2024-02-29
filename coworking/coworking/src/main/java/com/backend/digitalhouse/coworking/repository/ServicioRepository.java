package com.backend.digitalhouse.coworking.repository;

import com.backend.digitalhouse.coworking.entity.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long> {
}
