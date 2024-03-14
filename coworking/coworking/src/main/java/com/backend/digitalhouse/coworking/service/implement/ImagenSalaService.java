package com.backend.digitalhouse.coworking.service.implement;

import com.backend.digitalhouse.coworking.dto.entrada.imagenSala.ImagenSalaEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.imagenSala.ImagenSalaSalidaDto;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.coworking.repository.ImagenSalaRepository;
import com.backend.digitalhouse.coworking.service.IImagenSalaService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class ImagenSalaService implements IImagenSalaService {
    private final Logger LOGGER = LoggerFactory.getLogger(ImagenSalaService.class);
    private final ImagenSalaRepository imagenSalaRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ImagenSalaService(ImagenSalaRepository imagenSalaRepository, ModelMapper modelMapper) {
        this.imagenSalaRepository = imagenSalaRepository;
        this.modelMapper = modelMapper;
        configureMappings();
    }

    private void configureMappings() {
    }

    @Override
    public List<ImagenSalaSalidaDto> listarImagenesSala() {
        return null;
    }

    @Override
    public ImagenSalaSalidaDto registrarImagenSala(ImagenSalaEntradaDto imagenSala) throws BadRequestException {
        return null;
    }

    @Override
    public ImagenSalaSalidaDto buscarImagenSalaPorId(Long id) {
        return null;
    }

    @Override
    public void eliminarImagenSala(Long id) throws ResourceNotFoundException {

    }

    @Override
    public ImagenSalaSalidaDto modificarImagenSala(Long id, Map<String, Object> camposAModificar) throws ResourceNotFoundException {
        return null;
    }

}
