package com.backend.digitalhouse.coworking.repository;

import com.backend.digitalhouse.coworking.entity.TipoSala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoSalaRepository extends JpaRepository<TipoSala, Long> {
}
