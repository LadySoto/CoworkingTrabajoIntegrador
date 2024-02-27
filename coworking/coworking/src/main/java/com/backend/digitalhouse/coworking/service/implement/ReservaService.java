package com.backend.digitalhouse.coworking.service.implement;

import com.backend.digitalhouse.coworking.repository.ReservaRepository;
import com.backend.digitalhouse.coworking.service.IReservaService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ReservaService implements IReservaService {
    private final Logger LOGGER = LoggerFactory.getLogger(ReservaService .class);
    private final ReservaRepository reservaRepository;
    private final ModelMapper modelMapper;

    public ReservaService(ReservaRepository reservaRepository, ModelMapper modelMapper) {
        this.reservaRepository = reservaRepository;
        this.modelMapper = modelMapper;
    }
}
