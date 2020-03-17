package com.github.sansp00.maven.sonarqube.gateway;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.time.LocalDate;

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
import com.github.sansp00.maven.sonarqube.gateway.model.Category;
import com.github.sansp00.maven.sonarqube.gateway.model.ProjectAnalysesSearchResponse;

@RunWith(MockitoJUnitRunner.class)
public class SonarQubeProjectAnalysesGatewayClientTest {

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

	SonarQubeProjectAnalysesGatewayClient gatewayClient;

	@Before
	public void init() {
		gatewayClient = new SonarQubeProjectAnalysesGatewayClient("", client);
	}

	@Test
	public void searchWithProjectKeyCategoryAndDates() throws SonarQubeGatewayException {
		ProjectAnalysesSearchResponse psa = GatewayModelBuilder.buildProjectAnalysesSearch();

		Mockito.when(client.target(SonarQubeProjectAnalysesGatewayClient.SEARCH_URI)).thenReturn(webTarget);
		Mockito.when(webTarget.queryParam(SonarQubeProjectAnalysesGatewayClient.PROJECT_KEY,
				GatewayModelBuilder.PROJECT_KEY)).thenReturn(webTarget);
		Mockito.when(webTarget.queryParam(SonarQubeProjectAnalysesGatewayClient.EVENT_CATEGORY,
				Category.QUALITY_PROFILE.getCode())).thenReturn(webTarget);
		Mockito.when(webTarget.queryParam(ArgumentMatchers.eq(SonarQubeProjectAnalysesGatewayClient.FROM),
				ArgumentMatchers.anyString())).thenReturn(webTarget);
		Mockito.when(webTarget.queryParam(ArgumentMatchers.eq(SonarQubeProjectAnalysesGatewayClient.TO),
				ArgumentMatchers.anyString())).thenReturn(webTarget);

		
		Mockito.when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(builder);
		Mockito.when(builder.get()).thenReturn(response);
		Mockito.when(response.getStatus()).thenReturn(200);
		Mockito.when(response.getStatusInfo()).thenReturn(statusType);
		Mockito.when(statusType.getFamily()).thenReturn(Family.SUCCESSFUL);
		Mockito.when(response.hasEntity()).thenReturn(Boolean.TRUE);
		Mockito.when(response.readEntity(ProjectAnalysesSearchResponse.class)).thenReturn(psa);

		ProjectAnalysesSearchResponse projectAnalysesSearchResponse = gatewayClient
				.search(GatewayModelBuilder.PROJECT_KEY, Category.QUALITY_PROFILE, LocalDate.now(), LocalDate.now());

		Mockito.verify(webTarget).queryParam(SonarQubeProjectAnalysesGatewayClient.PROJECT_KEY,
				GatewayModelBuilder.PROJECT_KEY);

		assertFalse(projectAnalysesSearchResponse.getAnalyses().isEmpty());
		assertEquals(1, projectAnalysesSearchResponse.getAnalyses().size());
		assertEquals(GatewayModelBuilder.ANALISYS_KEY, projectAnalysesSearchResponse.getAnalyses().get(0).getKey());
	}

	@Test(expected = IllegalArgumentException.class)
	public void searchMissingRequiredProjectKey() throws SonarQubeGatewayException {
		gatewayClient.search(null, Category.QUALITY_PROFILE, null, null);
	}

	
}
