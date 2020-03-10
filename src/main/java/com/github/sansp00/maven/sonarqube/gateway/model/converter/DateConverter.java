package com.github.sansp00.maven.sonarqube.gateway.model.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateConverter {
    // 2016-12-11
    //private DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);


    public LocalDate toLocalDate(String value) {
        return LocalDate.parse(value, DATE_FORMATTER);
    }

    public String fromLocalDate(LocalDate value) {
        return value.format(DATE_FORMATTER);
    }

}
