package com.github.sansp00.maven.sonarqube.gateway.model.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;

import org.junit.Test;

public class DateTimeConverterTest {

	final DateTimeConverter converter = new DateTimeConverter();
	String stringRepresentation = "2007-12-03T10:15:30";
	LocalDateTime localDateRepresentation = LocalDateTime.of(2007, 12, 3, 10, 15, 30);

	@Test
	public void toLocalDate() {
		LocalDateTime value = converter.toLocalDateTime(stringRepresentation);
		assertNotNull(value);
		assertEquals(localDateRepresentation, value);
	}

	@Test
	public void fromLocalDate() {
		String value = converter.fromLocalDateTime(localDateRepresentation);
		assertNotNull(value);
		assertEquals(stringRepresentation, value);
	}

}
