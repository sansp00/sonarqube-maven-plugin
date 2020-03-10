package com.github.sansp00.maven.sonarqube.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Attr {
	@JsonProperty("jira-issue-key")
	private String jiraIssueKey;

	@JsonProperty("jira-issue-key")
	public String getJiraIssueKey() {
		return jiraIssueKey;
	}

	@JsonProperty("jira-issue-key")
	public void setJiraIssueKey(String jiraIssueKey) {
		this.jiraIssueKey = jiraIssueKey;
	}

}
