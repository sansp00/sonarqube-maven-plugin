package com.github.sansp00.maven.sonarqube.gateway.exception;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;
import static pl.pojo.tester.api.assertion.Method.CONSTRUCTOR;
import static pl.pojo.tester.api.assertion.Method.TO_STRING;

import org.junit.Test;

public class SonarQubeGatewayClientExceptionTest {
	@Test
	public void testPojo() {
		// Arrange
		final Class<?> classUnderTest = SonarQubeGatewayClientException.class;

		// Act

		// Assert
		assertPojoMethodsFor(classUnderTest) //
				.quickly() //
				.testing(CONSTRUCTOR) //
				.areWellImplemented();

		assertPojoMethodsFor(classUnderTest) //
				.testing(TO_STRING) //
				.areWellImplemented();

	}
}
