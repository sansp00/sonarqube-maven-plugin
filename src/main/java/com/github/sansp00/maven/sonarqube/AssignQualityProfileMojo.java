package com.github.sansp00.maven.sonarqube;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import com.github.sansp00.maven.sonarqube.gateway.SonarQubeGatewayClient;
import com.github.sansp00.maven.sonarqube.gateway.exception.SonarQubeGatewayException;

@Mojo(name = "assign-profile", requiresProject = true)
public class AssignQualityProfileMojo extends AbstractProjectSonarQubeMojo {

	/*
	 * Quality profile
	 */
	@Parameter(property = "qualityProfile", required = true)
	private String qualityProfile;

	/*
	 * Project language
	 */
	@Parameter(property = "language", defaultValue = "java")
	private String language;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.maven.plugin.Mojo#execute()
	 */
	public void execute() throws MojoExecutionException {
		final SonarQubeGatewayClient client = buildClient();
		info(String.format("Assigning key '%s' to quality profile '%s' using language '%s'", getKey(),
				qualityProfile, language));

		try {
			client.qualityProfile().addProject(language, getKey(), qualityProfile);
			info("Assignment successfull");
		} catch (IllegalArgumentException e) {
			error("Assignment unsuccessfull, SonarQube client illegal parameter", e);
			throw new MojoExecutionException("Illegal parameters, unable to assign project to profile", e);
		} catch (SonarQubeGatewayException e) {
			error("Assignment unsuccessfull, SonarQube gateway client error", e);
			throw new MojoExecutionException("Gateway client error, unable to assign project to profile", e);
		}

	}

}
