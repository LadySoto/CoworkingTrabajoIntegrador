package com.backend.digitalhouse.coworking.controller.security;

import com.backend.digitalhouse.coworking.dto.security.AuthenticationRequest;
import com.backend.digitalhouse.coworking.dto.security.AuthenticationResponse;
import com.backend.digitalhouse.coworking.service.implement.security.AuthenticationService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")

public class AuthenticationController {

  private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);
  @Autowired
  private AuthenticationService authenticationService;

  @PreAuthorize("permitAll")
  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> login(
      @RequestBody @Valid AuthenticationRequest authRequest) {

    LOGGER.info("Correo: {}", authRequest.getCorreo());
    LOGGER.info("Contraseña: {}", authRequest.getContraseña());

    AuthenticationResponse jwtDto = authenticationService.login(authRequest);
    return ResponseEntity.ok(jwtDto);
  }

  @PreAuthorize("permitAll")
  @GetMapping("/public-access")
  public String publicAccesEndpoint(){
    return "Este es un endpoint público";
  }
}
