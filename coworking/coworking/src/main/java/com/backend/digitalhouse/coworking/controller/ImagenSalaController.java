package com.backend.digitalhouse.coworking.controller;

import com.backend.digitalhouse.coworking.dto.entrada.imagenSala.ImagenSalaEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.imagenSala.ImagenSalaSalidaDto;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.coworking.service.IImagenSalaService;
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
@RequestMapping("/imagensala")
@CrossOrigin(origins = "http://localhost:3000")
public class ImagenSalaController {
    private final IImagenSalaService imagenSalaService;
    @Autowired
    public ImagenSalaController(IImagenSalaService imagenSalaService) {
        this.imagenSalaService = imagenSalaService;
    }

    //POST
    @Operation(summary = "Se registro una imagen de sala")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Imagen de sala registrada correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ImagenSalaSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @PostMapping("/registrar")
    public ResponseEntity<ImagenSalaSalidaDto> registrarImagenSala(@Valid @RequestBody ImagenSalaEntradaDto imagenSala) throws BadRequestException {
        return new ResponseEntity<>(imagenSalaService.registrarImagenSala(imagenSala), HttpStatus.CREATED);
    }

    //PATCH
    @Operation(summary = "Modificacion de una imagen de sala")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imagen de sala modificada correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ImagenSalaSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Imagen de sala no encontrada",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })

    @PatchMapping("/modificar/{id}")
    public ResponseEntity<ImagenSalaSalidaDto> modificarImagenSala(@PathVariable Long id, @Valid @RequestBody Map<String,Object> camposAModificar) throws ResourceNotFoundException {
        return new ResponseEntity<>(imagenSalaService.modificarImagenSala(id, camposAModificar), HttpStatus.OK);
    }

    //GET
    @Operation(summary = "Búsqueda de una imagen de sala por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imagen de sala encontrada correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ImagenSalaSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Id inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Imagen de sala no encontrada",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })

    @GetMapping("busqueda/{id}")
    public ResponseEntity<ImagenSalaSalidaDto> obtenerImagenSalaPorId(@PathVariable Long id) {
        return new ResponseEntity<>(imagenSalaService.buscarImagenSalaPorId(id), HttpStatus.OK);
    }

    @Operation(summary = "Listar todas las imagenes de sala")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de imagenes de sala obtenido correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ImagenSalaSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content)
    })

    @GetMapping("listar")
    public ResponseEntity<List<ImagenSalaSalidaDto>> listarImagenesSala() {
        return new ResponseEntity<>(imagenSalaService.listarImagenesSala(), HttpStatus.OK);
    }

    //DELETE
    @Operation(summary = "Eliminación de una imagen de sala por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Imagen de sala eliminada correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Id inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Imagen de sala no encontrada",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<?> eliminarImagenSala(@PathVariable Long id) throws ResourceNotFoundException {
        imagenSalaService.eliminarImagenSala(id);
        return new ResponseEntity<>("Imagen de sala eliminada correctamente", HttpStatus.OK);
    }
}
