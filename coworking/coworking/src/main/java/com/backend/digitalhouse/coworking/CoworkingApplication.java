package com.backend.digitalhouse.coworking;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CoworkingApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(CoworkingApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(CoworkingApplication.class, args);
		LOGGER.info("Coworking is now running...");
	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
