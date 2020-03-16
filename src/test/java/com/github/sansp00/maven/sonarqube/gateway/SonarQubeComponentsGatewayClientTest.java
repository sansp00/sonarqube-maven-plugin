package com.github.sansp00.maven.sonarqube.gateway;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;
import javax.ws.rs.core.Response.StatusType;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.github.sansp00.maven.sonarqube.gateway.exception.SonarQubeGatewayException;
import com.github.sansp00.maven.sonarqube.gateway.model.ComponentSearchResponse;
import com.github.sansp00.maven.sonarqube.gateway.model.ComponentShowResponse;

@RunWith(MockitoJUnitRunner.class)
public class SonarQubeComponentsGatewayClientTest {

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

	SonarQubeComponentsGatewayClient gatewayClient;

	@Before
	public void init() {
		gatewayClient = new SonarQubeComponentsGatewayClient("", client);
	}

	@Test
	public void search() throws SonarQubeGatewayException {
		ComponentSearchResponse cs = GatewayModelBuilder.buildComponentSearch();

		Mockito.when(client.target(SonarQubeComponentsGatewayClient.SEARCH_URI)).thenReturn(webTarget);

		Mockito.when(
				webTarget.queryParam(SonarQubeComponentsGatewayClient.COMPONENT_KEYWORD, GatewayModelBuilder.KEYWORD))
				.thenReturn(webTarget);

		Mockito.when(webTarget.queryParam(ArgumentMatchers.eq(SonarQubeComponentsGatewayClient.COMPONENT_QUALIFIERS),
				ArgumentMatchers.anyString())).thenReturn(webTarget);

		Mockito.when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(builder);
		Mockito.when(builder.get()).thenReturn(response);
		Mockito.when(response.getStatus()).thenReturn(200);
		Mockito.when(response.getStatusInfo()).thenReturn(statusType);
		Mockito.when(statusType.getFamily()).thenReturn(Family.SUCCESSFUL);
		Mockito.when(response.hasEntity()).thenReturn(Boolean.TRUE);
		Mockito.when(response.readEntity(ComponentSearchResponse.class)).thenReturn(cs);

		ComponentSearchResponse componentSearchResponse = gatewayClient.search(GatewayModelBuilder.KEYWORD,
				SonarQubeComponentsGatewayClient.DEFAULT_COMPONENT_QUALIFIERS);

		Mockito.verify(webTarget).queryParam(SonarQubeComponentsGatewayClient.COMPONENT_KEYWORD,
				GatewayModelBuilder.KEYWORD);

		assertFalse(componentSearchResponse.getComponents().isEmpty());
		assertEquals(1, componentSearchResponse.getComponents().size());
		assertEquals(GatewayModelBuilder.PROJECT_KEY, componentSearchResponse.getComponents().get(0).getKey());
	}

	@Test(expected = IllegalArgumentException.class)
	public void searchMissingRequiredComponentQualifiers() throws SonarQubeGatewayException {
		gatewayClient.search(GatewayModelBuilder.KEYWORD, null);
	}

	@Test
	public void show() throws SonarQubeGatewayException {
		ComponentShowResponse cs = GatewayModelBuilder.buildComponentShow();

		Mockito.when(client.target(SonarQubeComponentsGatewayClient.SHOW_URI)).thenReturn(webTarget);

		Mockito.when(
				webTarget.queryParam(SonarQubeComponentsGatewayClient.COMPONENT_KEY, GatewayModelBuilder.COMPONENT_KEY))
				.thenReturn(webTarget);

		Mockito.when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(builder);
		Mockito.when(builder.get()).thenReturn(response);
		Mockito.when(response.getStatus()).thenReturn(200);
		Mockito.when(response.getStatusInfo()).thenReturn(statusType);
		Mockito.when(statusType.getFamily()).thenReturn(Family.SUCCESSFUL);
		Mockito.when(response.hasEntity()).thenReturn(Boolean.TRUE);
		Mockito.when(response.readEntity(ComponentShowResponse.class)).thenReturn(cs);

		Optional<ComponentShowResponse> componentShowResponse = gatewayClient.show(GatewayModelBuilder.COMPONENT_KEY);

		Mockito.verify(webTarget).queryParam(SonarQubeComponentsGatewayClient.COMPONENT_KEY,
				GatewayModelBuilder.COMPONENT_KEY);

		assertTrue(componentShowResponse.isPresent());
		componentShowResponse.ifPresent(r -> {
			assertEquals(GatewayModelBuilder.COMPONENT_KEY, r.getComponent().getKey());
		});
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void searchMissingRequiredComponentKey() throws SonarQubeGatewayException {
		gatewayClient.show(null);
	}

}
