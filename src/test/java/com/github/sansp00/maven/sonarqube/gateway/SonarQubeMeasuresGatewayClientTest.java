package com.github.sansp00.maven.sonarqube.gateway;

import java.time.LocalDate;
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
import com.github.sansp00.maven.sonarqube.gateway.model.MeasureComponentResponse;
import com.github.sansp00.maven.sonarqube.gateway.model.MeasureSearchHistoryResponse;

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

	SonarQubeMeasuresGatewayClient gatewayClient;

	@Before
	public void init() {
		gatewayClient = new SonarQubeMeasuresGatewayClient("", client);
	}

	@Test
	public void componentsWithComponentKeyAndMetricKeysAndAadditionalFields() throws SonarQubeGatewayException {

		Mockito.when(client.target(SonarQubeMeasuresGatewayClient.COMPONENT_URI)).thenReturn(webTarget);

		Mockito.when(
				webTarget.queryParam(SonarQubeMeasuresGatewayClient.METRIC_KEYS, GatewayModelBuilder.MEASURE_METRIC))
				.thenReturn(webTarget);
		Mockito.when(
				webTarget.queryParam(SonarQubeMeasuresGatewayClient.COMPONENT_KEY, GatewayModelBuilder.COMPONENT_KEY))
				.thenReturn(webTarget);

		Mockito.when(webTarget.queryParam(ArgumentMatchers.eq(SonarQubeMeasuresGatewayClient.ADDITIONAL_FIELDS),
				ArgumentMatchers.anyString())).thenReturn(webTarget);

		Mockito.when(
				webTarget.queryParam(SonarQubeMeasuresGatewayClient.COMPONENT_KEY, GatewayModelBuilder.COMPONENT_KEY))
				.thenReturn(webTarget);

		Mockito.when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(builder);
		Mockito.when(builder.get()).thenReturn(response);
		Mockito.when(response.getStatus()).thenReturn(200);
		Mockito.when(response.getStatusInfo()).thenReturn(statusType);
		Mockito.when(statusType.getFamily()).thenReturn(Family.SUCCESSFUL);
		Mockito.when(response.hasEntity()).thenReturn(Boolean.TRUE);
		Mockito.when(response.readEntity(MeasureComponentResponse.class))
				.thenReturn(GatewayModelBuilder.buildMeasureComponentResponse());

		gatewayClient.component(GatewayModelBuilder.COMPONENT_KEY, GatewayModelBuilder.MEASURE_METRIC_LIST,
				Arrays.asList("toto"));

		Mockito.verify(webTarget).queryParam(SonarQubeMeasuresGatewayClient.COMPONENT_KEY,
				GatewayModelBuilder.COMPONENT_KEY);
	}

	@Test(expected = IllegalArgumentException.class)
	public void createWithoutComponentKey() throws SonarQubeGatewayException {
		gatewayClient.component(null, GatewayModelBuilder.MEASURE_METRIC_LIST, Arrays.asList("toto"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void createWithoutMetricKeys() throws SonarQubeGatewayException {
		gatewayClient.component(GatewayModelBuilder.COMPONENT_KEY, null, Arrays.asList("toto"));
	}

	@Test
	public void searchHistoryWithComponentKeyAndMetricKeysAndDates() throws SonarQubeGatewayException {

		Mockito.when(client.target(SonarQubeMeasuresGatewayClient.SEARCH_HISTORY_URI)).thenReturn(webTarget);

		Mockito.when(webTarget.queryParam(SonarQubeMeasuresGatewayClient.METRICS, GatewayModelBuilder.MEASURE_METRIC))
				.thenReturn(webTarget);
		Mockito.when(
				webTarget.queryParam(SonarQubeMeasuresGatewayClient.COMPONENT_KEY, GatewayModelBuilder.COMPONENT_KEY))
				.thenReturn(webTarget);
		Mockito.when(webTarget.queryParam(ArgumentMatchers.eq(SonarQubeMeasuresGatewayClient.FROM),
				ArgumentMatchers.anyString())).thenReturn(webTarget);

		Mockito.when(webTarget.queryParam(ArgumentMatchers.eq(SonarQubeMeasuresGatewayClient.TO),
				ArgumentMatchers.anyString())).thenReturn(webTarget);

		Mockito.when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(builder);
		Mockito.when(builder.get()).thenReturn(response);
		Mockito.when(response.getStatus()).thenReturn(200);
		Mockito.when(response.getStatusInfo()).thenReturn(statusType);
		Mockito.when(statusType.getFamily()).thenReturn(Family.SUCCESSFUL);
		Mockito.when(response.hasEntity()).thenReturn(Boolean.TRUE);
		Mockito.when(response.readEntity(MeasureSearchHistoryResponse.class))
				.thenReturn(GatewayModelBuilder.buildMeasureSearchHistoryResponse());

		gatewayClient.searchHistory(GatewayModelBuilder.COMPONENT_KEY, GatewayModelBuilder.MEASURE_METRIC_LIST, LocalDate.now(),
				LocalDate.now());

		Mockito.verify(webTarget).queryParam(SonarQubeMeasuresGatewayClient.COMPONENT_KEY,
				GatewayModelBuilder.COMPONENT_KEY);
	}
	

	@Test(expected = IllegalArgumentException.class)
	public void searchHistoryWithoutComponentKey() throws SonarQubeGatewayException {
		gatewayClient.searchHistory(null, GatewayModelBuilder.MEASURE_METRIC_LIST, LocalDate.now(), LocalDate.now());
	}

	@Test(expected = IllegalArgumentException.class)
	public void searchHistoryWithoutMetricKeys() throws SonarQubeGatewayException {
		gatewayClient.searchHistory(GatewayModelBuilder.COMPONENT_KEY, null, LocalDate.now(), LocalDate.now());
	}
}
