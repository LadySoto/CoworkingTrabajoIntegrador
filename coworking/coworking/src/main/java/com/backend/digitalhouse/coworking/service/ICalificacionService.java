package com.backend.digitalhouse.coworking.service;

import com.backend.digitalhouse.coworking.dto.entrada.calificacion.CalificacionEntradaDto;
import com.backend.digitalhouse.coworking.dto.modificacion.calificacion.CalificacionModificacionEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.calificacion.CalificacionSalidaDto;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import java.util.List;

public interface ICalificacionService {

    List<CalificacionSalidaDto> listarCalificaciones();

    CalificacionSalidaDto registrarCalificacion(CalificacionEntradaDto calificacion) throws BadRequestException;

    CalificacionSalidaDto buscarCalificacionPorId(Long id);

    void eliminarCalificacion(Long id) throws ResourceNotFoundException;

    CalificacionSalidaDto modificarCalificacion(CalificacionModificacionEntradaDto calificacionModificada) throws ResourceNotFoundException;
}
