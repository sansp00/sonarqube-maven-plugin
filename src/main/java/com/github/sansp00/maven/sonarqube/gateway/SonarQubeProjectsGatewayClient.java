package com.github.sansp00.maven.sonarqube.gateway;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.maven.shared.utils.StringUtils;

import com.github.sansp00.maven.sonarqube.gateway.exception.SonarQubeGatewayException;
import com.github.sansp00.maven.sonarqube.gateway.model.ProjectCreateResponse;
import com.github.sansp00.maven.sonarqube.gateway.model.ProjectSearchResponse;
import com.github.sansp00.maven.sonarqube.gateway.model.Visibility;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class SonarQubeProjectsGatewayClient {
	private final String baseUri;
	private final Client client;

	public static final String SEARCH_MINE_URI = "api/projects/search_my_projects";
	public static final String SEARCH_URI = "api/projects/search";
	public static final String CREATE_URI = "api/projects/create";
	public static final String DELETE_URI = "api/projects/delete";
	public static final String UPDATE_VISIBILITY_URI = "api/projects/update_visibility";

	public static final String PROJECT_NAME = "name";
	public static final String PROJECT_KEY = "project";
	public static final String PROJECTS_KEY = "projects";
	public static final String PROJECT_BRANCH = "branch";
	public static final String PROJECT_VISIBILITY = "visibility";
	public static final String PROJECT_KEYWORD = "q";
	public static final String ANALYSED_BEFORE = "analyzedBefore";
	public static final String ON_PROVISIONED_ONLY = "onProvisionedOnly";
	public static final String PROJECT_QUALIFIERS = "qualifiers";

	public static final List<String> DEFAULT_COMPONENT_QUALIFIERS = Arrays.asList("TRK", "BRC");

	public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public SonarQubeProjectsGatewayClient(final String baseUri, final Client client) {
		this.baseUri = baseUri;
		this.client = client;
	}

	public ProjectSearchResponse search(final String keyword, final List<String> projectKeys)
			throws SonarQubeGatewayException {
		return search(keyword, projectKeys, null, null, null);
	}

	public ProjectSearchResponse search(final String keyword, final List<String> projectKeys,
			final List<String> projectQualifiers, final LocalDate analysedBefore, final Boolean onProvisionedOnly)
			throws SonarQubeGatewayException {
		// Query
		WebTarget webTarget = client.target(baseUri + SEARCH_URI);

		// Optional params
		if (CollectionUtils.isNotEmpty(projectKeys)) {
			webTarget = webTarget.queryParam(PROJECTS_KEY, StringUtils.join(projectKeys.listIterator(), ","));
		}
		if (StringUtils.isNotEmpty(keyword)) {
			webTarget = webTarget.queryParam(PROJECT_KEYWORD, keyword);
		}
		if (CollectionUtils.isNotEmpty(projectQualifiers)) {
			webTarget = webTarget.queryParam(PROJECT_QUALIFIERS, StringUtils.join(projectQualifiers.listIterator(), ","));
		}

		if (analysedBefore != null) {
			webTarget = webTarget.queryParam(ANALYSED_BEFORE, analysedBefore.format(DATE_FORMAT));
		}

		if (onProvisionedOnly != null) {
			webTarget = webTarget.queryParam(ON_PROVISIONED_ONLY, String.valueOf(onProvisionedOnly));
		}

		// Call
		ProjectSearchConsumer projectSearchConsumer = new ProjectSearchConsumer();
		SonarQubeGatewayResponseHandler.get(webTarget, ProjectSearchResponse.class, projectSearchConsumer);
		return projectSearchConsumer.getProjectSearchResponse();
	}

	class ProjectSearchConsumer implements Consumer<Optional<ProjectSearchResponse>> {
		ProjectSearchResponse response = new ProjectSearchResponse();

		@Override
		public void accept(Optional<ProjectSearchResponse> projectSearchResponse) {
			if (projectSearchResponse.isPresent()) {
				response.getProjects().addAll(projectSearchResponse.get().getProjects());
				response.getAdditionalProperties().putAll(projectSearchResponse.get().getAdditionalProperties());
			}
		}

		public ProjectSearchResponse getProjectSearchResponse() {
			return response;
		}
	}

	public ProjectSearchResponse searchMine() throws SonarQubeGatewayException {
		WebTarget webTarget = client.target(baseUri + SEARCH_MINE_URI);

		ProjectSearchConsumer projectSearchConsumer = new ProjectSearchConsumer();
		SonarQubeGatewayResponseHandler.get(webTarget, ProjectSearchResponse.class, projectSearchConsumer);
		return projectSearchConsumer.getProjectSearchResponse();
	}

	public Optional<ProjectCreateResponse> create(final String name, final String projectKey, final String branch,
			final Visibility visibility) throws SonarQubeGatewayException {
		// Required params
		if (StringUtils.isBlank(name)) {
			throw new IllegalArgumentException("Invalid parameter: 'name'");
		}

		if (StringUtils.isBlank(projectKey)) {
			throw new IllegalArgumentException("Invalid parameter: 'projectKey'");
		}

		// Query
		WebTarget webTarget = client.target(baseUri + CREATE_URI) //
				.queryParam(PROJECT_NAME, name) //
				.queryParam(PROJECT_KEY, projectKey); //

		// Optional params
		if (StringUtils.isNotBlank(branch)) {
			webTarget = webTarget.queryParam(PROJECT_BRANCH, branch);
		}

		// Optional params
		if (visibility != Visibility.UNDEFINED) {
			webTarget = webTarget.queryParam(PROJECT_VISIBILITY, visibility.getCode());
		}

		// Call
		Response response = webTarget.request(MediaType.APPLICATION_JSON).post(null, Response.class);
		return SonarQubeGatewayResponseHandler.handleResponse(response, ProjectCreateResponse.class);
	}

	public void delete(final String projectKey) throws SonarQubeGatewayException {
		// Required params
		if (StringUtils.isBlank(projectKey)) {
			throw new IllegalArgumentException("Invalid parameter: 'projectKey'");
		}

		// Query
		WebTarget webTarget = client.target(baseUri + DELETE_URI) //
				.queryParam(PROJECT_KEY, projectKey);

		// Call
		Response response = webTarget.request(MediaType.APPLICATION_JSON).post(null);
		SonarQubeGatewayResponseHandler.handleResponse(response);
	}

	public void updateVisility(final String projectKey, final Visibility visibility) throws SonarQubeGatewayException {
		// Required params
		if (StringUtils.isBlank(projectKey)) {
			throw new IllegalArgumentException("Invalid parameter: 'projectKey'");
		}

		if (visibility == null || visibility == Visibility.UNDEFINED) {
			throw new IllegalArgumentException("Invalid parameter: 'visibility'");
		}

		// Query
		WebTarget webTarget = client.target(baseUri + UPDATE_VISIBILITY_URI) //
				.queryParam(PROJECT_KEY, projectKey) //
				.queryParam(PROJECT_VISIBILITY, visibility.getCode());

		// Call
		Response response = webTarget.request(MediaType.APPLICATION_JSON).post(null, Response.class);
		SonarQubeGatewayResponseHandler.handleResponse(response);
	}
}
