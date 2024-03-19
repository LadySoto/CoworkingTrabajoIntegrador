package com.backend.digitalhouse.coworking.service;

import com.backend.digitalhouse.coworking.dto.entrada.tipoIdenticacion.TipoIdentificacionEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.tipoIdentificacion.TipoIdentificacionSalidaDto;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import java.util.List;
import java.util.Map;

public interface ITipoIdentificacionService {
    TipoIdentificacionSalidaDto registrarTipoIdentificacion(TipoIdentificacionEntradaDto tipoIdentificacion) throws BadRequestException;

    List<TipoIdentificacionSalidaDto> listarTiposIdentificacion();

    TipoIdentificacionSalidaDto buscarTipoIdentificacionPorId(Long id);

    void eliminarTipoIdentificacion(Long id) throws ResourceNotFoundException;

    TipoIdentificacionSalidaDto modificarTipoIdentificacion(Long id, Map<String, Object> camposAModificar) throws ResourceNotFoundException;
}