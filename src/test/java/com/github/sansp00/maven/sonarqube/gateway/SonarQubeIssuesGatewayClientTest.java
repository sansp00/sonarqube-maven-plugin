package com.github.sansp00.maven.sonarqube.gateway;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Arrays;

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
import com.github.sansp00.maven.sonarqube.gateway.model.IssueSeverities;
import com.github.sansp00.maven.sonarqube.gateway.model.IssueTypes;
import com.github.sansp00.maven.sonarqube.gateway.model.IssuesSearchResponse;

@RunWith(MockitoJUnitRunner.class)
public class SonarQubeIssuesGatewayClientTest {

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

	SonarQubeIssuesGatewayClient gatewayClient;

	@Before
	public void init() {
		gatewayClient = new SonarQubeIssuesGatewayClient("", client);
	}

	@Test
	public void searchWithProjectKeysAndTypesAndServerities() throws SonarQubeGatewayException {
		IssuesSearchResponse is = GatewayModelBuilder.buildIssuesSearch();

		Mockito.when(client.target(SonarQubeIssuesGatewayClient.SEARCH_URI)).thenReturn(webTarget);

		Mockito.when(webTarget.queryParam(SonarQubeIssuesGatewayClient.PROJECT_KEYS, GatewayModelBuilder.PROJECT_KEY))
				.thenReturn(webTarget);

		Mockito.when(webTarget.queryParam(ArgumentMatchers.eq(SonarQubeIssuesGatewayClient.SEVERITIES),
				ArgumentMatchers.anyString())).thenReturn(webTarget);

		Mockito.when(webTarget.queryParam(ArgumentMatchers.eq(SonarQubeIssuesGatewayClient.TYPES),
				ArgumentMatchers.anyString())).thenReturn(webTarget);

		Mockito.when(webTarget.queryParam(ArgumentMatchers.eq(SonarQubeIssuesGatewayClient.RESOLVED),
				ArgumentMatchers.anyString())).thenReturn(webTarget);

		// Defaults
		Mockito.when(webTarget.queryParam(ArgumentMatchers.eq(SonarQubeIssuesGatewayClient.SORT_FIELD),
				ArgumentMatchers.anyString())).thenReturn(webTarget);

		Mockito.when(webTarget.queryParam(ArgumentMatchers.eq(SonarQubeIssuesGatewayClient.ADDITIONAL_FIELDS),
				ArgumentMatchers.anyString())).thenReturn(webTarget);

		Mockito.when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(builder);
		Mockito.when(builder.get()).thenReturn(response);
		Mockito.when(response.getStatus()).thenReturn(200);
		Mockito.when(response.getStatusInfo()).thenReturn(statusType);
		Mockito.when(statusType.getFamily()).thenReturn(Family.SUCCESSFUL);
		Mockito.when(response.hasEntity()).thenReturn(Boolean.TRUE);
		Mockito.when(response.readEntity(IssuesSearchResponse.class)).thenReturn(is);

		IssuesSearchResponse issuesSearchResponse = gatewayClient.search(Arrays.asList(GatewayModelBuilder.PROJECT_KEY),
				null, Arrays.asList(IssueTypes.CODE_SMELL), Arrays.asList(IssueSeverities.MAJOR), true);

		assertFalse(issuesSearchResponse.getComponents().isEmpty());
		assertEquals(1, issuesSearchResponse.getComponents().size());
		assertEquals(GatewayModelBuilder.PROJECT_KEY, issuesSearchResponse.getComponents().get(0).getKey());
		assertFalse(issuesSearchResponse.getIssues().isEmpty());
		assertEquals(1, issuesSearchResponse.getIssues().size());
		assertEquals(GatewayModelBuilder.ISSUE_KEY, issuesSearchResponse.getIssues().get(0).getKey());
	}

	@Test
	public void searchWithComponentKeysAndTypesAndServerities() throws SonarQubeGatewayException {
		IssuesSearchResponse is = GatewayModelBuilder.buildIssuesSearch();

		Mockito.when(client.target(SonarQubeIssuesGatewayClient.SEARCH_URI)).thenReturn(webTarget);

		Mockito.when(
				webTarget.queryParam(SonarQubeIssuesGatewayClient.COMPONENT_KEYS, GatewayModelBuilder.COMPONENT_KEY))
				.thenReturn(webTarget);

		Mockito.when(webTarget.queryParam(ArgumentMatchers.eq(SonarQubeIssuesGatewayClient.SEVERITIES),
				ArgumentMatchers.anyString())).thenReturn(webTarget);

		Mockito.when(webTarget.queryParam(ArgumentMatchers.eq(SonarQubeIssuesGatewayClient.TYPES),
				ArgumentMatchers.anyString())).thenReturn(webTarget);

		Mockito.when(webTarget.queryParam(ArgumentMatchers.eq(SonarQubeIssuesGatewayClient.RESOLVED),
				ArgumentMatchers.anyString())).thenReturn(webTarget);

		// Defaults
		Mockito.when(webTarget.queryParam(ArgumentMatchers.eq(SonarQubeIssuesGatewayClient.ON_COMPONENT_ONLY),
				ArgumentMatchers.anyString())).thenReturn(webTarget);

		Mockito.when(webTarget.queryParam(ArgumentMatchers.eq(SonarQubeIssuesGatewayClient.SORT_FIELD),
				ArgumentMatchers.anyString())).thenReturn(webTarget);

		Mockito.when(webTarget.queryParam(ArgumentMatchers.eq(SonarQubeIssuesGatewayClient.ADDITIONAL_FIELDS),
				ArgumentMatchers.anyString())).thenReturn(webTarget);

		Mockito.when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(builder);
		Mockito.when(builder.get()).thenReturn(response);
		Mockito.when(response.getStatus()).thenReturn(200);
		Mockito.when(response.getStatusInfo()).thenReturn(statusType);
		Mockito.when(statusType.getFamily()).thenReturn(Family.SUCCESSFUL);
		Mockito.when(response.hasEntity()).thenReturn(Boolean.TRUE);
		Mockito.when(response.readEntity(IssuesSearchResponse.class)).thenReturn(is);

		IssuesSearchResponse issuesSearchResponse = gatewayClient.search(null,
				Arrays.asList(GatewayModelBuilder.COMPONENT_KEY), Arrays.asList(IssueTypes.CODE_SMELL),
				Arrays.asList(IssueSeverities.MAJOR), true);

		assertFalse(issuesSearchResponse.getComponents().isEmpty());
		assertEquals(1, issuesSearchResponse.getComponents().size());
		assertEquals(GatewayModelBuilder.PROJECT_KEY, issuesSearchResponse.getComponents().get(0).getKey());
		assertFalse(issuesSearchResponse.getIssues().isEmpty());
		assertEquals(1, issuesSearchResponse.getIssues().size());
		assertEquals(GatewayModelBuilder.ISSUE_KEY, issuesSearchResponse.getIssues().get(0).getKey());
	}

	@Test(expected = IllegalArgumentException.class)
	public void searchMissingRequiredComponentKeysAndProjectKeys() throws SonarQubeGatewayException {
		gatewayClient.search(null, null, Arrays.asList(IssueTypes.CODE_SMELL), Arrays.asList(IssueSeverities.MAJOR),
				null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void searchRequiredComponentKeysAndProjectKeys() throws SonarQubeGatewayException {
		gatewayClient.search(Arrays.asList(GatewayModelBuilder.PROJECT_KEY),
				Arrays.asList(GatewayModelBuilder.COMPONENT_KEY), Arrays.asList(IssueTypes.CODE_SMELL),
				Arrays.asList(IssueSeverities.MAJOR), null);
	}

	//
//	@Test
//	public void show() throws SonarQubeGatewayException {
//		ComponentShowResponse cs = GatewayModelBuilder.buildComponentShow();
//
//		Mockito.when(client.target(SonarQubeComponentsGatewayClient.SHOW_URI)).thenReturn(webTarget);
//
//		Mockito.when(
//				webTarget.queryParam(SonarQubeComponentsGatewayClient.COMPONENT_KEY, GatewayModelBuilder.COMPONENT_KEY))
//				.thenReturn(webTarget);
//
//		Mockito.when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(builder);
//		Mockito.when(builder.get()).thenReturn(response);
//		Mockito.when(response.getStatus()).thenReturn(200);
//		Mockito.when(response.getStatusInfo()).thenReturn(statusType);
//		Mockito.when(statusType.getFamily()).thenReturn(Family.SUCCESSFUL);
//		Mockito.when(response.hasEntity()).thenReturn(Boolean.TRUE);
//		Mockito.when(response.readEntity(ComponentShowResponse.class)).thenReturn(cs);
//
//		Optional<ComponentShowResponse> componentShowResponse = gatewayClient.show(GatewayModelBuilder.COMPONENT_KEY);
//
//		Mockito.verify(webTarget).queryParam(SonarQubeComponentsGatewayClient.COMPONENT_KEY,
//				GatewayModelBuilder.COMPONENT_KEY);
//
//		assertTrue(componentShowResponse.isPresent());
//		componentShowResponse.ifPresent(r -> {
//			assertEquals(GatewayModelBuilder.COMPONENT_KEY, r.getComponent().getKey());
//		});
//	}
//	
//	@Test(expected = IllegalArgumentException.class)
//	public void searchMissingRequiredComponentKey() throws SonarQubeGatewayException {
//		gatewayClient.show(null);
//	}

}
