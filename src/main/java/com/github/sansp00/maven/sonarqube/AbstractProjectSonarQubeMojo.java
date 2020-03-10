package com.github.sansp00.maven.sonarqube;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.maven.plugins.annotations.Parameter;

public abstract class AbstractProjectSonarQubeMojo extends AbstractSonarQubeMojo {

	/*
	 * Branch
	 */
	@Parameter(property = "branch", defaultValue = "master")
	private String branch;

	/*
	 * Suffix the key with the branch name
	 */
	@Parameter(property = "suffixBranchToKey", defaultValue = "true")
	private Boolean suffixBranchToKey;

	protected String getBranch() {
		return branch;
	}

	protected String getKey() {
		if (suffixBranchToKey) {
			return String.format("%s:%s:%s", getMavenProject().getGroupId(), getMavenProject().getArtifactId(), branch);
		}
		return String.format("%s:%s", getMavenProject().getGroupId(), getMavenProject().getArtifactId());
	}

	protected String getProjectUrl() {
		return getProjectUrl(getKey());
	}

	protected String getProjectUrl(final String projectKey) {
		try {
			return getUrl() + "dashboard?id=" + URLEncoder.encode(projectKey, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return getUrl() + "dashboard?id=" + projectKey + " [NOT ENCODED !!]";
		}
	}
}
