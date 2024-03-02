package com.backend.digitalhouse.coworking.repository;

import com.backend.digitalhouse.coworking.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}