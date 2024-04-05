package com.backend.digitalhouse.coworking.service;

import com.backend.digitalhouse.coworking.dto.entrada.servicio.ServicioEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.servicio.ServicioSalidaDto;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import java.util.List;
import java.util.Map;

public interface IServicioService {
    ServicioSalidaDto registrarServicio(ServicioEntradaDto servicio) throws BadRequestException;

    List<ServicioSalidaDto> listarServicios();

    ServicioSalidaDto buscarServicioPorId(Long id);

    List<ServicioSalidaDto> buscarServiciosPorNombre(String nombre);

    void eliminarServicio(Long id) throws ResourceNotFoundException;

    ServicioSalidaDto modificarServicio(Long id, Map<String, Object> camposAModificar) throws ResourceNotFoundException;
}