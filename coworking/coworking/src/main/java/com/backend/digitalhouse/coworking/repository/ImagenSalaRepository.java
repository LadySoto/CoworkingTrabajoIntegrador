package com.backend.digitalhouse.coworking.repository;

import com.backend.digitalhouse.coworking.entity.ImagenSala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagenSalaRepository extends JpaRepository<ImagenSala, Long> {
}
