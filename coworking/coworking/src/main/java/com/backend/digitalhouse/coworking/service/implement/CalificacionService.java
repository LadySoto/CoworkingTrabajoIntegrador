package com.backend.digitalhouse.coworking.service.implement;

import com.backend.digitalhouse.coworking.repository.CalificacionRepository;
import com.backend.digitalhouse.coworking.service.ICalificacionService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CalificacionService implements ICalificacionService {
    private final Logger LOGGER = LoggerFactory.getLogger(CalificacionService.class);
    private final CalificacionRepository calificacionRepository;
    private final ModelMapper modelMapper;

    public CalificacionService(CalificacionRepository calificacionRepository, ModelMapper modelMapper) {
        this.calificacionRepository = calificacionRepository;
        this.modelMapper = modelMapper;
    }
}
