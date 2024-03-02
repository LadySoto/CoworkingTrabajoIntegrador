package com.backend.digitalhouse.coworking.service.implement;

import com.backend.digitalhouse.coworking.dto.entrada.servicio.ServicioEntradaDto;
import com.backend.digitalhouse.coworking.dto.modificacion.servicio.ServicioModificacionEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.servicio.ServicioSalidaDto;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.coworking.repository.ServicioRepository;
import com.backend.digitalhouse.coworking.service.IServicioService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ServicioService implements IServicioService {
    private final Logger LOGGER = LoggerFactory.getLogger(ServicioService.class);
    private final ServicioRepository servicioRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ServicioService(ServicioRepository servicioRepository, ModelMapper modelMapper) {
        this.servicioRepository = servicioRepository;
        this.modelMapper = modelMapper;
        configureMappings();
    }

    private void configureMappings() {
    }

    @Override
    public List<ServicioSalidaDto> listarServicios() {
        return null;
    }

    @Override
    public ServicioSalidaDto registrarServicio(ServicioEntradaDto servicio) throws BadRequestException {
        return null;
    }

    @Override
    public ServicioSalidaDto buscarServicioPorId(Long id) {
        return null;
    }

    @Override
    public void eliminarServicio(Long id) throws ResourceNotFoundException {

    }

    @Override
    public ServicioSalidaDto modificarServicio(ServicioModificacionEntradaDto servicioModificado) throws ResourceNotFoundException {
        return null;
    }
}