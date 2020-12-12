package com.github.sansp00.maven.sonarqube.gateway;

import org.apache.maven.shared.utils.StringUtils;

import com.github.sansp00.maven.sonarqube.gateway.exception.SonarQubeGatewayClientException;
import com.github.sansp00.maven.sonarqube.gateway.exception.SonarQubeGatewayException;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class SonarQubeQualityProfileGatewayClient {
	private final String baseUri;
	private final Client client;

	public static final String ADD_PROJECT_URI = "api/qualityprofiles/add_project";
	public static final String REMOVE_PROJECT_URI = "api/qualityprofiles/remove_project";

	public static final String QUALITY_PROFILE = "qualityProfile";
	public static final String PROJECT_KEY = "project";
	public static final String LANGUAGE = "language";

	public SonarQubeQualityProfileGatewayClient(final String baseUri, final Client client) {
		this.baseUri = baseUri;
		this.client = client;
	}

	public void addProject(final String language, final String projectKey, final String qualityProfile)
			throws SonarQubeGatewayException {
		// Required params
		if (StringUtils.isBlank(projectKey)) {
			throw new IllegalArgumentException("Invalid parameter: 'projectKey'");
		}

		if (StringUtils.isBlank(qualityProfile)) {
			throw new IllegalArgumentException("Invalid parameter: 'qualityProfile'");
		}

		// Query
		WebTarget webTarget = client.target(baseUri + ADD_PROJECT_URI) //
				.queryParam(QUALITY_PROFILE, qualityProfile) //
				.queryParam(PROJECT_KEY, projectKey);

		// Optional params
		if (StringUtils.isNotBlank(language)) {
			webTarget = webTarget.queryParam(LANGUAGE, language);
		}

		// Call
		Response response = webTarget.request(MediaType.APPLICATION_JSON).post(null, Response.class);
		SonarQubeGatewayResponseHandler.handleResponse(response);
	}

	public void removeProject(final String language, final String projectKey, final String qualityProfile)
			throws SonarQubeGatewayClientException {
		// Required params
		if (StringUtils.isBlank(projectKey)) {
			throw new IllegalArgumentException("Invalid parameter: 'projectKey'");
		}

		if (StringUtils.isBlank(qualityProfile)) {
			throw new IllegalArgumentException("Invalid parameter: 'qualityProfile'");
		}

		// Query
		WebTarget webTarget = client.target(baseUri + REMOVE_PROJECT_URI) //
				.queryParam(QUALITY_PROFILE, qualityProfile) //
				.queryParam(PROJECT_KEY, projectKey);

		// Optional params
		if (StringUtils.isNotBlank(language)) {
			webTarget = webTarget.queryParam(LANGUAGE, language);
		}

		// Call
		Response response = webTarget.request(MediaType.APPLICATION_JSON).post(null, Response.class);
		SonarQubeGatewayResponseHandler.handleResponse(response);
	}
}
