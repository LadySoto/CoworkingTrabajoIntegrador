package com.backend.digitalhouse.coworking.controller;

import com.backend.digitalhouse.coworking.dto.entrada.usuario.UsuarioEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.usuario.UsuarioSalidaDto;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.coworking.service.IUsuarioService;
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
@RequestMapping("/usuario")
@CrossOrigin(origins = "http://localhost:3000")
public class UsuarioController {
    private final IUsuarioService usuarioService;
    @Autowired
    public UsuarioController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    //POST
    @Operation(summary = "Se registro un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "El usuario se ha registrada correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @PostMapping("/registrar")
    public ResponseEntity<UsuarioSalidaDto> registrarUsuario(@Valid @RequestBody UsuarioEntradaDto usuario) throws BadRequestException {
        return new ResponseEntity<>(usuarioService.registrarUsuario(usuario), HttpStatus.CREATED);
    }

    //PATCH
    @Operation(summary = "Modificacion de usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario modificado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })

    @PatchMapping("/modificar/{id}")
    public ResponseEntity<UsuarioSalidaDto> modificarUsuario(@PathVariable Long id, @Valid @RequestBody Map<String,Object> camposAModificar) throws ResourceNotFoundException {
        return new ResponseEntity<>(usuarioService.modificarUsuario(id, camposAModificar), HttpStatus.OK);
    }

    //GET
    @Operation(summary = "Búsqueda de usuario por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Id inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })

    @GetMapping("busqueda/{id}")
    public ResponseEntity<UsuarioSalidaDto> obtenerUsuarioPorId(@PathVariable Long id) {
        return new ResponseEntity<>(usuarioService.buscarUsuarioPorId(id), HttpStatus.OK);
    }

    @Operation(summary = "Listar todos los usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de usuarios obtenido correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content)
    })

    @GetMapping("listar")
    public ResponseEntity<List<UsuarioSalidaDto>> listarUsuarios() {
        return new ResponseEntity<>(usuarioService.listarUsuarios(), HttpStatus.OK);
    }

    //DELETE
    @Operation(summary = "Eliminación de usuario por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuario eliminado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Id inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) throws ResourceNotFoundException {
        usuarioService.eliminarUsuario(id);
        return new ResponseEntity<>("Usuario eliminado correctamente", HttpStatus.NO_CONTENT);
    }
}
