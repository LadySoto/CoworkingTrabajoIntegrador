package com.backend.digitalhouse.coworking.service.implement;

import com.backend.digitalhouse.coworking.dto.entrada.reserva.ReservaEntradaDto;
import com.backend.digitalhouse.coworking.dto.modificacion.reserva.ReservaModificacionEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.reserva.ReservaSalidaDto;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.coworking.repository.ReservaRepository;
import com.backend.digitalhouse.coworking.service.IReservaService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReservaService implements IReservaService {
    private final Logger LOGGER = LoggerFactory.getLogger(ReservaService .class);
    private final ReservaRepository reservaRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ReservaService(ReservaRepository reservaRepository, ModelMapper modelMapper) {
        this.reservaRepository = reservaRepository;
        this.modelMapper = modelMapper;
        configureMappings();
    }

    private void configureMappings() {
    }

    @Override
    public List<ReservaSalidaDto> listarReservas() {
        return null;
    }

    @Override
    public ReservaSalidaDto registrarReserva(ReservaEntradaDto reserva) throws BadRequestException {
        return null;
    }

    @Override
    public ReservaSalidaDto buscarReservaPorId(Long id) {
        return null;
    }

    @Override
    public void eliminarReserva(Long id) throws ResourceNotFoundException {

    }

    @Override
    public ReservaSalidaDto modificarReserva(ReservaModificacionEntradaDto reservaModificada) throws ResourceNotFoundException {
        return null;
    }
}
