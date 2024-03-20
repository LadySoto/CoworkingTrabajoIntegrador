package com.backend.digitalhouse.coworking.service;

import com.backend.digitalhouse.coworking.dto.entrada.tipoSala.TipoSalaEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.tipoSala.TipoSalaSalidaDto;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import java.util.List;
import java.util.Map;

public interface ITipoSalaService {
    TipoSalaSalidaDto registrarTipoSala(TipoSalaEntradaDto tipoSala) throws BadRequestException;
    List<TipoSalaSalidaDto> listarTipoSala();

    TipoSalaSalidaDto buscarTipoSalaPorId(Long id);

    void eliminarTipoSala(Long id) throws ResourceNotFoundException;

    TipoSalaSalidaDto modificarTipoSala(Long id, Map<String, Object> camposAModificar) throws ResourceNotFoundException;
}