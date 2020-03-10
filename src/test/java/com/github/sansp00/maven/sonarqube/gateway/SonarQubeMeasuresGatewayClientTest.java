package com.github.sansp00.maven.sonarqube.gateway;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Optional;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;
import javax.ws.rs.core.Response.StatusType;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.github.sansp00.maven.sonarqube.gateway.exception.SonarQubeGatewayException;
import com.github.sansp00.maven.sonarqube.gateway.model.ProjectCreateResponse;
import com.github.sansp00.maven.sonarqube.gateway.model.ProjectSearchResponse;
import com.github.sansp00.maven.sonarqube.gateway.model.Visibility;

@RunWith(MockitoJUnitRunner.class)
public class SonarQubeMeasuresGatewayClientTest {

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

	SonarQubeProjectsGatewayClient gatewayClient;

	@Before
	public void init() {
		gatewayClient = new SonarQubeProjectsGatewayClient("", client);
	}
	
	@Test
	public void search() throws SonarQubeGatewayException {
		ProjectSearchResponse ps = GatewayModelBuilder.buildProjectSearch();

		Mockito.when(client.target(SonarQubeProjectsGatewayClient.SEARCH_URI)).thenReturn(webTarget);
		Mockito.when(webTarget.queryParam(SonarQubeProjectsGatewayClient.PROJECTS_KEY, GatewayModelBuilder.PROJECT_KEY)).thenReturn(webTarget);
		Mockito.when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(builder);
		Mockito.when(builder.get()).thenReturn(response);
		Mockito.when(response.getStatus()).thenReturn(200);
		Mockito.when(response.getStatusInfo()).thenReturn(statusType);
		Mockito.when(statusType.getFamily()).thenReturn(Family.SUCCESSFUL);
		Mockito.when(response.hasEntity()).thenReturn(Boolean.TRUE);
		Mockito.when(response.readEntity(ProjectSearchResponse.class)).thenReturn(ps);

		ProjectSearchResponse projectSearchResponse = gatewayClient.search(null, Arrays.asList(GatewayModelBuilder.PROJECT_KEY));

		Mockito.verify(webTarget).queryParam(SonarQubeProjectsGatewayClient.PROJECTS_KEY, GatewayModelBuilder.PROJECT_KEY);

		assertFalse(projectSearchResponse.getProjects().isEmpty());
		Assert.assertEquals(GatewayModelBuilder.PROJECT_NAME, projectSearchResponse.getProjects().stream().findFirst().get().getName());
	}

	@Test
	public void create() throws SonarQubeGatewayException {
		ProjectCreateResponse pcr = new ProjectCreateResponse();
		pcr.setProject(GatewayModelBuilder.buildProject());

		Mockito.when(client.target(SonarQubeProjectsGatewayClient.CREATE_URI)).thenReturn(webTarget);
		Mockito.when(webTarget.queryParam(SonarQubeProjectsGatewayClient.PROJECT_NAME, GatewayModelBuilder.PROJECT_NAME)).thenReturn(webTarget);
		Mockito.when(webTarget.queryParam(SonarQubeProjectsGatewayClient.PROJECT_KEY, GatewayModelBuilder.PROJECT_KEY)).thenReturn(webTarget);
		Mockito.when(webTarget.queryParam(SonarQubeProjectsGatewayClient.PROJECT_BRANCH, GatewayModelBuilder.PROJECT_BRANCH)).thenReturn(webTarget);
		Mockito.when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(builder);
		Mockito.when(builder.post(null, Response.class)).thenReturn(response);
		Mockito.when(response.getStatus()).thenReturn(200);
		Mockito.when(response.getStatusInfo()).thenReturn(statusType);
		Mockito.when(statusType.getFamily()).thenReturn(Family.SUCCESSFUL);
		Mockito.when(response.hasEntity()).thenReturn(Boolean.TRUE);
		Mockito.when(response.readEntity(ProjectCreateResponse.class)).thenReturn(pcr);

		Optional<ProjectCreateResponse> projectCreateResponse = gatewayClient.create(GatewayModelBuilder.PROJECT_NAME, GatewayModelBuilder.PROJECT_KEY, GatewayModelBuilder.PROJECT_BRANCH, Visibility.UNDEFINED);

		Mockito.verify(webTarget).queryParam(SonarQubeProjectsGatewayClient.PROJECT_NAME, GatewayModelBuilder.PROJECT_NAME);
		Mockito.verify(webTarget).queryParam(SonarQubeProjectsGatewayClient.PROJECT_KEY, GatewayModelBuilder.PROJECT_KEY);
		Mockito.verify(webTarget).queryParam(SonarQubeProjectsGatewayClient.PROJECT_BRANCH, GatewayModelBuilder.PROJECT_BRANCH);

		assertTrue(projectCreateResponse.isPresent());
		Assert.assertEquals(GatewayModelBuilder.PROJECT_NAME, projectCreateResponse.get().getProject().getName());
	}

	@Test
	public void delete() throws SonarQubeGatewayException {
		Mockito.when(client.target(SonarQubeProjectsGatewayClient.DELETE_URI)).thenReturn(webTarget);
		Mockito.when(webTarget.queryParam(SonarQubeProjectsGatewayClient.PROJECT_KEY, GatewayModelBuilder.PROJECT_KEY)).thenReturn(webTarget);
		Mockito.when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(builder);
		Mockito.when(builder.post(null)).thenReturn(response);
		
		Mockito.when(response.getStatus()).thenReturn(200);
		Mockito.when(response.getStatusInfo()).thenReturn(statusType);
		Mockito.when(statusType.getFamily()).thenReturn(Family.SUCCESSFUL);
		Mockito.when(response.hasEntity()).thenReturn(Boolean.FALSE);

		gatewayClient.delete(GatewayModelBuilder.PROJECT_KEY);

		Mockito.verify(webTarget).queryParam(SonarQubeProjectsGatewayClient.PROJECT_KEY, GatewayModelBuilder.PROJECT_KEY);

	}

	@Test
	public void updateVisitlity() throws SonarQubeGatewayException {
		Mockito.when(client.target(SonarQubeProjectsGatewayClient.UPDATE_VISIBILITY_URI)).thenReturn(webTarget);
		Mockito.when(webTarget.queryParam(SonarQubeProjectsGatewayClient.PROJECT_KEY, GatewayModelBuilder.PROJECT_KEY)).thenReturn(webTarget);
		Mockito.when(webTarget.queryParam(SonarQubeProjectsGatewayClient.PROJECT_VISIBILITY, Visibility.PUBLIC.getCode())).thenReturn(webTarget);
		Mockito.when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(builder);
		Mockito.when(builder.post(null, Response.class)).thenReturn(response);
		
		Mockito.when(response.getStatus()).thenReturn(200);
		Mockito.when(response.getStatusInfo()).thenReturn(statusType);
		Mockito.when(statusType.getFamily()).thenReturn(Family.SUCCESSFUL);
		Mockito.when(response.hasEntity()).thenReturn(Boolean.FALSE);

		gatewayClient.updateVisility(GatewayModelBuilder.PROJECT_KEY, Visibility.PUBLIC);

		Mockito.verify(webTarget).queryParam(SonarQubeProjectsGatewayClient.PROJECT_KEY, GatewayModelBuilder.PROJECT_KEY);

	}

	
}
