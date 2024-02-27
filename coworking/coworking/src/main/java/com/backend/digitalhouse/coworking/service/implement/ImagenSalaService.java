package com.backend.digitalhouse.coworking.service.implement;

import com.backend.digitalhouse.coworking.repository.ImagenSalaRepository;
import com.backend.digitalhouse.coworking.service.IImagenSalaService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ImagenSalaService implements IImagenSalaService {
    private final Logger LOGGER = LoggerFactory.getLogger(ImagenSalaService.class);
    private final ImagenSalaRepository imagenSalaRepository;
    private final ModelMapper modelMapper;

    public ImagenSalaService(ImagenSalaRepository imagenSalaRepository, ModelMapper modelMapper) {
        this.imagenSalaRepository = imagenSalaRepository;
        this.modelMapper = modelMapper;
    }
}
