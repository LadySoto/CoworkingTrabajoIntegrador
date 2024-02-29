package com.backend.digitalhouse.coworking.service;

import com.backend.digitalhouse.coworking.dto.entrada.imagenSala.ImagenSalaEntradaDto;
import com.backend.digitalhouse.coworking.dto.modificacion.imagenSala.ImagenSalaModificacionEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.imagenSala.ImagenSalaSalidaDto;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import java.util.List;

public interface IImagenSalaService {

    List<ImagenSalaSalidaDto> listarImagenesSala();

    ImagenSalaSalidaDto registrarImagenSala(ImagenSalaEntradaDto imagenSala) throws BadRequestException;

    ImagenSalaSalidaDto buscarImagenSalaPorId(Long id);

    void eliminarImagenSala(Long id) throws ResourceNotFoundException;

    ImagenSalaSalidaDto modificarImagenSala(ImagenSalaModificacionEntradaDto imagenSalaModificada) throws ResourceNotFoundException;
}