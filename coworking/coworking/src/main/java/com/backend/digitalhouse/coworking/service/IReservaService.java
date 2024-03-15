package com.backend.digitalhouse.coworking.service;

import com.backend.digitalhouse.coworking.dto.entrada.reserva.ReservaEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.reserva.ReservaSalidaDto;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import java.util.List;
import java.util.Map;

public interface IReservaService {
    List<ReservaSalidaDto> listarReservas();

    ReservaSalidaDto registrarReserva(ReservaEntradaDto reserva) throws BadRequestException;

    ReservaSalidaDto buscarReservaPorId(Long id);

    void eliminarReserva(Long id) throws ResourceNotFoundException;

    ReservaSalidaDto modificarReserva(Long id, Map<String, Object> camposAModificar) throws ResourceNotFoundException;
}