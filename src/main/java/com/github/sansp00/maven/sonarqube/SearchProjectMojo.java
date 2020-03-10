package com.github.sansp00.maven.sonarqube;

import java.util.Arrays;
import java.util.List;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;

import com.github.sansp00.maven.sonarqube.gateway.SonarQubeGatewayClient;
import com.github.sansp00.maven.sonarqube.gateway.exception.SonarQubeGatewayException;
import com.github.sansp00.maven.sonarqube.gateway.model.Project;
import com.github.sansp00.maven.sonarqube.gateway.model.ProjectSearchResponse;

@Mojo(name = "search-project", requiresProject = true)
public class SearchProjectMojo extends AbstractProjectSonarQubeMojo {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.maven.plugin.Mojo#execute()
	 */
	public void execute() throws MojoExecutionException {
		final SonarQubeGatewayClient client = buildClient();
		info(String.format("Searching key '%s'", getKey()));
		try {
			final ProjectSearchResponse response = client.project().search(null, Arrays.asList(getKey()));
			final List<Project> projects = response.getProjects();

			info(String.format("Search returned %s results", projects.size()));

			if (projects.isEmpty()) {
				error("Search unsuccessfull, SonarQube mojo illegal state");
				throw new MojoExecutionException("Illegal state, unable to get project");
			} else {
				if (!projects.isEmpty()) {
					projects.stream().forEach(p -> {
						outputProject(p);
					});
				}
			}
		} catch (IllegalArgumentException e) {
			error("Search unsuccessfull, SonarQube client illegal parameter", e);
			throw new MojoExecutionException("Illegal parameters, unable to get project", e);
		} catch (SonarQubeGatewayException e) {
			error("Search unsuccessfull, SonarQube gateway client error", e);
			throw new MojoExecutionException("Gateway client error, unable to get project", e);
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
