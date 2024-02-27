package com.backend.digitalhouse.coworking.service.implement;

import com.backend.digitalhouse.coworking.repository.RolRepository;
import com.backend.digitalhouse.coworking.service.IRolService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RolService implements IRolService {
    private final Logger LOGGER = LoggerFactory.getLogger(RolService.class);
    private final RolRepository rolRepository;
    private final ModelMapper modelMapper;

    public RolService(RolRepository rolRepository, ModelMapper modelMapper) {
        this.rolRepository = rolRepository;
        this.modelMapper = modelMapper;
    }
}
