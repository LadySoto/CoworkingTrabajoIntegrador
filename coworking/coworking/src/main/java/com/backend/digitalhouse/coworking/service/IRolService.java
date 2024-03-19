package com.backend.digitalhouse.coworking.service;

import com.backend.digitalhouse.coworking.dto.entrada.rol.RolEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.rol.RolSalidaDto;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import java.util.List;
import java.util.Map;

public interface IRolService {
        RolSalidaDto registrarRol(RolEntradaDto rol) throws BadRequestException;

        List<RolSalidaDto> listarRoles();

        RolSalidaDto buscarRolPorId(Long id);

        void eliminarRol(Long id) throws ResourceNotFoundException;

        RolSalidaDto modificarRol(Long id, Map<String, Object> camposAModificar) throws ResourceNotFoundException;
    }