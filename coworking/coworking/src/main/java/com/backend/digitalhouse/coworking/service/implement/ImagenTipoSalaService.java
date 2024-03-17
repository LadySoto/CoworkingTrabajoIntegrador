package com.backend.digitalhouse.coworking.service.implement;

import com.backend.digitalhouse.coworking.dto.entrada.imagenTipoSala.ImagenTipoSalaEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.imagenTipoSala.ImagenTipoSalaSalidaDto;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.coworking.service.IImagenTipoSalaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ImagenTipoSalaService implements IImagenTipoSalaService {
    @Override
    public List<ImagenTipoSalaSalidaDto> listarImagenesTipoSala() {
        return null;
    }

    @Override
    public ImagenTipoSalaSalidaDto registrarImagenTipoSala(ImagenTipoSalaEntradaDto imagenTipoSala) throws BadRequestException {
        return null;
    }


    @Override
    public ImagenTipoSalaSalidaDto buscarImagenTipoSalaPorId(Long id) {
        return null;
    }

    @Override
    public void eliminarImagenTipoSala(Long id) throws ResourceNotFoundException {

    }

    @Override
    public ImagenTipoSalaSalidaDto modificarImagenTipoSala(Long id, Map<String, Object> camposAModificar) throws ResourceNotFoundException {
        return null;
    }
}
