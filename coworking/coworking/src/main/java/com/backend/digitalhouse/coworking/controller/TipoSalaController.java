package com.backend.digitalhouse.coworking.controller;

import com.backend.digitalhouse.coworking.dto.entrada.tipoSala.TipoSalaEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.tipoSala.TipoSalaSalidaDto;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.coworking.service.ITipoSalaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tiposala")
@CrossOrigin(origins = "http://localhost:3000")
public class TipoSalaController {
    private final ITipoSalaService tipoSalaService;

    @Autowired
    public TipoSalaController(ITipoSalaService tipoSalaService) {
        this.tipoSalaService = tipoSalaService;
    }

    //POST
    @Operation(summary = "Se registro un tipo de sala")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "El tipo de sala se ha registrada correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TipoSalaSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @PreAuthorize("hasAuthority('SAVE_ONE_TIPOSALA')")
    @PostMapping("/registrar")
    public ResponseEntity<TipoSalaSalidaDto> registrarTipoSala(@Valid @RequestBody TipoSalaEntradaDto tipoSala) throws BadRequestException {
        return new ResponseEntity<>(tipoSalaService.registrarTipoSala(tipoSala), HttpStatus.CREATED);
    }

    @ExceptionHandler(Exception.class)
        public ResponseEntity<Map<String, String>> handleGenericException(Exception exception, HttpServletRequest request){
        Map <String, String> apiError = new HashMap<>();
        apiError.put("message", exception.getLocalizedMessage());
        apiError.put("timestamp", new Date().toString());
        apiError.put("url", request.getRequestURL().toString());
        apiError.put("http-method", request.getMethod());

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (exception instanceof AccessDeniedException){
            status = HttpStatus.FORBIDDEN;
        }

        return ResponseEntity.status(status).body(apiError);
        }

    //PATCH

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
    @PreAuthorize("hasAuthority('UPDATE_ONE_TIPOSALA')")
    @PatchMapping("/modificar/{id}")
    public ResponseEntity<TipoSalaSalidaDto> modificarTipoSala(@PathVariable Long id, @Valid @RequestBody Map<String,Object> camposAModificar) throws ResourceNotFoundException {
        return new ResponseEntity<>(tipoSalaService.modificarTipoSala(id, camposAModificar), HttpStatus.OK);
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

    @PreAuthorize("hasAuthority('SEARCH_ONE_TIPOSALA')")
    @GetMapping("busqueda/{id}")
    public ResponseEntity<TipoSalaSalidaDto> obtenerTipoSalaPorId(@PathVariable Long id) {
        return new ResponseEntity<>(tipoSalaService.buscarTipoSalaPorId(id), HttpStatus.OK);
    }

    @Operation(summary = "Listar todos los tipos de sala")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de los tipos de sala obtenido correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TipoSalaSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content)
    })


    @GetMapping("listar")

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

    @PreAuthorize("hasAuthority('DELETE_ONE_TIPOSALA')")
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<?> eliminarTipoSala(@PathVariable Long id) throws ResourceNotFoundException {
        tipoSalaService.eliminarTipoSala(id);
        return new ResponseEntity<>("El tipo de sala se ha eliminado correctamente", HttpStatus.OK);
    }
}