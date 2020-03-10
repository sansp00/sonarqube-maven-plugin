
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
public class Metric {

	@JsonProperty("key")
	private String key;

	@JsonProperty("name")
	private String name;

	@JsonProperty("description")
	private String description;

	@JsonProperty("domain")
	private String domain;

	@JsonProperty("type")
	private String type;

	@JsonProperty("higherValuesAreBetter")
	private Boolean higherValuesAreBetter;

	@JsonProperty("qualitative")
	private Boolean qualitative;

	@JsonProperty("hidden")
	private Boolean hidden;

	@JsonProperty("custom")
	private Boolean custom;

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

	public Metric withKey(String key) {
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

	public Metric withName(String name) {
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

	public Metric withDescription(String description) {
		this.description = description;
		return this;
	}

	@JsonProperty("domain")
	public String getDomain() {
		return domain;
	}

	@JsonProperty("domain")
	public void setDomain(String domain) {
		this.domain = domain;
	}

	public Metric withDomain(String domain) {
		this.domain = domain;
		return this;
	}

	@JsonProperty("type")
	public String getType() {
		return type;
	}

	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}

	public Metric withType(String type) {
		this.type = type;
		return this;
	}

	@JsonProperty("higherValuesAreBetter")
	public Boolean getHigherValuesAreBetter() {
		return higherValuesAreBetter;
	}

	@JsonProperty("higherValuesAreBetter")
	public void setHigherValuesAreBetter(Boolean higherValuesAreBetter) {
		this.higherValuesAreBetter = higherValuesAreBetter;
	}

	public Metric withHigherValuesAreBetter(Boolean higherValuesAreBetter) {
		this.higherValuesAreBetter = higherValuesAreBetter;
		return this;
	}

	@JsonProperty("qualitative")
	public Boolean getQualitative() {
		return qualitative;
	}

	@JsonProperty("qualitative")
	public void setQualitative(Boolean qualitative) {
		this.qualitative = qualitative;
	}

	public Metric withQualitative(Boolean qualitative) {
		this.qualitative = qualitative;
		return this;
	}

	@JsonProperty("hidden")
	public Boolean getHidden() {
		return hidden;
	}

	@JsonProperty("hidden")
	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}

	public Metric withHidden(Boolean hidden) {
		this.hidden = hidden;
		return this;
	}

	@JsonProperty("custom")
	public Boolean getCustom() {
		return custom;
	}

	@JsonProperty("custom")
	public void setCustom(Boolean custom) {
		this.custom = custom;
	}

	public Metric withCustom(Boolean custom) {
		this.custom = custom;
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
