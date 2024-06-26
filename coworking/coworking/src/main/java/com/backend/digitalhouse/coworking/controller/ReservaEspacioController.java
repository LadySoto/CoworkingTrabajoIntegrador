package com.backend.digitalhouse.coworking.controller;

import com.backend.digitalhouse.coworking.dto.entrada.reservaEspacio.ReservaEspacioEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.reservaEspacio.ReservaEspacioSalidaDto;
import com.backend.digitalhouse.coworking.dto.salida.reservaEspacio.SalaReservaSalidaDto;
import com.backend.digitalhouse.coworking.dto.salida.sala.SalaSalidaDto;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.coworking.service.IReservaEspacioService;
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
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/reservaespacio")
@CrossOrigin(origins = "http://localhost:3000")
public class ReservaEspacioController {
    private final IReservaEspacioService reservaEspacioService;
    @Autowired
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

    @PreAuthorize("hasAuthority('SAVE_ONE_RESERVA')")
    @PostMapping("/registrar")
    public ResponseEntity<ReservaEspacioSalidaDto> registrarReservaEspacio(@Valid @RequestBody ReservaEspacioEntradaDto reservaEspacio) throws BadRequestException {
        return new ResponseEntity<>(reservaEspacioService.registrarReservaEspacio(reservaEspacio), HttpStatus.CREATED);
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

    @PreAuthorize("hasAuthority('SEARCH_ONE_RESERVA')")
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

    @PreAuthorize("hasAuthority('READ_ALL_RESERVAS')")
    @GetMapping("listar")
    public ResponseEntity<List<ReservaEspacioSalidaDto>> listarReservaEspacios() {
        return new ResponseEntity<>(reservaEspacioService.listarReservaEspacios(), HttpStatus.OK);
    }

    @Operation(summary = "Listar todas las fechas disponibles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de fechas disponibles obtenido correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReservaEspacioSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content)
    })

    @GetMapping("/fechasDisponibles/{idSala}")
    public ResponseEntity<List<LocalDateTime>> listarFechasDisponibles(@PathVariable Long idSala) throws BadRequestException {
        return new ResponseEntity<>(reservaEspacioService.listarFechasDisponibles(idSala), HttpStatus.OK);
    }

    @Operation(summary = "Listar todas las fechas ocupadas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de fechas ocupadas obtenido correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReservaEspacioSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content)
    })

    @GetMapping("/fechasOcupadas/{idSala}")
    public ResponseEntity<List<LocalDateTime>> listarFechasOcupadas(@PathVariable Long idSala) throws BadRequestException {
        return new ResponseEntity<>(reservaEspacioService.listarFechasOcupadas(idSala), HttpStatus.OK);
    }

    @Operation(summary = "Listar salas disponibles para una fecha y hora")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de salas disponibles obtenido correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReservaEspacioSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content)
    })

    @GetMapping("/salasDisponibles/{fechaHoraInicio}/{fechaHoraFin}")
    public ResponseEntity<List<SalaReservaSalidaDto>> listarSalasDisponibles(@PathVariable("fechaHoraInicio") LocalDateTime fechaHoraInicio, @PathVariable("fechaHoraFin") LocalDateTime fechaHoraFin) throws BadRequestException {
        return new ResponseEntity<>(reservaEspacioService.listarSalasDisponibles(fechaHoraInicio, fechaHoraFin), HttpStatus.OK);
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

    @PreAuthorize("hasAuthority('DELETE_ONE_RESERVA')")
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<?> eliminarReservaEspacio(@PathVariable Long id) throws ResourceNotFoundException {
        reservaEspacioService.eliminarReservaEspacio(id);
        return new ResponseEntity<>("Reserva de espacio eliminada correctamente", HttpStatus.OK);
    }

}
