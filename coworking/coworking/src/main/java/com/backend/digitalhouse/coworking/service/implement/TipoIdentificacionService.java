package com.backend.digitalhouse.coworking.service.implement;

import com.backend.digitalhouse.coworking.dto.entrada.tipoIdenticacion.TipoIdentificacionEntradaDto;
import com.backend.digitalhouse.coworking.dto.modificacion.tipoIdentificacion.TipoIdentificacionModificacionEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.tipoIdentificacion.TipoIdentificacionSalidaDto;
import com.backend.digitalhouse.coworking.entity.TipoIdentificacion;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.coworking.repository.TipoIdentificacionRepository;
import com.backend.digitalhouse.coworking.service.ITipoIdentificacionService;
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
public class TipoIdentificacionService implements ITipoIdentificacionService {
    private final Logger LOGGER = LoggerFactory.getLogger(TipoIdentificacionService.class);
    private final TipoIdentificacionRepository tipoIdentificacionRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public TipoIdentificacionService(TipoIdentificacionRepository tipoIdentificacionRepository, ModelMapper modelMapper) {
        this.tipoIdentificacionRepository = tipoIdentificacionRepository;
        this.modelMapper = modelMapper;
        configureMappings();
    }

    @Override
    public TipoIdentificacionSalidaDto registrarTipoIdentificacion(TipoIdentificacionEntradaDto tipoIdentificacion) throws BadRequestException {
        if (tipoIdentificacion != null) {
            TipoIdentificacion tipoIdentificacionGuardado = tipoIdentificacionRepository.save(dtoEntradaAEntidad(tipoIdentificacion));
            TipoIdentificacionSalidaDto tipoIdentificacionSalidaDto = entidadADtoSalida(tipoIdentificacionGuardado);
            LOGGER.info("Tipo de identificacion guardado: {}", tipoIdentificacionSalidaDto);
            return tipoIdentificacionSalidaDto;
        } else {
            LOGGER.error("No se puede registrar el tipo de identificacion");
            throw new BadRequestException("No se puede registrar el tipo de identificacion");
        }
    }


    @Override
    public TipoIdentificacionSalidaDto buscarTipoIdentificacionPorId(Long id) {
        TipoIdentificacion tipoIdentificacionBuscado = null;
        try{
            tipoIdentificacionBuscado = tipoIdentificacionRepository.findById(id).orElse(null);
        }catch(Exception e){
            LOGGER.info("Id de tipo de identificacion no se encuentra");
        }
        TipoIdentificacionSalidaDto tipoIdentificacionSalida = null;
        if (tipoIdentificacionBuscado != null) {
            tipoIdentificacionSalida = entidadADtoSalida(tipoIdentificacionBuscado);
            LOGGER.info("Tipo de identificacion por id: {}", tipoIdentificacionSalida);
        } else LOGGER.info("Tipo de identificacion por id: {}", id);
        return tipoIdentificacionSalida;
    }

    @Override
    public List<TipoIdentificacionSalidaDto> listarTiposIdentificacion() {
        List<TipoIdentificacionSalidaDto> tiposIdentificacion = tipoIdentificacionRepository.findAll().stream()
                .map(this::entidadADtoSalida).toList();
        LOGGER.info("Listado de todos los tipos de identificacion: {}", tiposIdentificacion);
        return tiposIdentificacion;
    }

    @Override
    public void eliminarTipoIdentificacion(Long id) throws ResourceNotFoundException {
        if (buscarTipoIdentificacionPorId(id) != null) {
            tipoIdentificacionRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el tipo de identificacion con id: {}", id);
        } else {
            LOGGER.error("No se ha encontrado el tipo de identificacion con id {}", id);
            throw new ResourceNotFoundException("No se ha encontrado el tipo de identificacion con id " + id);
        }
    }

    @Override
    public TipoIdentificacionSalidaDto modificarTipoIdentificacion(Long id, Map<String, Object> camposAModificar) throws ResourceNotFoundException {
        Optional<TipoIdentificacion> tipoIdentificacionGuardada = tipoIdentificacionRepository.findById(id);
        TipoIdentificacionSalidaDto tipoIdentificacionSalidaDto = null;

        if (tipoIdentificacionGuardada.isPresent()) {
            camposAModificar.forEach((key, value) -> {
                Field campoAModificar = ReflectionUtils.findField(TipoIdentificacion.class, key);
                campoAModificar.setAccessible(true);
                ReflectionUtils.setField(campoAModificar, tipoIdentificacionGuardada.get(), value);
            });
            tipoIdentificacionRepository.save(tipoIdentificacionGuardada.get());
            tipoIdentificacionSalidaDto = entidadADtoSalida(tipoIdentificacionGuardada.get());
            LOGGER.info("El tipo de identificacion ha sido actualizada: {}", tipoIdentificacionGuardada.get());

            return tipoIdentificacionSalidaDto;
        } else {
            LOGGER.error("No fue posible actualizar los datos, el tipo de identificacion no se encuentra registrado");
            throw new ResourceNotFoundException("No fue posible actualizar los datos, el tipo de identificacion no se encuentra registrado");
        }
    }

    private void configureMappings() {
        modelMapper.typeMap(TipoIdentificacionEntradaDto.class, TipoIdentificacion.class);
        modelMapper.typeMap(TipoIdentificacion.class, TipoIdentificacionSalidaDto.class);
        modelMapper.typeMap(TipoIdentificacionModificacionEntradaDto.class, TipoIdentificacion.class);
    }
    private TipoIdentificacion dtoEntradaAEntidad(TipoIdentificacionEntradaDto tipoIdentificacionEntradaDto) {
        return modelMapper.map(tipoIdentificacionEntradaDto, TipoIdentificacion.class);
    }

    private TipoIdentificacionSalidaDto entidadADtoSalida(TipoIdentificacion tipoIdentificacion) {
        return modelMapper.map(tipoIdentificacion, TipoIdentificacionSalidaDto.class);
    }

    private TipoIdentificacion dtoSalidaAEntidad(TipoIdentificacionSalidaDto tipoIdentificacionSalida) {
        return modelMapper.map(tipoIdentificacionSalida, TipoIdentificacion.class);
    }

    private TipoIdentificacion dtoModificadoAEntidad(TipoIdentificacionModificacionEntradaDto tipoIdentificacionModificacionEntradaDto) {
        return modelMapper.map(tipoIdentificacionModificacionEntradaDto, TipoIdentificacion.class);
    }




}