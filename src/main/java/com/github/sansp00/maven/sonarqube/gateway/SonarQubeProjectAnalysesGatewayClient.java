package com.github.sansp00.maven.sonarqube.gateway;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Consumer;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

import org.codehaus.plexus.util.StringUtils;

import com.github.sansp00.maven.sonarqube.gateway.exception.SonarQubeGatewayException;
import com.github.sansp00.maven.sonarqube.gateway.model.Analysis;
import com.github.sansp00.maven.sonarqube.gateway.model.Category;
import com.github.sansp00.maven.sonarqube.gateway.model.ProjectAnalysesSearchResponse;
import com.github.sansp00.maven.sonarqube.gateway.model.converter.DateTimeConverter;

public class SonarQubeProjectAnalysesGatewayClient {
	private final String baseUri;
	private final Client client;

	public static final String SEARCH_URI = "api/project_analyses/search";

	public static final String PROJECT_KEY = "project";
	public static final String EVENT_CATEGORY = "category";
	public static final String FROM = "from";
	public static final String TO = "to";

	public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public SonarQubeProjectAnalysesGatewayClient(final String baseUri, final Client client) {
		this.baseUri = baseUri;
		this.client = client;
	}

	public ProjectAnalysesSearchResponse search(final String projectKey, final Category category, final LocalDate from,
			final LocalDate to) throws SonarQubeGatewayException {
		// Required params
		if (StringUtils.isEmpty(projectKey)) {
			throw new IllegalArgumentException("Invalid parameter: 'projectKey'");
		}
		// Query
		WebTarget webTarget = client.target(baseUri + SEARCH_URI) //
				.queryParam(PROJECT_KEY, projectKey);

		// Optional params
		if (category != Category.UNDEFINED) {
			webTarget = webTarget.queryParam(EVENT_CATEGORY, category.getCode());
		}

		if (from != null) {
			webTarget = webTarget.queryParam(FROM, from.format(DATE_FORMAT));
		}

		if (to != null) {
			webTarget = webTarget.queryParam(TO, to.format(DATE_FORMAT));
		}

		// Call
		ProjectAnalysesSearchConsumer projectAnalysesSearchConsumer = new ProjectAnalysesSearchConsumer();
		SonarQubeGatewayResponseHandler.get(webTarget, ProjectAnalysesSearchResponse.class,
				projectAnalysesSearchConsumer);

		return projectAnalysesSearchConsumer.getProjectAnalysesSearchResponse();
	}

	class ProjectAnalysesSearchConsumer implements Consumer<Optional<ProjectAnalysesSearchResponse>> {
		ProjectAnalysesSearchResponse response = new ProjectAnalysesSearchResponse();

		@Override
		public void accept(Optional<ProjectAnalysesSearchResponse> projectAnalysesSearchResponse) {
			if (projectAnalysesSearchResponse.isPresent()) {
				response.getAnalyses().addAll(projectAnalysesSearchResponse.get().getAnalyses());
				response.getAdditionalProperties()
						.putAll(projectAnalysesSearchResponse.get().getAdditionalProperties());
			}
		}

		public ProjectAnalysesSearchResponse getProjectAnalysesSearchResponse() {
			Collections.sort(response.getAnalyses(), new AnalysisDateComparator());
			return response;
		}
	}

	class AnalysisDateComparator implements Comparator<Analysis> {
		final DateTimeConverter dateTimeConverter = new DateTimeConverter();

		@Override
		public int compare(Analysis left, Analysis right) {
//			final LocalDateTime leftDate = dateTimeConverter.toLocalDateTime(left.getDate());
//			final LocalDateTime rightDate = dateTimeConverter.toLocalDateTime(right.getDate());
			return left.getDate().compareTo(right.getDate());
		}

	}

}
