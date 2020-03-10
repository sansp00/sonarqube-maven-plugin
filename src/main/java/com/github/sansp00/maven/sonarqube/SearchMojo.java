package com.github.sansp00.maven.sonarqube;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import com.github.sansp00.maven.sonarqube.gateway.SonarQubeGatewayClient;
import com.github.sansp00.maven.sonarqube.gateway.exception.SonarQubeGatewayException;
import com.github.sansp00.maven.sonarqube.gateway.model.Component;
import com.github.sansp00.maven.sonarqube.gateway.model.ComponentSearchResponse;

@Mojo(name = "search", requiresProject = false)
public class SearchMojo extends AbstractProjectSonarQubeMojo {

	/*
	 * Keyword
	 */
	@Parameter(property = "keyword")
	private String keyword;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.maven.plugin.Mojo#execute()
	 */
	public void execute() throws MojoExecutionException {
		final SonarQubeGatewayClient client = buildClient();
		info("Searching projects");
		if (StringUtils.isNotEmpty(keyword)) {
			info(String.format("Using keywork '%s'", keyword));
		}
		try {
			final ComponentSearchResponse response = client.component().search(keyword, null);
			List<Component> components = response.getComponents();

			info("Search successfull");
			info(String.format("Search returned %s results", components.size()));

			if (StringUtils.isNotEmpty(groupIdFilter)) {
				info("Filtering results by groupId");
				info(String.format("Using groupId filter '%s'", groupIdFilter));
				components = components.stream()
						.filter(c -> StringUtils.split(c.getKey(), ":")[0].matches(groupIdFilter))
						.collect(Collectors.toList());
				info(String.format("Filter returned %s results", components.size()));
			}

			if (StringUtils.isNotEmpty(artifactIdFilter)) {
				info("Filtering results by artifactId");
				info(String.format("Using artifactId filter '%s'", artifactIdFilter));
				components = components.stream()
						.filter(c -> StringUtils.split(c.getKey(), ":")[1].matches(artifactIdFilter))
						.collect(Collectors.toList());
				info(String.format("Filter returned %s results", components.size()));
			}

			info(String.format("Search returned %s filtered results", components.size()));
			if (!components.isEmpty()) {
				components.stream().forEach(c -> {
					outputComponent(c);
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

	void outputComponent(final Component component) {
		info(String.format("+- key: '%s' [url: '%s']", component.getKey(), getProjectUrl(component.getKey())));
		info(String.format("|  * name: '%s'", component.getName()));
		info(String.format("|  * qualifier: '%s'", component.getQualifier()));
		info(String.format("|  * visibility: '%s'", component.getVisibility()));
		info(String.format("|  * last analysis: '%s'", component.getLastAnalysisDate()));
	}

}
