package com.github.sansp00.maven.sonarqube;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import com.github.sansp00.maven.sonarqube.gateway.SonarQubeGatewayClient;
import com.github.sansp00.maven.sonarqube.gateway.exception.SonarQubeGatewayException;
import com.github.sansp00.maven.sonarqube.gateway.model.Visibility;

@Mojo(name = "update-visibility", requiresProject = true)
public class UpdateVisibilityMojo extends AbstractProjectSonarQubeMojo {
	/*
	 * Project branch
	 */
	@Parameter(property = "visibility", required = true)
	private Visibility visibility;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.maven.plugin.Mojo#execute()
	 */
	public void execute() throws MojoExecutionException {
		final SonarQubeGatewayClient client = buildClient();
		info(String.format("Updating key '%s' to visibility '%s'", getKey(), visibility));

		try {
			client.project().updateVisility(getKey(), visibility);
			info("Update successfull");
		} catch (IllegalArgumentException e) {
			error("Update unsuccessfull, SonarQube client illegal parameter", e);
			throw new MojoExecutionException("Illegal parameters, unable to update project visibility", e);
		} catch (SonarQubeGatewayException e) {
			error("Update unsuccessfull, SonarQube gateway client error", e);
			throw new MojoExecutionException("Gateway client error, unable to update project visibility", e);
		}
	}

}
