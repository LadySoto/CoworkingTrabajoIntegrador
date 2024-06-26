package com.backend.digitalhouse.coworking.service.implement;

import com.backend.digitalhouse.coworking.dto.entrada.reservaEspacio.ReservaEspacioEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.reservaEspacio.ReservaEspacioSalidaDto;
import com.backend.digitalhouse.coworking.dto.salida.reservaEspacio.SalaReservaSalidaDto;
import com.backend.digitalhouse.coworking.dto.salida.reservaEspacio.UsuarioReservaSalidaDto;
import com.backend.digitalhouse.coworking.dto.salida.sala.SalaSalidaDto;
import com.backend.digitalhouse.coworking.dto.salida.usuario.UsuarioSalidaDto;
import com.backend.digitalhouse.coworking.entity.*;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.coworking.repository.ReservaEspacioRepository;
import com.backend.digitalhouse.coworking.repository.SalaRepository;
import com.backend.digitalhouse.coworking.service.IReservaEspacioService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReservaEspacioService implements IReservaEspacioService {
    private final Logger LOGGER = LoggerFactory.getLogger(ReservaEspacioService.class);
    private final ReservaEspacioRepository reservaEspacioRepository;
    private final SalaRepository salaRepository;
    private final ModelMapper modelMapper;
    private final UsuarioService usuarioService;
    private final SalaService salaService;

    @Autowired
    public ReservaEspacioService(ReservaEspacioRepository reservaEspacioRepository, SalaRepository salaRepository, ModelMapper modelMapper, UsuarioService usuarioService, SalaService salaService) {
        this.reservaEspacioRepository = reservaEspacioRepository;
        this.salaRepository = salaRepository;
        this.modelMapper = modelMapper;
        this.usuarioService = usuarioService;
        this.salaService = salaService;
        configureMappings();
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

        if (sala.getCapacidad() < reservaEspacio.getCantidadPersonas()) {
            LOGGER.info("La cantidad de personas no puede superar la capacidad de la sala");
            throw new BadRequestException("La cantidad de personas no puede superar la capacidad de la sala");
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

            while (fechaHoraInicio.isBefore(fechaHoraFin) || fechaInicio.equals(fechaHoraFin)) {
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
    public List<LocalDateTime> listarFechasOcupadas(Long idSala) throws BadRequestException {
        LocalDateTime fechaInicio = LocalDateTime.now();
        LocalDateTime fechaFin = fechaInicio.plusDays(30);

        SalaSalidaDto sala = salaService.buscarSalaPorId(idSala);

        if (sala == null) {
            LOGGER.error("No existe esta sala");
            throw new BadRequestException("La sala con ID: " + idSala + " no existe");
        }

        List<ReservaEspacio> reservas = reservaEspacioRepository.findBySalaIdAndFechaHoraInicioBetween(idSala, fechaInicio, fechaFin);

        Set<LocalDateTime> todasLasFechas = new HashSet<>();
        for (ReservaEspacio reserva : reservas) {
            LocalDateTime fechaHoraInicio = reserva.getFechaHoraInicio().truncatedTo(ChronoUnit.HOURS);
            LocalDateTime fechaHoraFin = reserva.getFechaHoraFin().truncatedTo(ChronoUnit.HOURS);

            LOGGER.info("fecha y hora de inicio: " + fechaHoraInicio + "fecha y hora de fin: " + fechaHoraFin);

            while (fechaHoraInicio.isBefore(fechaHoraFin) || fechaInicio.equals(fechaHoraFin)) {
                todasLasFechas.add(fechaHoraInicio);
                fechaHoraInicio = fechaHoraInicio.plusHours(1);
            }

            LOGGER.info("fecha y hora de inicio: " + fechaHoraInicio + "fecha y hora de fin: " + fechaHoraFin);
        }

        List<LocalDateTime> fechasOcupadas = new ArrayList<>();
        for (LocalDateTime fecha = fechaInicio; fecha.isBefore(fechaFin); fecha = fecha.plusDays(1)) {
            LocalDateTime hora = fecha.truncatedTo(ChronoUnit.HOURS);

            while (hora.isBefore(fecha.plusDays(1))) {
                if (todasLasFechas.contains(hora)) {
                    fechasOcupadas.add(hora);
                }
                hora = hora.plusHours(1);
            }

            LOGGER.info("fecha: " + fecha + "hora: " + hora);
        }
        LOGGER.info("Estas son las fechas y horas ocupadas: " + fechasOcupadas);

        return fechasOcupadas;
    }

   @Override
    public List<SalaReservaSalidaDto> listarSalasDisponibles(LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin) throws BadRequestException {

        if(fechaHoraInicio.equals(fechaHoraFin)){
            LOGGER.info("Los datos de fechaHoraInicio y fechaHoraFin no pueden ser iguales");
            throw new BadRequestException("Los datos de fechaHoraInicio y fechaHoraFin no pueden ser iguales");
        }

       LocalDateTime fechaAhora = LocalDateTime.now();
        if (fechaAhora.isBefore(fechaAhora) || fechaHoraFin.isBefore(fechaAhora)){
            LOGGER.info("La fecha de entrada y salida no pueden ser anteriores a la fecha actual");
            throw new BadRequestException("La fecha de entrada y salida no pueden ser anteriores a la fecha actual");
        }

       List<ReservaEspacioSalidaDto> reservasActuales = listarReservaEspacios();
       List<SalaSalidaDto> listadoSalas = salaService.listarSalas();

       // Filtrar las salas según la disponibilidad
       List<SalaReservaSalidaDto> salasOcupadas = new ArrayList<>();
       for (ReservaEspacioSalidaDto reserva : reservasActuales) {
           LocalDateTime inicioReserva = reserva.getFechaHoraInicio();
           LocalDateTime finReserva = reserva.getFechaHoraFin();

           LOGGER.info("Fecha y hora del inicio de la reserva ya hecha: " + inicioReserva + " Fecha y hora del inicio de la que quiero la reserva: " + fechaHoraInicio);

           LOGGER.info("Fecha y hora del fin de la reserva ya hecha: " + finReserva + " Fecha y hora del fin de la que quiero la reserva: " + fechaHoraFin);

           if ((inicioReserva.isBefore(fechaHoraInicio) && finReserva.isAfter(fechaHoraInicio)) ||
                   (inicioReserva.isBefore(fechaHoraFin) && finReserva.isAfter(fechaHoraFin)) ||
                   (inicioReserva.isAfter(fechaHoraInicio) && finReserva.isBefore(fechaHoraFin)) ||
                   (inicioReserva.isEqual(fechaHoraInicio) || finReserva.isEqual(fechaHoraFin))) {
               salasOcupadas.add(reserva.getSalaReserva());
           }
       }

       LOGGER.info("Estas son las salas ocupadas" + salasOcupadas);

       // Convertir las salas disponibles a SalaReservaSalidaDto
       List<SalaReservaSalidaDto> salasDisponibles = listadoSalas.stream()
               .map(sala -> new SalaReservaSalidaDto(sala.getId(), sala.getNombre(), sala.getDescripcion(), sala.getCapacidad(), sala.getTipoSala(), sala.getImagenes(), sala.getServicios()))
               .collect(Collectors.toList());

       // Filtrar las salas disponibles basado en los identificadores de las salas ocupadas
       List<Long> idsSalasOcupadas = salasOcupadas.stream().map(SalaReservaSalidaDto::getId).collect(Collectors.toList());
       Iterator<SalaReservaSalidaDto> iterator = salasDisponibles.iterator();
       while (iterator.hasNext()) {
           SalaReservaSalidaDto sala = iterator.next();
           if (idsSalasOcupadas.contains(sala.getId())) {
               iterator.remove();
           }
       }

       LOGGER.info("Estas son las salas disponibles después de eliminar las ocupadas" + salasDisponibles);

       return salasDisponibles;
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

    private void configureMappings() {
        modelMapper.emptyTypeMap(ReservaEspacioEntradaDto.class, ReservaEspacio.class)
                .addMappings(mapper -> mapper.map(ReservaEspacioEntradaDto::getIdUsuario, ReservaEspacio::setUsuario))
                .addMappings(mapper -> mapper.map(ReservaEspacioEntradaDto::getIdSala, ReservaEspacio::setSala))
                .addMappings(mapper -> mapper.map(ReservaEspacioEntradaDto::getFechaHoraInicio, ReservaEspacio::setFechaHoraInicio))
                .addMappings(mapper -> mapper.map(ReservaEspacioEntradaDto::getFechaHoraFin, ReservaEspacio::setFechaHoraFin))
                .addMappings(mapper -> mapper.map(ReservaEspacioEntradaDto::getCantidadPersonas, ReservaEspacio::setCantidadPersonas))
                .addMappings(mapper -> mapper.map(ReservaEspacioEntradaDto::getCalificacion, ReservaEspacio::setCalificacion));
        modelMapper.typeMap(ReservaEspacio.class, ReservaEspacioSalidaDto.class);
        modelMapper.typeMap(UsuarioReservaSalidaDto.class, Usuario.class);
        modelMapper.typeMap(SalaReservaSalidaDto.class, Sala.class);
    }

    private Usuario usuarioEntradaDtoAEntity(Long id) {
        Usuario usuario = modelMapper.map(usuarioService.buscarUsuarioPorId(id), Usuario.class);
        return usuario;
    }

    private Sala salaEntradaDtoAEntity(Long id) {
        Sala sala = modelMapper.map(salaService.buscarSalaPorId(id), Sala.class);
        return sala;
    }

    public ReservaEspacio dtoEntradaAEntidad(ReservaEspacioEntradaDto reservaEspacioEntradaDto) {
        ReservaEspacio reservaEspacio = new ReservaEspacio();

        reservaEspacio.setUsuario(usuarioEntradaDtoAEntity(reservaEspacioEntradaDto.getIdUsuario()));
        reservaEspacio.setSala(salaEntradaDtoAEntity(reservaEspacioEntradaDto.getIdSala()));
        reservaEspacio.setFechaHoraInicio(reservaEspacioEntradaDto.getFechaHoraInicio());
        reservaEspacio.setFechaHoraFin(reservaEspacioEntradaDto.getFechaHoraFin());
        reservaEspacio.setCantidadPersonas(reservaEspacioEntradaDto.getCantidadPersonas());
        reservaEspacio.setCalificacion(reservaEspacioEntradaDto.getCalificacion());

        return reservaEspacio;
    }

    private UsuarioReservaSalidaDto entityAUsuarioReservaSalidaDto (Usuario usuario) {
        UsuarioReservaSalidaDto usuarioReservaSalidaDto = modelMapper.map(usuario, UsuarioReservaSalidaDto.class);
        usuarioReservaSalidaDto.setIdTipoIdentificacion(usuarioService.entityATipoIdentificacionSalidaDto(usuario.getTipoIdentificacion()));
        return usuarioReservaSalidaDto;
    }
    private SalaReservaSalidaDto entityASalaReservaSalidaDto(Sala sala) {
        SalaReservaSalidaDto salaReservaSalidaDto = modelMapper.map(sala, SalaReservaSalidaDto.class);
        return salaReservaSalidaDto;
    }

    public ReservaEspacioSalidaDto entidadADtoSalida(ReservaEspacio reservaEspacio) {
        ReservaEspacioSalidaDto reservaEspacioSalidaDto = modelMapper.map(reservaEspacio, ReservaEspacioSalidaDto.class);
        reservaEspacioSalidaDto.setUsuarioReserva(entityAUsuarioReservaSalidaDto(reservaEspacio.getUsuario()));
        reservaEspacioSalidaDto.setSalaReserva(entityASalaReservaSalidaDto(reservaEspacio.getSala()));
        return reservaEspacioSalidaDto;
    }

    public Long convertirALong(Object o){
        String stringALong = String.valueOf(o);
        Long convertirALong = Long.parseLong(stringALong);
        return convertirALong;
    }
}
