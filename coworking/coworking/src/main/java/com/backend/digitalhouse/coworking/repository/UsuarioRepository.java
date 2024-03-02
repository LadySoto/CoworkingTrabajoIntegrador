package com.backend.digitalhouse.coworking.repository;

import com.backend.digitalhouse.coworking.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}