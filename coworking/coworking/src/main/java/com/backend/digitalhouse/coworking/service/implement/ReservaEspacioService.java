package com.backend.digitalhouse.coworking.service.implement;

import com.backend.digitalhouse.coworking.dto.entrada.reservaEspacio.ReservaEspacioEntradaDto;
import com.backend.digitalhouse.coworking.dto.modificacion.reservaEspacio.ReservaEspacioModificacionEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.reserva.ReservaSalidaDto;
import com.backend.digitalhouse.coworking.dto.salida.reservaEspacio.ReservaEspacioSalidaDto;
import com.backend.digitalhouse.coworking.dto.salida.sala.SalaSalidaDto;
import com.backend.digitalhouse.coworking.dto.salida.usuario.UsuarioSalidaDto;
import com.backend.digitalhouse.coworking.entity.*;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.coworking.repository.ReservaEspacioRepository;
import com.backend.digitalhouse.coworking.service.IReservaEspacioService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ReservaEspacioService implements IReservaEspacioService {
    private final Logger LOGGER = LoggerFactory.getLogger(ReservaEspacioService.class);
    private final ReservaEspacioRepository reservaEspacioRepository;
    private final ModelMapper modelMapper;
    private final UsuarioService usuarioService;
    private final ReservaService reservaService;
    private final SalaService salaService;

    public ReservaEspacioService(ReservaEspacioRepository reservaEspacioRepository, ModelMapper modelMapper, UsuarioService usuarioService, ReservaService reservaService, SalaService salaService) {
        this.reservaEspacioRepository = reservaEspacioRepository;
        this.modelMapper = modelMapper;
        this.usuarioService = usuarioService;
        this.reservaService = reservaService;
        this.salaService = salaService;
        configureMappings();
    }

    @Override
    public ReservaEspacioSalidaDto registrarReservaEspacio(ReservaEspacioEntradaDto reservaEspacio) throws BadRequestException {
        if (reservaEspacio!= null) {
            ReservaEspacioSalidaDto reservaEspacioSalidaDto = entidadADtoSalida(reservaEspacioRepository.save(dtoEntradaAEntidad(reservaEspacio)));
            LOGGER.info("Nuevo reserva de espacio registrada con exito: {}", reservaEspacioSalidaDto);
            return reservaEspacioSalidaDto;
        } else {
            LOGGER.error("No se pudo registrar la reserva de espacio");
            throw new BadRequestException("No se pudo registrar la reserva de espacio");
        }
    }

    @Override
    public List<ReservaEspacioSalidaDto> listarReservaEspacios() {
        List<ReservaEspacioSalidaDto> reservaEspacios = reservaEspacioRepository.findAll().stream()
                .map(reservaEspacio -> entidadADtoSalida(reservaEspacio)).toList();
        LOGGER.info("Listado de reservas de espacios: {}", reservaEspacios);
        return reservaEspacios;
    }

    @Override
    public ReservaEspacioSalidaDto buscarReservaEspacioPorId(Long id) {
        ReservaEspacio reservaEspacioBuscada = null;
        try{
            reservaEspacioBuscada = reservaEspacioRepository.findById(id).orElse(null);
        }catch(Exception e){
            LOGGER.info("el Id de la reserva de espacio no se encuentra");
        }
        ReservaEspacioSalidaDto reservaEspacioSalidaDto = null;
        if (reservaEspacioBuscada != null) {
            reservaEspacioSalidaDto = entidadADtoSalida(reservaEspacioBuscada);
            LOGGER.info("Reserva de espacio por id: {}", reservaEspacioSalidaDto);
        } else LOGGER.info("Reserva de espacio por id: {}", id);
        return reservaEspacioSalidaDto;
    }

    @Override
    public void eliminarReservaEspacio(Long id) throws ResourceNotFoundException {
        if (buscarReservaEspacioPorId(id) != null) {
            reservaEspacioRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado la reserva de espacio con id: {}", id);
        } else {
            LOGGER.error("No se ha encontrado la reserva de espacio con id {}", id);
            throw new ResourceNotFoundException("No se ha encontrado la reserva de espacio con id " + id);
        }
    }

    @Override
    public ReservaEspacioSalidaDto modificarReservaEspacio(Long id, Map<String, Object> camposAModificar) throws ResourceNotFoundException {
        Optional<ReservaEspacio> reservaEspacioGuardada = reservaEspacioRepository.findById(id);
        ReservaEspacioSalidaDto reservaEspacioSalidaDto = null;

        if (reservaEspacioGuardada.isPresent()) {
            camposAModificar.forEach((key, value) -> {
                if (key.equals("usuario")){
                    UsuarioSalidaDto cambioUsuario = usuarioService.buscarUsuarioPorId(convertirALong(value));
                    Usuario usuario = modelMapper.map(cambioUsuario, Usuario.class);
                    Field campoAModificar = ReflectionUtils.findField(ReservaEspacio.class, key);
                    campoAModificar.setAccessible(true);
                    ReflectionUtils.setField(campoAModificar, reservaEspacioGuardada.get(), usuario);
                } else if (key.equals("reserva")) {
                    ReservaSalidaDto cambioReserva = reservaService.buscarReservaPorId(convertirALong(value));
                    Reserva reserva = modelMapper.map(cambioReserva, Reserva.class);
                    Field campoAModificar = ReflectionUtils.findField(ReservaEspacio.class, key);
                    campoAModificar.setAccessible(true);
                    ReflectionUtils.setField(campoAModificar, reservaEspacioGuardada.get(), reserva);
                } else if (key.equals("sala")) {
                    SalaSalidaDto cambioSala = salaService.buscarSalaPorId(convertirALong(value));
                    Sala sala = modelMapper.map(cambioSala, Sala.class);
                    Field campoAModificar = ReflectionUtils.findField(ReservaEspacio.class, key);
                    campoAModificar.setAccessible(true);
                    ReflectionUtils.setField(campoAModificar, reservaEspacioGuardada.get(), sala);
                } else {
                    Field campoAModificar = ReflectionUtils.findField(ReservaEspacio.class, key);
                    campoAModificar.setAccessible(true);
                    ReflectionUtils.setField(campoAModificar,  reservaEspacioGuardada.get(), value);
                }
            });
            reservaEspacioRepository.save(reservaEspacioGuardada.get());
            reservaEspacioSalidaDto = entidadADtoSalida(reservaEspacioGuardada.get());
            LOGGER.info("La reserva de espacio ha sido actualizada: {}", reservaEspacioGuardada.get());

            return reservaEspacioSalidaDto;
        } else {
            LOGGER.error("No fue posible actualizar los datos, la reserva de espacio no se encuentra registrada");
            throw new ResourceNotFoundException("No fue posible actualizar los datos, la reserva de espacio no se encuentra registrada");
        }
    }

    private void configureMappings() {
        modelMapper.emptyTypeMap(ReservaEspacioEntradaDto.class, ReservaEspacio.class)
                .addMappings(mapper -> mapper.map(ReservaEspacioEntradaDto::getIdUsuario, ReservaEspacio::setUsuario))
                .addMappings(mapper -> mapper.map(ReservaEspacioEntradaDto::getIdReserva, ReservaEspacio::setReserva))
                .addMappings(mapper -> mapper.map(ReservaEspacioEntradaDto::getIdSala, ReservaEspacio::setSala));
        modelMapper.typeMap(ReservaEspacio.class, ReservaEspacioSalidaDto.class);
        modelMapper.typeMap(ReservaEspacioModificacionEntradaDto.class, ReservaEspacio.class);
        modelMapper.typeMap(UsuarioSalidaDto.class, Usuario.class);
        modelMapper.typeMap(ReservaSalidaDto.class, Reserva.class);
        modelMapper.typeMap(SalaSalidaDto.class, Sala.class);
    }

    private Usuario usuarioEntradaDtoAEntity(Long id) {
        Usuario usuario = modelMapper.map(usuarioService.buscarUsuarioPorId(id), Usuario.class);
        return usuario;
    }
    private Reserva reservaEntradaDtoAEntity(Long id) {
        Reserva reserva = modelMapper.map(reservaService.buscarReservaPorId(id), Reserva.class);
        return reserva;
    }

    private Sala salaEntradaDtoAEntity(Long id) {
        Sala sala = modelMapper.map(salaService.buscarSalaPorId(id), Sala.class);
        return sala;
    }

    public ReservaEspacio dtoEntradaAEntidad(ReservaEspacioEntradaDto reservaEspacioEntradaDto) {
        ReservaEspacio reservaEspacio = modelMapper.map(reservaEspacioEntradaDto, ReservaEspacio.class);
        reservaEspacio.setUsuario(usuarioEntradaDtoAEntity(reservaEspacioEntradaDto.getIdUsuario()));
        reservaEspacio.setReserva(reservaEntradaDtoAEntity(reservaEspacioEntradaDto.getIdReserva()));
        reservaEspacio.setSala(salaEntradaDtoAEntity(reservaEspacioEntradaDto.getIdSala()));
        return reservaEspacio;
    }

    private UsuarioSalidaDto entityAUsuarioSalidaDto(Usuario usuario) {
        return modelMapper.map(usuario, UsuarioSalidaDto.class);
    }
    private ReservaSalidaDto entityAReservaSalidaDto(Reserva reserva) {
        return modelMapper.map(reserva, ReservaSalidaDto.class);
    }
    private SalaSalidaDto entityASalaSalidaDto(Sala sala) {
        return modelMapper.map(sala, SalaSalidaDto.class);
    }

    public ReservaEspacioSalidaDto entidadADtoSalida(ReservaEspacio reservaEspacio) {
        ReservaEspacioSalidaDto reservaEspacioSalidaDto = modelMapper.map(reservaEspacio, ReservaEspacioSalidaDto.class);
        reservaEspacioSalidaDto.setUsuario(entityAUsuarioSalidaDto(reservaEspacio.getUsuario()));
        reservaEspacioSalidaDto.setReserva(entityAReservaSalidaDto(reservaEspacio.getReserva()));
        reservaEspacioSalidaDto.setSala(entityASalaSalidaDto(reservaEspacio.getSala()));
        return reservaEspacioSalidaDto;
    }

    private ReservaEspacio dtoModificadoAEntidad(ReservaEspacioModificacionEntradaDto reservaEspacioModificacionEntradaDto) {
        ReservaEspacio reservaEspacio = modelMapper.map(reservaEspacioModificacionEntradaDto, ReservaEspacio.class);
        if (reservaEspacioModificacionEntradaDto.getIdUsuario() != 0){
            reservaEspacio.setUsuario(usuarioEntradaDtoAEntity(reservaEspacioModificacionEntradaDto.getIdUsuario()));
        }
        if (reservaEspacioModificacionEntradaDto.getIdReserva() != 0){
            reservaEspacio.setReserva(reservaEntradaDtoAEntity(reservaEspacioModificacionEntradaDto.getIdReserva()));
        }
        if (reservaEspacioModificacionEntradaDto.getIdSala() != 0){
            reservaEspacio.setSala(salaEntradaDtoAEntity(reservaEspacioModificacionEntradaDto.getIdSala()));
        } return reservaEspacio;
    }

    public Long convertirALong(Object o){
        String stringALong = String.valueOf(o);
        Long convertirALong = Long.parseLong(stringALong);
        return convertirALong;
    }
}
