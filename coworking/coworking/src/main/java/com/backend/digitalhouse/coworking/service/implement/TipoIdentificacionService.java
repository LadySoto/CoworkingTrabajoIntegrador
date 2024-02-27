package com.backend.digitalhouse.coworking.service.implement;

import com.backend.digitalhouse.coworking.entity.TipoIdentificacion;
import com.backend.digitalhouse.coworking.repository.TipoIdentificacionRepository;
import com.backend.digitalhouse.coworking.service.ITipoIdentificacionService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TipoIdentificacionService implements ITipoIdentificacionService {
    private final Logger LOGGER = LoggerFactory.getLogger(TipoIdentificacion.class);
    private final TipoIdentificacionRepository tipoIdentificacionRepository;
    private final ModelMapper modelMapper;

    public TipoIdentificacionService(TipoIdentificacionRepository tipoIdentificacionRepository, ModelMapper modelMapper) {
        this.tipoIdentificacionRepository = tipoIdentificacionRepository;
        this.modelMapper = modelMapper;
    }
}