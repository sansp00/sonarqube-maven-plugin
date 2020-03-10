
package com.github.sansp00.maven.sonarqube.gateway.model;

import java.util.ArrayList;
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
public class MeasureSearchHistoryResponse implements PagingResponse {

	@JsonProperty("paging")
	private Paging paging;

	@JsonProperty("component")
	private Component component;

	@JsonProperty("measures")
	private List<Measure> measures = new ArrayList<>();

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

	public MeasureSearchHistoryResponse withComponent(Component component) {
		this.component = component;
		return this;
	}

	@JsonProperty("measures")
	public List<Measure> getMeasures() {
		return measures;
	}

	@JsonProperty("measures")
	public void setMeasures(List<Measure> measures) {
		this.measures = measures;
	}

	public MeasureSearchHistoryResponse withMeasures(List<Measure> measures) {
		this.measures = measures;
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

	public MeasureSearchHistoryResponse withPeriods(List<Period> periods) {
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

	@JsonProperty("paging")
	public Paging getPaging() {
		return paging;
	}

	@JsonProperty("paging")
	public void setPaging(Paging paging) {
		this.paging = paging;
	}

	public MeasureSearchHistoryResponse withPaging(Paging paging) {
		this.paging = paging;
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
