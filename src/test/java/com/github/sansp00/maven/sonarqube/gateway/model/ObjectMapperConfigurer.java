package com.github.sansp00.maven.sonarqube.gateway.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.github.sansp00.maven.sonarqube.gateway.model.converter.DateTimeConverter;

public class ObjectMapperConfigurer {

	private ObjectMapperConfigurer() {
		// NoOp
	}

	public static void configure(final ObjectMapper objectMapper) {
		configureJdk8Module(objectMapper);
		configureParameterNamesModule(objectMapper);
		configureDateTimeModule(objectMapper);
	}

	public static void configureParameterNamesModule(final ObjectMapper objectMapper) {
		objectMapper.registerModule(new ParameterNamesModule());
	}

	public static void configureJdk8Module(final ObjectMapper objectMapper) {
		objectMapper.registerModule(new Jdk8Module());
	}

	public static void configureDateTimeModule(final ObjectMapper objectMapper) {
		final JavaTimeModule javaTimeModule = new JavaTimeModule();
		javaTimeModule.addDeserializer(LocalDateTime.class,
				new LocalDateTimeDeserializer(DateTimeConverter.DATE_TIME_FORMATTER));
		objectMapper.registerModule(javaTimeModule).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	}

}
