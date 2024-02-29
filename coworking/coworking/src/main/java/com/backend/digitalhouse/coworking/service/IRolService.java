package com.backend.digitalhouse.coworking.service;

import com.backend.digitalhouse.coworking.dto.entrada.rol.RolEntradaDto;
import com.backend.digitalhouse.coworking.dto.modificacion.rol.RolModificacionEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.rol.RolSalidaDto;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import java.util.List;

    public interface IRolService {
        List<RolSalidaDto> listarRoles();

        RolSalidaDto registrarRol(RolEntradaDto rol) throws BadRequestException;

        RolSalidaDto buscarRolPorId(Long id);

        void eliminarRol(Long id) throws ResourceNotFoundException;

        RolSalidaDto modificarRol(RolModificacionEntradaDto rolModificado) throws ResourceNotFoundException;
    }