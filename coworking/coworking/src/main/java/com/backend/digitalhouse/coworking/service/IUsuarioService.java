package com.backend.digitalhouse.coworking.service;

import com.backend.digitalhouse.coworking.dto.entrada.usuario.UsuarioEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.usuario.UsuarioSalidaDto;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import java.util.List;
import java.util.Map;

public interface IUsuarioService {
    UsuarioSalidaDto registrarUsuario(UsuarioEntradaDto usuario) throws BadRequestException;

    List<UsuarioSalidaDto> listarUsuarios();

    UsuarioSalidaDto buscarUsuarioPorId(Long id);

    void eliminarUsuario(Long id) throws ResourceNotFoundException;

    UsuarioSalidaDto modificarUsuario(Long id, Map<String, Object> camposAModificar) throws ResourceNotFoundException;

    UsuarioSalidaDto modificarIdRolUsuario(Long id, Long nuevoIdRol) throws ResourceNotFoundException;
}