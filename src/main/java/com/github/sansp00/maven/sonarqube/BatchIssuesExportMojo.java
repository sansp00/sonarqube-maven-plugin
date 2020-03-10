package com.github.sansp00.maven.sonarqube;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.shared.utils.io.FileUtils;
import org.mapstruct.factory.Mappers;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.sansp00.maven.sonarqube.gateway.SonarQubeGatewayClient;
import com.github.sansp00.maven.sonarqube.gateway.exception.SonarQubeGatewayException;
import com.github.sansp00.maven.sonarqube.gateway.model.IssueSeverities;
import com.github.sansp00.maven.sonarqube.gateway.model.IssueTypes;
import com.github.sansp00.maven.sonarqube.gateway.model.IssuesSearchResponse;
import com.github.sansp00.maven.sonarqube.gateway.model.mapper.IssueMapper;
import com.github.sansp00.maven.sonarqube.model.Issue;

@Mojo(name = "batch-issues-export", requiresProject = false)
public class BatchIssuesExportMojo extends AbstractSonarQubeMojo {

    @Parameter(property = "keys")
    private List<String> keys;

    @Parameter(property = "keysFile")
    private File keysFile;

    @Parameter(property = "resolved", defaultValue = "false")
    private Boolean resolved;

    // Blocker, Critical, Major, Minor
    @Parameter(property = "severities")
    private List<String> severities;

    // Bug, Vulnerability, Code smell
    @Parameter(property = "types")
    private List<String> types;

    @Parameter(property = "jsonOutputFile", defaultValue = "issues.json")
    private String jsonOutputFile;

    @Parameter(property = "encoding", defaultValue = "${project.reporting.outputEncoding}")
    private String encoding;

    public void execute() throws MojoExecutionException {
        final SonarQubeGatewayClient client = buildClient();

        try {
            prepare();
            info(String.format("Exporting issues"));

            info(String.format("Using keys '%s'", String.join(",", keys)));
            info(String.format("Retrieving issues for keys '%s'", String.join(",", keys)));

            final IssuesSearchResponse issuesSearchResponse = client.issues()
                    .search(keys,
                            null,
                            types.stream().map(t -> IssueTypes.valueOfCode(t)).collect(Collectors.toList()),
                            severities.stream().map(s -> IssueSeverities.valueOfCode(s)).collect(Collectors.toList()),
                            resolved);
            // final IssuesSearchResponse issuesSearchResponse =
            // client.issues().search(keys, null, resolved);

            if (!issuesSearchResponse.getComponents().isEmpty()) {
                info("Retrieve successfull");
                info(String.format("Exporting issues to '%s'", jsonOutputFile));
                export(issuesSearchResponse);
                info("Export successfull");
            } else {
                error("Retrieve unsuccessfull, SonarQube mojo illegal state");
            }
        } catch (IllegalArgumentException e) {
            error("Export unsuccessfull, SonarQube client illegal parameter", e);
            throw new MojoExecutionException("Illegal parameters, unable to export projects issues", e);
        } catch (SonarQubeGatewayException e) {
            error("Export unsuccessfull, SonarQube gateway client error", e);
            throw new MojoExecutionException("Gateway client error, unable to export projects issues", e);
        } catch (IOException e) {
            error("Export unsuccessfull, SonarQube mojo illegal state");
            throw new MojoExecutionException("Illegal state, unable to export issues", e);
        }
    }

    void prepare() throws IOException {
        if (CollectionUtils.isEmpty(keys) && keysFile == null) {
            throw new IllegalArgumentException("Missing required parameter 'keys' or 'keysFile'");
        }

        if (CollectionUtils.isEmpty(keys)) {
            keys = new ArrayList<>();
        }

        if (keysFile != null) {
            final ObjectMapper objectMapper = new ObjectMapper();
            keys.addAll(objectMapper.readValue(keysFile, new TypeReference<List<String>>() {
            }));
        }
    }

    void export(final IssuesSearchResponse issuesSearchResponse) throws JsonParseException, JsonMappingException, IOException {
        final IssueMapper mapper = Mappers.getMapper(IssueMapper.class);
        final List<Issue> issues = issuesSearchResponse.getIssues().stream().map(i -> mapper.map(i)).collect(Collectors.toList());
        final ObjectMapper objectMapper = new ObjectMapper();
        configureObjectMapper(objectMapper);
        FileUtils.fileWrite(jsonOutputFile, encoding, objectMapper.writeValueAsString(issues));
    }

    void configureObjectMapper(ObjectMapper objectMapper) {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.setVisibility(PropertyAccessor.ALL, Visibility.NONE);
        objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
    }
}
