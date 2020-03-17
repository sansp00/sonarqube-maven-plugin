package com.github.sansp00.maven.sonarqube.gateway.model;

import static pl.pojo.tester.api.FieldPredicate.exclude;
import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;
import static pl.pojo.tester.api.assertion.Method.CONSTRUCTOR;
import static pl.pojo.tester.api.assertion.Method.EQUALS;
import static pl.pojo.tester.api.assertion.Method.GETTER;
import static pl.pojo.tester.api.assertion.Method.HASH_CODE;
import static pl.pojo.tester.api.assertion.Method.SETTER;
import static pl.pojo.tester.api.assertion.Method.TO_STRING;

import org.junit.Test;

public class FailingTest {
	@Test
	public void testPojo() {
		// Arrange
		final Class<?> classUnderTest = Event.class;

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
}
