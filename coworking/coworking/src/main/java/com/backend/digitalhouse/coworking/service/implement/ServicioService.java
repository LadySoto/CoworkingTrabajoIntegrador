package com.backend.digitalhouse.coworking.service.implement;

import com.backend.digitalhouse.coworking.repository.ServicioRepository;
import com.backend.digitalhouse.coworking.service.ISalaService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ServicioService implements ISalaService {
    private final Logger LOGGER = LoggerFactory.getLogger(RolService.class);
    private final ServicioRepository servicioRepository;
    private final ModelMapper modelMapper;

    public ServicioService(ServicioRepository servicioRepository, ModelMapper modelMapper) {
        this.servicioRepository = servicioRepository;
        this.modelMapper = modelMapper;
    }
}