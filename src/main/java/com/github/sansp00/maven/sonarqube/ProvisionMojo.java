package com.github.sansp00.maven.sonarqube;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import com.github.sansp00.maven.sonarqube.gateway.SonarQubeGatewayClient;
import com.github.sansp00.maven.sonarqube.gateway.exception.SonarQubeGatewayException;
import com.github.sansp00.maven.sonarqube.gateway.model.ProjectSearchResponse;
import com.github.sansp00.maven.sonarqube.gateway.model.Visibility;

@Mojo(name = "provision", requiresProject = true)
public class ProvisionMojo extends AbstractProjectSonarQubeMojo {

	/*
	 * Project name
	 */
	@Parameter(property = "name", defaultValue = "${project.name}")
	private String name;
	/*
	 * Project branch
	 */
	@Parameter(property = "visibility", defaultValue = "PRIVATE")
	private Visibility visibility;

	/*
	 * Project language
	 */
	@Parameter(property = "language", defaultValue = "java")
	private String language;

	/*
	 * Quality profile
	 */
	@Parameter(property = "qualityProfile")
	private String qualityProfile;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.maven.plugin.Mojo#execute()
	 */
	public void execute() throws MojoExecutionException {
		final SonarQubeGatewayClient client = buildClient();
		info(String.format("Provisionning key '%s' with visibility '%s' and language '%s'", getKey(), visibility,
				language));
		try {
			final ProjectSearchResponse response = client.project().search(null, Arrays.asList(getKey()));

			if (response.getProjects().isEmpty()) {
				client.project().create(name, getKey(), getBranch(), visibility);
				info("Provision successfull");
			} else {
				info("Provision not required, project existed");
			}
		} catch (IllegalArgumentException e) {
			error("Provision unsuccessfull, SonarQube client illegal parameter", e);
			throw new MojoExecutionException("Illegal parameters, unable to provision project", e);
		} catch (SonarQubeGatewayException e) {
			error("Provision unsuccessfull, SonarQube gateway client error", e);
			throw new MojoExecutionException("Gateway client error, unable to provision project", e);
		}

		if (StringUtils.isNotEmpty(qualityProfile)) {
			info(String.format("Assigning key '%s' to quality profile '%s' using language '%s'", getKey(),
					qualityProfile, language));
			try {
				client.qualityProfile().addProject(language, getKey(), qualityProfile);
				info("Assignment successfull");
			} catch (IllegalArgumentException e) {
				error("Assignment unsuccessfull, SonarQube client illegal parameter", e);
				throw new MojoExecutionException("Illegal parameters, unable to assigned project to quality profile",
						e);
			} catch (SonarQubeGatewayException e) {
				error("Assignment unsuccessfull, SonarQube gateway client error", e);
				throw new MojoExecutionException("Gateway client error, unable to assigned project to quality profile",
						e);
			}
		}

	}

}
