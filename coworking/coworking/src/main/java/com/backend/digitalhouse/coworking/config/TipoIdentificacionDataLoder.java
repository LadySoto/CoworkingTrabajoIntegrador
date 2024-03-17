package com.backend.digitalhouse.coworking.config;

import com.backend.digitalhouse.coworking.entity.TipoIdentificacion;
import com.backend.digitalhouse.coworking.repository.TipoIdentificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.Arrays;
import java.util.List;

public class TipoIdentificacionDataLoder implements CommandLineRunner {

    private final TipoIdentificacionRepository tipoIdentificacionRepository;

    @Autowired
    public TipoIdentificacionDataLoder(TipoIdentificacionRepository tipoIdentificacionRepository) {
        this.tipoIdentificacionRepository = tipoIdentificacionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (tipoIdentificacionRepository.count() == 0) {
            cargaTiposIdentificacionesIniciales();
        }
    }

    private void cargaTiposIdentificacionesIniciales() {
        List<TipoIdentificacion> tipoIdentificacionesIniciales = Arrays.asList(
                new TipoIdentificacion("Nit",1),
                new TipoIdentificacion("C.C",1),
                new TipoIdentificacion("C.E",1),
                new TipoIdentificacion("T.T", 1)
        );
        tipoIdentificacionRepository.saveAll(tipoIdentificacionesIniciales);
    }
}
