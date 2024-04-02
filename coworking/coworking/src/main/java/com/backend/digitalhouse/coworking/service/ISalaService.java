package com.backend.digitalhouse.coworking.service;

import com.backend.digitalhouse.coworking.dto.entrada.sala.SalaEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.sala.SalaSalidaDto;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import java.util.List;
import java.util.Map;

public interface ISalaService {
    SalaSalidaDto registrarSala(SalaEntradaDto sala) throws BadRequestException;

    List<SalaSalidaDto> listarSalas();

    SalaSalidaDto buscarSalaPorId(Long id);

    List<SalaSalidaDto> buscarSalasPorTipoSala(String nombreTipoSala);

    List<SalaSalidaDto> buscarSalasPorNombre(String nombre);

    List<SalaSalidaDto> buscarSalaPorServicio(String nombreServicio);

    void eliminarSala(Long id) throws ResourceNotFoundException;

    SalaSalidaDto modificarSala(Long id, Map<String, Object> camposAModificar) throws ResourceNotFoundException;
}
