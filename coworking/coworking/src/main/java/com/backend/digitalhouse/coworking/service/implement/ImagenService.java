package com.backend.digitalhouse.coworking.service.implement;

import com.backend.digitalhouse.coworking.dto.entrada.imagen.ImagenEntradaDto;
import com.backend.digitalhouse.coworking.dto.modificacion.imagen.ImagenModificacionEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.imagen.ImagenSalidaDto;
import com.backend.digitalhouse.coworking.dto.salida.sala.SalaSalidaDto;
import com.backend.digitalhouse.coworking.entity.*;
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
    private final SalaService salaService;

    @Autowired
    public ImagenService(ImagenRepository imagenRepository, ModelMapper modelMapper, SalaService salaService) {
        this.imagenRepository = imagenRepository;
        this.modelMapper = modelMapper;
        this.salaService = salaService;
        configureMappings();
    }

    @Override
    public ImagenSalidaDto registrarImagen(ImagenEntradaDto imagen) throws BadRequestException {
        if (imagen != null) {
            ImagenSalidaDto imagenSalidaDto = entidadADtoSalida(imagenRepository.save(dtoEntradaAEntidad(imagen)));
            LOGGER.info("Nueva imagen registrada con exito: {}", imagenSalidaDto);
            return imagenSalidaDto;
        } else {
            LOGGER.error("No se pudo registrar la imagen");
            throw new BadRequestException("No se pudo registrar la imagen");
        }
    }

    @Override
    public List<ImagenSalidaDto> listarImagenes() {
        List<ImagenSalidaDto> imagenes = imagenRepository.findAll().stream()
                .map(imagen -> entidadADtoSalida(imagen)).toList();
        LOGGER.info("Listado de imagenes: {}", imagenes);
        return imagenes;
    }

    @Override
    public List<ImagenSalidaDto> listarImagenesPorSalaId(Sala sala) {
        List<ImagenSalidaDto> imagenesPorSalaId = imagenRepository.findBySala(sala).stream()
                .map(imagen -> entidadADtoSalida(imagen)).toList();
        LOGGER.info("Listado de imagenes por id de sala: {}", imagenesPorSalaId);
        return imagenesPorSalaId;
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
                if (key.equals("sala")){
                    SalaSalidaDto cambioSala = salaService.buscarSalaPorId(convertirALong(value));
                    Sala sala = modelMapper.map(cambioSala, Sala.class);
                    Field campoAModificar = ReflectionUtils.findField(Imagen.class, key);
                    campoAModificar.setAccessible(true);
                    ReflectionUtils.setField(campoAModificar, imagenGuardada.get(), sala);
                } else {
                    Field campoAModificar = ReflectionUtils.findField(Imagen.class, key);
                    campoAModificar.setAccessible(true);
                    ReflectionUtils.setField(campoAModificar, imagenGuardada.get(), value);
                }
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
        modelMapper.emptyTypeMap(ImagenEntradaDto.class, Imagen.class)
                        .addMappings(mapper -> mapper.map(ImagenEntradaDto::getNombre, Imagen::setNombre))
                        .addMappings(mapper -> mapper.map(ImagenEntradaDto::getImagen, Imagen::setImagen))
                        .addMappings(mapper -> mapper.map(ImagenEntradaDto::getIdSala, Imagen::setSala));
        modelMapper.typeMap(Imagen.class, ImagenSalidaDto.class);
        modelMapper.typeMap(ImagenModificacionEntradaDto.class, Imagen.class);
        modelMapper.emptyTypeMap(SalaSalidaDto.class, Sala.class)
                .addMappings(mapper -> mapper.map(SalaSalidaDto::getId, Sala::setId))
                .addMappings(mapper -> mapper.map(SalaSalidaDto::getNombre, Sala::setNombre))
                .addMappings(mapper -> mapper.map(SalaSalidaDto::getDescripcion, Sala::setDescripcion))
                .addMappings(mapper -> mapper.map(SalaSalidaDto::getCapacidad, Sala::setCapacidad))
                .addMappings(mapper -> mapper.map(SalaSalidaDto::getDisponible, Sala::setDisponible))
                .addMappings(mapper -> mapper.map(SalaSalidaDto::getEstado, Sala::setEstado))
                .addMappings(mapper -> mapper.map(SalaSalidaDto::getPromedioCalificacion, Sala::setPromedioCalificacion))
                .addMappings(mapper -> mapper.map(SalaSalidaDto::getTipoSala, Sala::setTipoSala));
    }

    private Sala salaEntradaDtoAEntity(Long id) {
        Sala sala = modelMapper.map(salaService.buscarSalaPorId(id), Sala.class);
        return sala;
    }

    public Imagen dtoEntradaAEntidad(ImagenEntradaDto imagenEntradaDto) {
        Imagen imagen = modelMapper.map(imagenEntradaDto, Imagen.class);
        imagen.setSala(salaEntradaDtoAEntity(imagenEntradaDto.getIdSala()));
        return imagen;
    }

    private SalaSalidaDto entityASalaSalidaDto(Sala sala) {
        return modelMapper.map(sala, SalaSalidaDto.class);
    }

    public ImagenSalidaDto entidadADtoSalida(Imagen imagen) {
        ImagenSalidaDto imagenSalidaDto = modelMapper.map(imagen, ImagenSalidaDto.class);
        imagenSalidaDto.setIdSala(salaService.entidadADtoSalida(imagen.getSala()));
        return imagenSalidaDto;
    }

    private Imagen dtoModificadoAEntidad(ImagenModificacionEntradaDto imagenModificacionEntradaDto) {
        Imagen imagen = modelMapper.map(imagenModificacionEntradaDto, Imagen.class);

        if (imagenModificacionEntradaDto.getIdSala() != 0){
            imagen.setSala(salaEntradaDtoAEntity(imagenModificacionEntradaDto.getIdSala()));
        }
        return imagen;
    }

    public Long convertirALong(Object o){
        String stringALong = String.valueOf(o);
        Long convertirALong = Long.parseLong(stringALong);
        return convertirALong;
    }
}
