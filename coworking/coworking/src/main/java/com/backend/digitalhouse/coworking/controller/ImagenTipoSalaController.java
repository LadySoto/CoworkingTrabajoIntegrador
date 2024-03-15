package com.backend.digitalhouse.coworking.controller;

import com.backend.digitalhouse.coworking.dto.entrada.imagenTipoSala.ImagenTipoSalaEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.imagenTipoSala.ImagenTipoSalaSalidaDto;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.coworking.service.IImagenTipoSalaService;
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
@RequestMapping("/imagentiposala")
@CrossOrigin(origins = "http://localhost:3000")
public class ImagenTipoSalaController {

    private final IImagenTipoSalaService imagenTipoSalaService;
    @Autowired
    public ImagenTipoSalaController(IImagenTipoSalaService imagenTipoSalaService) {
        this.imagenTipoSalaService = imagenTipoSalaService;
    }

    //POST
    @Operation(summary = "Se registro una imagen de tipo sala")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Imagen de tipo sala registrada correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ImagenTipoSalaSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @PostMapping("/registrar")
    public ResponseEntity<ImagenTipoSalaSalidaDto> registrarImagenTipoSala(@Valid @RequestBody ImagenTipoSalaEntradaDto imagenTipoSala) throws BadRequestException {
        return new ResponseEntity<>(imagenTipoSalaService.registrarImagenTipoSala(imagenTipoSala), HttpStatus.CREATED);
    }

    //PATCH
    @Operation(summary = "Modificacion de una imagen de tipo sala")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imagen de tipo sala modificada correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ImagenTipoSalaSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Imagen de tipo sala no encontrada",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })

    @PatchMapping("/modificar/{id}")
    public ResponseEntity<ImagenTipoSalaSalidaDto> modificarImagenTipoSala(@PathVariable Long id, @Valid @RequestBody Map<String,Object> camposAModificar) throws ResourceNotFoundException {
        return new ResponseEntity<>(imagenTipoSalaService.modificarImagenTipoSala(id, camposAModificar), HttpStatus.OK);
    }

    //GET
    @Operation(summary = "Búsqueda de una imagen de tipo sala por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imagen de tipo sala encontrada correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ImagenTipoSalaSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Id inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Imagen de tipo sala no encontrada",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })

    @GetMapping("busqueda/{id}")
    public ResponseEntity<ImagenTipoSalaSalidaDto> obtenerImagenTipoSalaPorId(@PathVariable Long id) {
        return new ResponseEntity<>(imagenTipoSalaService.buscarImagenTipoSalaPorId(id), HttpStatus.OK);
    }

    @Operation(summary = "Listar todas las imagenes de tipo sala")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de imagenes de tipo sala obtenido correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ImagenTipoSalaSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content)
    })

    @GetMapping("listar")
    public ResponseEntity<List<ImagenTipoSalaSalidaDto>> listarImagenesTipoSala() {
        return new ResponseEntity<>(imagenTipoSalaService.listarImagenesTipoSala(), HttpStatus.OK);
    }

    //DELETE
    @Operation(summary = "Eliminación de una imagen de tipo sala por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Imagen de tipo sala eliminada correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Id inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Imagen de tipo sala no encontrada",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<?> eliminarImagenTipoSala(@PathVariable Long id) throws ResourceNotFoundException {
        imagenTipoSalaService.eliminarImagenTipoSala(id);
        return new ResponseEntity<>("Imagen de tipo sala eliminada correctamente", HttpStatus.OK);
    }
}
