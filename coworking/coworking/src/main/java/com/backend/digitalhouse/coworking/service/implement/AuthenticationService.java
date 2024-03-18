package com.backend.digitalhouse.coworking.service.implement;

import com.backend.digitalhouse.coworking.dto.AuthenticationRequest;
import com.backend.digitalhouse.coworking.dto.AuthenticationResponse;
import com.backend.digitalhouse.coworking.entity.Usuario;
import com.backend.digitalhouse.coworking.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtService jwtService;

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationService.class);
    public AuthenticationResponse login(AuthenticationRequest authRequest) {


        LOGGER.info("Correo: {}", authRequest.getCorreo());
        LOGGER.info("Contraseña: {}", authRequest.getContraseña());

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(authRequest.getCorreo(), authRequest.getContraseña());
        LOGGER.info("authToken: {}", authToken);

        authenticationManager.authenticate(authToken);

        Usuario usuario = usuarioRepository.findByCorreo(authRequest.getCorreo()).get();
        LOGGER.info("usuario: {}", usuario);

        String jwt = jwtService.generateToken(usuario, generateExtraClaims(usuario));
        
        return new AuthenticationResponse(jwt);

    }

    private Map<String, Object> generateExtraClaims(Usuario usuario) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("correo", usuario.getCorreo());
        extraClaims.put("rol", usuario.getRol().getNombre());
        extraClaims.put("permissions", usuario.getAuthorities());

        return extraClaims;
    }
}