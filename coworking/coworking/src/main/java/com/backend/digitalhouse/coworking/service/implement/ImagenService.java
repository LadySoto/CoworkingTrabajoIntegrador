package com.backend.digitalhouse.coworking.service.implement;

import com.backend.digitalhouse.coworking.repository.ImagenRepository;
import com.backend.digitalhouse.coworking.service.IImagenService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ImagenService implements IImagenService {
    private final Logger LOGGER = LoggerFactory.getLogger(ImagenService.class);
    private final ImagenRepository imagenRepository;
    private final ModelMapper modelMapper;

    public ImagenService(ImagenRepository imagenRepository, ModelMapper modelMapper) {
        this.imagenRepository = imagenRepository;
        this.modelMapper = modelMapper;
    }
}
