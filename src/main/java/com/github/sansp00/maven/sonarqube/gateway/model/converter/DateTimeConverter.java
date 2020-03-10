package com.github.sansp00.maven.sonarqube.gateway.model.converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeConverter {
	// 2016-12-11T17:12:45+0100
	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
	public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
	//private DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

	public LocalDateTime toLocalDateTime(String value) {
		return LocalDateTime.parse(value, DATE_TIME_FORMATTER);
	}

	public String fromLocalDateTime(LocalDateTime value) {
		return value.format(DATE_TIME_FORMATTER);
	}

}
