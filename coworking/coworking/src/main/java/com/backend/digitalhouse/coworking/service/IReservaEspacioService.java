package com.backend.digitalhouse.coworking.service;

import com.backend.digitalhouse.coworking.dto.entrada.reservaEspacio.ReservaEspacioEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.reservaEspacio.ReservaEspacioSalidaDto;
import com.backend.digitalhouse.coworking.dto.salida.reservaEspacio.SalaReservaSalidaDto;
import com.backend.digitalhouse.coworking.dto.salida.sala.SalaSalidaDto;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

public interface IReservaEspacioService {
    ReservaEspacioSalidaDto registrarReservaEspacio(ReservaEspacioEntradaDto reservaEspacio) throws BadRequestException;

    List<ReservaEspacioSalidaDto> listarReservaEspacios();

    List<LocalDateTime> listarFechasDisponibles(Long idSala) throws BadRequestException;

    List<SalaReservaSalidaDto> listarSalasDisponibles(LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin) throws BadRequestException;

    ReservaEspacioSalidaDto buscarReservaEspacioPorId(Long id);

    void eliminarReservaEspacio(Long id) throws ResourceNotFoundException;
}