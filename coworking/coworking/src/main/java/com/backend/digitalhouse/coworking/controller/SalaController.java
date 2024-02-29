package com.backend.digitalhouse.coworking.controller;

import com.backend.digitalhouse.coworking.dto.entrada.sala.SalaEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.sala.SalaSalidaDto;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.service.ISalaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/sala")
public class SalaController {
    private final ISalaService salaService;

    @Autowired
    public SalaController(ISalaService salaService) {
        this.salaService = salaService;
    }

    @Operation(summary = "Se registra una nueva sala")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sala registrada correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SalaSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @PostMapping("/registrar")
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    public ResponseEntity<SalaSalidaDto> registrarSala(@Valid @RequestBody SalaEntradaDto sala) throws BadRequestException {
        return new ResponseEntity<>(salaService.registrarSala(sala), HttpStatus.CREATED);
    }
}
