package com.backend.digitalhouse.coworking.service.implement;

import com.backend.digitalhouse.coworking.dto.entrada.reserva.ReservaEntradaDto;
import com.backend.digitalhouse.coworking.dto.modificacion.reserva.ReservaModificacionEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.calificacion.CalificacionSalidaDto;
import com.backend.digitalhouse.coworking.dto.salida.reserva.ReservaSalidaDto;
import com.backend.digitalhouse.coworking.entity.*;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.coworking.repository.ReservaRepository;
import com.backend.digitalhouse.coworking.service.IReservaService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ReservaService implements IReservaService {
    private final Logger LOGGER = LoggerFactory.getLogger(ReservaService.class);
    private final ReservaRepository reservaRepository;
    private final ModelMapper modelMapper;
    private final CalificacionService calificacionService;

    @Autowired
    public ReservaService(ReservaRepository reservaRepository, ModelMapper modelMapper, CalificacionService calificacionService) {
        this.reservaRepository = reservaRepository;
        this.modelMapper = modelMapper;
        this.calificacionService = calificacionService;
        configureMappings();
    }

    @Override
    public ReservaSalidaDto registrarReserva(ReservaEntradaDto reserva) throws BadRequestException {
        if (reserva != null) {
            ReservaSalidaDto reservaSalidaDto = entidadADtoSalida(reservaRepository.save(dtoEntradaAEntidad(reserva)));
            LOGGER.info("Nueva reserva registrada con exito: {}", reservaSalidaDto);
            return reservaSalidaDto;
        } else {
            LOGGER.error("No se pudo registrar la reserva");
            throw new BadRequestException("No se pudo registrar la reserva");
        }
    }

    @Override
    public List<ReservaSalidaDto> listarReservas() {
        List<ReservaSalidaDto> reservas = reservaRepository.findAll().stream()
                .map(reserva -> entidadADtoSalida(reserva)).toList();
        LOGGER.info("Listado de reservas: {}", reservas);
        return reservas;
    }

    @Override
    public ReservaSalidaDto buscarReservaPorId(Long id) {
        Reserva reservaBuscada = null;
        try{
            reservaBuscada = reservaRepository.findById(id).orElse(null);
        }catch(Exception e){
            LOGGER.info("el Id de la reserva no se encuentra");
        }
        ReservaSalidaDto reservaSalidaDto = null;
        if (reservaBuscada != null) {
            reservaSalidaDto = entidadADtoSalida(reservaBuscada);
            LOGGER.info("Reserva por id: {}", reservaSalidaDto);
        } else LOGGER.info("Reserva por id: {}", id);
        return reservaSalidaDto;
    }

    @Override
    public void eliminarReserva(Long id) throws ResourceNotFoundException {
        if (buscarReservaPorId(id) != null) {
            reservaRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado la reserva con id: {}", id);
        } else {
            LOGGER.error("No se ha encontrado la reserva con id {}", id);
            throw new ResourceNotFoundException("No se ha encontrado la reserva con id " + id);
        }
    }

    @Override
    public ReservaSalidaDto modificarReserva(Long id, Map<String, Object> camposAModificar) throws ResourceNotFoundException {
        Optional<Reserva> reservaGuardada = reservaRepository.findById(id);
        ReservaSalidaDto reservaSalidaDto = null;
        if (reservaGuardada.isPresent()) {
            camposAModificar.forEach((key, value) -> {
                if (key.equals("calificacion")){
                    CalificacionSalidaDto cambioCalificacion = calificacionService.buscarCalificacionPorId(convertirALong(value));
                    Calificacion calificacion = modelMapper.map(cambioCalificacion, Calificacion.class);
                    Field campoAModificar = ReflectionUtils.findField(Reserva.class, key);
                    campoAModificar.setAccessible(true);
                    ReflectionUtils.setField(campoAModificar, reservaGuardada.get(), calificacion);
                } else {
                    Field campoAModificar = ReflectionUtils.findField(Reserva.class, key);
                    campoAModificar.setAccessible(true);
                    ReflectionUtils.setField(campoAModificar, reservaGuardada.get(), value);
                }
            });
            reservaRepository.save(reservaGuardada.get());
            reservaSalidaDto = entidadADtoSalida(reservaGuardada.get());
            LOGGER.info("La reserva ha sido actualizada: {}", reservaGuardada.get());

            return reservaSalidaDto;
        } else {
            LOGGER.error("No fue posible actualizar los datos, la reserva no se encuentra registrada");
            throw new ResourceNotFoundException("No fue posible actualizar los datos, la reserva no se encuentra registrada");
        }
    }

    private void configureMappings() {
        modelMapper.emptyTypeMap(ReservaEntradaDto.class, Reserva.class)
                .addMappings(mapper -> mapper.map(ReservaEntradaDto::getFechaHoraInicio, Reserva::setFechaHoraInicio))
                .addMappings(mapper -> mapper.map(ReservaEntradaDto::getFechaHoraFin, Reserva::setFechaHoraFin))
                .addMappings(mapper -> mapper.map(ReservaEntradaDto::getIdCalificacion, Reserva::setCalificacion))
                .addMappings(mapper -> mapper.map(ReservaEntradaDto::getCantidadHora, Reserva::setCantidadHora));
        modelMapper.typeMap(Reserva.class, ReservaSalidaDto.class);
        modelMapper.typeMap(ReservaModificacionEntradaDto.class, Reserva.class);
        modelMapper.typeMap(CalificacionSalidaDto.class, Calificacion.class);
    }

    private Calificacion calificacionEntradaDtoAEntity(Long id) {
        Calificacion calificacion = modelMapper.map(calificacionService.buscarCalificacionPorId(id), Calificacion.class);
        return calificacion;
    }

    public Reserva dtoEntradaAEntidad(ReservaEntradaDto reservaEntradaDto) {
        Reserva reserva = modelMapper.map(reservaEntradaDto, Reserva.class);
        reserva.setCalificacion(calificacionEntradaDtoAEntity(reservaEntradaDto.getIdCalificacion()));
        return reserva;
    }

    private CalificacionSalidaDto entityARolSalidaDto(Calificacion calificacion) {
        return modelMapper.map(calificacion, CalificacionSalidaDto.class);
    }

    public ReservaSalidaDto entidadADtoSalida(Reserva reserva) {
        ReservaSalidaDto reservaSalidaDto = modelMapper.map(reserva, ReservaSalidaDto.class);
        reservaSalidaDto.setCalificacion(entityARolSalidaDto(reserva.getCalificacion()));
        return reservaSalidaDto;
    }

    private Reserva dtoModificadoAEntidad(ReservaModificacionEntradaDto reservaModificacionEntradaDto) {
        Reserva reserva = modelMapper.map(reservaModificacionEntradaDto, Reserva.class);
        if (reservaModificacionEntradaDto.getIdCalificacion() != 0){
            reserva.setCalificacion(calificacionEntradaDtoAEntity(reservaModificacionEntradaDto.getIdCalificacion()));
        } return reserva;
    }

    public Long convertirALong(Object o){
        String stringALong = String.valueOf(o);
        Long convertirALong = Long.parseLong(stringALong);
        return convertirALong;
    }
}
