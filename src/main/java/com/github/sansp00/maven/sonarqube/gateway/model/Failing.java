
package com.github.sansp00.maven.sonarqube.gateway.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Failing {

	@JsonProperty("key")
	private String key;

	@JsonProperty("name")
	private String name;

	@JsonProperty("branch")
	private String branch;

	@JsonProperty("key")
	public String getKey() {
		return key;
	}

	@JsonProperty("key")
	public void setKey(String key) {
		this.key = key;
	}

	public Failing withKey(String key) {
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

	public Failing withName(String name) {
		this.name = name;
		return this;
	}

	@JsonProperty("branch")
	public String getBranch() {
		return branch;
	}

	@JsonProperty("branch")
	public void setBranch(String branch) {
		this.branch = branch;
	}

	public Failing withBranch(String branch) {
		this.branch = branch;
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
