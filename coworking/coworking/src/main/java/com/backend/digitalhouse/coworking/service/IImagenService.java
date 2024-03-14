package com.backend.digitalhouse.coworking.service;

import com.backend.digitalhouse.coworking.dto.entrada.imagen.ImagenEntradaDto;
import com.backend.digitalhouse.coworking.dto.modificacion.imagen.ImagenModificacionEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.imagen.ImagenSalidaDto;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import java.util.List;
import java.util.Map;

public interface IImagenService {
    List<ImagenSalidaDto> listarImagenes();

    ImagenSalidaDto registrarImagen(ImagenEntradaDto imagen) throws BadRequestException;

    ImagenSalidaDto buscarImagenPorId(Long id);

    void eliminarImagen(Long id) throws ResourceNotFoundException;

    ImagenSalidaDto modificarImagen(Long id, Map<String, Object> camposAModificar) throws ResourceNotFoundException;
}
