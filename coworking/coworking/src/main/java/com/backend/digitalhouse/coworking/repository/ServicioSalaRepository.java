package com.backend.digitalhouse.coworking.repository;

import com.backend.digitalhouse.coworking.entity.ServicioSala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ServicioSalaRepository extends JpaRepository<ServicioSala, Long> {
}