package com.backend.digitalhouse.coworking;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class CoworkingApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(CoworkingApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(CoworkingApplication.class, args);
		LOGGER.info("Coworking is now running...");
	}

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	public CommandLineRunner createPasswordsCommand(){
		return args -> {
			LOGGER.info("Contraseña codificada para 'clave123': {}", passwordEncoder.encode("clave123"));

			LOGGER.info("Contraseña codificada para 'clave456': {}", passwordEncoder.encode("clave456"));
		};

	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
