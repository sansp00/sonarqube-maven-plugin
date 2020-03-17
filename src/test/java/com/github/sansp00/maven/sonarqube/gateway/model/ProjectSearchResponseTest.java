package com.github.sansp00.maven.sonarqube.gateway.model;

import static org.junit.Assert.assertNotNull;
import static pl.pojo.tester.api.FieldPredicate.exclude;
import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;
import static pl.pojo.tester.api.assertion.Method.CONSTRUCTOR;
import static pl.pojo.tester.api.assertion.Method.EQUALS;
import static pl.pojo.tester.api.assertion.Method.GETTER;
import static pl.pojo.tester.api.assertion.Method.HASH_CODE;
import static pl.pojo.tester.api.assertion.Method.SETTER;
import static pl.pojo.tester.api.assertion.Method.TO_STRING;

import java.io.IOException;

import org.codehaus.plexus.util.FileUtils;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ProjectSearchResponseTest {
	@Test
	public void testPojo() {
		// Arrange
		final Class<?> classUnderTest = ProjectSearchResponse.class;

		// Act

		// Assert
		assertPojoMethodsFor(classUnderTest, exclude("additionalProperties")) //
				.quickly() //
				.testing(GETTER) //
				.testing(SETTER) //
				.testing(EQUALS, HASH_CODE) //
				.testing(CONSTRUCTOR) //
				.areWellImplemented();

		assertPojoMethodsFor(classUnderTest) //
				.testing(TO_STRING) //
				.areWellImplemented();

	}

	@Test
	public void testMapper() throws JsonParseException, JsonMappingException, IOException {
		// Arrange
		final ObjectMapper objectMapper = new ObjectMapper();
		ObjectMapperConfigurer.configure(objectMapper);
		final String jsonData = FileUtils.fileRead("src/test/resources/gateway/ProjectSearchResponse.json");

		// Act
		ProjectSearchResponse response = objectMapper.readValue(jsonData, ProjectSearchResponse.class);

		// Assert
		assertNotNull(response);
	}
}
