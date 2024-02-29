package com.backend.digitalhouse.coworking.repository;

import com.backend.digitalhouse.coworking.entity.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Long> {
}