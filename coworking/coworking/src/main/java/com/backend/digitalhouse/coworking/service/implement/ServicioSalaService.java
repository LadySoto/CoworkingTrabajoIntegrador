package com.backend.digitalhouse.coworking.service.implement;

import com.backend.digitalhouse.coworking.dto.entrada.servicioSala.ServicioSalaEntradaDto;
import com.backend.digitalhouse.coworking.dto.modificacion.servicioSala.ServicioSalaModificacionEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.servicioSala.ServicioSalaSalidaDto;
import com.backend.digitalhouse.coworking.entity.Sala;
import com.backend.digitalhouse.coworking.entity.Servicio;
import com.backend.digitalhouse.coworking.entity.ServicioSala;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.coworking.repository.ServicioSalaRepository;
import com.backend.digitalhouse.coworking.service.IServicioSalaService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class ServicioSalaService implements IServicioSalaService {

    private final Logger LOGGER = LoggerFactory.getLogger(ServicioSalaService.class);
    private final ServicioSalaRepository servicioSalaRepository;
    private final ModelMapper modelMapper;
    private final SalaService salaService;
    private final ServicioService servicioService;

    @Autowired
    public ServicioSalaService(ServicioSalaRepository servicioSalaRepository, ModelMapper modelMapper, SalaService salaService, ServicioService servicioService) {
        this.servicioSalaRepository = servicioSalaRepository;
        this.modelMapper = modelMapper;
        this.salaService = salaService;
        this.servicioService = servicioService;
        configureMappings();
    }

    @Override
    public ServicioSalaSalidaDto registrarServicioSala(ServicioSalaEntradaDto servicioSala) throws BadRequestException {
        if (servicioSala != null) {
            ServicioSala servicioSalaGuardado = servicioSalaRepository.save(dtoEntradaAEntidad(servicioSala));
            ServicioSalaSalidaDto servicioSalaSalidaDto = entidadADtoSalida(servicioSalaGuardado);
            LOGGER.info("Servicio de la sala guardado: {}", servicioSalaSalidaDto);
            return servicioSalaSalidaDto;
        } else {
            LOGGER.error("No se puede registrar el servicio de la sala");
            throw new BadRequestException("No se puede registrar el servicio de la sala");
        }
    }

    @Override
    public ServicioSalaSalidaDto buscarServicioSalaPorId(Long id) {
        ServicioSala servicioSalaBuscado = null;
        try{
            servicioSalaBuscado = servicioSalaRepository.findById(id).orElse(null);
        }catch(Exception e){
            LOGGER.info("Id del servicio de la sala no se encuentra");
        }
        ServicioSalaSalidaDto servicioSalaSalida = null;
        if (servicioSalaBuscado != null) {
            servicioSalaSalida = entidadADtoSalida(servicioSalaBuscado);
            LOGGER.info("Servicio de la sala por id: {}", servicioSalaSalida);
        } else LOGGER.info("Servicio de la sala por id: {}", id);
        return servicioSalaSalida;
    }

    @Override
    public List<ServicioSalaSalidaDto> listarServiciosSala() {
        List<ServicioSalaSalidaDto> serviciosSala = servicioSalaRepository.findAll().stream()
                .map(this::entidadADtoSalida).toList();
        LOGGER.info("Listado de todos los servicios de la sala: {}", serviciosSala);
        return serviciosSala;
    }

    @Override
    public List<ServicioSalaSalidaDto> listarServicioSalaPorSalaId(Sala sala) {
        return null;
    }

    @Override
    public List<ServicioSalaSalidaDto> listarServicioSalaPorServicioId(Servicio servicio) {
        return null;
    }

    @Override
    public void eliminarServicioSala(Long id) throws ResourceNotFoundException {
        if (buscarServicioSalaPorId(id) != null) {
            servicioSalaRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el servicio de la sala con id: {}", id);
        } else {
            LOGGER.error("No se ha encontrado el servicio de la sala con id {}", id);
            throw new ResourceNotFoundException("No se ha encontrado el servicio de la sala con id " + id);
        }
    }




    private void configureMappings() {
        modelMapper.typeMap(ServicioSalaEntradaDto.class, ServicioSala.class);
        modelMapper.typeMap(ServicioSala.class, ServicioSalaSalidaDto.class);
    }
    @Override
    public ServicioSalaSalidaDto modificarServicioSala(Long id, Map<String, Object> camposAModificar) throws ResourceNotFoundException {
        return null;
    }

    private ServicioSala dtoEntradaAEntidad(ServicioSalaEntradaDto servicioSalaEntradaDto) {
        return modelMapper.map(servicioSalaEntradaDto, ServicioSala.class);
    }

    private ServicioSalaSalidaDto entidadADtoSalida(ServicioSala servicioSala) {
        return modelMapper.map(servicioSala, ServicioSalaSalidaDto.class);
    }
    private ServicioSala dtoSalidaAEntidad(ServicioSalaSalidaDto servicioSalaSalida) {
        return modelMapper.map(servicioSalaSalida, ServicioSala.class);
    }

    private ServicioSala dtoModificadoAEntidad(ServicioSalaModificacionEntradaDto servicioSalaModificacionEntradaDto) {
        return modelMapper.map(servicioSalaModificacionEntradaDto, ServicioSala.class);
    }

}
