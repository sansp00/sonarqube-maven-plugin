package com.github.sansp00.maven.sonarqube.gateway.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
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
public class Ancestor {

	@JsonProperty("organization")
	private String organization;

	@JsonProperty("id")
	private String id;

	@JsonProperty("key")
	private String key;

	@JsonProperty("name")
	private String name;

	@JsonProperty("qualifier")
	private Qualifier qualifier;

	@JsonProperty("path")
	private String path;

	@JsonProperty("analysisDate")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
	private LocalDateTime analysisDate;

	@JsonProperty("version")
	private String version;

	@JsonProperty("description")
	private String description;

	@JsonProperty("tags")
	private List<String> tags = null;

	@JsonProperty("visibility")
	private Visibility visibility;

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

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	public Ancestor withId(String id) {
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

	public Ancestor withKey(String key) {
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

	public Ancestor withName(String name) {
		this.name = name;
		return this;
	}

	@JsonProperty("qualifier")
	public Qualifier getQualifier() {
		return qualifier;
	}

	@JsonProperty("qualifier")
	public void setQualifier(Qualifier qualifier) {
		this.qualifier = qualifier;
	}

	public Ancestor withQualifier(Qualifier qualifier) {
		this.qualifier = qualifier;
		return this;
	}

	@JsonProperty("path")
	public String getPath() {
		return path;
	}

	@JsonProperty("path")
	public void setPath(String path) {
		this.path = path;
	}

	public Ancestor withPath(String path) {
		this.path = path;
		return this;
	}

	@JsonProperty("analysisDate")
	public LocalDateTime getAnalysisDate() {
		return analysisDate;
	}

	@JsonProperty("analysisDate")
	public void setAnalysisDate(LocalDateTime analysisDate) {
		this.analysisDate = analysisDate;
	}

	public Ancestor withAnalysisDate(LocalDateTime analysisDate) {
		this.analysisDate = analysisDate;
		return this;
	}

	@JsonProperty("version")
	public String getVersion() {
		return version;
	}

	@JsonProperty("version")
	public void setVersion(String version) {
		this.version = version;
	}

	public Ancestor withVersion(String version) {
		this.version = version;
		return this;
	}

	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	@JsonProperty("description")
	public void setDescription(String description) {
		this.description = description;
	}

	public Ancestor withDescription(String description) {
		this.description = description;
		return this;
	}

	@JsonProperty("tags")
	public List<String> getTags() {
		return tags;
	}

	@JsonProperty("tags")
	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public Ancestor withTags(List<String> tags) {
		this.tags = tags;
		return this;
	}

	@JsonProperty("visibility")
	public Visibility getVisibility() {
		return visibility;
	}

	@JsonProperty("visibility")
	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}

	public Ancestor withVisibility(Visibility visibility) {
		this.visibility = visibility;
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
