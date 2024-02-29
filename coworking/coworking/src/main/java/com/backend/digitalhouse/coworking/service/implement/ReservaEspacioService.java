package com.backend.digitalhouse.coworking.service.implement;

import com.backend.digitalhouse.coworking.dto.entrada.reservaEspacio.ReservaEspacioEntradaDto;
import com.backend.digitalhouse.coworking.dto.modificacion.reservaEspacio.ReservaEspacioModificacionEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.reservaEspacio.ReservaEspacioSalidaDto;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.coworking.repository.ReservaEspacioRepository;
import com.backend.digitalhouse.coworking.service.IReservaEspacioService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReservaEspacioService implements IReservaEspacioService {
    private final Logger LOGGER = LoggerFactory.getLogger(ReservaEspacioService.class);
    private final ReservaEspacioRepository reservaEspacioRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ReservaEspacioService(ReservaEspacioRepository reservaEspacioRepository, ModelMapper modelMapper) {
        this.reservaEspacioRepository = reservaEspacioRepository;
        this.modelMapper = modelMapper;
        configureMappings();
    }

    private void configureMappings() {
    }

    @Override
    public List<ReservaEspacioSalidaDto> listarReservaEspacios() {
        return null;
    }

    @Override
    public ReservaEspacioSalidaDto registrarReservaEspacio(ReservaEspacioEntradaDto reservaEspacio) throws BadRequestException {
        return null;
    }

    @Override
    public ReservaEspacioSalidaDto buscarReservaEspacioPorId(Long id) {
        return null;
    }

    @Override
    public void eliminarReservaEspacio(Long id) throws ResourceNotFoundException {

    }

    @Override
    public ReservaEspacioSalidaDto modificarReservaEspacio(ReservaEspacioModificacionEntradaDto reservaEspacioModificada) throws ResourceNotFoundException {
        return null;
    }
}
