package com.backend.digitalhouse.coworking.controller;

import com.backend.digitalhouse.coworking.dto.AuthenticationRequest;
import com.backend.digitalhouse.coworking.dto.AuthenticationResponse;
import com.backend.digitalhouse.coworking.service.AuthenticationService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")

public class AuthenticationController {

  private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);
  @Autowired
  private AuthenticationService authenticationService;

  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> login(
      @RequestBody @Valid AuthenticationRequest authRequest) {

    LOGGER.info("Nombre: {}", authRequest.getNombre());
    LOGGER.info("Contraseña: {}", authRequest.getContraseña());

    AuthenticationResponse jwtDto = authenticationService.login(authRequest);
    return ResponseEntity.ok(jwtDto);
  }

  @GetMapping("/public-access")
  public String publicAccesEndpoint(){
    return "Este es un endpoint público";
  }
}
