package com.backend.digitalhouse.coworking.service.implement;

import com.backend.digitalhouse.coworking.dto.entrada.sala.SalaEntradaDto;
import com.backend.digitalhouse.coworking.dto.entrada.tipoSala.TipoSalaEntradaDto;
import com.backend.digitalhouse.coworking.dto.modificacion.tipoSala.TipoSalaModificacionEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.sala.SalaSalidaDto;
import com.backend.digitalhouse.coworking.dto.salida.tipoSala.TipoSalaSalidaDto;
import com.backend.digitalhouse.coworking.entity.Sala;
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

@Service
public class TipoSalaService implements ITipoSalaService {
    private final Logger LOGGER = LoggerFactory.getLogger(TipoSala.class);
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
        public TipoSalaSalidaDto modificarTipoSala (TipoSalaModificacionEntradaDto tipoSalaModificado) throws
        ResourceNotFoundException {
        TipoSala tipoSalaRecibido = dtoModificadoAEntidad(tipoSalaModificado);
        TipoSala tipoSalaAModificar = tipoSalaRepository.findById(tipoSalaRecibido.getId()).orElse(null);
        TipoSalaSalidaDto tipoSalaSalidaDto = null;

        if (tipoSalaAModificar != null) {

            tipoSalaAModificar = tipoSalaRecibido;
            tipoSalaRepository.save(tipoSalaAModificar);
            tipoSalaSalidaDto = entidadADtoSalida(tipoSalaAModificar);
            LOGGER.info("La tipo Sala ha sido modificada: {}", tipoSalaAModificar);

        } else {

            LOGGER.error("No fue posible actualizar los datos, el tipo Sala no se encuentra registrado");
            throw new ResourceNotFoundException("No fue posible actualizar los datos, el tipo Sala  no se encuentra registrado");
        }
        return tipoSalaSalidaDto;
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