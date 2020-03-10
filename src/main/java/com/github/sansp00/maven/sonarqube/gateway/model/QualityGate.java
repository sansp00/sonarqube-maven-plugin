
package com.github.sansp00.maven.sonarqube.gateway.model;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QualityGate {

	@JsonProperty("status")
	private String status;

	@JsonProperty("stillFailing")
	private Boolean stillFailing;

	@JsonProperty("failing")
	private List<Failing> failing = null;

	@JsonProperty("status")
	public String getStatus() {
		return status;
	}

	@JsonProperty("status")
	public void setStatus(String status) {
		this.status = status;
	}

	public QualityGate withStatus(String status) {
		this.status = status;
		return this;
	}

	@JsonProperty("stillFailing")
	public Boolean getStillFailing() {
		return stillFailing;
	}

	@JsonProperty("stillFailing")
	public void setStillFailing(Boolean stillFailing) {
		this.stillFailing = stillFailing;
	}

	public QualityGate withStillFailing(Boolean stillFailing) {
		this.stillFailing = stillFailing;
		return this;
	}

	@JsonProperty("failing")
	public List<Failing> getFailing() {
		return failing;
	}

	@JsonProperty("failing")
	public void setFailing(List<Failing> failing) {
		this.failing = failing;
	}

	public QualityGate withFailing(List<Failing> failing) {
		this.failing = failing;
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
