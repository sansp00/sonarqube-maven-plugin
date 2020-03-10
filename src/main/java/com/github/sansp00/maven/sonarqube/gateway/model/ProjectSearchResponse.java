
package com.github.sansp00.maven.sonarqube.gateway.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectSearchResponse implements PagingResponse {

	@JsonProperty("paging")
	private Paging paging;

	@JsonAlias({ "components" })
	@JsonProperty("projects")
	private List<Project> projects = new ArrayList<Project>();

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("paging")
	public Paging getPaging() {
		return paging;
	}

	@JsonProperty("paging")
	public void setPaging(Paging paging) {
		this.paging = paging;
	}

	public ProjectSearchResponse withPaging(Paging paging) {
		this.paging = paging;
		return this;
	}

	@JsonAlias({ "components" })
	@JsonProperty("projects")
	public List<Project> getProjects() {
		return projects;
	}

	@JsonAlias({ "components" })
	@JsonProperty("projects")
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public ProjectSearchResponse withProjects(List<Project> projects) {
		this.projects = projects;
		return this;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public ProjectSearchResponse withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, "additionalProperties");
	}

	@Override
	public boolean equals(Object other) {
		return EqualsBuilder.reflectionEquals(this, other, "additionalProperties");
	}

}
