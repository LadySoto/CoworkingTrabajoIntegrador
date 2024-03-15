package com.backend.digitalhouse.coworking.service;

import com.backend.digitalhouse.coworking.dto.entrada.imagenTipoSala.ImagenTipoSalaEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.imagenTipoSala.ImagenTipoSalaSalidaDto;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import java.util.List;
import java.util.Map;

public interface IImagenTipoSalaService {
    List<ImagenTipoSalaSalidaDto> listarImagenesTipoSala();

    ImagenTipoSalaSalidaDto registrarImagenTipoSala(ImagenTipoSalaEntradaDto imagenTipoSala) throws BadRequestException;

    ImagenTipoSalaSalidaDto buscarImagenTipoSalaPorId(Long id);

    void eliminarImagenTipoSala(Long id) throws ResourceNotFoundException;

    ImagenTipoSalaSalidaDto modificarImagenTipoSala(Long id, Map<String, Object> camposAModificar) throws ResourceNotFoundException;
}