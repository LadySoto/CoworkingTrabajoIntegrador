package com.backend.digitalhouse.coworking.controller;

import com.backend.digitalhouse.coworking.dto.entrada.sala.SalaEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.sala.SalaSalidaDto;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.coworking.service.ISalaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sala")
@CrossOrigin(origins = "http://localhost:3000")
public class SalaController {
    private final ISalaService salaService;

    @Autowired
    public SalaController(ISalaService salaService) {
        this.salaService = salaService;
    }

    //POST
    @Operation(summary = "Se registró una sala")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sala registrada correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SalaSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @PreAuthorize("hasAuthority('SAVE_ONE_SALA')")
    @PostMapping("/registrar")
    public ResponseEntity<SalaSalidaDto> registrarSala(@Valid @RequestBody SalaEntradaDto sala) throws BadRequestException {
        return new ResponseEntity<>(salaService.registrarSala(sala), HttpStatus.CREATED);
    }

    //PATCH
    @Operation(summary = "Modificacion de una sala")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sala modificada correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SalaSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Sala no encontrada",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })

    @PreAuthorize("hasAuthority('UPDATE_ONE_SALA')")
    @PatchMapping("/modificar/{id}")
    public ResponseEntity<SalaSalidaDto> modificarSala(@PathVariable Long id, @Valid @RequestBody Map<String,Object> camposAModificar) throws ResourceNotFoundException {
        return new ResponseEntity<>(salaService.modificarSala(id, camposAModificar), HttpStatus.OK);
    }

    //GET
    @Operation(summary = "Búsqueda de una sala por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sala encontrada correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SalaSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Id inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Sala no encontrada",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })

    @PreAuthorize("hasAuthority('SEARCH_ONE_SALA')")
    @GetMapping("busqueda/{id}")
    public ResponseEntity<SalaSalidaDto> obtenerSalaPorId(@PathVariable Long id) {
        return new ResponseEntity<>(salaService.buscarSalaPorId(id), HttpStatus.OK);
    }

    @Operation(summary = "Búsqueda de una sala por Tipo de Sala")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sala encontrada correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SalaSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Tipo de Sala inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No se encuentran salas",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })

    @GetMapping("/porTipoSala/{nombreTipoSala}")
    public ResponseEntity<List<SalaSalidaDto>> buscarPorTipoSala(@PathVariable String nombreTipoSala) {
        return new ResponseEntity<>(salaService.buscarSalasPorTipoSala(nombreTipoSala), HttpStatus.OK);
    }

    @Operation(summary = "Búsqueda de una sala por Nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sala encontrada correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SalaSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Nombre de Sala inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No se encuentran salas",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })

    @GetMapping("/porNombre/{nombre}")
    public ResponseEntity<List<SalaSalidaDto>> buscarPorNombre(@PathVariable String nombre) {
        return new ResponseEntity<>(salaService.buscarSalasPorNombre(nombre), HttpStatus.OK);
    }

    @Operation(summary = "Búsqueda de una sala por Servicio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sala encontrada correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SalaSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Nombre de Servicio inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No se encuentran salas",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })

    @GetMapping("/porServicio/{nombreServicio}")
    public ResponseEntity<List<SalaSalidaDto>> buscarPorServicio(@PathVariable String nombreServicio) {
        return new ResponseEntity<>(salaService.buscarSalaPorServicio(nombreServicio), HttpStatus.OK);
    }

    @Operation(summary = "Listar todas las salas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de salas obtenido correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SalaSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content)
    })

     @GetMapping("listar")

    public ResponseEntity<List<SalaSalidaDto>> listarSalas() {
        return new ResponseEntity<>(salaService.listarSalas(), HttpStatus.OK);
    }

    //DELETE
    @Operation(summary = "Eliminación de una sala por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sala eliminada correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Id inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Sala no encontrada",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })

    @PreAuthorize("hasAuthority('DELETE_ONE_SALA')")
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<?> eliminarSala(@PathVariable Long id) throws ResourceNotFoundException {
        salaService.eliminarSala(id);
        return new ResponseEntity<>("Sala eliminada correctamente", HttpStatus.OK);
    }
}