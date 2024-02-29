package com.backend.digitalhouse.coworking.service;

import com.backend.digitalhouse.coworking.dto.entrada.servicio.ServicioEntradaDto;
import com.backend.digitalhouse.coworking.dto.modificacion.servicio.ServicioModificacionEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.servicio.ServicioSalidaDto;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import java.util.List;

public interface IServicioService {
    List<ServicioSalidaDto> listarServicios();

    ServicioSalidaDto registrarServicio(ServicioEntradaDto servicio) throws BadRequestException;

    ServicioSalidaDto buscarServicioPorId(Long id);

    void eliminarServicio(Long id) throws ResourceNotFoundException;

    ServicioSalidaDto modificarServicio(ServicioModificacionEntradaDto servicioModificado) throws ResourceNotFoundException;
}