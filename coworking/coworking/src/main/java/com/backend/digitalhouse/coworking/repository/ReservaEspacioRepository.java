package com.backend.digitalhouse.coworking.repository;

import com.backend.digitalhouse.coworking.entity.ReservaEspacio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaEspacioRepository extends JpaRepository<ReservaEspacio, Long> {
}