package com.backend.digitalhouse.coworking.controller;

import com.backend.digitalhouse.coworking.dto.AuthenticationRequest;
import com.backend.digitalhouse.coworking.dto.AuthenticationResponse;
import com.backend.digitalhouse.coworking.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")

public class AuthenticationController {

  @Autowired
  private AuthenticationService authenticationService;

  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> login(
      @RequestBody @Valid AuthenticationRequest authRequest) {
    AuthenticationResponse jwtDto = authenticationService.login(authRequest);
    return ResponseEntity.ok(jwtDto);
  }

  @GetMapping("/public-access")
  public String publicAccesEndpoint(){
    return "Este es un endpoint público";
  }
}
