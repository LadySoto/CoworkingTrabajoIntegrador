package com.backend.digitalhouse.coworking.service.implement;

import com.backend.digitalhouse.coworking.dto.entrada.servicioSala.ServicioSalaEntradaDto;
import com.backend.digitalhouse.coworking.dto.modificacion.servicioSala.ServicioSalaModificacionEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.servicioSala.ServicioSalaSalidaDto;
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

@Service
public class ServicioSalaService implements IServicioSalaService {

    private final Logger LOGGER = LoggerFactory.getLogger(ServicioSalaService.class);
    private final ServicioSalaRepository servicioSalaRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ServicioSalaService(ServicioSalaRepository servicioSalaRepository, ModelMapper modelMapper) {
        this.servicioSalaRepository = servicioSalaRepository;
        this.modelMapper = modelMapper;
        //configureMappings();
    }

    private void configureMappings() {
        modelMapper.typeMap(ServicioSalaEntradaDto.class, ServicioSala.class);
        modelMapper.typeMap(ServicioSala.class, ServicioSalaSalidaDto.class);
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
    public ServicioSalaSalidaDto modificarServicioSala(ServicioSalaModificacionEntradaDto servicioSalaModificado) throws ResourceNotFoundException {
        ServicioSala servicioSalaRecibido = dtoModificadoAEntidad(servicioSalaModificado);
        ServicioSala servicioSalaAModificar = servicioSalaRepository.findById(servicioSalaRecibido.getId()).orElse(null);
        ServicioSalaSalidaDto servicioSalaSalidaDto = null;

        if (servicioSalaAModificar != null) {

            servicioSalaAModificar = servicioSalaRecibido;
            servicioSalaRepository.save(servicioSalaAModificar);
            servicioSalaSalidaDto = entidadADtoSalida(servicioSalaAModificar);
            LOGGER.info("El servicio de la sala se ha sido modificado: {}", servicioSalaAModificar);

        } else {

            LOGGER.error("No fue posible actualizar los datos, el servicio de la sala no se encuentra registrado");
            throw new ResourceNotFoundException("No fue posible actualizar los datos, el servicio de la sala  no se encuentra registrado");
        }
        return servicioSalaSalidaDto;
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
    public List<ServicioSalaSalidaDto> listarServiciosSalas() {
        List<ServicioSalaSalidaDto> servicioSalas = servicioSalaRepository.findAll().stream()
                .map(this::entidadADtoSalida).toList();
        LOGGER.info("Listado de todos los servicios de la sala: {}", servicioSalas);
        return servicioSalas;
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
