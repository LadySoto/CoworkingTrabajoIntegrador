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
    public List<SalaSalidaDto> listarSalas() {
        List<SalaSalidaDto> salas = salaRepository.findAll().stream()
                .map(sala -> entidadADtoSalida(sala)).toList();
        LOGGER.info("Listado de las salas: {}", salas);
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
    public SalaSalidaDto modificarSala(SalaModificacionEntradaDto salaModificada) throws ResourceNotFoundException {
        Sala salaConModificacion = dtoModificadoAEntidad(salaModificada);
        Sala salaGuardada = salaRepository.findById(salaConModificacion.getId()).orElse(null);
        SalaSalidaDto salaSalidaDto = null;

        if (salaGuardada != null) {
            if (salaConModificacion.getNombre() != salaGuardada.getNombre()) {
                salaGuardada.setNombre(salaConModificacion.getNombre());
            }
            if (salaConModificacion.getDescripcion() != salaGuardada.getDescripcion()) {
                salaGuardada.setDescripcion(salaConModificacion.getDescripcion());
            }
            if (salaConModificacion.getCapacidad() != salaGuardada.getCapacidad()) {
                salaGuardada.setCapacidad(salaConModificacion.getCapacidad());
            }
            if (salaConModificacion.getDisponible() != salaGuardada.getDisponible()) {
                salaGuardada.setDisponible(salaConModificacion.getDisponible());
            }
            if (salaConModificacion.getEstado() != salaGuardada.getEstado()) {
                salaGuardada.setEstado(salaConModificacion.getEstado());
            }
            if (salaConModificacion.getPromedioCalificacion() != salaGuardada.getPromedioCalificacion() ) {
                salaGuardada.setPromedioCalificacion(salaConModificacion.getPromedioCalificacion());
            }
            if (salaConModificacion.getTipoSala() != null && salaConModificacion.getTipoSala().getId() != salaGuardada.getTipoSala().getId()) {
                salaGuardada.setTipoSala(salaConModificacion.getTipoSala());
            }

            salaRepository.save(salaGuardada);
            salaSalidaDto = entidadADtoSalida(salaGuardada);
            LOGGER.info("La sala ha sido actualizada: {}", salaGuardada);

        } else {

            LOGGER.error("No fue posible actualizar los datos, la sala no se encuentra registrada");
            throw new ResourceNotFoundException("No fue posible actualizar los datos, la sala no se encuentra registrada");
        }
        return salaSalidaDto;
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
        TipoSala tipoSalaPrueba = modelMapper.map(tipoSala, TipoSala.class);
        return tipoSalaPrueba;
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
}


