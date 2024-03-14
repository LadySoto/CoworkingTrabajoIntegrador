package com.backend.digitalhouse.coworking.service.implement;

import com.backend.digitalhouse.coworking.dto.entrada.imagen.ImagenEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.imagen.ImagenSalidaDto;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.coworking.repository.ImagenRepository;
import com.backend.digitalhouse.coworking.service.IImagenService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class ImagenService implements IImagenService {
    private final Logger LOGGER = LoggerFactory.getLogger(ImagenService.class);
    private final ImagenRepository imagenRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ImagenService(ImagenRepository imagenRepository, ModelMapper modelMapper) {
        this.imagenRepository = imagenRepository;
        this.modelMapper = modelMapper;
        configureMappings();
    }

    private void configureMappings() {
    }

    @Override
    public List<ImagenSalidaDto> listarImagenes() {
        return null;
    }

    @Override
    public ImagenSalidaDto registrarImagen(ImagenEntradaDto imagen) throws BadRequestException {
        return null;
    }

    @Override
    public ImagenSalidaDto buscarImagenPorId(Long id) {
        return null;
    }

    @Override
    public void eliminarImagen(Long id) throws ResourceNotFoundException {

    }

    @Override
    public ImagenSalidaDto modificarImagen(Long id, Map<String, Object> camposAModificar) throws ResourceNotFoundException {
        return null;
    }

}
