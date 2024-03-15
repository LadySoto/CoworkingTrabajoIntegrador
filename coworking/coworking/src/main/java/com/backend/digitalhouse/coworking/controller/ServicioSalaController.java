package com.backend.digitalhouse.coworking.controller;

import com.backend.digitalhouse.coworking.dto.entrada.servicioSala.ServicioSalaEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.servicioSala.ServicioSalaSalidaDto;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.coworking.service.IServicioSalaService;
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
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/serviciosala")
@CrossOrigin(origins = "http://localhost:3000")
public class ServicioSalaController {
    private final IServicioSalaService servicioSalaService;

    @Autowired
    public ServicioSalaController(IServicioSalaService servicioSalaService) {
        this.servicioSalaService = servicioSalaService;
    }

    //POST
    @Operation(summary = "Se registro un servicio de sala")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Servicio de sala registrado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ServicioSalaSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @PostMapping("/registrar")
    public ResponseEntity<ServicioSalaSalidaDto> registrarServicioSala(@Valid @RequestBody ServicioSalaEntradaDto servicioSala) throws BadRequestException {
        return new ResponseEntity<>(servicioSalaService.registrarServicioSala(servicioSala), HttpStatus.CREATED);
    }

    //PATCH
    @Operation(summary = "Modificacion de un servicio de sala")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Servicio de sala modificado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ServicioSalaSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Servicio de sala no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })

    @PatchMapping("/modificar/{id}")
    public ResponseEntity<ServicioSalaSalidaDto> modificarServicioSala(@PathVariable Long id, @Valid @RequestBody Map<String,Object> camposAModificar) throws ResourceNotFoundException {
        return new ResponseEntity<>(servicioSalaService.modificarServicioSala(id, camposAModificar), HttpStatus.OK);
    }

    //GET
    @Operation(summary = "Búsqueda de un servicio de sala por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Servicio de sala encontrado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ServicioSalaSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Id inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Servicio de sala no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })

    @GetMapping("busqueda/{id}")
    public ResponseEntity<ServicioSalaSalidaDto> obtenerServicioSalaPorId(@PathVariable Long id) {
        return new ResponseEntity<>(servicioSalaService.buscarServicioSalaPorId(id), HttpStatus.OK);
    }

    @Operation(summary = "Listar todos los servicios de sala")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de servicios de sala obtenido correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ServicioSalaSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content)
    })

    @GetMapping("listar")
    public ResponseEntity<List<ServicioSalaSalidaDto>> listarServiciosSala() {
        return new ResponseEntity<>(servicioSalaService.listarServiciosSala(), HttpStatus.OK);
    }

    //DELETE
    @Operation(summary = "Eliminación de un servicio de sala por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Servicio de sala eliminado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Id inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Servicio de sala no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<?> eliminarServicioSala(@PathVariable Long id) throws ResourceNotFoundException {
        servicioSalaService.eliminarServicioSala(id);
        return new ResponseEntity<>("Servicio de sala eliminado correctamente", HttpStatus.OK);
    }

}
