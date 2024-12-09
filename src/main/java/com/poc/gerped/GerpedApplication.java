package com.poc.gerped;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
public class GerpedApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerpedApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		var modelMapper = new ModelMapper();

		return modelMapper;
	}
}
