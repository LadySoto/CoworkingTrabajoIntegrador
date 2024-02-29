package com.backend.digitalhouse.coworking.service;

import com.backend.digitalhouse.coworking.dto.entrada.tipoIdenticacion.TipoIdentificacionEntradaDto;
import com.backend.digitalhouse.coworking.dto.modificacion.tipoIdentificacion.TipoIdentificacionModificacionEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.tipoIdentificacion.TipoIdentificacionSalidaDto;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import java.util.List;

public interface ITipoIdentificacionService {
    List<TipoIdentificacionSalidaDto> listarTiposIdentificacion();

    TipoIdentificacionSalidaDto registrarTipoIdentificacion(TipoIdentificacionEntradaDto tipoIdentificacion) throws BadRequestException;

    TipoIdentificacionSalidaDto buscarTipoIdentificacionPorId(Long id);

    void eliminarTipoIdentificacion(Long id) throws ResourceNotFoundException;

    TipoIdentificacionSalidaDto modificarTipoIdentificacion(TipoIdentificacionModificacionEntradaDto tipoIdentificacionModificado) throws ResourceNotFoundException;
}