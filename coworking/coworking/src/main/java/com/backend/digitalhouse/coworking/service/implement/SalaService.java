package com.backend.digitalhouse.coworking.service.implement;

import com.backend.digitalhouse.coworking.repository.SalaRepository;
import com.backend.digitalhouse.coworking.service.ISalaService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SalaService implements ISalaService {
    private final Logger LOGGER = LoggerFactory.getLogger(RolService.class);
    private final SalaRepository salaRepository;
    private final ModelMapper modelMapper;

    public SalaService(SalaRepository salaRepository, ModelMapper modelMapper) {
        this.salaRepository = salaRepository;
        this.modelMapper = modelMapper;
    }
}