package com.backend.digitalhouse.coworking.service.implement;

import com.backend.digitalhouse.coworking.dto.entrada.calificacion.CalificacionEntradaDto;
import com.backend.digitalhouse.coworking.dto.modificacion.calificacion.CalificacionModificacionEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.calificacion.CalificacionSalidaDto;
import com.backend.digitalhouse.coworking.entity.Calificacion;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.coworking.repository.CalificacionRepository;
import com.backend.digitalhouse.coworking.service.ICalificacionService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CalificacionService implements ICalificacionService {
    private final Logger LOGGER = LoggerFactory.getLogger(CalificacionService.class);
    private final CalificacionRepository calificacionRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CalificacionService(CalificacionRepository calificacionRepository, ModelMapper modelMapper) {
        this.calificacionRepository = calificacionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CalificacionSalidaDto registrarCalificacion(CalificacionEntradaDto calificacion) throws BadRequestException {
        return null;
    }

    @Override
    public CalificacionSalidaDto modificarCalificacion(CalificacionModificacionEntradaDto calificacionModificada) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public CalificacionSalidaDto buscarCalificacionPorId(Long id) {
        return null;
    }

    @Override
    public List<CalificacionSalidaDto> listarCalificaciones() {
        return null;
    }

    @Override
    public void eliminarCalificacion(Long id) throws ResourceNotFoundException {
    }











}
