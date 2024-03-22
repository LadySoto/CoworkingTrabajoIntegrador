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
            authConfig.requestMatchers(HttpMethod.GET, "/sala/listar").hasAuthority(Permission.READ_ALL_SALAS.name());
            authConfig.requestMatchers(HttpMethod.POST, "/sala/registrar").hasAuthority(Permission.SAVE_ONE_SALA.name());
            authConfig.anyRequest().denyAll();

        };
    };
}
