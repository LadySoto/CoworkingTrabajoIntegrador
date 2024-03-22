package com.backend.digitalhouse.coworking.repository;

import com.backend.digitalhouse.coworking.entity.Imagen;
import com.backend.digitalhouse.coworking.entity.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImagenRepository extends JpaRepository<Imagen, Long> {
    Optional<Imagen> findBySala(Sala sala);
}