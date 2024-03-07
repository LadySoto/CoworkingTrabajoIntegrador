package com.backend.digitalhouse.coworking.config;

import com.backend.digitalhouse.coworking.entity.Rol;
import com.backend.digitalhouse.coworking.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;

@Component
public class RolDataLoder implements CommandLineRunner {

    private final RolRepository rolRepository;

    @Autowired
    public RolDataLoder(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (rolRepository.count() == 0) {
            cargaRolesIniciales();
        }
    }

    private void cargaRolesIniciales() {
        List<Rol> serviciosIniciales = Arrays.asList(
                new Rol("Administrador",1),
                new Rol("Usuario Registrado",1),
                new Rol("Usuario Anonimo",1)
        );
        rolRepository.saveAll(serviciosIniciales);
    }
}
