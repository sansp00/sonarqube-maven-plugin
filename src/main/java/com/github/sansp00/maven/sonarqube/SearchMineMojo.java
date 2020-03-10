package com.github.sansp00.maven.sonarqube;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import com.github.sansp00.maven.sonarqube.gateway.SonarQubeGatewayClient;
import com.github.sansp00.maven.sonarqube.gateway.exception.SonarQubeGatewayException;
import com.github.sansp00.maven.sonarqube.gateway.model.Project;
import com.github.sansp00.maven.sonarqube.gateway.model.ProjectSearchResponse;

@Mojo(name = "search-mine", requiresProject = false)
public class SearchMineMojo extends AbstractProjectSonarQubeMojo {

    /*
     * GroupId Filter value
     */
    @Parameter(property = "groupIdFilter")
    private String groupIdFilter;

    /*
     * ArtifactId Filter value
     */
    @Parameter(property = "artifactIdFilter")
    private String artifactIdFilter;

    @Parameter(property = "branchFilter")
    private String branchFilter;

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.maven.plugin.Mojo#execute()
     */
    public void execute() throws MojoExecutionException {
        final SonarQubeGatewayClient client = buildClient();
        info("Searching project associated with current user");
        try {
            final ProjectSearchResponse projectSearchResponse = client.project().searchMine();

            List<Project> projects = projectSearchResponse.getProjects();

            info("Search successfull");
            info(String.format("Search returned %s results", projects.size()));

            if (StringUtils.isNotEmpty(groupIdFilter)) {
                info("Filtering results by groupId");
                info(String.format("Using groupId filter '%s'", groupIdFilter));
                projects = projects.stream().filter(c -> StringUtils.split(c.getKey(), ":")[0].matches(groupIdFilter)).collect(Collectors.toList());
                info(String.format("Filter returned %s results", projects.size()));
            }

            if (StringUtils.isNotEmpty(artifactIdFilter)) {
                info("Filtering results by artifactId");
                info(String.format("Using artifactId filter '%s'", artifactIdFilter));
                projects = projects.stream().filter(c -> StringUtils.split(c.getKey(), ":")[1].matches(artifactIdFilter))
                        .collect(Collectors.toList());
                info(String.format("Filter returned %s results", projects.size()));
            }

            if (StringUtils.isNotEmpty(branchFilter)) {
                info("Filtering results by branch");
                info(String.format("Using branch filter '%s'", branchFilter));
                projects = projects.stream().filter(c -> {
                    final String[] elements = StringUtils.split(c.getKey(), ":");
                    if( elements.length > 2 && elements[2].matches(branchFilter) ) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());
                info(String.format("Filter returned %s results", projects.size()));
            }

            info(String.format("Search returned %s filtered results", projects.size()));
            if (!projects.isEmpty()) {
                projects.stream().forEach(p -> {
                    outputProject(p);
                });
            }
        } catch (IllegalArgumentException e) {
            error("Search unsuccessfull, SonarQube client illegal parameter", e);
            throw new MojoExecutionException("Illegal parameters, unable to search my projects", e);
        } catch (SonarQubeGatewayException e) {
            error("MojoExecutionException unsuccessfull, SonarQube gateway client error", e);
            throw new MojoExecutionException("Gateway client error, unable to search my projects", e);
        }
    }

    void outputProject(final Project project) {
        info(String.format("+- key: '%s' [url: '%s']", project.getKey(), getProjectUrl(project.getKey())));
        info(String.format("|  * name: '%s'", project.getName()));
        info(String.format("|  * qualifier: '%s'", project.getQualifier()));
        info(String.format("|  * visibility: '%s'", project.getVisibility()));
        info(String.format("|  * last analysis: '%s'", project.getLastAnalysisDate()));
    }

}
