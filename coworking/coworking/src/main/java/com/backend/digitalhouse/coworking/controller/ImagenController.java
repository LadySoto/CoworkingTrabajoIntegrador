package com.backend.digitalhouse.coworking.controller;

import com.backend.digitalhouse.coworking.dto.entrada.imagen.ImagenEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.imagen.ImagenSalidaDto;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.coworking.service.IImagenService;
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
@RequestMapping("/imagen")
@CrossOrigin(origins = "http://localhost:3000")
public class ImagenController {
    private final IImagenService imagenService;

    @Autowired
    public ImagenController(IImagenService imagenService) {
        this.imagenService = imagenService;
    }

    //POST
    @Operation(summary = "Se registro una imagen")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Imagen registrada correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ImagenSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @PostMapping("/registrar")
    public ResponseEntity<ImagenSalidaDto> registrarImagen(@Valid @RequestBody ImagenEntradaDto imagen) throws BadRequestException {
        return new ResponseEntity<>(imagenService.registrarImagen(imagen), HttpStatus.CREATED);
    }

    //PATCH
    @Operation(summary = "Modificacion de una imagen")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imagen modificada correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ImagenSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Imagen no encontrada",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })

    @PatchMapping("/modificar/{id}")
    public ResponseEntity<ImagenSalidaDto> modificarImagen(@PathVariable Long id, @Valid @RequestBody Map<String,Object> camposAModificar) throws ResourceNotFoundException {
        return new ResponseEntity<>(imagenService.modificarImagen(id, camposAModificar), HttpStatus.OK);
    }

    //GET
    @Operation(summary = "Búsqueda de una imagen por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imagen encontrada correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ImagenSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Id inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Imagen no encontrada",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })

    @GetMapping("busqueda/{id}")
    public ResponseEntity<ImagenSalidaDto> obtenerImagenPorId(@PathVariable Long id) {
        return new ResponseEntity<>(imagenService.buscarImagenPorId(id), HttpStatus.OK);
    }

    @Operation(summary = "Listar todas las imagenes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de imagenes obtenido correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ImagenSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content)
    })

    @GetMapping("listar")
    public ResponseEntity<List<ImagenSalidaDto>> listarImagenes() {
        return new ResponseEntity<>(imagenService.listarImagenes(), HttpStatus.OK);
    }

    //DELETE
    @Operation(summary = "Eliminación de una imagen por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Imagen eliminada correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Id inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Imagen no encontrada",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<?> eliminarImagen(@PathVariable Long id) throws ResourceNotFoundException {
        imagenService.eliminarImagen(id);
        return new ResponseEntity<>("Imagen eliminada correctamente", HttpStatus.OK);
    }


}
