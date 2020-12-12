package com.github.sansp00.maven.sonarqube.gateway;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.github.sansp00.maven.sonarqube.gateway.exception.SonarQubeGatewayException;
import com.github.sansp00.maven.sonarqube.gateway.model.MeasureComponentResponse;
import com.github.sansp00.maven.sonarqube.gateway.model.MeasureSearchHistoryResponse;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.WebTarget;

public class SonarQubeMeasuresGatewayClient {
	private final String baseUri;
	private final Client client;

	public static final String COMPONENT_URI = "api/measures/component";
	public static final String SEARCH_HISTORY_URI = "api/measures/search_history";

	public static final String COMPONENT_KEY = "component";
	public static final String ADDITIONAL_FIELDS = "additionalFields";
	public static final String METRIC_KEYS = "metricKeys";
	public static final String METRICS = "metrics";
	public static final String FROM = "from";
	public static final String TO = "to";

	public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public SonarQubeMeasuresGatewayClient(final String baseUri, final Client client) {
		this.baseUri = baseUri;
		this.client = client;
	}

	public Optional<MeasureComponentResponse> component(final String componentKey, final List<String> metricKeys, final List<String> additionalFields)
			throws SonarQubeGatewayException {
		// Required params
		if (CollectionUtils.isEmpty(metricKeys)) {
			throw new IllegalArgumentException("Invalid parameter: 'metricKeys'");
		}

		if (StringUtils.isEmpty(componentKey)) {
			throw new IllegalArgumentException("Invalid parameter: 'componentKey'");
		}

		// Query
		WebTarget webTarget = client.target(baseUri + COMPONENT_URI) //
				.queryParam(COMPONENT_KEY, componentKey)
				.queryParam(METRIC_KEYS, String.join(",", metricKeys));

        // Optional params
        if (!CollectionUtils.isEmpty(additionalFields)) {
            webTarget = webTarget.queryParam(ADDITIONAL_FIELDS, String.join(",", additionalFields));
        }

		// Call
		MeasureResponseConsumer measureResponseConsumer = new MeasureResponseConsumer();
		SonarQubeGatewayResponseHandler.get(webTarget, MeasureComponentResponse.class, measureResponseConsumer);
		return measureResponseConsumer.getMeasureResponse();
	}

	public MeasureSearchHistoryResponse searchHistory(final String componentKey, final List<String> metrics,
			final LocalDate from, final LocalDate to) throws SonarQubeGatewayException {
		// Required params
		if (StringUtils.isEmpty(componentKey)) {
			throw new IllegalArgumentException("Invalid parameter: 'componentKey'");
		}

		if (CollectionUtils.isEmpty(metrics)) {
			throw new IllegalArgumentException("Invalid parameter: 'metrics'");
		}

		// Query
		WebTarget webTarget = client.target(baseUri + SEARCH_HISTORY_URI) //
				.queryParam(METRICS, String.join(",", metrics)) //
				.queryParam(COMPONENT_KEY, componentKey);

		// Optional params
		if (from != null) {
			webTarget = webTarget.queryParam(FROM, from.format(DATE_FORMAT));
		}

		if (to != null) {
			webTarget = webTarget.queryParam(TO, to.format(DATE_FORMAT));
		}

		// Call
		MeasureSearchHistoryResponseConsumer measureSearchHistoryResponseConsumer = new MeasureSearchHistoryResponseConsumer();
		SonarQubeGatewayResponseHandler.get(webTarget, MeasureSearchHistoryResponse.class,
				measureSearchHistoryResponseConsumer);
		return measureSearchHistoryResponseConsumer.getMeasureSearchHistoryResponse();
	}

	class MeasureResponseConsumer implements Consumer<Optional<MeasureComponentResponse>> {
		Optional<MeasureComponentResponse> response = Optional.empty();

		@Override
		public void accept(Optional<MeasureComponentResponse> measureResponse) {
			response = measureResponse;
		}

		public Optional<MeasureComponentResponse> getMeasureResponse() {
			return response;
		}

	}

	class MeasureSearchHistoryResponseConsumer implements Consumer<Optional<MeasureSearchHistoryResponse>> {
		MeasureSearchHistoryResponse response = new MeasureSearchHistoryResponse();

		@Override
		public void accept(Optional<MeasureSearchHistoryResponse> measureSearchHistoryResponse) {
			if (measureSearchHistoryResponse.isPresent()) {
			    response.setComponent(measureSearchHistoryResponse.get().getComponent());
				response.getMeasures().addAll(measureSearchHistoryResponse.get().getMeasures());
				response.getAdditionalProperties().putAll(measureSearchHistoryResponse.get().getAdditionalProperties());
			}

		}

		public MeasureSearchHistoryResponse getMeasureSearchHistoryResponse() {
			return response;
		}

	}

}
