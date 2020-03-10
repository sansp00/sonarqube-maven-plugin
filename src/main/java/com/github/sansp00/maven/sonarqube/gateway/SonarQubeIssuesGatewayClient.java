package com.github.sansp00.maven.sonarqube.gateway;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

import org.apache.commons.collections4.CollectionUtils;

import com.github.sansp00.maven.sonarqube.gateway.exception.SonarQubeGatewayException;
import com.github.sansp00.maven.sonarqube.gateway.model.IssueSeverities;
import com.github.sansp00.maven.sonarqube.gateway.model.IssueTypes;
import com.github.sansp00.maven.sonarqube.gateway.model.IssuesSearchResponse;
import com.github.sansp00.maven.sonarqube.gateway.model.SearchAdditionnalFields;
import com.github.sansp00.maven.sonarqube.gateway.model.SearchSortFields;

public class SonarQubeIssuesGatewayClient {
    private final String baseUri;
    private final Client client;

    public static final String SEARCH_ISSUES_URI = "api/issues/search";

    public static final String ADDITIONAL_FIELDS = "additionalFields";
    public static final String ASCENDING_SORT = "asc";
    public static final String ASSIGNED = "assigned";
    public static final String ASSIGNEES = "assignees";
    public static final String AUTHORS = "authors";
    public static final String COMPONENT_KEYS = "componentKeys";
    public static final String PROJECT_KEYS = "projectKeys";
    public static final String CREATED_AFTER = "createdAfter";
    public static final String CREATED_AT = "createdAt";
    public static final String CREATED_IN_LAST = "createdInLast";
    public static final String FACET_MODE = "facetMode";
    public static final String FACETS = "facets";
    public static final String ISSUES = "issues";
    public static final String ON_COMPONENT_ONLY = "onComponentOnly";
    public static final String RESOLUTIONS = "resolutions";
    public static final String RESOLVED = "resolved";
    public static final String RULES = "rules";
    public static final String SORT_FIELD = "s";
    public static final String SEVERITIES = "severities";
    public static final String SINCE_LEAK_PERIOD = "sinceLeakPeriod";
    public static final String STATUSES = "statuses";
    public static final String TAGS = "tags";
    public static final String TYPES = "types";

    public SonarQubeIssuesGatewayClient(final String baseUri, final Client client) {
        this.baseUri = baseUri;
        this.client = client;
    }

    public IssuesSearchResponse search(final List<String> projectKeys,
            final List<String> componentKeys,
            final List<IssueTypes> types,
            final List<IssueSeverities> severities,
            final Boolean resolved) throws SonarQubeGatewayException {

        // Required params
        if (CollectionUtils.isEmpty(projectKeys) && CollectionUtils.isEmpty(componentKeys)) {
            throw new IllegalArgumentException("Invalid parameters: 'componentKeys' or 'projectKeys'");
        }

        if (!CollectionUtils.isEmpty(projectKeys) && !CollectionUtils.isEmpty(componentKeys)) {
            throw new IllegalArgumentException("Invalid parameters: 'componentKeys' and 'projectKeys'");
        }

        // Query
        WebTarget webTarget = client.target(baseUri + SEARCH_ISSUES_URI);

        // Required mutually exclusive params
        if (!CollectionUtils.isEmpty(projectKeys)) {
            webTarget = webTarget.queryParam(PROJECT_KEYS, String.join(",", projectKeys));
        }
        if (!CollectionUtils.isEmpty(componentKeys)) {
            webTarget = webTarget.queryParam(COMPONENT_KEYS, String.join(",", componentKeys));
            webTarget = webTarget.queryParam(ON_COMPONENT_ONLY, "true");

        }

        // Optional params
        if (resolved != null) {
            webTarget = webTarget.queryParam(RESOLVED, String.valueOf(resolved));
        }

        if (severities != null && !severities.isEmpty()) {
            webTarget = webTarget.queryParam(SEVERITIES, severities.stream().map(s -> s.getCode()).collect(Collectors.joining(",")));
        }

        if (types != null && !types.isEmpty()) {
            webTarget = webTarget.queryParam(TYPES, types.stream().map(t -> t.getCode()).collect(Collectors.joining(",")));
        }

        webTarget = webTarget.queryParam(SORT_FIELD, SearchSortFields.FILE_LINE.getCode());
        webTarget = webTarget.queryParam(ADDITIONAL_FIELDS, SearchAdditionnalFields.ALL.getCode());

        // Call
        IssuesSearchConsumer issuesSearchConsumer = new IssuesSearchConsumer();
        SonarQubeGatewayResponseHandler.get(webTarget, IssuesSearchResponse.class, issuesSearchConsumer);

        return issuesSearchConsumer.getIssuesSearchResponse();
    }

    class IssuesSearchConsumer implements Consumer<Optional<IssuesSearchResponse>> {
        IssuesSearchResponse response = new IssuesSearchResponse();

        @Override
        public void accept(Optional<IssuesSearchResponse> issuesSearchResponse) {

            issuesSearchResponse.ifPresent(r -> {
                response.getIssues().addAll(r.getIssues());
                response.getAdditionalProperties().putAll(r.getAdditionalProperties());
                response.getComponents().addAll(r.getComponents());
                response.getRules().addAll(r.getRules());
                response.getUsers().addAll(r.getUsers());
            });
        }

        public IssuesSearchResponse getIssuesSearchResponse() {
            return response;
        }
    }
}
