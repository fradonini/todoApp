package com.francescodonini.todos;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {
	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
