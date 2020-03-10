package com.github.sansp00.maven.sonarqube;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;

import com.github.sansp00.maven.sonarqube.gateway.SonarQubeGatewayClient;
import com.github.sansp00.maven.sonarqube.gateway.exception.SonarQubeGatewayException;

@Mojo(name = "delete", requiresProject = true)
public class DeleteMojo extends AbstractProjectSonarQubeMojo {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.maven.plugin.Mojo#execute()
	 */
	public void execute() throws MojoExecutionException {
		final SonarQubeGatewayClient client = buildClient();
		try {
			info(String.format("Deleting key '%s' ", getKey()));
			client.project().delete(getKey());
			info("Deletion successfull");
		} catch (IllegalArgumentException e) {
			error("Deletion unsuccessfull, SonarQube client illegal parameter", e);
			throw new MojoExecutionException("Illegal parameters, unable to delete project", e);
		} catch (SonarQubeGatewayException e) {
			error("Deletion unsuccessfull, SonarQube gateway client error", e);
			throw new MojoExecutionException("Gateway client error, unable to delete project", e);
		}
	}

}
