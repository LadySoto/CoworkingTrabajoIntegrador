package com.backend.digitalhouse.coworking.service.implement;

import com.backend.digitalhouse.coworking.entity.TipoIdentificacion;
import com.backend.digitalhouse.coworking.repository.UsuarioRepository;
import com.backend.digitalhouse.coworking.service.IUsuarioService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements IUsuarioService {
    private final Logger LOGGER = LoggerFactory.getLogger(TipoIdentificacion.class);
    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, ModelMapper modelMapper) {
        this.usuarioRepository = usuarioRepository;
        this.modelMapper = modelMapper;
    }
}