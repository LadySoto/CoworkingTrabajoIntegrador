package com.backend.digitalhouse.coworking.service.implement;

import com.backend.digitalhouse.coworking.dto.entrada.usuario.UsuarioEntradaDto;
import com.backend.digitalhouse.coworking.dto.modificacion.usuario.UsuarioModificacionEntradaDto;
import com.backend.digitalhouse.coworking.dto.salida.rol.RolSalidaDto;
import com.backend.digitalhouse.coworking.dto.salida.tipoIdentificacion.TipoIdentificacionSalidaDto;
import com.backend.digitalhouse.coworking.dto.salida.usuario.UsuarioSalidaDto;
import com.backend.digitalhouse.coworking.entity.*;
import com.backend.digitalhouse.coworking.exceptions.BadRequestException;
import com.backend.digitalhouse.coworking.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.coworking.repository.RolRepository;
import com.backend.digitalhouse.coworking.repository.UsuarioRepository;
import com.backend.digitalhouse.coworking.service.IUsuarioService;
import com.backend.digitalhouse.coworking.util.Role;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UsuarioService implements IUsuarioService {
    private final Logger LOGGER = LoggerFactory.getLogger(UsuarioService.class);
    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;
    private final TipoIdentificacionService tipoIdentificacionService;

    private final RolRepository rolRepository;
    private final RolService rolService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, ModelMapper modelMapper, TipoIdentificacionService tipoIdentificacionService, RolRepository rolRepository, RolService rolService, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.modelMapper = modelMapper;
        this.tipoIdentificacionService = tipoIdentificacionService;
        this.rolService = rolService;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
        configureMappings();
    }

    @Override
    public UsuarioSalidaDto registrarUsuario(UsuarioEntradaDto usuario) throws BadRequestException {
        if (usuario != null) {

            Usuario usuarioEntity = dtoEntradaAEntidad(usuario);
            String contrasenaEncriptada = passwordEncoder.encode(usuarioEntity.getContrasena());
            usuarioEntity.setContrasena(contrasenaEncriptada);
            UsuarioSalidaDto usuarioSalidaDto = entidadADtoSalida(usuarioRepository.save(usuarioEntity));
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
        try {
            usuarioBuscado = usuarioRepository.findById(id).orElse(null);
        } catch (Exception e) {
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
    public UsuarioSalidaDto modificarUsuario(Long id, Map<String, Object> camposAModificar) throws ResourceNotFoundException {
        Optional<Usuario> usuarioGuardado = usuarioRepository.findById(id);
        UsuarioSalidaDto usuarioSalidaDto = null;

        if (usuarioGuardado.isPresent()) {
            camposAModificar.forEach((key, value) -> {
                if (key.equals("rol")) {
                    RolSalidaDto cambioRol = rolService.buscarRolPorId(convertirALong(value));
                    Rol rol = modelMapper.map(cambioRol, Rol.class);
                    Field campoAModificar = ReflectionUtils.findField(Usuario.class, key);
                    campoAModificar.setAccessible(true);
                    ReflectionUtils.setField(campoAModificar, usuarioGuardado.get(), rol);
                } else if (key.equals("tipoIdentificacion")) {
                    TipoIdentificacionSalidaDto cambioTipoIdentificacion = tipoIdentificacionService.buscarTipoIdentificacionPorId(convertirALong(value));
                    TipoIdentificacion tipoIdentificacion = modelMapper.map(cambioTipoIdentificacion, TipoIdentificacion.class);
                    Field campoAModificar = ReflectionUtils.findField(Usuario.class, key);
                    campoAModificar.setAccessible(true);
                    ReflectionUtils.setField(campoAModificar, usuarioGuardado.get(), tipoIdentificacion);
                } else {
                    Field campoAModificar = ReflectionUtils.findField(Usuario.class, key);
                    campoAModificar.setAccessible(true);
                    ReflectionUtils.setField(campoAModificar, usuarioGuardado.get(), value);
                }
            });
            usuarioRepository.save(usuarioGuardado.get());
            usuarioSalidaDto = entidadADtoSalida(usuarioGuardado.get());
            LOGGER.info("El usuario ha sido actualizado: {}", usuarioGuardado.get());

            return usuarioSalidaDto;
        } else {
            LOGGER.error("No fue posible actualizar los datos, el usuario no se encuentra registrado");
            throw new ResourceNotFoundException("No fue posible actualizar los datos, el usuario no se encuentra registrado");
        }
    }

    @Override
    public UsuarioSalidaDto modificarIdRolUsuario(Long id, Long nuevoIdRol) throws ResourceNotFoundException {
        LOGGER.info("Inicio modificar rol usuario");
        Optional<Usuario> usuarioGuardadoOptional = usuarioRepository.findById(id);

        if (usuarioGuardadoOptional.isPresent()) {
            Usuario usuarioGuardado = usuarioGuardadoOptional.get();

            usuarioGuardado.setRol(rolRepository.findById(nuevoIdRol)
            .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado con ID: " + nuevoIdRol)));

            if (usuarioGuardado.getRol().getId()==1){
                usuarioGuardado.setRole(Role.ADMINISTRATOR);
            }
            else {
                usuarioGuardado.setRole(Role.CUSTOMER);
            }
            Usuario usuarioActualizado = usuarioRepository.save(usuarioGuardado);

            UsuarioSalidaDto usuarioSalidaDto = entidadADtoSalida(usuarioActualizado);
            LOGGER.info("El rol de usuario ha sido actualizado: {}", usuarioActualizado);
            return usuarioSalidaDto;
        } else {
            LOGGER.error("No fue posible actualizar los datos, el usuario no se encuentra registrado");
            throw new ResourceNotFoundException("No fue posible actualizar los datos, el usuario no se encuentra registrado");
        }
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
        if (usuario.getRol().getId()==1){
            usuario.setRole(Role.ADMINISTRATOR);
        }
        else {
            usuario.setRole(Role.CUSTOMER);
        }
        return usuario;
    }

    protected TipoIdentificacionSalidaDto entityATipoIdentificacionSalidaDto(TipoIdentificacion tipoIdentificacion) {
        return modelMapper.map(tipoIdentificacion, TipoIdentificacionSalidaDto.class);
    }
    protected RolSalidaDto entityARolSalidaDto(Rol rol) {
        return modelMapper.map(rol, RolSalidaDto.class);
    }

    public UsuarioSalidaDto entidadADtoSalida(Usuario usuario) {
        UsuarioSalidaDto usuarioSalidaDto = modelMapper.map(usuario, UsuarioSalidaDto.class);
        usuarioSalidaDto.setIdTipoIdentificacion(entityATipoIdentificacionSalidaDto(usuario.getTipoIdentificacion()));
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

    public Long convertirALong(Object o){
        String stringALong = String.valueOf(o);
        Long convertirALong = Long.parseLong(stringALong);
        return convertirALong;
    }
}
