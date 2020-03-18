package com.github.sansp00.maven.sonarqube.gateway.model.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;

import org.junit.Test;

public class DateConverterTest {

	final DateConverter converter = new DateConverter();
	String stringRepresentation = "2007-12-03";
	LocalDate localDateRepresentation = LocalDate.of(2007, 12, 3);

	@Test
	public void toLocalDate() {
		LocalDate value = converter.toLocalDate(stringRepresentation);
		assertNotNull(value);
		assertEquals(localDateRepresentation, value);
	}

	@Test
	public void fromLocalDate() {
		String value = converter.fromLocalDate(localDateRepresentation);
		assertNotNull(value);
		assertEquals(stringRepresentation, value);
	}

}
