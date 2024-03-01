package com.backend.digitalhouse.coworking.service.implement;

import com.backend.digitalhouse.coworking.dto.entrada.sala.SalaEntradaDto;
import com.backend.digitalhouse.coworking.dto.modificacion.sala.SalaModificacionEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.sala.SalaSalidaDto;
import com.backend.digitalhouse.coworking.dto.salida.tipoSala.TipoSalaSalidaDto;
import com.backend.digitalhouse.coworking.entity.Sala;
import com.backend.digitalhouse.coworking.entity.TipoSala;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.coworking.repository.SalaRepository;
import com.backend.digitalhouse.coworking.service.ISalaService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SalaService implements ISalaService {
    private final Logger LOGGER = LoggerFactory.getLogger(SalaService.class);
    private final SalaRepository salaRepository;
    private final ModelMapper modelMapper;
    private final TipoSalaService tipoSalaService;

    @Autowired
    public SalaService(SalaRepository salaRepository, ModelMapper modelMapper, TipoSalaService tipoSalaService) {
        this.salaRepository = salaRepository;
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
    public SalaSalidaDto modificarSala(SalaModificacionEntradaDto salaModificada) throws ResourceNotFoundException {
        Sala salaRecibida = dtoModificadoAEntidad(salaModificada);
        Sala salaAModificar = salaRepository.findById(salaRecibida.getId()).orElse(null);
        SalaSalidaDto salaSalidaDto = null;

        if (salaAModificar != null) {

            salaAModificar = salaRecibida;
            salaRepository.save(salaAModificar);
            salaSalidaDto = entidadADtoSalida(salaAModificar);
            LOGGER.info("La sala ha sido actualizada: {}", salaAModificar);

        } else {

            LOGGER.error("No fue posible actualizar los datos, la sala no se encuentra registrada");
            throw new ResourceNotFoundException("No fue posible actualizar los datos, la sala no se encuentra registrada");
        }
        return salaSalidaDto;
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
    public List<SalaSalidaDto> listarSalas() {
        List<SalaSalidaDto> salas = salaRepository.findAll().stream()
                .map(sala -> entidadADtoSalida(sala)).toList();
        LOGGER.info("Listadas de todas las salas: {}", salas);
        return salas;
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

    private void configureMappings() {
       modelMapper.typeMap(SalaEntradaDto.class, Sala.class)
                .addMappings(mapper -> mapper.map(SalaEntradaDto::getTipoSala, Sala::setTipoSala));
        modelMapper.typeMap(Sala.class, SalaSalidaDto.class);
                //.addMappings(mapper -> mapper.map(Sala::getTipoSala, SalaSalidaDto::setTipoSala));
        modelMapper.typeMap(SalaModificacionEntradaDto.class, Sala.class);
                //.addMappings(mapper -> mapper.map(SalaModificacionEntradaDto::getTipoSala, Sala::setTipoSala));
        modelMapper.typeMap(TipoSalaSalidaDto.class, TipoSala.class);
    }

    private TipoSala tipoSalaEntradaDtoAEntity(Long id) {
        TipoSalaSalidaDto tipoSala = tipoSalaService.buscarTipoSalaPorId(id);
        System.out.println("JUAN"+tipoSala.toString());
        TipoSala tipoSalaPrueba = modelMapper.map(tipoSala, TipoSala.class);
        System.out.println("KATE"+tipoSalaPrueba.toString());
        return tipoSalaPrueba;
    }
    private TipoSalaSalidaDto entityATipoSalaSalidaDto(TipoSala tipoSala) {
        return modelMapper.map(tipoSala, TipoSalaSalidaDto.class);
    }

    public Sala dtoEntradaAEntidad(SalaEntradaDto salaEntradaDto) {
        Sala sala = modelMapper.map(salaEntradaDto, Sala.class);
        sala.setTipoSala(tipoSalaEntradaDtoAEntity(salaEntradaDto.getTipoSala()));
        return sala;
    }

    public SalaSalidaDto entidadADtoSalida(Sala sala) {
        SalaSalidaDto salaSalidaDto = modelMapper.map(sala, SalaSalidaDto.class);
        salaSalidaDto.setTipoSala(entityATipoSalaSalidaDto(sala.getTipoSala()));
        return salaSalidaDto;
    }

    private Sala dtoModificadoAEntidad(SalaModificacionEntradaDto salaModificacionEntradaDto) {
        return modelMapper.map(salaModificacionEntradaDto, Sala.class);
    }
}


