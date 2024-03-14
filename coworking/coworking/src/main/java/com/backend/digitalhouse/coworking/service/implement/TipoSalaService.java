package com.backend.digitalhouse.coworking.service.implement;

import com.backend.digitalhouse.coworking.dto.entrada.tipoSala.TipoSalaEntradaDto;
import com.backend.digitalhouse.coworking.dto.modificacion.tipoSala.TipoSalaModificacionEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.tipoSala.TipoSalaSalidaDto;
import com.backend.digitalhouse.coworking.entity.TipoSala;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.coworking.repository.TipoSalaRepository;
import com.backend.digitalhouse.coworking.service.ITipoSalaService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;


@Service
public class TipoSalaService implements ITipoSalaService {
    private final Logger LOGGER = LoggerFactory.getLogger(TipoSalaService.class);
    private final TipoSalaRepository tipoSalaRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public TipoSalaService(TipoSalaRepository tipoSalaRepository, ModelMapper modelMapper) {
        this.tipoSalaRepository = tipoSalaRepository;
        this.modelMapper = modelMapper;
        configureMappings();
    }

    private void configureMappings() {
        modelMapper.typeMap(TipoSalaEntradaDto.class, TipoSala.class);
        modelMapper.typeMap(TipoSala.class, TipoSalaSalidaDto.class);
    }

    @Override
    public TipoSalaSalidaDto registrarTipoSala(TipoSalaEntradaDto tipoSala) throws BadRequestException {
        if (tipoSala != null) {
            TipoSala tipoSalaGuardada = tipoSalaRepository.save(dtoEntradaAEntidad(tipoSala));
            TipoSalaSalidaDto tipoSalaSalidaDto = entidadADtoSalida(tipoSalaGuardada);
            LOGGER.info("Tipo sala guardada: {}", tipoSalaSalidaDto);
            return tipoSalaSalidaDto;
        } else {
            LOGGER.error("No se puede registrar la sala");
            throw new BadRequestException("No se puede registrar la sala");
        }
    }


    @Override
        public TipoSalaSalidaDto buscarTipoSalaPorId (Long id){
            TipoSala tipoSalaBuscado = null;
            try{
                tipoSalaBuscado = tipoSalaRepository.findById(id).orElse(null);
            }catch(Exception e){
                LOGGER.info("Id de tipo sala no se encuentra");
            }
            TipoSalaSalidaDto tipoSalaSalida = null;
            if (tipoSalaBuscado != null) {
                tipoSalaSalida = entidadADtoSalida(tipoSalaBuscado);
                LOGGER.info("Tipo sala por id: {}", tipoSalaSalida);
            } else LOGGER.info("Tipo sala por id: {}", id);
            return tipoSalaSalida;
        }

        @Override
        public List<TipoSalaSalidaDto> listarTipoSala () {
            List<TipoSalaSalidaDto> tipoSalas = tipoSalaRepository.findAll().stream()
                    .map(this::entidadADtoSalida).toList();
            LOGGER.info("Listado de todos los tipos de sala: {}", tipoSalas);
            return tipoSalas;
        }

        @Override
        public void eliminarTipoSala (Long id) throws ResourceNotFoundException {
            if (buscarTipoSalaPorId(id) != null) {
                tipoSalaRepository.deleteById(id);
                LOGGER.warn("Se ha eliminado el tipo de sala con id: {}", id);
            } else {
                LOGGER.error("No se ha encontrado el tipo de sala con id {}", id);
                throw new ResourceNotFoundException("No se ha encontrado el tipo de sala con id " + id);
            }
        }

    @Override
    public TipoSalaSalidaDto modificarTipoSala(Long id, Map<String, Object> camposAModificar) throws ResourceNotFoundException {
        return null;
    }

    private TipoSala dtoEntradaAEntidad(TipoSalaEntradaDto tipoSalaEntradaDto) {
        return modelMapper.map(tipoSalaEntradaDto, TipoSala.class);
    }

    private TipoSalaSalidaDto entidadADtoSalida(TipoSala tipoSala) {
        return modelMapper.map(tipoSala, TipoSalaSalidaDto.class);
    }
    private TipoSala dtoSalidaAEntidad(TipoSalaSalidaDto tipoSalaSalida) {
        return modelMapper.map(tipoSalaSalida, TipoSala.class);
    }

    private TipoSala dtoModificadoAEntidad(TipoSalaModificacionEntradaDto tipoSalaModificacionEntradaDto) {
        return modelMapper.map(tipoSalaModificacionEntradaDto, TipoSala.class);
    }
}