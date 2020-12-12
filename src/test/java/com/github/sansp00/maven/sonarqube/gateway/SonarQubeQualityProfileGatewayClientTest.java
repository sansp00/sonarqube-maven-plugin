package com.github.sansp00.maven.sonarqube.gateway;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.Invocation.Builder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status.Family;
import jakarta.ws.rs.core.Response.StatusType;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.github.sansp00.maven.sonarqube.gateway.exception.SonarQubeGatewayException;

@RunWith(MockitoJUnitRunner.class)
public class SonarQubeQualityProfileGatewayClientTest {

	@Mock
	Client client;

	@Mock
	WebTarget webTarget;

	@Mock
	Builder builder;

	@Mock
	Response response;

	@Mock
	StatusType statusType;

	SonarQubeQualityProfileGatewayClient gatewayClient;

	@Before
	public void init() {
		gatewayClient = new SonarQubeQualityProfileGatewayClient("", client);
	}

	@Test
	public void addProjectWithProjectKeyAndQualityProfileAndLangugage() throws SonarQubeGatewayException {
		Mockito.when(client.target(SonarQubeQualityProfileGatewayClient.ADD_PROJECT_URI)).thenReturn(webTarget);

		Mockito.when(
				webTarget.queryParam(SonarQubeQualityProfileGatewayClient.PROJECT_KEY, GatewayModelBuilder.PROJECT_KEY))
				.thenReturn(webTarget);

		Mockito.when(webTarget.queryParam(SonarQubeQualityProfileGatewayClient.QUALITY_PROFILE,
				GatewayModelBuilder.QUALITY_PROFILE_NAME)).thenReturn(webTarget);

		Mockito.when(webTarget.queryParam(ArgumentMatchers.eq(SonarQubeQualityProfileGatewayClient.LANGUAGE),
				ArgumentMatchers.anyString())).thenReturn(webTarget);

		Mockito.when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(builder);

		Mockito.when(builder.post(null, Response.class)).thenReturn(response);

		Mockito.when(response.getStatus()).thenReturn(200);
		Mockito.when(response.getStatusInfo()).thenReturn(statusType);
		Mockito.when(statusType.getFamily()).thenReturn(Family.SUCCESSFUL);
		Mockito.when(response.hasEntity()).thenReturn(Boolean.FALSE);

		gatewayClient.addProject(GatewayModelBuilder.LANGUAGE_NAME, GatewayModelBuilder.PROJECT_KEY,
				GatewayModelBuilder.QUALITY_PROFILE_NAME);

	}

	@Test(expected = IllegalArgumentException.class)
	public void addProjectMissingProjectKey() throws SonarQubeGatewayException {
		gatewayClient.addProject(GatewayModelBuilder.LANGUAGE_NAME, null, GatewayModelBuilder.QUALITY_PROFILE_NAME);
	}

	@Test(expected = IllegalArgumentException.class)
	public void addProjectMissingQualityProfile() throws SonarQubeGatewayException {
		gatewayClient.addProject(GatewayModelBuilder.LANGUAGE_NAME, GatewayModelBuilder.PROJECT_KEY, null);
	}

	@Test
	public void removeProjectWithProjectKeyAndQualityProfileAndLangugage() throws SonarQubeGatewayException {
		Mockito.when(client.target(SonarQubeQualityProfileGatewayClient.REMOVE_PROJECT_URI)).thenReturn(webTarget);

		Mockito.when(
				webTarget.queryParam(SonarQubeQualityProfileGatewayClient.PROJECT_KEY, GatewayModelBuilder.PROJECT_KEY))
				.thenReturn(webTarget);

		Mockito.when(webTarget.queryParam(SonarQubeQualityProfileGatewayClient.QUALITY_PROFILE,
				GatewayModelBuilder.QUALITY_PROFILE_NAME)).thenReturn(webTarget);

		Mockito.when(webTarget.queryParam(ArgumentMatchers.eq(SonarQubeQualityProfileGatewayClient.LANGUAGE),
				ArgumentMatchers.anyString())).thenReturn(webTarget);

		Mockito.when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(builder);

		Mockito.when(builder.post(null, Response.class)).thenReturn(response);

		Mockito.when(response.getStatus()).thenReturn(200);
		Mockito.when(response.getStatusInfo()).thenReturn(statusType);
		Mockito.when(statusType.getFamily()).thenReturn(Family.SUCCESSFUL);
		Mockito.when(response.hasEntity()).thenReturn(Boolean.FALSE);

		gatewayClient.removeProject(GatewayModelBuilder.LANGUAGE_NAME, GatewayModelBuilder.PROJECT_KEY,
				GatewayModelBuilder.QUALITY_PROFILE_NAME);

	}

	@Test(expected = IllegalArgumentException.class)
	public void removeProjectMissingProjectKey() throws SonarQubeGatewayException {
		gatewayClient.removeProject(GatewayModelBuilder.LANGUAGE_NAME, null, GatewayModelBuilder.QUALITY_PROFILE_NAME);
	}

	@Test(expected = IllegalArgumentException.class)
	public void removeProjectMissingQualityProfile() throws SonarQubeGatewayException {
		gatewayClient.removeProject(GatewayModelBuilder.LANGUAGE_NAME, GatewayModelBuilder.PROJECT_KEY, null);
	}
}
