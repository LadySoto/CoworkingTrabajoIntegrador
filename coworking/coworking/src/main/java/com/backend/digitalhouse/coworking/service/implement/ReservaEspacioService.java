package com.backend.digitalhouse.coworking.service.implement;

import com.backend.digitalhouse.coworking.dto.entrada.reservaEspacio.ReservaEspacioEntradaDto;
import com.backend.digitalhouse.coworking.dto.modificacion.reservaEspacio.ReservaEspacioModificacionEntradaDto;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class ReservaEspacioService implements IReservaEspacioService {
    private final Logger LOGGER = LoggerFactory.getLogger(ReservaEspacioService.class);
    private final ReservaEspacioRepository reservaEspacioRepository;
    private final ModelMapper modelMapper;
    private final UsuarioService usuarioService;
    private final SalaService salaService;

    @Autowired
    public ReservaEspacioService(ReservaEspacioRepository reservaEspacioRepository, ModelMapper modelMapper, UsuarioService usuarioService, SalaService salaService) {
        this.reservaEspacioRepository = reservaEspacioRepository;
        this.modelMapper = modelMapper;
        this.usuarioService = usuarioService;
        this.salaService = salaService;
    }

    @Override
    public ReservaEspacioSalidaDto registrarReservaEspacio(ReservaEspacioEntradaDto reservaEspacio) throws BadRequestException {

        UsuarioSalidaDto usuario = usuarioService.buscarUsuarioPorId(reservaEspacio.getIdUsuario());
        SalaSalidaDto sala = salaService.buscarSalaPorId(reservaEspacio.getIdSala());

        if (usuario == null || sala == null){
            if (usuario == null && sala == null){
                LOGGER.info("El usuario y la sala no se encuentran en la base de datos");
                throw new BadRequestException("El usuario y la sala no se encuentran en la base de datos");
            } else if (usuario == null) {
                LOGGER.info("El usuario no se encuentran en la base de datos");
                throw new BadRequestException("El usuario no se encuentran en la base de datos");
            } else {
                LOGGER.info("La sala no se encuentran en la base de datos");
                throw new BadRequestException("La sala no se encuentran en la base de datos");
            }
        }

        if(reservaEspacio.getFechaHoraInicio().equals(reservaEspacio.getFechaHoraFin())){
            LOGGER.info("Los datos de fechaHoraInicio y fechaHoraFin no pueden ser iguales");
            throw new BadRequestException("Los datos de fechaHoraInicio y fechaHoraFin no pueden ser iguales");
        }

       LocalDateTime fechaInicioRedondeada = reservaEspacio.getFechaHoraInicio().truncatedTo(ChronoUnit.HOURS);
        LocalDateTime fechaFinRedondeada = reservaEspacio.getFechaHoraFin().truncatedTo(ChronoUnit.HOURS);
        LOGGER.info("Estas son las fechas de inicio: " + fechaInicioRedondeada  + " y fin: " + fechaFinRedondeada);

        List<LocalDateTime> fechasDisponibles = listarFechasDisponibles(reservaEspacio.getIdSala());
        LOGGER.info("estas son las fechas disponibles" + fechasDisponibles);

        if (fechasDisponibles.contains(fechaInicioRedondeada) && fechasDisponibles.contains(fechaFinRedondeada)) {
            LOGGER.info("Estas fechas están disponibles");

            ReservaEspacioSalidaDto reservaEspacioSalidaDto = entidadADtoSalida(reservaEspacioRepository.save(dtoEntradaAEntidad(reservaEspacio)));
            LOGGER.info("Nueva reserva de espacio registrada con exito: {}", reservaEspacioSalidaDto);
            return reservaEspacioSalidaDto;
        } else {
            LOGGER.error("No se pudo registrar la reserva de espacio");
            throw new BadRequestException("No se pudo registrar la reserva del espacio porque la fecha no esta disponible");
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
    public List<LocalDateTime> listarFechasDisponibles(Long idSala) throws BadRequestException {
        LocalDateTime fechaInicio = LocalDateTime.now();
        LocalDateTime fechaFin = fechaInicio.plusDays(30);

        SalaSalidaDto sala = salaService.buscarSalaPorId(idSala);

        if (sala == null){
            LOGGER.error("No existe esta sala");
            throw new BadRequestException("La sala con ID: " + idSala + " no existe");
        }

        List<ReservaEspacio> reservas = reservaEspacioRepository.findBySalaIdAndFechaHoraInicioBetween(idSala,fechaInicio, fechaFin);

        Set<LocalDateTime> todasLasFechas = new HashSet<>();
        for (ReservaEspacio reserva : reservas) {
            LocalDateTime fechaHoraInicio = reserva.getFechaHoraInicio().truncatedTo(ChronoUnit.HOURS);
            LocalDateTime fechaHoraFin = reserva.getFechaHoraFin().truncatedTo(ChronoUnit.HOURS);

            while (fechaHoraInicio.isBefore(fechaHoraFin) /*|| fechaInicio.equals(fechaHoraFin)*/) {
                todasLasFechas.add(fechaHoraInicio);
                fechaHoraInicio = fechaHoraInicio.plusHours(1);
            }
        }

        List<LocalDateTime> fechasDisponibles = new ArrayList<>();
        for (LocalDateTime fecha = fechaInicio; fecha.isBefore(fechaFin); fecha = fecha.plusDays(1)) {
            LocalDateTime hora = fecha.truncatedTo(ChronoUnit.HOURS);
            while (hora.isBefore(fecha.plusDays(1))) {
                if (!todasLasFechas.contains(hora)) {
                    fechasDisponibles.add(hora);
                }
                hora = hora.plusHours(1);
            }
        }
        return fechasDisponibles;
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
                .addMappings(mapper -> mapper.map(ReservaEspacioEntradaDto::getIdSala, ReservaEspacio::setSala))
                .addMappings(mapper -> mapper.map(ReservaEspacioEntradaDto::getFechaHoraInicio, ReservaEspacio::setFechaHoraInicio))
                .addMappings(mapper -> mapper.map(ReservaEspacioEntradaDto::getFechaHoraFin, ReservaEspacio::setFechaHoraFin))
                .addMappings(mapper -> mapper.map(ReservaEspacioEntradaDto::getCalificacion, ReservaEspacio::setCalificacion));
        modelMapper.typeMap(ReservaEspacio.class, ReservaEspacioSalidaDto.class);
        modelMapper.typeMap(ReservaEspacioModificacionEntradaDto.class, ReservaEspacio.class);
        modelMapper.typeMap(UsuarioSalidaDto.class, Usuario.class);
        modelMapper.typeMap(SalaSalidaDto.class, Sala.class);
    }

   /* private void configureMappings() {
        modelMapper.createTypeMap(ReservaEspacioEntradaDto.class, ReservaEspacio.class)
                .addMapping(ReservaEspacioEntradaDto::getIdUsuario, ReservaEspacio::setUsuario)
                .addMapping(ReservaEspacioEntradaDto::getIdSala, ReservaEspacio::setSala);

        modelMapper.typeMap(ReservaEspacio.class, ReservaEspacioSalidaDto.class);
        modelMapper.typeMap(ReservaEspacioModificacionEntradaDto.class, ReservaEspacio.class);
        modelMapper.typeMap(UsuarioSalidaDto.class, Usuario.class);
        modelMapper.typeMap(SalaSalidaDto.class, Sala.class);
    }*/

    private Usuario usuarioEntradaDtoAEntity(Long id) {
        Usuario usuario = modelMapper.map(usuarioService.buscarUsuarioPorId(id), Usuario.class);
        return usuario;
    }

    private Sala salaEntradaDtoAEntity(Long id) {
        Sala sala = modelMapper.map(salaService.buscarSalaPorId(id), Sala.class);
        return sala;
    }

   /* public ReservaEspacio dtoEntradaAEntidad(ReservaEspacioEntradaDto reservaEspacioEntradaDto) {
        ReservaEspacio reservaEspacio = modelMapper.map(reservaEspacioEntradaDto, ReservaEspacio.class);
        reservaEspacio.setUsuario(usuarioEntradaDtoAEntity(reservaEspacioEntradaDto.getIdUsuario()));
        reservaEspacio.setSala(salaEntradaDtoAEntity(reservaEspacioEntradaDto.getIdSala()));
        return reservaEspacio;
    }*/

   /* public ReservaEspacio dtoEntradaAEntidad(ReservaEspacioEntradaDto reservaEspacioEntradaDto) {
        ReservaEspacio reservaEspacio = new ReservaEspacio();

        // Mapear las propiedades de ReservaEspacioEntradaDto directamente a ReservaEspacio
        modelMapper.map(reservaEspacioEntradaDto, reservaEspacio);

        // Mapear el idUsuario y idSala a las entidades correspondientes
        reservaEspacio.setUsuario(usuarioEntradaDtoAEntity(reservaEspacioEntradaDto.getIdUsuario()));
        reservaEspacio.setSala(salaEntradaDtoAEntity(reservaEspacioEntradaDto.getIdSala()));

        return reservaEspacio;
    }*/

    public ReservaEspacio dtoEntradaAEntidad(ReservaEspacioEntradaDto reservaEspacioEntradaDto) {
        ReservaEspacio reservaEspacio = new ReservaEspacio();

        reservaEspacio.setUsuario(usuarioEntradaDtoAEntity(reservaEspacioEntradaDto.getIdUsuario()));
        reservaEspacio.setSala(salaEntradaDtoAEntity(reservaEspacioEntradaDto.getIdSala()));
        reservaEspacio.setFechaHoraInicio(reservaEspacioEntradaDto.getFechaHoraInicio());
        reservaEspacio.setFechaHoraFin(reservaEspacioEntradaDto.getFechaHoraFin());
        reservaEspacio.setCalificacion(reservaEspacioEntradaDto.getCalificacion());

        return reservaEspacio;
    }

    private UsuarioSalidaDto entityAUsuarioSalidaDto(Usuario usuario) {
        UsuarioSalidaDto usuarioSalidaDto = modelMapper.map(usuario, UsuarioSalidaDto.class);
        usuarioSalidaDto.setIdTipoIdentificacion(usuarioService.entityATipoIdentificacionSalidaDto(usuario.getTipoIdentificacion()));
        usuarioSalidaDto.setIdRol(usuarioService.entityARolSalidaDto(usuario.getRol()));
        return usuarioSalidaDto;
    }
    private SalaSalidaDto entityASalaSalidaDto(Sala sala) {
        SalaSalidaDto salaSalidaDto = modelMapper.map(sala, SalaSalidaDto.class);
        return salaSalidaDto;
    }

    public ReservaEspacioSalidaDto entidadADtoSalida(ReservaEspacio reservaEspacio) {
        ReservaEspacioSalidaDto reservaEspacioSalidaDto = modelMapper.map(reservaEspacio, ReservaEspacioSalidaDto.class);
        reservaEspacioSalidaDto.setUsuario(entityAUsuarioSalidaDto(reservaEspacio.getUsuario()));
        reservaEspacioSalidaDto.setSala(entityASalaSalidaDto(reservaEspacio.getSala()));
        return reservaEspacioSalidaDto;
    }

    private ReservaEspacio dtoModificadoAEntidad(ReservaEspacioModificacionEntradaDto reservaEspacioModificacionEntradaDto) {
        ReservaEspacio reservaEspacio = modelMapper.map(reservaEspacioModificacionEntradaDto, ReservaEspacio.class);
        if (reservaEspacioModificacionEntradaDto.getIdUsuario() != 0){
            reservaEspacio.setUsuario(usuarioEntradaDtoAEntity(reservaEspacioModificacionEntradaDto.getIdUsuario()));
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
