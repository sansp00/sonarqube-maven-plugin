
package com.github.sansp00.maven.sonarqube.gateway.model;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Event {

	@JsonProperty("key")
	private String key;

	@JsonProperty("category")
	private Category category;

	@JsonProperty("name")
	private String name;

	@JsonProperty("description")
	private String description;

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("key")
	public String getKey() {
		return key;
	}

	@JsonProperty("key")
	public void setKey(String key) {
		this.key = key;
	}

	public Event withKey(String key) {
		this.key = key;
		return this;
	}

	@JsonProperty("category")
	public Category getCategory() {
		return category;
	}

	@JsonProperty("category")
	public void setCategory(Category category) {
		this.category = category;
	}

	public Event withCategory(Category category) {
		this.category = category;
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

	public Event withName(String name) {
		this.name = name;
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

	public Event withDescription(String description) {
		this.description = description;
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

	public Event withAdditionalProperty(String name, Object value) {
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
