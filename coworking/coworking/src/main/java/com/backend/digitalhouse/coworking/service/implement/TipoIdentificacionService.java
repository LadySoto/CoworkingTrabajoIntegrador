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
import java.util.List;

@Service
public class TipoIdentificacionService implements ITipoIdentificacionService {
    private final Logger LOGGER = LoggerFactory.getLogger(TipoIdentificacion.class);
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
        return null;
    }

    private void configureMappings() {
    }

    @Override
    public List<TipoIdentificacionSalidaDto> listarTiposIdentificacion() {
        return null;
    }
    @Override
    public TipoIdentificacionSalidaDto buscarTipoIdentificacionPorId(Long id) {
        return null;
    }

    @Override
    public void eliminarTipoIdentificacion(Long id) throws ResourceNotFoundException {

    }

    @Override
    public TipoIdentificacionSalidaDto modificarTipoIdentificacion(TipoIdentificacionModificacionEntradaDto tipoIdentificacionModificado) throws ResourceNotFoundException {
        return null;
    }
}