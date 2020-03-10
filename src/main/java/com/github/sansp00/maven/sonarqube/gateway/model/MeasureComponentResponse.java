
package com.github.sansp00.maven.sonarqube.gateway.model;

import java.util.HashMap;
import java.util.List;
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
public class MeasureComponentResponse {

	@JsonProperty("component")
	private Component component;

	@JsonProperty("metrics")
	private List<Metric> metrics = null;

	@JsonProperty("periods")
	private List<Period> periods = null;

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("component")
	public Component getComponent() {
		return component;
	}

	@JsonProperty("component")
	public void setComponent(Component component) {
		this.component = component;
	}

	public MeasureComponentResponse withComponent(Component component) {
		this.component = component;
		return this;
	}

	@JsonProperty("metrics")
	public List<Metric> getMetrics() {
		return metrics;
	}

	@JsonProperty("metrics")
	public void setMetrics(List<Metric> metrics) {
		this.metrics = metrics;
	}

	public MeasureComponentResponse withMetrics(List<Metric> metrics) {
		this.metrics = metrics;
		return this;
	}

	@JsonProperty("periods")
	public List<Period> getPeriods() {
		return periods;
	}

	@JsonProperty("periods")
	public void setPeriods(List<Period> periods) {
		this.periods = periods;
	}

	public MeasureComponentResponse withPeriods(List<Period> periods) {
		this.periods = periods;
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
