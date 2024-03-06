package com.backend.digitalhouse.coworking.service;

import com.backend.digitalhouse.coworking.dto.AuthenticationRequest;
import com.backend.digitalhouse.coworking.dto.AuthenticationResponse;
import com.backend.digitalhouse.coworking.entity.Usuario;
import com.backend.digitalhouse.coworking.repository.UsuarioRepository;
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
    public AuthenticationResponse login(AuthenticationRequest authRequest) {

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(authRequest.getNombre(), authRequest.getContraseña());
        authenticationManager.authenticate(authToken);

        Usuario usuario = usuarioRepository.findByNombre(authRequest.getNombre()).get();

        String jwt = jwtService.generateToken(usuario, generateExtraClaims(usuario));
        
        return new AuthenticationResponse(jwt);

    }

    private Map<String, Object> generateExtraClaims(Usuario usuario) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("nombre", usuario.getNombre());
        extraClaims.put("rol", usuario.getRol().getNombre());

        return extraClaims;
    }
}