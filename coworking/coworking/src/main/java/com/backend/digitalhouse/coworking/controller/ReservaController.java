package com.backend.digitalhouse.coworking.controller;

import com.backend.digitalhouse.coworking.dto.entrada.reserva.ReservaEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.reserva.ReservaSalidaDto;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.coworking.service.IReservaService;
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
@RequestMapping("/reserva")
@CrossOrigin(origins = "http://localhost:3000")
public class ReservaController {
    private final IReservaService reservaService;

    @Autowired
    public ReservaController(IReservaService reservaService) {
        this.reservaService = reservaService;
    }

    //POST
    @Operation(summary = "Se registro una reserva")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reserva registrada correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReservaSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @PostMapping("/registrar")
    public ResponseEntity<ReservaSalidaDto> registrarReserva(@Valid @RequestBody ReservaEntradaDto reserva) throws BadRequestException {
        return new ResponseEntity<>(reservaService.registrarReserva(reserva), HttpStatus.CREATED);
    }

    //PATCH
    @Operation(summary = "Modificacion de una reserva")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva modificada correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReservaSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Reserva no encontrada",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })

    @PatchMapping("/modificar/{id}")
    public ResponseEntity<ReservaSalidaDto> modificarReserva(@PathVariable Long id, @Valid @RequestBody Map<String,Object> camposAModificar) throws ResourceNotFoundException {
        return new ResponseEntity<>(reservaService.modificarReserva(id, camposAModificar), HttpStatus.OK);
    }

    //GET
    @Operation(summary = "Búsqueda de una reserva por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva encontrada correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReservaSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Id inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Reserva no encontrada",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })

    @GetMapping("busqueda/{id}")
    public ResponseEntity<ReservaSalidaDto> obtenerReservaPorId(@PathVariable Long id) {
        return new ResponseEntity<>(reservaService.buscarReservaPorId(id), HttpStatus.OK);
    }

    @Operation(summary = "Listar todas las reservas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de reservas obtenido correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReservaSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content)
    })

    @GetMapping("listar")
    public ResponseEntity<List<ReservaSalidaDto>> listarReservas() {
        return new ResponseEntity<>(reservaService.listarReservas(), HttpStatus.OK);
    }

    //DELETE
    @Operation(summary = "Eliminación de una reserva por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Reserva eliminada correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Id inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Reserva no encontrada",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<?> eliminarReserva(@PathVariable Long id) throws ResourceNotFoundException {
        reservaService.eliminarReserva(id);
        return new ResponseEntity<>("Reserva eliminada correctamente", HttpStatus.OK);
    }
}
