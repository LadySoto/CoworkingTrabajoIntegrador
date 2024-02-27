package com.backend.digitalhouse.coworking.repository;

import com.backend.digitalhouse.coworking.entity.TipoSala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaRepository extends JpaRepository<TipoSala, Long> {
}

//* OJOOOOO ARREGLAR TIPO SALA CON SALA