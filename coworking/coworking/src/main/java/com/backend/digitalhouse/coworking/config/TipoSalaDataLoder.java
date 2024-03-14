package com.backend.digitalhouse.coworking.config;

import com.backend.digitalhouse.coworking.entity.TipoSala;
import com.backend.digitalhouse.coworking.repository.TipoSalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.Arrays;
import java.util.List;

public class TipoSalaDataLoder implements CommandLineRunner {

    private final TipoSalaRepository tipoSalaRepository;

    @Autowired
    public TipoSalaDataLoder(TipoSalaRepository tipoSalaRepository) {
        this.tipoSalaRepository = tipoSalaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (tipoSalaRepository.count() == 0) {
            cargaTiposSalasIniciales();
        }
    }

    private void cargaTiposSalasIniciales() {
        List<TipoSala> tipoSalasIniciales = Arrays.asList(
                new TipoSala("Vip","Sala exclusiva para equipos de trabajo peqeños"),
                new TipoSala("Múltiple","Sala perfecta para equipos de trabajo grandes"),
                new TipoSala("Pet Friendly", "Sala perfecta para estar en compañía de tu mascota"),
                new TipoSala("Sala Personal", "Sala individual")
        );
        tipoSalaRepository.saveAll(tipoSalasIniciales);
    }
}
