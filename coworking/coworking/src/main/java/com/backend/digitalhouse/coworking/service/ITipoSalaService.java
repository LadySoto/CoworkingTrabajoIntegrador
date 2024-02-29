package com.backend.digitalhouse.coworking.service;

import com.backend.digitalhouse.coworking.dto.entrada.tipoSala.TipoSalaEntradaDto;
import com.backend.digitalhouse.coworking.dto.modificacion.tipoSala.TipoSalaModificacionEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.tipoSala.TipoSalaSalidaDto;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import java.util.List;

public interface ITipoSalaService {
    List<TipoSalaSalidaDto> listarTipoSala();

    TipoSalaSalidaDto registrarTipoSala(TipoSalaEntradaDto tipoSala) throws BadRequestException;

    TipoSalaSalidaDto buscarTipoSalaPorId(Long id);

    void eliminarTipoSala(Long id) throws ResourceNotFoundException;

    TipoSalaSalidaDto modificarTipoSala(TipoSalaModificacionEntradaDto tipoSalaModificado) throws ResourceNotFoundException;
}