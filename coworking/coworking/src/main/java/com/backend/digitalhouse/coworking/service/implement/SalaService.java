package com.backend.digitalhouse.coworking.service.implement;

import com.backend.digitalhouse.coworking.dto.entrada.sala.SalaEntradaDto;
import com.backend.digitalhouse.coworking.dto.modificacion.sala.SalaModificacionEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.imagen.ImagenSalidaDto;
import com.backend.digitalhouse.coworking.dto.salida.sala.SalaSalidaDto;
import com.backend.digitalhouse.coworking.dto.salida.tipoSala.TipoSalaSalidaDto;
import com.backend.digitalhouse.coworking.entity.*;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.coworking.repository.ImagenRepository;
import com.backend.digitalhouse.coworking.repository.SalaRepository;
import com.backend.digitalhouse.coworking.service.ISalaService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SalaService implements ISalaService {
    private final Logger LOGGER = LoggerFactory.getLogger(SalaService.class);
    private final SalaRepository salaRepository;
    private final ImagenRepository imagenRepository;
    private final ModelMapper modelMapper;
    private final TipoSalaService tipoSalaService;


    @Autowired
    public SalaService(SalaRepository salaRepository, ImagenRepository imagenRepository, ModelMapper modelMapper, TipoSalaService tipoSalaService) {
        this.salaRepository = salaRepository;
        this.imagenRepository = imagenRepository;
        this.modelMapper = modelMapper;
        this.tipoSalaService = tipoSalaService;
        configureMappings();
    }

    @Override
    public SalaSalidaDto registrarSala(SalaEntradaDto sala) throws BadRequestException {
        if (sala != null) {
            SalaSalidaDto salaSalidaDto = entidadADtoSalida(salaRepository.save(dtoEntradaAEntidad(sala)));
            LOGGER.info("Sala guardada: {}", salaSalidaDto);
            return salaSalidaDto;
        } else {
            LOGGER.error("No se pudo registrar la sala");
            throw new BadRequestException("No se pudo registrar la sala");
        }
    }

    @Override
    public List<SalaSalidaDto> listarSalas() {
        List<SalaSalidaDto> salas = salaRepository.findAll().stream()
                .map(sala -> entidadADtoSalida(sala)).toList();
        LOGGER.info("Listado de las salas: {}", salas);
        for (SalaSalidaDto salaImagenes: salas) {
            salaImagenes.setImagenes(imagenesPorSalaId(modelMapper.map(salaImagenes, Sala.class)));
        }
        return salas;
    }

    @Override
    public SalaSalidaDto buscarSalaPorId(Long id) {
        Sala salaBuscada = null;
        try{
            salaBuscada = salaRepository.findById(id).orElse(null);
        }catch(Exception e){
            LOGGER.info("el Id de la sala no se encuentra");
        }
        SalaSalidaDto salaSalidaDto = null;
        if (salaBuscada != null) {
            salaSalidaDto = entidadADtoSalida(salaBuscada);
            LOGGER.info("Sala por id: {}", salaSalidaDto);
        } else LOGGER.info("Sala por id: {}", id);
        return salaSalidaDto;
    }

    @Override
    public void eliminarSala(Long id) throws ResourceNotFoundException {
        if (buscarSalaPorId(id) != null) {
            salaRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado la sala con id: {}", id);
        } else {
            LOGGER.error("No se ha encontrado la sala con id {}", id);
            throw new ResourceNotFoundException("No se ha encontrado la sala con id " + id);
        }
    }

    @Override
    public SalaSalidaDto modificarSala(Long id, Map<String, Object> camposAModificar) throws ResourceNotFoundException {
        Optional<Sala> salaGuardada = salaRepository.findById(id);
        SalaSalidaDto salaSalidaDto = null;

        if (salaGuardada.isPresent()) {
            camposAModificar.forEach((key, value) -> {
                if (key.equals("tipoSala")){
                    TipoSalaSalidaDto cambioTipoSala = tipoSalaService.buscarTipoSalaPorId(convertirALong(value));
                    TipoSala tipoSala = modelMapper.map(cambioTipoSala, TipoSala.class);
                    Field campoAModificar = ReflectionUtils.findField(Sala.class, key);
                    campoAModificar.setAccessible(true);
                    ReflectionUtils.setField(campoAModificar, salaGuardada.get(), tipoSala);
                } else {
                    Field campoAModificar = ReflectionUtils.findField(Sala.class, key);
                    campoAModificar.setAccessible(true);
                    ReflectionUtils.setField(campoAModificar, salaGuardada.get(), value);
                }
            });
            salaRepository.save(salaGuardada.get());
            salaSalidaDto = entidadADtoSalida(salaGuardada.get());
            LOGGER.info("La sala ha sido actualizada: {}", salaGuardada.get());

            return salaSalidaDto;
        } else {
            LOGGER.error("No fue posible actualizar los datos, la sala no se encuentra registrada");
            throw new ResourceNotFoundException("No fue posible actualizar los datos, la sala no se encuentra registrada");
        }
    }

    private void configureMappings() {
        modelMapper.emptyTypeMap(SalaEntradaDto.class, Sala.class)
                .addMappings(mapper -> mapper.map(SalaEntradaDto::getNombre, Sala::setNombre))
                .addMappings(mapper -> mapper.map(SalaEntradaDto::getDescripcion, Sala::setDescripcion))
                .addMappings(mapper -> mapper.map(SalaEntradaDto::getCapacidad, Sala::setCapacidad))
                .addMappings(mapper -> mapper.map(SalaEntradaDto::getDisponible, Sala::setDisponible))
                .addMappings(mapper -> mapper.map(SalaEntradaDto::getEstado, Sala::setEstado))
                .addMappings(mapper -> mapper.map(SalaEntradaDto::getPromedioCalificacion, Sala::setPromedioCalificacion))
                .addMappings(mapper -> mapper.map(SalaEntradaDto::getTipoSala, Sala::setTipoSala));
        modelMapper.typeMap(Sala.class, SalaSalidaDto.class);
        modelMapper.typeMap(SalaModificacionEntradaDto.class, Sala.class);
        modelMapper.typeMap(TipoSalaSalidaDto.class, TipoSala.class);
        modelMapper.typeMap(Imagen.class, ImagenSalidaDto.class);

    }

    private TipoSala tipoSalaEntradaDtoAEntity(Long id) {
        TipoSala tipoSala = modelMapper.map(tipoSalaService.buscarTipoSalaPorId(id), TipoSala.class);
        return tipoSala;
    }
    public Sala dtoEntradaAEntidad(SalaEntradaDto salaEntradaDto) {
        Sala sala = modelMapper.map(salaEntradaDto, Sala.class);
        sala.setTipoSala(tipoSalaEntradaDtoAEntity(salaEntradaDto.getTipoSala()));
        return sala;
    }

    private TipoSalaSalidaDto entityATipoSalaSalidaDto(TipoSala tipoSala) {
        return modelMapper.map(tipoSala, TipoSalaSalidaDto.class);
    }

    public SalaSalidaDto entidadADtoSalida(Sala sala) {
        SalaSalidaDto salaSalidaDto = modelMapper.map(sala, SalaSalidaDto.class);
        salaSalidaDto.setTipoSala(entityATipoSalaSalidaDto(sala.getTipoSala()));
        return salaSalidaDto;
    }

    private Sala dtoModificadoAEntidad(SalaModificacionEntradaDto salaModificacionEntradaDto) {
        Sala sala = modelMapper.map(salaModificacionEntradaDto, Sala.class);
        if (salaModificacionEntradaDto.getTipoSala() != 0){
            sala.setTipoSala(tipoSalaEntradaDtoAEntity(salaModificacionEntradaDto.getTipoSala()));
        }
        return sala;
    }
    public Long convertirALong(Object o){
        String stringALong = String.valueOf(o);
        Long convertirALong = Long.parseLong(stringALong);
        return convertirALong;
    }

    public List<String> imagenesPorSalaId (Sala sala) {
        List<String> imagenesListaSala = new ArrayList<>();

        List<ImagenSalidaDto> imagenesPorSalaId = imagenRepository.findBySala(sala).stream()
                .map(imagen -> entidadImagenADtoSalida(imagen)).toList();
        LOGGER.info("Listado de imagenes por id de sala: {}", imagenesPorSalaId);

        for (ImagenSalidaDto imagenSalidaDTO: imagenesPorSalaId) {
            imagenesListaSala.add(imagenSalidaDTO.getImagen());
        }
        return imagenesListaSala;
    }

    public ImagenSalidaDto entidadImagenADtoSalida(Imagen imagen) {
        ImagenSalidaDto imagenSalidaDto = modelMapper.map(imagen, ImagenSalidaDto.class);
        imagenSalidaDto.setIdSala(entidadADtoSalida(imagen.getSala()));
        return imagenSalidaDto;
    }
}


