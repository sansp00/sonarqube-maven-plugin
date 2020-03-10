
package com.github.sansp00.maven.sonarqube.gateway.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Project {

	@JsonProperty("organization")
	private String organization;

	@JsonProperty("id")
	private String id;

	@JsonProperty("key")
	private String key;

	@JsonProperty("name")
	private String name;

	@JsonProperty("qualifier")
	private String qualifier;

	@JsonProperty("visibility")
	private String visibility;

	@JsonProperty("lastAnalysisDate")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
	private LocalDateTime lastAnalysisDate;

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("organization")
	public String getOrganization() {
		return organization;
	}

	@JsonProperty("organization")
	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public Project withOrganization(String organization) {
		this.organization = organization;
		return this;
	}

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	public Project withId(String id) {
		this.id = id;
		return this;
	}

	@JsonProperty("key")
	public String getKey() {
		return key;
	}

	@JsonProperty("key")
	public void setKey(String key) {
		this.key = key;
	}

	public Project withKey(String key) {
		this.key = key;
		return this;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	public Project withName(String name) {
		this.name = name;
		return this;
	}

	@JsonProperty("qualifier")
	public String getQualifier() {
		return qualifier;
	}

	public Qualifier getQualifierEnum() {
		return Qualifier.valueOfCode(qualifier);
	}

	@JsonProperty("qualifier")
	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}

	@JsonProperty("qualifier")
	public void setQualifier(Qualifier qualifier) {
		this.qualifier = qualifier.getCode();
	}

	public Project withQualifier(String qualifier) {
		this.qualifier = qualifier;
		return this;
	}

	public Project withQualifier(Qualifier qualifier) {
		this.qualifier = qualifier.getCode();
		return this;
	}

	@JsonProperty("visibility")
	public String getVisibility() {
		return visibility;
	}

	public Visibility getVisibilityEnum() {
		return Visibility.valueOfCode(visibility);
	}

	@JsonProperty("visibility")
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public void setVisibility(Visibility visibility) {
		this.visibility = visibility.getCode();
	}

	public Project withVisibility(String visibility) {
		this.visibility = visibility;
		return this;
	}

	public Project withVisibility(Visibility visibility) {
		this.visibility = visibility.getCode();
		return this;
	}

	@JsonProperty("lastAnalysisDate")
	public LocalDateTime getLastAnalysisDate() {
		return lastAnalysisDate;
	}

	@JsonProperty("lastAnalysisDate")
	public void setLastAnalysisDate(LocalDateTime lastAnalysisDate) {
		this.lastAnalysisDate = lastAnalysisDate;
	}

	public Project withLastAnalysisDate(LocalDateTime lastAnalysisDate) {
		this.lastAnalysisDate = lastAnalysisDate;
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

	public Project withAdditionalProperty(String name, Object value) {
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
