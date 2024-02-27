package com.backend.digitalhouse.coworking.repository;

import com.backend.digitalhouse.coworking.entity.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalificacionRepository extends JpaRepository<Calificacion, Long> {
}