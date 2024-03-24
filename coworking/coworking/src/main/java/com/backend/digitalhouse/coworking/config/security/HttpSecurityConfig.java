package com.backend.digitalhouse.coworking.config.security;

import com.backend.digitalhouse.coworking.config.security.filter.JwtAuthenticationFilter;
import com.backend.digitalhouse.coworking.util.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class HttpSecurityConfig {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {

        http
                .csrf(csrfConfig -> csrfConfig.disable())
                .sessionManagement(sessionMangConfig ->sessionMangConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
              //.authorizeHttpRequests(buildRequestMatchers());

        return http.build();

    }

    private static Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> buildRequestMatchers() {
        return authConfig -> {
            authConfig.requestMatchers(HttpMethod.POST, "/auth/authenticate").permitAll();
            authConfig.requestMatchers(HttpMethod.GET, "/auth/public-access").permitAll();
            authConfig.requestMatchers("/error").permitAll();

            authConfig.requestMatchers(HttpMethod.GET, "/tiposala/listar").hasAuthority(Permission.READ_ALL_TIPOSSALAS.name());
            authConfig.requestMatchers(HttpMethod.POST, "/tiposala/registrar").hasAuthority(Permission.SAVE_ONE_TIPOSALA.name());
            authConfig.requestMatchers(HttpMethod.GET, "/tiposala/busqueda/{id}").hasAuthority(Permission.SEARCH_ONE_TIPOSALA.name());
            authConfig.requestMatchers(HttpMethod.PATCH, "/tiposala/modificar/{id}").hasAuthority(Permission.UPDATE_ONE_TIPOSALA.name());
            authConfig.requestMatchers(HttpMethod.DELETE, "/tiposala/eliminar/{id}").hasAuthority(Permission.DELETE_ONE_TIPOSALA.name());

            authConfig.requestMatchers(HttpMethod.GET, "/sala/listar").hasAuthority(Permission.READ_ALL_SALAS.name());
            authConfig.requestMatchers(HttpMethod.POST, "/sala/registrar").hasAuthority(Permission.SAVE_ONE_SALA.name());
            authConfig.requestMatchers(HttpMethod.GET, "/sala/busqueda/{id}").hasAuthority(Permission.SEARCH_ONE_SALA.name());
            authConfig.requestMatchers(HttpMethod.PATCH, "/sala/modificar/{id}").hasAuthority(Permission.UPDATE_ONE_SALA.name());
            authConfig.requestMatchers(HttpMethod.DELETE, "/sala/eliminar/{id}").hasAuthority(Permission.DELETE_ONE_SALA.name());

            authConfig.requestMatchers(HttpMethod.GET, "/imagen/listar").hasAuthority(Permission.READ_ALL_IMAGENES.name());
            authConfig.requestMatchers(HttpMethod.POST, "/imagen/registrar").hasAuthority(Permission.SAVE_ONE_IMAGEN.name());
            authConfig.requestMatchers(HttpMethod.GET, "/imagen/busqueda/{id}").hasAuthority(Permission.SEARCH_ONE_IMAGEN.name());
            authConfig.requestMatchers(HttpMethod.POST, "/imagen/modificar/{id}").hasAuthority(Permission.UPDATE_ONE_IMAGEN.name());
            authConfig.requestMatchers(HttpMethod.DELETE, "/imagen/eliminar/{id}").hasAuthority(Permission.DELETE_ONE_IMAGEN.name());

            authConfig.requestMatchers(HttpMethod.GET, "/reservaespacio/listar").hasAuthority(Permission.READ_ALL_RESERVAS.name());
            authConfig.requestMatchers(HttpMethod.POST, "/reservaespacio/registrar").hasAuthority(Permission.SAVE_ONE_RESERVA.name());
            authConfig.requestMatchers(HttpMethod.GET, "/reservaespacio/busqueda/{id}").hasAuthority(Permission.SEARCH_ONE_RESERVA.name());
            authConfig.requestMatchers(HttpMethod.POST, "/reservaespacio/modificar/{id}").hasAuthority(Permission.UPDATE_ONE_RESERVA.name());
            authConfig.requestMatchers(HttpMethod.DELETE, "/reservaespacio/eliminar/{id}").hasAuthority(Permission.DELETE_ONE_RESERVA.name());

            authConfig.requestMatchers(HttpMethod.GET, "/servicio/listar").hasAuthority(Permission.READ_ALL_SERVICIOS.name());
            authConfig.requestMatchers(HttpMethod.POST, "/servicio/registrar").hasAuthority(Permission.SAVE_ONE_SERVICIO.name());
            authConfig.requestMatchers(HttpMethod.GET, "/servicio/busqueda/{id}").hasAuthority(Permission.SEARCH_ONE_SERVICIO.name());
            authConfig.requestMatchers(HttpMethod.POST, "/servicio/modificar/{id}").hasAuthority(Permission.UPDATE_ONE_SERVICIO.name());
            authConfig.requestMatchers(HttpMethod.DELETE, "/servicio/eliminar/{id}").hasAuthority(Permission.DELETE_ONE_SERVICIO.name());

            authConfig.requestMatchers(HttpMethod.GET, "/serviciosala/listar").hasAuthority(Permission.READ_ALL_SEVICIOSSSALAS.name());
            authConfig.requestMatchers(HttpMethod.POST, "/serviciosala/registrar").hasAuthority(Permission.SAVE_ONE_SERVICIOSALA.name());
            authConfig.requestMatchers(HttpMethod.GET, "/serviciosala/busqueda/{id}").hasAuthority(Permission.SEARCH_ONE_SERVICIOSALA.name());
            authConfig.requestMatchers(HttpMethod.PATCH, "/serviciosala/modificar/{id}").hasAuthority(Permission.UPDATE_ONE_SERVICIOSALA.name());
            authConfig.requestMatchers(HttpMethod.DELETE, "/serviciosala/eliminar/{id}").hasAuthority(Permission.DELETE_ONE_SERVICIOSALA.name());

            authConfig.requestMatchers(HttpMethod.GET, "/usuario/listar").hasAuthority(Permission.READ_ALL_USUARIOS.name());
            authConfig.requestMatchers(HttpMethod.GET, "/usuario/busqueda/{id}").hasAuthority(Permission.SEARCH_ONE_USUARIO.name());

            authConfig.requestMatchers(HttpMethod.GET, "/tipoidentificacion/listar").hasAuthority(Permission.READ_ALL_TIPOSIDENTIFICACION.name());
            authConfig.requestMatchers(HttpMethod.POST, "/tipoidentificacion/registrar").hasAuthority(Permission.SAVE_ONE_TIPOIDENTIFICACION.name());
            authConfig.requestMatchers(HttpMethod.GET, "/tipoidentificacion/busqueda/{id}").hasAuthority(Permission.SEARCH_ONE_TIPOIDENTIFICACION.name());
            authConfig.requestMatchers(HttpMethod.PATCH, "/tipoidentificacion/modificar/{id}").hasAuthority(Permission.UPDATE_ONE_TIPOIDENTIFICACION.name());
            authConfig.requestMatchers(HttpMethod.DELETE, "/tipoidentificacion/eliminar/{id}").hasAuthority(Permission.DELETE_ONE_TIPOIDENTIFICACION.name());

            authConfig.requestMatchers(HttpMethod.GET, "/rol/listar").hasAuthority(Permission.READ_ALL_ROLES.name());
            authConfig.requestMatchers(HttpMethod.POST, "/rol/registrar").hasAuthority(Permission.SAVE_ONE_ROL.name());
            authConfig.requestMatchers(HttpMethod.GET, "/rol/busqueda/{id}").hasAuthority(Permission.SEARCH_ONE_ROL.name());
            authConfig.requestMatchers(HttpMethod.PATCH, "/rol/modificar/{id}").hasAuthority(Permission.UPDATE_ONE_ROL.name());
            authConfig.requestMatchers(HttpMethod.DELETE, "/rol/eliminar/{id}").hasAuthority(Permission.DELETE_ONE_ROL.name());

            authConfig.anyRequest().denyAll();

        };
    };
}
