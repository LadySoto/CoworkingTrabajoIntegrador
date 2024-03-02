package com.backend.digitalhouse.coworking.service.implement;

import com.backend.digitalhouse.coworking.dto.entrada.usuario.UsuarioEntradaDto;
import com.backend.digitalhouse.coworking.dto.modificacion.usuario.UsuarioModificacionEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.usuario.UsuarioSalidaDto;
import com.backend.digitalhouse.coworking.entity.Usuario;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.coworking.repository.UsuarioRepository;
import com.backend.digitalhouse.coworking.service.IUsuarioService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService implements IUsuarioService {
    private final Logger LOGGER = LoggerFactory.getLogger(Usuario.class);
    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, ModelMapper modelMapper) {
        this.usuarioRepository = usuarioRepository;
        this.modelMapper = modelMapper;
        configureMappings();
    }

    private void configureMappings() {
    }

    @Override
    public List<UsuarioSalidaDto> listarUsuarios() {
        return null;
    }

    @Override
    public UsuarioSalidaDto registrarUsuario(UsuarioEntradaDto usuario) throws BadRequestException {
        return null;
    }

    @Override
    public UsuarioSalidaDto buscarUsuarioPorId(Long id) {
        return null;
    }

    @Override
    public void eliminarUsuario(Long id) throws ResourceNotFoundException {

    }

    @Override
    public UsuarioSalidaDto modificarUsuario(UsuarioModificacionEntradaDto usuarioModificado) throws ResourceNotFoundException {
        return null;
    }
}