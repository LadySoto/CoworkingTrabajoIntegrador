package com.backend.digitalhouse.coworking.service.implement;

import com.backend.digitalhouse.coworking.dto.entrada.imagen.ImagenEntradaDto;
import com.backend.digitalhouse.coworking.dto.modificacion.imagen.ImagenModificacionEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.imagen.ImagenSalidaDto;
import com.backend.digitalhouse.coworking.entity.Imagen;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.coworking.repository.ImagenRepository;
import com.backend.digitalhouse.coworking.service.IImagenService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ImagenService implements IImagenService {
    private final Logger LOGGER = LoggerFactory.getLogger(ImagenService.class);
    private final ImagenRepository imagenRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ImagenService(ImagenRepository imagenRepository, ModelMapper modelMapper) {
        this.imagenRepository = imagenRepository;
        this.modelMapper = modelMapper;
        configureMappings();
    }


    @Override
    public ImagenSalidaDto registrarImagen(ImagenEntradaDto imagen) throws BadRequestException {
        if (imagen != null) {
            Imagen imagenGuardada = imagenRepository.save(dtoEntradaAEntidad(imagen));
            ImagenSalidaDto imagenSalidaDto = entidadADtoSalida(imagenGuardada);
            LOGGER.info("Imagen registrada: {}", imagenSalidaDto);
            return imagenSalidaDto;
        } else {
            LOGGER.error("No se pudo registrar la imagen");
            throw new BadRequestException("No se pudo registrar la imagen");
        }
    }

    @Override
    public List<ImagenSalidaDto> listarImagenes() {
        List<ImagenSalidaDto> imagenes = imagenRepository.findAll().stream()
                .map(this::entidadADtoSalida).toList();
        LOGGER.info("Listado de imagenes: {}", imagenes);
        return imagenes;
    }

    @Override
    public ImagenSalidaDto buscarImagenPorId(Long id) {
        Imagen imagenBuscada = null;
        try{
            imagenBuscada = imagenRepository.findById(id).orElse(null);
        }catch(Exception e){
            LOGGER.info("el Id de la  imagen no se encuentra");
        }
        ImagenSalidaDto imagenSalidaDto = null;
        if (imagenBuscada != null) {
            imagenSalidaDto = entidadADtoSalida(imagenBuscada);
            LOGGER.info("Imagen por id: {}", imagenSalidaDto);
        } else LOGGER.info("Imagen por id: {}", id);
        return imagenSalidaDto;
    }

    @Override
    public void eliminarImagen(Long id) throws ResourceNotFoundException {
        if (buscarImagenPorId(id) != null) {
            imagenRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado la imagen con id: {}", id);
        } else {
            LOGGER.error("No se ha encontrado la imagen con id {}", id);
            throw new ResourceNotFoundException("No se ha encontrado la imagen con id " + id);
        }
    }

    @Override
    public ImagenSalidaDto modificarImagen(Long id, Map<String, Object> camposAModificar) throws ResourceNotFoundException {
        Optional<Imagen> imagenGuardada = imagenRepository.findById(id);
        ImagenSalidaDto imagenSalidaDto = null;

        if (imagenGuardada.isPresent()) {
            camposAModificar.forEach((key, value) -> {
                Field campoAModificar = ReflectionUtils.findField(Imagen.class, key);
                campoAModificar.setAccessible(true);
                ReflectionUtils.setField(campoAModificar, imagenGuardada.get(), value);
            });
            imagenRepository.save(imagenGuardada.get());
            imagenSalidaDto = entidadADtoSalida(imagenGuardada.get());
            LOGGER.info("La imagen ha sido actualizada: {}", imagenGuardada.get());

            return imagenSalidaDto;
        } else {
            LOGGER.error("No fue posible actualizar los datos, la imagen no se encuentra registrada");
            throw new ResourceNotFoundException("No fue posible actualizar los datos, la imagen no se encuentra registrada");
        }
    }

    private void configureMappings() {
        modelMapper.typeMap(ImagenEntradaDto.class, Imagen.class);
        modelMapper.typeMap(Imagen.class, ImagenSalidaDto.class);
        modelMapper.typeMap(ImagenModificacionEntradaDto.class, Imagen.class);
    }
    public Imagen dtoEntradaAEntidad(ImagenEntradaDto imagenEntradaDto) {
        return modelMapper.map(imagenEntradaDto, Imagen.class);
    }

    public ImagenSalidaDto entidadADtoSalida(Imagen imagen) {
        return modelMapper.map(imagen, ImagenSalidaDto.class);
    }

    private Imagen dtoSalidaAEntidad(ImagenSalidaDto imagenSalida) {
        return modelMapper.map(imagenSalida, Imagen.class);
    }

    private Imagen dtoModificadoAEntidad(ImagenModificacionEntradaDto imagenModificacionEntradaDto) {
        return modelMapper.map(imagenModificacionEntradaDto, Imagen.class);
    }
}
