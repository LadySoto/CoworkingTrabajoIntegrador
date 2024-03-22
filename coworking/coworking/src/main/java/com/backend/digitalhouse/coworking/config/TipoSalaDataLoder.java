package com.backend.digitalhouse.coworking.config;

import com.backend.digitalhouse.coworking.entity.TipoSala;
import com.backend.digitalhouse.coworking.repository.TipoSalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
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
                new TipoSala("Vip","Sala exclusiva para equipos de trabajo pequeños", "https://media.istockphoto.com/id/187596796/es/foto/sala-de-estar-vip.jpg?s=612x612&w=0&k=20&c=Xfm-bTuPC6xrkuvAv1lmtFgioybJOzVl3bsC226j2gA="),
                new TipoSala("Múltiple","Sala perfecta para equipos de trabajo grandes", "https://img.freepik.com/foto-gratis/sala-conferencias-televisores-presentaciones_181624-26085.jpg"),
                new TipoSala("Pet Friendly", "Sala perfecta para estar en compañía de tu mascota", "https://biodog.es/wp-content/uploads/2019/04/Pet-friendly-biodog-barf.jpg"),
                new TipoSala("Sala Personal", "Sala individual", "https://www.oficinasmontiel.com/blog/wp-content/uploads/2022/03/04-1024x683.jpg")
        );
        tipoSalaRepository.saveAll(tipoSalasIniciales);
    }
}
