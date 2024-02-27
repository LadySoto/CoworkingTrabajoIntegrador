package com.backend.digitalhouse.coworking.service.implement;

import com.backend.digitalhouse.coworking.entity.TipoIdentificacion;
import com.backend.digitalhouse.coworking.repository.TipoSalaRepository;
import com.backend.digitalhouse.coworking.service.ITipoSalaService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TipoSalaService implements ITipoSalaService {
    private final Logger LOGGER = LoggerFactory.getLogger(TipoIdentificacion.class);
    private final TipoSalaRepository tipoSalaRepository;
    private final ModelMapper modelMapper;

    public TipoSalaService(TipoSalaRepository tipoSalaRepository, ModelMapper modelMapper) {
        this.tipoSalaRepository = tipoSalaRepository;
        this.modelMapper = modelMapper;
    }
}