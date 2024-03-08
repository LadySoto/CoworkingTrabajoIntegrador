package com.backend.digitalhouse.coworking.service;

import com.backend.digitalhouse.coworking.dto.entrada.servicioSala.ServicioSalaEntradaDto;
import com.backend.digitalhouse.coworking.dto.modificacion.servicioSala.ServicioSalaModificacionEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.servicioSala.ServicioSalaSalidaDto;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import java.util.List;

public interface IServicioSalaService {
    List<ServicioSalaSalidaDto> listarServiciosSalas();
    ServicioSalaSalidaDto registrarServicioSala(ServicioSalaEntradaDto servicioSala) throws BadRequestException;

    ServicioSalaSalidaDto buscarServicioSalaPorId(Long id);

    void eliminarServicioSala(Long id) throws ResourceNotFoundException;

    ServicioSalaSalidaDto modificarServicioSala(ServicioSalaModificacionEntradaDto servicioSalaModificada) throws ResourceNotFoundException;
}

