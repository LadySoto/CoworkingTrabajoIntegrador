package com.backend.digitalhouse.coworking.service.implement;

import com.backend.digitalhouse.coworking.dto.entrada.rol.RolEntradaDto;
import com.backend.digitalhouse.coworking.dto.modificacion.rol.RolModificacionEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.rol.RolSalidaDto;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.coworking.repository.RolRepository;
import com.backend.digitalhouse.coworking.service.IRolService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RolService implements IRolService {
    private final Logger LOGGER = LoggerFactory.getLogger(RolService.class);
    private final RolRepository rolRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RolService(RolRepository rolRepository, ModelMapper modelMapper) {
        this.rolRepository = rolRepository;
        this.modelMapper = modelMapper;
    }

    private void configureMappings() {
    }

    @Override
    public List<RolSalidaDto> listarRoles() {
        return null;
    }

    @Override
    public RolSalidaDto registrarRol(RolEntradaDto rol) throws BadRequestException {
        return null;
    }

    @Override
    public RolSalidaDto buscarRolPorId(Long id) {
        return null;
    }

    @Override
    public void eliminarRol(Long id) throws ResourceNotFoundException {

    }

    @Override
    public RolSalidaDto modificarRol(RolModificacionEntradaDto rolModificado) throws ResourceNotFoundException {
        return null;
    }
}
