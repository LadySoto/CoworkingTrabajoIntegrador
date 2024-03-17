package com.backend.digitalhouse.coworking.repository;

import com.backend.digitalhouse.coworking.entity.ImagenTipoSala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagenTipoSalaRepository extends JpaRepository<ImagenTipoSala, Long> {
}