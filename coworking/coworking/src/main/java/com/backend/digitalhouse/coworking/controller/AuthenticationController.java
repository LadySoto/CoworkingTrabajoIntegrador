package com.backend.digitalhouse.coworking.controller;

import com.backend.digitalhouse.coworking.dto.AuthenticationRequest;
import com.backend.digitalhouse.coworking.dto.AuthenticationResponse;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")

public class AuthenticationController {

  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> login(
      @RequestBody @Valid AuthenticationRequest authRequest) {
    return null;

  }
}
