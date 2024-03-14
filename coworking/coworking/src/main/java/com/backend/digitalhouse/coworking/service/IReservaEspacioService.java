package com.backend.digitalhouse.coworking.service;


import com.backend.digitalhouse.coworking.dto.entrada.reservaEspacio.ReservaEspacioEntradaDto;
import com.backend.digitalhouse.coworking.dto.modificacion.reservaEspacio.ReservaEspacioModificacionEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.reservaEspacio.ReservaEspacioSalidaDto;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import java.util.List;
import java.util.Map;

public interface IReservaEspacioService {
    List<ReservaEspacioSalidaDto> listarReservaEspacios();

    ReservaEspacioSalidaDto registrarReservaEspacio(ReservaEspacioEntradaDto reservaEspacio) throws BadRequestException;

    ReservaEspacioSalidaDto buscarReservaEspacioPorId(Long id);

    void eliminarReservaEspacio(Long id) throws ResourceNotFoundException;

    ReservaEspacioSalidaDto modificarReservaEspacio(Long id, Map<String, Object> camposAModificar) throws ResourceNotFoundException;
}