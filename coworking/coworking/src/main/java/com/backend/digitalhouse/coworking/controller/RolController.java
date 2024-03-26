package com.backend.digitalhouse.coworking.controller;

import com.backend.digitalhouse.coworking.dto.entrada.rol.RolEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.rol.RolSalidaDto;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.coworking.service.IRolService;
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
@RequestMapping("/rol")
@CrossOrigin(origins = "http://localhost:3000")
public class RolController {
    private final IRolService rolService;
    @Autowired
    public RolController(IRolService rolService) {
        this.rolService = rolService;
    }

    //POST
    @Operation(summary = "Se registró un rol")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "El rol se ha registrada correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = RolSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })

    @PreAuthorize("hasAuthority('SAVE_ONE_ROL')")
    @PostMapping("/registrar")
    public ResponseEntity<RolSalidaDto> registrarRol(@Valid @RequestBody RolEntradaDto rol) throws BadRequestException {
        return new ResponseEntity<>(rolService.registrarRol(rol), HttpStatus.CREATED);
    }

    //PATCH
    @Operation(summary = "Modificacion de rol")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rol modificado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = RolSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Rol no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })

    @PreAuthorize("hasAuthority('UPDATE_ONE_ROL')")
    @PatchMapping("/modificar/{id}")
    public ResponseEntity<RolSalidaDto> modificarRol(@PathVariable Long id, @Valid @RequestBody Map<String,Object> camposAModificar) throws ResourceNotFoundException {
        return new ResponseEntity<>(rolService.modificarRol(id, camposAModificar), HttpStatus.OK);
    }

    //GET
    @Operation(summary = "Búsqueda de rol por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rol encontrado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = RolSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Id inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Rol no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })

    @PreAuthorize("hasAuthority('SEARCH_ONE_ROL')")
    @GetMapping("busqueda/{id}")
    public ResponseEntity<RolSalidaDto> obtenerRolPorId(@PathVariable Long id) {
        return new ResponseEntity<>(rolService.buscarRolPorId(id), HttpStatus.OK);
    }

    @Operation(summary = "Listar todos los roles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de los roles obtenido correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = RolSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content)
    })

    @PreAuthorize("hasAuthority('READ_ALL_ROLES')")
    @GetMapping("listar")
    public ResponseEntity<List<RolSalidaDto>> listarRoles() {
        return new ResponseEntity<>(rolService.listarRoles(), HttpStatus.OK);
    }

    //DELETE
    @Operation(summary = "Eliminación de un rol por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Rol eliminado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Id inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Rol no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })

    @PreAuthorize("hasAuthority('DELETE_ONE_ROL')")
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<?> eliminarRol(@PathVariable Long id) throws ResourceNotFoundException {
        rolService.eliminarRol(id);
        return new ResponseEntity<>("El rol se ha eliminado correctamente", HttpStatus.OK);
    }
}