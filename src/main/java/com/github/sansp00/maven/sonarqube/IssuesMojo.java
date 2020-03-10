package com.github.sansp00.maven.sonarqube;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import com.github.sansp00.maven.sonarqube.gateway.SonarQubeGatewayClient;
import com.github.sansp00.maven.sonarqube.gateway.exception.SonarQubeGatewayException;
import com.github.sansp00.maven.sonarqube.gateway.model.Component;
import com.github.sansp00.maven.sonarqube.gateway.model.Issue;
import com.github.sansp00.maven.sonarqube.gateway.model.IssuesSearchResponse;
import com.github.sansp00.maven.sonarqube.gateway.model.IssueSeverities;
import com.github.sansp00.maven.sonarqube.gateway.model.IssueTypes;

@Mojo(name = "issues", requiresProject = true)
public class IssuesMojo extends AbstractProjectSonarQubeMojo {

    @Parameter(property = "resolved", defaultValue = "false")
    private Boolean resolved;

    // Blocker, Critical, Major, Minor
    @Parameter(property = "severities")
    private List<String> severities;

    // Bug, Vulnerability, Code smell
    @Parameter(property = "types")
    private List<String> types;
    
    public void execute() throws MojoExecutionException {
        final SonarQubeGatewayClient client = buildClient();

        info(String.format("Retrieving issues for key '%s'", getKey()));
        try {
            final IssuesSearchResponse issuesSearchResponse = client.issues()
                    .search(Arrays.asList(getKey()),
                            null,
                            types.stream().map(t -> IssueTypes.valueOfCode(t)).collect(Collectors.toList()),
                            severities.stream().map(s -> IssueSeverities.valueOfCode(s)).collect(Collectors.toList()),
                            resolved);
            Optional<Component> projectComponent = getProjectComponent(issuesSearchResponse.getComponents());
            projectComponent.ifPresent(p -> {
                info("Retrieve successfull");
                // outputIssues(p,
                // filterIssueSeverity(issuesSearchResponse.getIssues(),
                // severities));
                outputIssues(p, issuesSearchResponse.getIssues());
            });

            if (!projectComponent.isPresent()) {
                error("Retrieve unsuccessfull, SonarQube mojo illegal state");
                throw new MojoExecutionException("Illegal state, unable to retrieve project measures");
            }
        } catch (IllegalArgumentException e) {
            getLog().error("SonarQube client illegal parameter", e);
            throw new MojoExecutionException("Illegal parameters, unable to retrieve project measures", e);
        } catch (SonarQubeGatewayException e) {
            getLog().error("SonarQube gateway client error", e);
            throw new MojoExecutionException("Gateway client error, unable to retrieve project measures", e);
        }

    }

    List<Issue> filterIssueSeverity(final List<Issue> issues, final List<String> severities) {
        if (severities != null && !severities.isEmpty()) {
            return issues.stream().filter(i -> StringUtils.equalsAnyIgnoreCase(i.getSeverity(), severities.toArray(new String[severities.size()])))
                    .collect(Collectors.toList());
        }

        return issues;
    }

    Optional<Component> getProjectComponent(List<Component> components) {
        return components.stream().filter(c -> StringUtils.equalsIgnoreCase("TRK", c.getQualifier())).findFirst();
    }

    void outputIssues(Component component, final List<Issue> issues) {
        info(String.format("+- key: '%s' [url: '%s']", component.getKey(), getProjectUrl(component.getKey())));
        info(String.format("|  issues [quantity: '%d']", issues.size()));

        issues.sort(Comparator.comparing(Issue::getComponent).thenComparing(Issue::getSeverity).thenComparing(Issue::getType));

        for (Issue issue : issues) {
            info(String.format("|  +- component [%s]", issue.getComponent()));
            info(String.format("|  | * issue [severity: '%s', type: '%s']", issue.getSeverity(), issue.getType()));
            info(String.format("|  | * rule [id: '%s', message: '%s']", issue.getRule(), issue.getMessage()));
            info(String.format("|  | * stamps [created: '%s', updated: '%s']", issue.getCreationDate(), issue.getUpdateDate()));
            info(String.format("|  | * effort [time: '%s']", issue.getEffort()));
            info(String.format("|  | * line [number: '%s', offset: '%s-%s']",
                               issue.getLine(),
                               issue.getTextRange().getStartOffset(),
                               issue.getTextRange().getEndOffset()));
        }

    }
}
