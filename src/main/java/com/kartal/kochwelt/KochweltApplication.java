package com.kartal.kochwelt;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KochweltApplication {

	public static void main(String[] args) {
		SpringApplication.run(KochweltApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
	 return new ModelMapper();
	}


}
