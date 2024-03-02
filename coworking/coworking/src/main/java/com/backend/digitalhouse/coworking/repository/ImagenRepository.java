package com.backend.digitalhouse.coworking.repository;

import com.backend.digitalhouse.coworking.entity.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagenRepository extends JpaRepository<Imagen, Long> {
}