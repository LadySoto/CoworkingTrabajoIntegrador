package com.backend.digitalhouse.coworking.repository;

import com.backend.digitalhouse.coworking.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
}
