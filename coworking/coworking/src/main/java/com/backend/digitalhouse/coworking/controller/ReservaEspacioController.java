package com.backend.digitalhouse.coworking.controller;

import com.backend.digitalhouse.coworking.dto.entrada.reservaEspacio.ReservaEspacioEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.reservaEspacio.ReservaEspacioSalidaDto;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.coworking.service.IReservaEspacioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reservaespacio")
@CrossOrigin(origins = "http://localhost:3000")
public class ReservaEspacioController {
    private final IReservaEspacioService reservaEspacioService;

    public ReservaEspacioController(IReservaEspacioService reservaEspacioService) {
        this.reservaEspacioService = reservaEspacioService;
    }

    //POST
    @Operation(summary = "Se registro una reserva de espacio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "La reserva de espacio se ha registrado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReservaEspacioSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @PostMapping("/registrar")
    public ResponseEntity<ReservaEspacioSalidaDto> registrarReservaEspacio(@Valid @RequestBody ReservaEspacioEntradaDto reservaEspacio) throws BadRequestException {
        return new ResponseEntity<>(reservaEspacioService.registrarReservaEspacio(reservaEspacio), HttpStatus.CREATED);
    }

    //PATCH
    @Operation(summary = "Modificacion de una reserva de espacio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La reserva de espacio se ha modificado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReservaEspacioSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Reserva de espacio no encontrada",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })

    @PatchMapping("/modificar/{id}")
    public ResponseEntity<ReservaEspacioSalidaDto> modificarReservaEspacio(@PathVariable Long id, @Valid @RequestBody Map<String,Object> camposAModificar) throws ResourceNotFoundException {
        return new ResponseEntity<>(reservaEspacioService.modificarReservaEspacio(id, camposAModificar), HttpStatus.OK);
    }

    //GET
    @Operation(summary = "Búsqueda de una reserva de espacio por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva de espacio encontrada correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReservaEspacioSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Id inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Reserva de espacio no encontrada",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })

    @GetMapping("busqueda/{id}")
    public ResponseEntity<ReservaEspacioSalidaDto> obtenerReservaEspacioPorId(@PathVariable Long id) {
        return new ResponseEntity<>(reservaEspacioService.buscarReservaEspacioPorId(id), HttpStatus.OK);
    }

    @Operation(summary = "Listar todas las reservas de espacios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de reservas de espacios obtenido correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReservaEspacioSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content)
    })

    @GetMapping("listar")
    public ResponseEntity<List<ReservaEspacioSalidaDto>> listarReservaEspacios() {
        return new ResponseEntity<>(reservaEspacioService.listarReservaEspacios(), HttpStatus.OK);
    }

    //DELETE
    @Operation(summary = "Eliminación de reserva de espacio por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Reserva de espacio eliminada correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Id inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Reserva de espacio no encontrada",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<?> eliminarReservaEspacio(@PathVariable Long id) throws ResourceNotFoundException {
        reservaEspacioService.eliminarReservaEspacio(id);
        return new ResponseEntity<>("Reserva de espacio eliminada correctamente", HttpStatus.NO_CONTENT);
    }


}
