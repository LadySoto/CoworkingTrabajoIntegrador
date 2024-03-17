package com.backend.digitalhouse.coworking.controller;

import com.backend.digitalhouse.coworking.dto.entrada.servicio.ServicioEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.servicio.ServicioSalidaDto;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.coworking.service.IServicioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/servicio")
@CrossOrigin(origins = "http://localhost:3000")
public class ServicioController {

    private final IServicioService servicioService;
    @Autowired
    public ServicioController(IServicioService servicioService) {
        this.servicioService = servicioService;
    }

    //POST
    @Operation(summary = "Se registro un servicio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Servicio registrado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ServicioSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @PostMapping("/registrar")
    public ResponseEntity<ServicioSalidaDto> registrarServicio(@Valid @RequestBody ServicioEntradaDto servicio) throws BadRequestException {
        return new ResponseEntity<>(servicioService.registrarServicio(servicio), HttpStatus.CREATED);
    }

    //PATCH
    @Operation(summary = "Modificacion de un servicio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Servicio modificado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ServicioSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Servicio no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })

    @PatchMapping("/modificar/{id}")
    public ResponseEntity<ServicioSalidaDto> modificarServicio(@PathVariable Long id, @Valid @RequestBody Map<String,Object> camposAModificar) throws ResourceNotFoundException {
        return new ResponseEntity<>(servicioService.modificarServicio(id, camposAModificar), HttpStatus.OK);
    }

    //GET
    @Operation(summary = "Búsqueda de un servicio por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Servicio encontrado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ServicioSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Id inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Servicio no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })

    @GetMapping("busqueda/{id}")
    public ResponseEntity<ServicioSalidaDto> obtenerServicioPorId(@PathVariable Long id) {
        return new ResponseEntity<>(servicioService.buscarServicioPorId(id), HttpStatus.OK);
    }

    @Operation(summary = "Listar todas las servicios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de servicios obtenido correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ServicioSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content)
    })

    @GetMapping("listar")
    public ResponseEntity<List<ServicioSalidaDto>> listarServicios() {
        return new ResponseEntity<>(servicioService.listarServicios(), HttpStatus.OK);
    }

    //DELETE
    @Operation(summary = "Eliminación de un servicio por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Servicio eliminado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Id inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Servicio no encontrada",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<?> eliminarServicio(@PathVariable Long id) throws ResourceNotFoundException {
        servicioService.eliminarServicio(id);
        return new ResponseEntity<>("Servicio eliminado correctamente", HttpStatus.OK);
    }
}
