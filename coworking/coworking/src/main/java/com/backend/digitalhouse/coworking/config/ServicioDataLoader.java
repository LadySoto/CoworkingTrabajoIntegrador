package com.backend.digitalhouse.coworking.config;

import com.backend.digitalhouse.coworking.entity.Servicio;
import com.backend.digitalhouse.coworking.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;

@Component
public class ServicioDataLoader implements CommandLineRunner {

    private final ServicioRepository servicioRepository;

    @Autowired
    public ServicioDataLoader(ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (servicioRepository.count() == 0) {
            cargaServiciosIniciales();
        }
    }
    private void cargaServiciosIniciales() {
        List<Servicio> serviciosIniciales = Arrays.asList(
                new Servicio("Cafeteria",1),
                new Servicio("Guarderia Infantil",1),
                new Servicio("Guarderia de Mascotas",1),
                new Servicio("Proyector",1),
                new Servicio("Aire Acondicionado",1),
                new Servicio("Wifi",1)
        );
        servicioRepository.saveAll(serviciosIniciales);
    }
}
