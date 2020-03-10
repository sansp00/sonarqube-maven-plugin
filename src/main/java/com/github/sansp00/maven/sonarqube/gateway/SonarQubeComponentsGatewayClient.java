package com.github.sansp00.maven.sonarqube.gateway;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

import org.apache.commons.collections4.CollectionUtils;
import org.codehaus.plexus.util.StringUtils;

import com.github.sansp00.maven.sonarqube.gateway.exception.SonarQubeGatewayException;
import com.github.sansp00.maven.sonarqube.gateway.model.ComponentSearchResponse;
import com.github.sansp00.maven.sonarqube.gateway.model.ComponentShowResponse;

public class SonarQubeComponentsGatewayClient {
	private final String baseUri;
	private final Client client;

	public static final String SEARCH_URI = "api/components/search";
	public static final String SHOW_URI = "api/components/show";

	public static final String COMPONENT_LANGUAGE = "language";
	public static final String ONE_BASED_PAGE_NUMBER = "p";
	public static final String PAGE_SIZE = "ps";
	public static final String PROJECT_KEYS = "projects";
	public static final String COMPONENT_KEYWORD = "q";
	public static final String COMPONENT_QUALIFIERS = "qualifiers";
	public static final String COMPONENT_KEY = "component";

	public static final int DEFAULT_PAGE_SIZE = 100;
	public static final List<String> DEFAULT_COMPONENT_QUALIFIERS = Arrays.asList("TRK", "BRC");

	public SonarQubeComponentsGatewayClient(final String baseUri, final Client client) {
		this.baseUri = baseUri;
		this.client = client;

	}

	public ComponentSearchResponse search(final String keyword, final List<String> projectKeys)
			throws SonarQubeGatewayException {
		return search(keyword, projectKeys, DEFAULT_COMPONENT_QUALIFIERS);
	}

	public ComponentSearchResponse search(final String keyword, final List<String> projectKeys,
			final List<String> componentQualifiers) throws SonarQubeGatewayException {
		// Required params
		//TODO validate this
		if (CollectionUtils.isEmpty(componentQualifiers)) {
			throw new IllegalArgumentException("Invalid parameter: 'componentQualifiers'");
		}

		// Query
		WebTarget webTarget = client.target(baseUri + SEARCH_URI) //
				.queryParam(PROJECT_KEYS, StringUtils.join(projectKeys.listIterator(), ","));

		// Optional params
		if (CollectionUtils.isNotEmpty(projectKeys)) {
			webTarget = webTarget.queryParam(PROJECT_KEYS, StringUtils.join(projectKeys.listIterator(), ","));
		}
		if (StringUtils.isNotEmpty(keyword)) {
			webTarget = webTarget.queryParam(COMPONENT_KEYWORD, keyword);
		}
		//TODO validate this
		if (!CollectionUtils.isNotEmpty(componentQualifiers)) {
			webTarget = webTarget.queryParam(COMPONENT_QUALIFIERS,
					StringUtils.join(componentQualifiers.listIterator(), ","));
		}

		// Call
		ComponentSearchConsumer componentSearchConsumer = new ComponentSearchConsumer();
		SonarQubeGatewayResponseHandler.get(webTarget, ComponentSearchResponse.class, componentSearchConsumer);

		return componentSearchConsumer.getComponentSearchResponse();
	}

	public Optional<ComponentShowResponse> show(final String componentKey) throws SonarQubeGatewayException {
		// Required params
		if (StringUtils.isEmpty(componentKey)) {
			throw new IllegalArgumentException("Invalid parameter: 'componentKey'");
		}

		// Query
		WebTarget webTarget = client.target(baseUri + SHOW_URI)
				.queryParam(COMPONENT_KEY, componentKey);

		// Call
		ComponentShowConsumer componentShowConsumer = new ComponentShowConsumer();
		SonarQubeGatewayResponseHandler.get(webTarget, ComponentShowResponse.class, componentShowConsumer);

		return componentShowConsumer.getComponentShowResponse();

	}

	class ComponentSearchConsumer implements Consumer<Optional<ComponentSearchResponse>> {
		ComponentSearchResponse response = new ComponentSearchResponse();

		@Override
		public void accept(Optional<ComponentSearchResponse> componentSearchResponse) {
			if (componentSearchResponse.isPresent()) {
				response.getAdditionalProperties().putAll(componentSearchResponse.get().getAdditionalProperties());
				response.getComponents().addAll(componentSearchResponse.get().getComponents());
			}
		}

		public ComponentSearchResponse getComponentSearchResponse() {
			return response;
		}
	}

	class ComponentShowConsumer implements Consumer<Optional<ComponentShowResponse>> {
		Optional<ComponentShowResponse> response = Optional.empty();

		@Override
		public void accept(Optional<ComponentShowResponse> componentShowResponse) {
			response = componentShowResponse;
		}

		public Optional<ComponentShowResponse> getComponentShowResponse() {
			return response;
		}

	}
}
