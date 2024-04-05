package com.backend.digitalhouse.coworking.service.implement;

import com.backend.digitalhouse.coworking.dto.entrada.servicio.ServicioEntradaDto;
import com.backend.digitalhouse.coworking.dto.modificacion.servicio.ServicioModificacionEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.servicio.ServicioSalidaDto;
import com.backend.digitalhouse.coworking.entity.Servicio;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.coworking.repository.ServicioRepository;
import com.backend.digitalhouse.coworking.service.IServicioService;
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
public class ServicioService implements IServicioService {
    private final Logger LOGGER = LoggerFactory.getLogger(ServicioService.class);
    private final ServicioRepository servicioRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ServicioService(ServicioRepository servicioRepository, ModelMapper modelMapper) {
        this.servicioRepository = servicioRepository;
        this.modelMapper = modelMapper;
        configureMappings();
    }

    @Override
    public ServicioSalidaDto registrarServicio(ServicioEntradaDto servicio) throws BadRequestException {
        if (servicio != null) {
            List<ServicioSalidaDto> servicioConElMismoNombre = buscarServiciosPorNombre(servicio.getNombre());
            if (servicioConElMismoNombre.isEmpty()) {
                Servicio servicioGuardado = servicioRepository.save(dtoEntradaAEntidad(servicio));
                ServicioSalidaDto servicioSalidaDto = entidadADtoSalida(servicioGuardado);
                LOGGER.info("Servicio registrado: {}", servicioSalidaDto);
                return servicioSalidaDto;
            } else {
                LOGGER.error("No se pudo registrar el servicio, por que ya existe uno con el mismo nombre");
                throw new BadRequestException("No se pudo registrar el servicio, por que ya existe uno con el mismo nombre");
            }
        }else {
                LOGGER.error("No se pudo registrar el servicio");
                throw new BadRequestException("No se pudo registrar el servicio");
        }
    }


    @Override
    public List<ServicioSalidaDto> listarServicios() {
        List<ServicioSalidaDto> servicios = servicioRepository.findAll().stream()
                .map(this::entidadADtoSalida).toList();
        LOGGER.info("Listado de servicios: {}", servicios);
        return servicios;
    }

    @Override
    public ServicioSalidaDto buscarServicioPorId(Long id) {
        Servicio servicioBuscado = null;
        try{
            servicioBuscado = servicioRepository.findById(id).orElse(null);
        }catch(Exception e){
            LOGGER.info("el Id del servicio no se encuentra");
        }
        ServicioSalidaDto servicioSalidaDto = null;
        if (servicioBuscado != null) {
            servicioSalidaDto = entidadADtoSalida(servicioBuscado);
            LOGGER.info("Servicio por id: {}", servicioSalidaDto);
        } else LOGGER.info("Servicio por id: {}", id);
        return servicioSalidaDto;
    }

    @Override
    public List<ServicioSalidaDto> buscarServiciosPorNombre(String nombre) {
        List<Servicio> serviciosBuscados = servicioRepository.findByNombreContaining(nombre);
        List<ServicioSalidaDto> serviciosSalidaDto = new ArrayList<>();
        if (!serviciosBuscados.isEmpty()) {
            for (Servicio servicio : serviciosBuscados) {
                serviciosSalidaDto.add(entidadADtoSalida(servicio));
            }
            LOGGER.info("Se encontraron {} servicios con el nombre: {}", serviciosBuscados.size(), nombre);
        } else {
            LOGGER.info("No se encontraron servicios con el nombre: {}", nombre);
        }
        return serviciosSalidaDto;
    }

    @Override
    public void eliminarServicio(Long id) throws ResourceNotFoundException {
        if (buscarServicioPorId(id) != null) {
            servicioRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el servicio con id: {}", id);
        } else {
            LOGGER.error("No se ha encontrado el servicio con id {}", id);
            throw new ResourceNotFoundException("No se ha encontrado el servicio con id " + id);
        }
    }

    @Override
    public ServicioSalidaDto modificarServicio(Long id, Map<String, Object> camposAModificar) throws ResourceNotFoundException {
        Optional<Servicio> servicioGuardado = servicioRepository.findById(id);
        ServicioSalidaDto servicioSalidaDto = null;

        if (servicioGuardado.isPresent()) {
            camposAModificar.forEach((key, value) -> {
                Field campoAModificar = ReflectionUtils.findField(Servicio.class, key);
                campoAModificar.setAccessible(true);
                ReflectionUtils.setField(campoAModificar, servicioGuardado.get(), value);
            });
            servicioRepository.save(servicioGuardado.get());
            servicioSalidaDto = entidadADtoSalida(servicioGuardado.get());
            LOGGER.info("El servicio ha sido actualizado: {}", servicioGuardado.get());

            return servicioSalidaDto;
        } else {
            LOGGER.error("No fue posible actualizar los datos, el servicio no se encuentra registrado");
            throw new ResourceNotFoundException("No fue posible actualizar los datos, el servicio no se encuentra registrado");
        }
    }

    private void configureMappings() {
        modelMapper.typeMap(ServicioEntradaDto.class, Servicio.class);
        modelMapper.typeMap(Servicio.class, ServicioSalidaDto.class);
        modelMapper.typeMap(ServicioModificacionEntradaDto.class, Servicio.class);
    }
    public Servicio dtoEntradaAEntidad(ServicioEntradaDto servicioEntradaDto) {
        return modelMapper.map(servicioEntradaDto, Servicio.class);
    }

    public ServicioSalidaDto entidadADtoSalida(Servicio servicio) {
        return modelMapper.map(servicio, ServicioSalidaDto.class);
    }

    private Servicio dtoSalidaAEntidad(ServicioSalidaDto servicioSalida) {
        return modelMapper.map(servicioSalida, Servicio.class);
    }

    private Servicio dtoModificadoAEntidad(ServicioModificacionEntradaDto servicioModificacionEntradaDto) {
        return modelMapper.map(servicioModificacionEntradaDto, Servicio.class);
    }

}