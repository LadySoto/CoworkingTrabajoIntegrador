package com.backend.digitalhouse.coworking.repository;

import com.backend.digitalhouse.coworking.entity.ReservaEspacio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservaEspacioRepository extends JpaRepository<ReservaEspacio, Long> {
    List<ReservaEspacio> findBySalaIdAndFechaHoraInicioBetween(Long salaId, LocalDateTime fechaInicio, LocalDateTime fechaFin);
}