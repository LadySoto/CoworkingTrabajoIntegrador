package com.backend.digitalhouse.coworking.service.implement;

import com.backend.digitalhouse.coworking.dto.entrada.rol.RolEntradaDto;
import com.backend.digitalhouse.coworking.dto.modificacion.rol.RolModificacionEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.rol.RolSalidaDto;
import com.backend.digitalhouse.coworking.entity.Rol;
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
import java.util.Map;

@Service
public class RolService implements IRolService {
    private final Logger LOGGER = LoggerFactory.getLogger(RolService.class);
    private final RolRepository rolRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RolService(RolRepository rolRepository, ModelMapper modelMapper) {
        this.rolRepository = rolRepository;
        this.modelMapper = modelMapper;
        configureMappings();
    }

    @Override
    public RolSalidaDto registrarRol(RolEntradaDto rol) throws BadRequestException {
            if (rol != null) {
                Rol rolGuardado = rolRepository.save(dtoEntradaAEntidad(rol));
                RolSalidaDto rolSalidaDto = entidadADtoSalida(rolGuardado);
                LOGGER.info("Rol registrado: {}", rolSalidaDto);
                return rolSalidaDto;
            } else {
                LOGGER.error("No se puede registrar el rol");
                throw new BadRequestException("No se puede registrar el rol");
            }
    }

    @Override
    public RolSalidaDto buscarRolPorId(Long id) {
        Rol rolBuscado = null;
        try{
            rolBuscado = rolRepository.findById(id).orElse(null);
        }catch(Exception e){
            LOGGER.info("Id de rol no se encuentra");
        }
        RolSalidaDto rolSalida = null;
        if (rolBuscado != null) {
            rolSalida = entidadADtoSalida(rolBuscado);
            LOGGER.info("Rol por id: {}", rolSalida);
        } else LOGGER.info("Rol por id: {}", id);
        return rolSalida;
    }

    @Override
    public List<RolSalidaDto> listarRoles() {
        List<RolSalidaDto> roles = rolRepository.findAll().stream()
                .map(this::entidadADtoSalida).toList();
        LOGGER.info("Listado de todos los roles: {}", roles);
        return roles;
    }

    @Override
    public void eliminarRol(Long id) throws ResourceNotFoundException {
        if (buscarRolPorId(id) != null) {
            rolRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el rol con id: {}", id);
        } else {
            LOGGER.error("No se ha encontrado el rol con id {}", id);
            throw new ResourceNotFoundException("No se ha encontrado el rol con id " + id);
        }
    }

    @Override
    public RolSalidaDto modificarRol(Long id, Map<String, Object> camposAModificar) throws ResourceNotFoundException {
        return null;
    }

    private void configureMappings() {
        modelMapper.typeMap(RolEntradaDto.class, Rol.class);
        modelMapper.typeMap(Rol.class, RolSalidaDto.class);
    }

    private Rol dtoEntradaAEntidad(RolEntradaDto rolEntradaDto) {
        return modelMapper.map(rolEntradaDto, Rol.class);
    }

    private RolSalidaDto entidadADtoSalida(Rol rol) {
        return modelMapper.map(rol, RolSalidaDto.class);
    }

    private Rol dtoSalidaAEntidad(RolSalidaDto rolSalida) {
        return modelMapper.map(rolSalida, Rol.class);
    }

    private Rol dtoModificadoAEntidad(RolModificacionEntradaDto rolModificacionEntradaDto) {
        return modelMapper.map(rolModificacionEntradaDto, Rol.class);
    }

}
