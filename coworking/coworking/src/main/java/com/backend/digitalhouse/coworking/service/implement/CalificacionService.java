package com.backend.digitalhouse.coworking.service.implement;

import com.backend.digitalhouse.coworking.dto.entrada.calificacion.CalificacionEntradaDto;
import com.backend.digitalhouse.coworking.dto.modificacion.calificacion.CalificacionModificacionEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.calificacion.CalificacionSalidaDto;
import com.backend.digitalhouse.coworking.entity.Calificacion;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.coworking.repository.CalificacionRepository;
import com.backend.digitalhouse.coworking.service.ICalificacionService;
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
public class CalificacionService implements ICalificacionService {
    private final Logger LOGGER = LoggerFactory.getLogger(CalificacionService.class);
    private final CalificacionRepository calificacionRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CalificacionService(CalificacionRepository calificacionRepository, ModelMapper modelMapper) {
        this.calificacionRepository = calificacionRepository;
        this.modelMapper = modelMapper;
        configureMappings();
    }

    @Override
    public CalificacionSalidaDto registrarCalificacion(CalificacionEntradaDto calificacion) throws BadRequestException {
            if (calificacion != null) {
                Calificacion calificacionGuardada = calificacionRepository.save(dtoEntradaAEntidad(calificacion));
                CalificacionSalidaDto calificacionSalidaDto = entidadADtoSalida(calificacionGuardada);
                LOGGER.info("Calificacion registrada: {}", calificacionSalidaDto);
                return calificacionSalidaDto;
            } else {
                LOGGER.error("No se pudo registrar la calificacion");
                throw new BadRequestException("No se pudo registrar la calificacion");
            }
        }

    @Override
    public List<CalificacionSalidaDto> listarCalificaciones() {
            List<CalificacionSalidaDto> calificaciones = calificacionRepository.findAll().stream()
                    .map(this::entidadADtoSalida).toList();
            LOGGER.info("Listado de calificaciones: {}", calificaciones);
            return calificaciones;
        }

    @Override
    public CalificacionSalidaDto buscarCalificacionPorId(Long id) {
        Calificacion calificacionBuscada = null;
            try{
                calificacionBuscada = calificacionRepository.findById(id).orElse(null);
            }catch(Exception e){
                LOGGER.info("el Id de la  calificacion no se encuentra");
            }
        CalificacionSalidaDto calificacionSalidaDto = null;
            if (calificacionBuscada != null) {
                calificacionSalidaDto = entidadADtoSalida(calificacionBuscada);
                LOGGER.info("Calificacion por id: {}", calificacionSalidaDto);
            } else LOGGER.info("Calificacion por id: {}", id);
            return calificacionSalidaDto;
        }

    @Override
    public void eliminarCalificacion(Long id) throws ResourceNotFoundException {
            if (buscarCalificacionPorId(id) != null) {
                calificacionRepository.deleteById(id);
                LOGGER.warn("Se ha eliminado la calificacion con id: {}", id);
            } else {
                LOGGER.error("No se ha encontrado la calificacion con id {}", id);
                throw new ResourceNotFoundException("No se ha encontrado la calificacion con id " + id);
            }
    }

    @Override
    public CalificacionSalidaDto modificarCalificacion(Long id, Map<String, Object> camposAModificar) throws ResourceNotFoundException {
            Optional<Calificacion> calificacionGuardada = calificacionRepository.findById(id);
        CalificacionSalidaDto calificacionSalidaDto = null;

        if (calificacionGuardada.isPresent()) {
            camposAModificar.forEach((key, value) -> {
                Field campoAModificar = ReflectionUtils.findField(Calificacion.class, key);
                campoAModificar.setAccessible(true);
                ReflectionUtils.setField(campoAModificar, calificacionGuardada.get(), value);
            });
            calificacionRepository.save(calificacionGuardada.get());
            calificacionSalidaDto = entidadADtoSalida(calificacionGuardada.get());
            LOGGER.info("La calificacion ha sido actualizada: {}", calificacionGuardada.get());

            return calificacionSalidaDto;
        } else {
            LOGGER.error("No fue posible actualizar los datos, la calificacion no se encuentra registrada");
            throw new ResourceNotFoundException("No fue posible actualizar los datos, la calificacion no se encuentra registrada");
        }
    }
    private void configureMappings() {
        modelMapper.typeMap(CalificacionEntradaDto.class, Calificacion.class);
        modelMapper.typeMap(Calificacion.class, CalificacionSalidaDto.class);
        modelMapper.typeMap(CalificacionModificacionEntradaDto.class, Calificacion.class);
    }
    public Calificacion dtoEntradaAEntidad(CalificacionEntradaDto calificacionEntradaDto) {
        return modelMapper.map(calificacionEntradaDto, Calificacion.class);
    }

    public CalificacionSalidaDto entidadADtoSalida(Calificacion calificacion) {
        return modelMapper.map(calificacion, CalificacionSalidaDto.class);
    }

    private Calificacion dtoSalidaAEntidad(CalificacionSalidaDto calificacionSalida) {
        return modelMapper.map(calificacionSalida, Calificacion.class);
    }

    private Calificacion dtoModificadoAEntidad(CalificacionModificacionEntradaDto calificacionModificacionEntradaDto) {
        return modelMapper.map(calificacionModificacionEntradaDto, Calificacion.class);
    }
}

