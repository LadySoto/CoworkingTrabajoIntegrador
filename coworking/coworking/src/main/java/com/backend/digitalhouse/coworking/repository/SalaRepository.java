package com.backend.digitalhouse.coworking.repository;

import com.backend.digitalhouse.coworking.entity.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Long> {

    List<Sala> findByTipoSalaNombreContainingIgnoreCase(String nombre);
    List<Sala> findByNombreContaining(String nombre);
}