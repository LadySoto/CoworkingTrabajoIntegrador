package com.backend.digitalhouse.coworking.service.implement;

import com.backend.digitalhouse.coworking.repository.ReservaEspacioRepository;
import com.backend.digitalhouse.coworking.service.IReservaEspacioService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ReservaEspacioService implements IReservaEspacioService {
    private final Logger LOGGER = LoggerFactory.getLogger(ReservaEspacioService.class);
    private final ReservaEspacioRepository reservaEspacioRepository;
    private final ModelMapper modelMapper;

    public ReservaEspacioService(ReservaEspacioRepository reservaEspacioRepository, ModelMapper modelMapper) {
        this.reservaEspacioRepository = reservaEspacioRepository;
        this.modelMapper = modelMapper;
    }
}
