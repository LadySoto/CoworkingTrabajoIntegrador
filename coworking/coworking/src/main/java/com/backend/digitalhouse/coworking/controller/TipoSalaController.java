package com.backend.digitalhouse.coworking.controller;

import com.backend.digitalhouse.coworking.dto.entrada.tipoSala.TipoSalaEntradaDto;
import com.backend.digitalhouse.coworking.dto.modificacion.tipoSala.TipoSalaModificacionEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.tipoSala.TipoSalaSalidaDto;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.coworking.service.ITipoSalaService;
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

@RestController
@RequestMapping("/tiposala")
public class TipoSalaController {
    private final ITipoSalaService tipoSalaService;

    @Autowired
    public TipoSalaController(ITipoSalaService tipoSalaService) {
        this.tipoSalaService = tipoSalaService;
    }

    //POST
    @Operation(summary = "Se registra un nuevo tipo de sala")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "El tipo de sala se ha registrada correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TipoSalaSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @PostMapping("/registrar")
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    public ResponseEntity<TipoSalaSalidaDto> registrarTipoSala(@Valid @RequestBody TipoSalaEntradaDto tipoSala) throws BadRequestException {
        return new ResponseEntity<>(tipoSalaService.registrarTipoSala(tipoSala), HttpStatus.CREATED);
    }

    //PUT
    @Operation(summary = "Modificacion de tipo sala")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo sala modificado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TipoSalaSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Tipo de sala no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })

    @PutMapping("/modificar")
    public ResponseEntity<TipoSalaSalidaDto> modificarTipoSala(@Valid @RequestBody TipoSalaModificacionEntradaDto tipoSala) throws ResourceNotFoundException {
        return new ResponseEntity<>(tipoSalaService.modificarTipoSala(tipoSala), HttpStatus.OK);
    }

    //GET
    @Operation(summary = "Búsqueda de tipo sala por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo sala encontrada correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TipoSalaSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Id inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Tipo sala no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })

    @GetMapping("busqueda/{id}")
    public ResponseEntity<TipoSalaSalidaDto> obtenerTipoSalaPorId(@PathVariable Long id) {
        return new ResponseEntity<>(tipoSalaService.buscarTipoSalaPorId(id), HttpStatus.OK);
    }

    @Operation(summary = "Listar todos los tipos de sala")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de los tipos de sala obtenido correspondiente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TipoSalaSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content)
    })

    @GetMapping()
    public ResponseEntity<List<TipoSalaSalidaDto>> listarTipoSala() {
        return new ResponseEntity<>(tipoSalaService.listarTipoSala(), HttpStatus.OK);
    }

    //DELETE
    @Operation(summary = "Eliminación de un tipo de sala por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tipo de sala eliminada correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Id inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Tipo sala no encontrada",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<?> eliminarTipoSala(@PathVariable Long id) throws ResourceNotFoundException {
        tipoSalaService.eliminarTipoSala(id);
        return new ResponseEntity<>("El tipo sala se ha eliminado correctamente", HttpStatus.NO_CONTENT);
    }
}