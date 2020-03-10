package com.github.sansp00.maven.sonarqube.gateway;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;
import javax.ws.rs.core.Response.StatusType;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.github.sansp00.maven.sonarqube.gateway.exception.SonarQubeGatewayClientException;
import com.github.sansp00.maven.sonarqube.gateway.model.Error;
import com.github.sansp00.maven.sonarqube.gateway.model.Errors;

@RunWith(MockitoJUnitRunner.class)
public class SonarQubeGatewayResponseHandlerTest {

	@Mock
	Response response;

	@Mock
	StatusType statusType;

	@Test
	public void handleResponseSuccessfullWithoutEntity() throws SonarQubeGatewayClientException {
		when(response.getStatus()).thenReturn(200);
		when(response.getStatusInfo()).thenReturn(statusType);
		when(statusType.getFamily()).thenReturn(Family.SUCCESSFUL);
		when(response.hasEntity()).thenReturn(false);

		Optional<String> result = SonarQubeGatewayResponseHandler.handleResponse(response, String.class);
		assertFalse(result.isPresent());
		verify(response).getStatus();
		verify(response).getStatusInfo();
		verify(statusType).getFamily();
		verify(response).hasEntity();
	}

	@Test
	public void handleResponseSuccessfullWithEntity() throws SonarQubeGatewayClientException {
		when(response.getStatus()).thenReturn(200);
		when(response.getStatusInfo()).thenReturn(statusType);
		when(statusType.getFamily()).thenReturn(Family.SUCCESSFUL);
		when(response.hasEntity()).thenReturn(true);
		when(response.readEntity(String.class)).thenReturn("MyResult");

		Optional<String> result = SonarQubeGatewayResponseHandler.handleResponse(response, String.class);

		assertTrue(result.isPresent());
		assertThat(result.get(), is("MyResult"));

		verify(response).getStatus();
		verify(response).getStatusInfo();
		verify(statusType).getFamily();
		verify(response).hasEntity();
		verify(response).readEntity(String.class);
	}

	@Test
	public void handleResponseUnsuccessfullWithEntity() {
		when(response.getStatus()).thenReturn(400);
		when(response.getStatusInfo()).thenReturn(statusType);
		when(statusType.getFamily()).thenReturn(Family.CLIENT_ERROR);
		when(response.hasEntity()).thenReturn(true);
		when(response.readEntity(Errors.class)).thenReturn(buildErrors());

		try {
			SonarQubeGatewayResponseHandler.handleResponse(response, String.class);
			fail("Expected an SonarQubeGatewayClientException to be thrown");
		} catch (SonarQubeGatewayClientException e) {
			assertThat(e.getMessage(), is("SonarQube HTTP response [status: 400, message: MyErrorMessage]"));
		}
		verify(response).getStatus();
		verify(response).getStatusInfo();
		verify(statusType).getFamily();
		verify(response).hasEntity();
		verify(response).readEntity(Errors.class);
	}

	@Test
	public void handleResponseUnsuccessfullWithoutEntity() {
		when(response.getStatus()).thenReturn(400);
		when(response.getStatusInfo()).thenReturn(statusType);
		when(statusType.getFamily()).thenReturn(Family.CLIENT_ERROR);
		when(response.hasEntity()).thenReturn(false);

		try {
			SonarQubeGatewayResponseHandler.handleResponse(response, String.class);
			fail("Expected an SonarQubeGatewayClientException to be thrown");
		} catch (SonarQubeGatewayClientException e) {
			assertThat(e.getMessage(), is("SonarQube HTTP response [status: 400, message: UNDEFINED]"));
		}
		verify(response).getStatus();
		verify(response).getStatusInfo();
		verify(statusType).getFamily();
		verify(response).hasEntity();

	}

	private Errors buildErrors() {
		Errors errors = new Errors();
		errors.getErrors().add(buildError());
		return errors;
	}

	private Error buildError() {
		Error error = new Error();
		error.setMsg("MyErrorMessage");
		return error;
	}

}
