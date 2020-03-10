
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
public class Measure {

	@JsonProperty("metric")
	private String metric;

	@JsonProperty("value")
	private String value;

	@JsonProperty("periods")
	private List<Period> periods = null;

	@JsonProperty("history")
	private List<History> history = null;

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("metric")
	public String getMetric() {
		return metric;
	}

	@JsonProperty("metric")
	public void setMetric(String metric) {
		this.metric = metric;
	}

	public Measure withMetric(String metric) {
		this.metric = metric;
		return this;
	}

	public boolean isSetMetric() {
		return this.metric != null;
	}

	@JsonProperty("value")
	public String getValue() {
		return value;
	}

	@JsonProperty("value")
	public void setValue(String value) {
		this.value = value;
	}

	public Measure withValue(String value) {
		this.value = value;
		return this;
	}

	public boolean isSetValue() {
		return this.value != null;
	}

	@JsonProperty("periods")
	public List<Period> getPeriods() {
		return periods;
	}

	@JsonProperty("periods")
	public void setPeriods(List<Period> periods) {
		this.periods = periods;
	}

	public Measure withPeriods(List<Period> periods) {
		this.periods = periods;
		return this;
	}

	public boolean isSetPeriods() {
		return this.periods != null && !periods.isEmpty();
	}

	@JsonProperty("history")
	public List<History> getHistory() {
		return history;
	}

	@JsonProperty("history")
	public void setHistory(List<History> history) {
		this.history = history;
	}

	public Measure withHistory(List<History> history) {
		this.history = history;
		return this;
	}

	public boolean isSetHistory() {
		return this.history != null && !history.isEmpty();
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
