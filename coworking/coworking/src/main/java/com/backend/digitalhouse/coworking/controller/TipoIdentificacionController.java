package com.backend.digitalhouse.coworking.controller;


import com.backend.digitalhouse.coworking.dto.entrada.tipoIdenticacion.TipoIdentificacionEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.tipoIdentificacion.TipoIdentificacionSalidaDto;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.coworking.service.ITipoIdentificacionService;
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
@RequestMapping("/tipoidentificacion")
@CrossOrigin(origins = "http://localhost:3000")
public class TipoIdentificacionController {
    private final ITipoIdentificacionService tipoIdentificacionService;
    @Autowired
    public TipoIdentificacionController(ITipoIdentificacionService tipoIdentificacionService) {
        this.tipoIdentificacionService = tipoIdentificacionService;
    }

    //POST
    @Operation(summary = "Se registro un tipo de identificacion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "El tipo de identificacion se ha registrada correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TipoIdentificacionSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })

    @PreAuthorize("hasAuthority('SAVE_ONE_TIPOIDENTIFICACION')")
    @PostMapping("/registrar")
    public ResponseEntity<TipoIdentificacionSalidaDto> registrarTipoIdentificacion(@Valid @RequestBody TipoIdentificacionEntradaDto tipoIdentificacion) throws BadRequestException {
        return new ResponseEntity<>(tipoIdentificacionService.registrarTipoIdentificacion(tipoIdentificacion), HttpStatus.CREATED);
    }

    //PATCH
    @Operation(summary = "Modificacion de tipo de identificacion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de identificacion modificado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TipoIdentificacionSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Tipo de identificacion no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })

    @PreAuthorize("hasAuthority('UPDATE_ONE_TIPOIDENTIFICACION')")
    @PatchMapping("/modificar/{id}")
    public ResponseEntity<TipoIdentificacionSalidaDto> modificarTipoIdentificacion(@PathVariable Long id, @Valid @RequestBody Map<String,Object> camposAModificar) throws ResourceNotFoundException {
        return new ResponseEntity<>(tipoIdentificacionService.modificarTipoIdentificacion(id, camposAModificar), HttpStatus.OK);
    }

    //GET
    @Operation(summary = "Búsqueda de tipo de identificacion por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de identificacion encontrado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TipoIdentificacionSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Id inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Tipo de identificacion no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })

    @PreAuthorize("hasAuthority('SEARCH_ONE_TIPOIDENTIFICACION')")
    @GetMapping("busqueda/{id}")
    public ResponseEntity<TipoIdentificacionSalidaDto> obtenerTipoIdentificacionPorId(@PathVariable Long id) {
        return new ResponseEntity<>(tipoIdentificacionService.buscarTipoIdentificacionPorId(id), HttpStatus.OK);
    }

    @Operation(summary = "Listar todos los tipos de identificacion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de los tipos de identificacion obtenido correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TipoIdentificacionSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content)
    })

    @PreAuthorize("hasAuthority('READ_ALL_TIPOSIDENTIFICACION')")
    @GetMapping("listar")
    public ResponseEntity<List<TipoIdentificacionSalidaDto>> listarTiposIdentificacion() {
        return new ResponseEntity<>(tipoIdentificacionService.listarTiposIdentificacion(), HttpStatus.OK);
    }

    //DELETE
    @Operation(summary = "Eliminación de un tipo de identificacion por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tipo de identificacion eliminado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Id inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Tipo de identificacion no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })

    @PreAuthorize("hasAuthority('DELETE_ONE_TIPOIDENTIFICACION')")
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<?> eliminarTipoIdentificacion(@PathVariable Long id) throws ResourceNotFoundException {
        tipoIdentificacionService.eliminarTipoIdentificacion(id);
        return new ResponseEntity<>("El tipo de identificacion se ha eliminado correctamente", HttpStatus.OK);
    }
}