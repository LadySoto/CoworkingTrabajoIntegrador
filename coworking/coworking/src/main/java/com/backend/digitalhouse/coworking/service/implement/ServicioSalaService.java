package com.backend.digitalhouse.coworking.service.implement;

import com.backend.digitalhouse.coworking.dto.entrada.servicioSala.ServicioSalaEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.sala.SalaSalidaDto;
import com.backend.digitalhouse.coworking.dto.salida.servicio.ServicioSalidaDto;
import com.backend.digitalhouse.coworking.dto.salida.servicioSala.ServicioSalaSalidaDto;
import com.backend.digitalhouse.coworking.entity.Sala;
import com.backend.digitalhouse.coworking.entity.Servicio;
import com.backend.digitalhouse.coworking.entity.ServicioSala;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.coworking.repository.SalaRepository;
import com.backend.digitalhouse.coworking.repository.ServicioRepository;
import com.backend.digitalhouse.coworking.repository.ServicioSalaRepository;
import com.backend.digitalhouse.coworking.service.IServicioSalaService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ServicioSalaService implements IServicioSalaService {

    private final Logger LOGGER = LoggerFactory.getLogger(ServicioSalaService.class);
    private final ServicioSalaRepository servicioSalaRepository;
    private final ServicioRepository servicioRepository;
    private final SalaRepository salaRepository;
    private final ModelMapper modelMapper;
    private final SalaService salaService;
    private final ServicioService servicioService;

    @Autowired
    public ServicioSalaService(ServicioSalaRepository servicioSalaRepository, ModelMapper modelMapper, SalaService salaService, ServicioService servicioService, ServicioRepository servicioRepository, SalaRepository salaRepository) {
        this.servicioSalaRepository = servicioSalaRepository;
        this.modelMapper = modelMapper;
        this.salaService = salaService;
        this.servicioService = servicioService;
        this.servicioRepository = servicioRepository;
        this.salaRepository = salaRepository;
        configureMappings();
    }

    @Override
    public List<ServicioSalaSalidaDto> registrarServicioSala(ServicioSalaEntradaDto servicioSala) throws BadRequestException {
        if (servicioSala != null) {
            List<ServicioSala> listaServiciosSala = dtoEntradaAEntidad(servicioSala);
            System.out.println("listaServiciosSala: " + listaServiciosSala.toString());
            List<ServicioSalaSalidaDto> serviciosSalaSalidaDto = new ArrayList<>();
            for (ServicioSala servicioSalaEntidad: listaServiciosSala) {
                System.out.println(servicioSalaEntidad);
                ServicioSala servicioSalaGuardado = servicioSalaRepository.save(servicioSalaEntidad);
                ServicioSalaSalidaDto servicioSalaSalidaDto = entidadADtoSalida(servicioSalaGuardado);
                LOGGER.info("Servicio de la sala guardado: {}", servicioSalaSalidaDto);
                serviciosSalaSalidaDto.add(servicioSalaSalidaDto);
            }

            return serviciosSalaSalidaDto;
        } else {
            LOGGER.error("No se puede registrar el servicio de la sala");
            throw new BadRequestException("No se puede registrar el servicio de la sala");
        }
    }

    @Override
    public List<ServicioSalaSalidaDto> listarServiciosSala() {
        List<ServicioSalaSalidaDto> serviciosSala = servicioSalaRepository.findAll().stream()
                .map(ServicioSala -> entidadADtoSalida(ServicioSala)).toList();
        LOGGER.info("Listado de todos los servicios de la sala: {}", serviciosSala);
        return serviciosSala;
    }

    @Override
    public List<ServicioSalaSalidaDto> listarServiciosSalaPorSalaId(Sala sala) {
        List<ServicioSalaSalidaDto> serviciosSalaPorSalaId = servicioSalaRepository.findBySala(sala).stream()
                .map(ServicioSala -> entidadADtoSalida(ServicioSala)).toList();
        LOGGER.info("Listado de servicios sala por id de sala: {}", serviciosSalaPorSalaId );
        return serviciosSalaPorSalaId ;
    }

    @Override
    public List<ServicioSalaSalidaDto> listarServiciosSalaPorServicioId(Servicio servicio) {
        List<ServicioSalaSalidaDto> serviciosSalaPorServicioId = servicioSalaRepository.findByServicio(servicio).stream()
                .map(ServicioSala -> entidadADtoSalida(ServicioSala)).toList();
        LOGGER.info("Listado de servicios sala por id de sala: {}", serviciosSalaPorServicioId );
        return serviciosSalaPorServicioId ;
    }

    @Override
    public ServicioSalaSalidaDto buscarServicioSalaPorId(Long id) {
        ServicioSala servicioSalaBuscado = null;
        try{
            servicioSalaBuscado = servicioSalaRepository.findById(id).orElse(null);
        }catch(Exception e){
            LOGGER.info("Id del servicio de la sala no se encuentra");
        }
        ServicioSalaSalidaDto servicioSalaSalidaDto = null;
        if (servicioSalaBuscado != null) {
            servicioSalaSalidaDto = entidadADtoSalida(servicioSalaBuscado);
            LOGGER.info("Servicio de la sala por id: {}", servicioSalaSalidaDto);
        } else LOGGER.info("Servicio de la sala por id: {}", id);
        return servicioSalaSalidaDto;
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
        TypeMap<ServicioSalaEntradaDto, List> typeMap = modelMapper.createTypeMap(ServicioSalaEntradaDto.class, List.class);

        typeMap.setConverter(context -> {
            ServicioSalaEntradaDto source = context.getSource();
            List<Long> idServicios = source.getIdServicios();
            Long idSala = source.getIdSala();

            List<ServicioSala> servicioSalas = new ArrayList<>();

            for (Long idServicio : idServicios) {
                ServicioSala servicioSala = new ServicioSala();
                servicioSala.setSala(salaRepository.findById(idSala)
                        .orElseThrow(() -> new IllegalArgumentException("No se pudo encontrar la sala con ID: " + idSala)));
                servicioSala.setServicio(servicioRepository.findById(idServicio)
                        .orElseThrow(() -> new IllegalArgumentException("No se pudo encontrar el servicio con ID: " + idServicio)));
                servicioSalas.add(servicioSala);
            }

            return servicioSalas;
        });
        modelMapper.emptyTypeMap(ServicioSalaEntradaDto.class, ServicioSala.class)
                .addMappings(mapper -> mapper.map(ServicioSalaEntradaDto::getIdServicios, ServicioSala::setServicio))
                .addMappings(mapper -> mapper.map(ServicioSalaEntradaDto::getIdSala, ServicioSala::setSala));
        modelMapper.emptyTypeMap(ServicioSalidaDto.class, Servicio.class)
                .addMappings(mapper -> mapper.map(ServicioSalidaDto::getId, Servicio::setId))
                .addMappings(mapper -> mapper.map(ServicioSalidaDto::getNombre, Servicio::setNombre))
                .addMappings(mapper -> mapper.map(ServicioSalidaDto::getEstado, Servicio::setEstado));
    }

    private Sala salaEntradaDtoAEntity(Long id) {
        Sala sala = modelMapper.map(salaService.buscarSalaPorId(id), Sala.class);
        return sala;
    }

    private Servicio servicioEntradaDtoAEntity(Long id) {
        Servicio servicio = modelMapper.map(servicioService.buscarServicioPorId(id), Servicio.class);
        return servicio;
    }

    private List<ServicioSala> dtoEntradaAEntidad(ServicioSalaEntradaDto servicioSalaEntradaDto) {
        List<ServicioSala> listaServiciosSala = new ArrayList<>();
        for (Long servicioId: servicioSalaEntradaDto.getIdServicios()) {
            ServicioSala servicioSala = modelMapper.map(servicioSalaEntradaDto, ServicioSala.class);
            servicioSala.setSala(salaEntradaDtoAEntity(servicioSalaEntradaDto.getIdSala()));
            servicioSala.setServicio(servicioEntradaDtoAEntity(servicioId));
            listaServiciosSala.add(servicioSala);
        }

        return listaServiciosSala;
    }

    private SalaSalidaDto entityASalaSalidaDto(Sala sala) {
        return modelMapper.map(sala, SalaSalidaDto.class);
    }

    private ServicioSalidaDto entityAServicioSalidaDto(Servicio servicio) {
        return modelMapper.map(servicio, ServicioSalidaDto.class);
    }

    private ServicioSalaSalidaDto entidadADtoSalida(ServicioSala servicioSala) {
        ServicioSalaSalidaDto servicioSalaSalidaDto = modelMapper.map(servicioSala, ServicioSalaSalidaDto.class);

        if (servicioSala.getServicio() != null && servicioSala.getServicio().getId() != 0){
            servicioSalaSalidaDto.setIdServicio(entityAServicioSalidaDto(servicioRepository.findById(servicioSala.getServicio().getId())
                    .orElseThrow(() -> new IllegalArgumentException("No se pudo encontrar el servicio con ID: " + servicioSala.getServicio().getId()))));
        }
        if (servicioSala.getSala() != null && servicioSala.getSala().getId() != 0){
            servicioSalaSalidaDto.setIdSala(entityASalaSalidaDto(salaRepository.findById(servicioSala.getSala().getId())
                    .orElseThrow(() -> new IllegalArgumentException("No se pudo encontrar la sala con ID: " + servicioSala.getSala().getId()))));
        }
        return servicioSalaSalidaDto;
    }

    public Long convertirALong(Object o){
        String stringALong = String.valueOf(o);
        Long convertirALong = Long.parseLong(stringALong);
        return convertirALong;
    }
}
