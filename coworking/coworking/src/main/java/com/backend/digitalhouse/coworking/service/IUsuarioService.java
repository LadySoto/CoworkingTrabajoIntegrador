package com.backend.digitalhouse.coworking.service;

import com.backend.digitalhouse.coworking.dto.entrada.tipoSala.TipoSalaEntradaDto;
import com.backend.digitalhouse.coworking.dto.entrada.usuario.UsuarioEntradaDto;
import com.backend.digitalhouse.coworking.dto.modificacion.tipoSala.TipoSalaModificacionEntradaDto;
import com.backend.digitalhouse.coworking.dto.modificacion.usuario.UsuarioModificacionEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.tipoSala.TipoSalaSalidaDto;
import com.backend.digitalhouse.coworking.dto.salida.usuario.UsuarioSalidaDto;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import java.util.List;
import java.util.Map;

public interface IUsuarioService {
    List<UsuarioSalidaDto> listarUsuarios();

    UsuarioSalidaDto registrarUsuario(UsuarioEntradaDto usuario) throws BadRequestException;

    UsuarioSalidaDto buscarUsuarioPorId(Long id);

    void eliminarUsuario(Long id) throws ResourceNotFoundException;

    UsuarioSalidaDto modificarUsuario(Long id, Map<String, Object> camposAModificar) throws ResourceNotFoundException;
}