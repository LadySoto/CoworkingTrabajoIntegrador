package com.backend.digitalhouse.coworking.service;

import com.backend.digitalhouse.coworking.dto.entrada.servicioSala.ServicioSalaEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.servicioSala.ServicioSalaSalidaDto;
import com.backend.digitalhouse.coworking.entity.Sala;
import com.backend.digitalhouse.coworking.entity.Servicio;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import java.util.List;
import java.util.Map;

public interface IServicioSalaService {
    List<ServicioSalaSalidaDto> registrarServicioSala(ServicioSalaEntradaDto servicioSala) throws BadRequestException;

    List<ServicioSalaSalidaDto> listarServiciosSala();

    List<ServicioSalaSalidaDto> listarServiciosSalaPorSalaId(Sala sala);

    List<ServicioSalaSalidaDto> listarServiciosSalaPorServicioId(Servicio servicio);

    ServicioSalaSalidaDto buscarServicioSalaPorId(Long id);

    void eliminarServicioSala(Long id) throws ResourceNotFoundException;

    ServicioSalaSalidaDto modificarServicioSala(Long id, Map<String, Object> camposAModificar) throws ResourceNotFoundException;
}

