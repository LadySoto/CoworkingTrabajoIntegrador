package com.backend.digitalhouse.coworking.service.implement;

import com.backend.digitalhouse.coworking.dto.entrada.usuario.UsuarioEntradaDto;
import com.backend.digitalhouse.coworking.dto.modificacion.usuario.UsuarioModificacionEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.rol.RolSalidaDto;
import com.backend.digitalhouse.coworking.dto.salida.tipoIdentificacion.TipoIdentificacionSalidaDto;
import com.backend.digitalhouse.coworking.dto.salida.usuario.UsuarioSalidaDto;
import com.backend.digitalhouse.coworking.entity.*;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.coworking.repository.UsuarioRepository;
import com.backend.digitalhouse.coworking.service.IUsuarioService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService implements IUsuarioService {
    private final Logger LOGGER = LoggerFactory.getLogger(UsuarioService.class);
    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;
    private final TipoIdentificacionService tipoIdentificacionService;
    private final RolService rolService;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, ModelMapper modelMapper, TipoIdentificacionService tipoIdentificacionService, RolService rolService) {
        this.usuarioRepository = usuarioRepository;
        this.modelMapper = modelMapper;
        this.tipoIdentificacionService = tipoIdentificacionService;
        this.rolService = rolService;
        configureMappings();
    }

    @Override
    public UsuarioSalidaDto registrarUsuario(UsuarioEntradaDto usuario) throws BadRequestException {
        if (usuario != null) {
            UsuarioSalidaDto usuarioSalidaDto = entidadADtoSalida(usuarioRepository.save(dtoEntradaAEntidad(usuario)));
            LOGGER.info("Nuevo usuario registrado con exito: {}", usuarioSalidaDto);
            return usuarioSalidaDto;
        } else {
            LOGGER.error("No se pudo registrar el usuario");
            throw new BadRequestException("No se pudo registrar el usuario");
        }
    }

    @Override
    public List<UsuarioSalidaDto> listarUsuarios() {
        List<UsuarioSalidaDto> usuarios = usuarioRepository.findAll().stream()
                .map(usuario -> entidadADtoSalida(usuario)).toList();
        LOGGER.info("Listado de usuarios: {}", usuarios);
        return usuarios;
    }

    @Override
    public UsuarioSalidaDto buscarUsuarioPorId(Long id) {
        Usuario usuarioBuscado = null;
        try{
            usuarioBuscado = usuarioRepository.findById(id).orElse(null);
        }catch(Exception e){
            LOGGER.info("el Id del usuario no se encuentra");
        }
        UsuarioSalidaDto usuarioSalidaDto = null;
        if (usuarioBuscado != null) {
            usuarioSalidaDto = entidadADtoSalida(usuarioBuscado);
            LOGGER.info("Usuario por id: {}", usuarioSalidaDto);
        } else LOGGER.info("Usuario por id: {}", id);
        return usuarioSalidaDto;
    }

    @Override
    public void eliminarUsuario(Long id) throws ResourceNotFoundException {
        if (buscarUsuarioPorId(id) != null) {
            usuarioRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el usuario con id: {}", id);
        } else {
            LOGGER.error("No se ha encontrado el usuario con id {}", id);
            throw new ResourceNotFoundException("No se ha encontrado el usuario con id " + id);
        }
    }

    @Override
    public UsuarioSalidaDto modificarUsuario(UsuarioModificacionEntradaDto usuarioModificado) throws ResourceNotFoundException {
        Usuario usuarioConModificacion = dtoModificadoAEntidad(usuarioModificado);
        Usuario usuarioGuardado = usuarioRepository.findById(usuarioConModificacion.getId()).orElse(null);
        UsuarioSalidaDto usuarioSalidaDto = null;

        if (usuarioGuardado != null) {
            if (usuarioConModificacion.getNombre() != usuarioGuardado.getNombre()) {
                usuarioGuardado.setNombre(usuarioConModificacion.getNombre());
            }
            if (usuarioConModificacion.getCorreo() != usuarioGuardado.getCorreo()) {
                usuarioGuardado.setCorreo(usuarioConModificacion.getCorreo());
            }
            if (usuarioConModificacion.getContrasena() != usuarioGuardado.getContrasena()) {
                usuarioGuardado.setContrasena(usuarioConModificacion.getContrasena());
            }
            if (usuarioConModificacion.getTipoIdentificacion() != usuarioGuardado.getTipoIdentificacion()) {
                usuarioGuardado.setTipoIdentificacion(usuarioConModificacion.getTipoIdentificacion());
            }
            if (usuarioConModificacion.getNumeroIdentificacion() != usuarioGuardado.getNumeroIdentificacion()) { usuarioGuardado.setNumeroIdentificacion(usuarioConModificacion.getNumeroIdentificacion());
            }
            if (usuarioConModificacion.getEstado() != usuarioGuardado.getEstado()) {
                    usuarioGuardado.setEstado(usuarioConModificacion.getEstado());
            }
            if (usuarioConModificacion.getRol() != usuarioGuardado.getRol()) {
                    usuarioGuardado.setRol(usuarioConModificacion.getRol());
            }

            usuarioRepository.save(usuarioGuardado);
            usuarioSalidaDto = entidadADtoSalida(usuarioGuardado);
            LOGGER.info("El usuario ha sido actualizado: {}", usuarioGuardado);

        } else {

            LOGGER.error("No fue posible actualizar los datos, el usuario no se encuentra registrado");
            throw new ResourceNotFoundException("No fue posible actualizar los datos, el usuario no se encuentra registrado");
        }
        return usuarioSalidaDto;
    }

    private void configureMappings() {
        modelMapper.emptyTypeMap(UsuarioEntradaDto.class, Usuario.class)
                .addMappings(mapper -> mapper.map(UsuarioEntradaDto::getNombre, Usuario::setNombre))
                .addMappings(mapper -> mapper.map(UsuarioEntradaDto::getCorreo, Usuario::setCorreo))
                .addMappings(mapper -> mapper.map(UsuarioEntradaDto::getContrasena, Usuario::setContrasena))
                .addMappings(mapper -> mapper.map(UsuarioEntradaDto::getIdTipoIdentificacion, Usuario::setTipoIdentificacion))
                .addMappings(mapper -> mapper.map(UsuarioEntradaDto::getNumeroIdentificacion, Usuario::setNumeroIdentificacion))
                .addMappings(mapper -> mapper.map(UsuarioEntradaDto::getEstado, Usuario::setEstado))
                .addMappings(mapper -> mapper.map(UsuarioEntradaDto::getIdRol, Usuario::setRol));
        modelMapper.typeMap(Usuario.class, UsuarioSalidaDto.class);
        modelMapper.typeMap(UsuarioModificacionEntradaDto.class, Usuario.class);
        //.addMappings(mapper -> mapper.map(UsuarioModificacionEntradaDto::getIdIdentificacion, Usuario::setTipoIdentificacion));
        modelMapper.typeMap(TipoIdentificacionSalidaDto.class, TipoIdentificacion.class);
        modelMapper.typeMap(RolSalidaDto.class, Rol.class);
    }

    private TipoIdentificacion tipoIdentificacionEntradaDtoAEntity(Long id) {
        TipoIdentificacion tipoIdentificacion = modelMapper.map(tipoIdentificacionService.buscarTipoIdentificacionPorId(id), TipoIdentificacion.class);
        return tipoIdentificacion;
    }
    private Rol rolEntradaDtoAEntity(Long id) {
        Rol rol = modelMapper.map(rolService.buscarRolPorId(id), Rol.class);
        return rol;
    }

    public Usuario dtoEntradaAEntidad(UsuarioEntradaDto usuarioEntradaDto) {
        Usuario usuario = modelMapper.map(usuarioEntradaDto, Usuario.class);
        usuario.setTipoIdentificacion(tipoIdentificacionEntradaDtoAEntity(usuarioEntradaDto.getIdTipoIdentificacion()));
        usuario.setRol(rolEntradaDtoAEntity(usuarioEntradaDto.getIdRol()));
        return usuario;
    }

    private TipoIdentificacionSalidaDto entityATipoIdentificacionSalidaDto(TipoIdentificacion tipoIdentificacion) {
        return modelMapper.map(tipoIdentificacion, TipoIdentificacionSalidaDto.class);
    }
    private RolSalidaDto entityARolSalidaDto(Rol rol) {
        return modelMapper.map(rol, RolSalidaDto.class);
    }

    public UsuarioSalidaDto entidadADtoSalida(Usuario usuario) {
        UsuarioSalidaDto usuarioSalidaDto = modelMapper.map(usuario, UsuarioSalidaDto.class);
        usuarioSalidaDto.setIdTipoIdentificacion(entityATipoIdentificacionSalidaDto(usuario.getTipoIdentificacion()));
        System.out.println(usuario.getRol());
        usuarioSalidaDto.setIdRol(entityARolSalidaDto(usuario.getRol()));
        return usuarioSalidaDto;
    }

    private Usuario dtoModificadoAEntidad(UsuarioModificacionEntradaDto usuarioModificacionEntradaDto) {
        Usuario usuario = modelMapper.map(usuarioModificacionEntradaDto, Usuario.class);

        if (usuarioModificacionEntradaDto.getIdTipoIdentificacion() != 0){
            usuario.setTipoIdentificacion(tipoIdentificacionEntradaDtoAEntity(usuarioModificacionEntradaDto.getIdTipoIdentificacion()));
        }

        if (usuarioModificacionEntradaDto.getIdRol() != 0){
                usuario.setRol(rolEntradaDtoAEntity(usuarioModificacionEntradaDto.getIdRol()));

        } return usuario;

    }
}
