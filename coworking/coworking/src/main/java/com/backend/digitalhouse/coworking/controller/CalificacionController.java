package com.backend.digitalhouse.coworking.controller;

import com.backend.digitalhouse.coworking.dto.entrada.calificacion.CalificacionEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.calificacion.CalificacionSalidaDto;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.coworking.service.ICalificacionService;
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
@RequestMapping("/calificacion")
@CrossOrigin(origins = "http://localhost:3000")
public class CalificacionController {
    private final ICalificacionService calificacionService;
    @Autowired
    public CalificacionController(ICalificacionService calificacionService) {
        this.calificacionService = calificacionService;
    }

    //POST
    @Operation(summary = "Se registro una calificacion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "La calificacion se ha registrado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CalificacionSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @PostMapping("/registrar")
    public ResponseEntity<CalificacionSalidaDto> registrarCalificacion(@Valid @RequestBody CalificacionEntradaDto calificacion) throws BadRequestException {
        return new ResponseEntity<>(calificacionService.registrarCalificacion(calificacion), HttpStatus.CREATED);
    }

    //PATCH
    @Operation(summary = "Modificacion de calificacion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Calificacion modificada correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CalificacionSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Calificacion no encontrada",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })

    @PatchMapping("/modificar/{id}")
    public ResponseEntity<CalificacionSalidaDto> modificarCalificacion(@PathVariable Long id, @Valid @RequestBody Map<String,Object> camposAModificar) throws ResourceNotFoundException {
        return new ResponseEntity<>(calificacionService.modificarCalificacion(id, camposAModificar), HttpStatus.OK);
    }

    //GET
    @Operation(summary = "Búsqueda de calificacion por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Calificacion encontrada correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CalificacionSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Id inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Calificacion no encontrada",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })

    @GetMapping("busqueda/{id}")
    public ResponseEntity<CalificacionSalidaDto> obtenerCalificacionPorId(@PathVariable Long id) {
        return new ResponseEntity<>(calificacionService.buscarCalificacionPorId(id), HttpStatus.OK);
    }

    @Operation(summary = "Listar todos las calificaciones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de calificaciones obtenido correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CalificacionSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content)
    })

    @GetMapping("listar")
    public ResponseEntity<List<CalificacionSalidaDto>> listarCalificaciones() {
        return new ResponseEntity<>(calificacionService.listarCalificaciones(), HttpStatus.OK);
    }

    //DELETE
    @Operation(summary = "Eliminación de calificacion por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Calificacion eliminada correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Id inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Calificacion no encontrada",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<?> eliminarCalificacion(@PathVariable Long id) throws ResourceNotFoundException {
        calificacionService.eliminarCalificacion(id);
        return new ResponseEntity<>("Calificacion eliminada correctamente", HttpStatus.OK);
    }
}
