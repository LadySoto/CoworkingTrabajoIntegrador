package com.backend.digitalhouse.coworking.service;

import com.backend.digitalhouse.coworking.dto.entrada.sala.SalaEntradaDto;
import com.backend.digitalhouse.coworking.dto.modificacion.sala.SalaModificacionEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.sala.SalaSalidaDto;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import java.util.List;

public interface ISalaService {
    List<SalaSalidaDto> listarSalas();
    SalaSalidaDto registrarSala(SalaEntradaDto sala) throws BadRequestException;

    SalaSalidaDto buscarSalaPorId(Long id);

    void eliminarSala(Long id) throws ResourceNotFoundException;

    SalaSalidaDto modificarSala(SalaModificacionEntradaDto salaModificada) throws ResourceNotFoundException;
}
