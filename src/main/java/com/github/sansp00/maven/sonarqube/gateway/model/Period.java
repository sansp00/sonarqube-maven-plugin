
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
public class Period {

	@JsonProperty("index")
	private Integer index;

	@JsonProperty("value")
	private String value;

	@JsonProperty("mode")
	private String mode;

	@JsonProperty("date")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
	private LocalDateTime date;

	@JsonProperty("parameter")
	private String parameter;

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("index")
	public Integer getIndex() {
		return index;
	}

	@JsonProperty("index")
	public void setIndex(Integer index) {
		this.index = index;
	}

	public Period withIndex(Integer index) {
		this.index = index;
		return this;
	}

	@JsonProperty("value")
	public String getValue() {
		return value;
	}

	@JsonProperty("value")
	public void setValue(String value) {
		this.value = value;
	}

	public Period withValue(String value) {
		this.value = value;
		return this;
	}

	@JsonProperty("mode")
	public String getMode() {
		return mode;
	}

	@JsonProperty("mode")
	public void setMode(String mode) {
		this.mode = mode;
	}

	public Period withMode(String mode) {
		this.mode = mode;
		return this;
	}

	@JsonProperty("date")
	public LocalDateTime getDate() {
		return date;
	}

	@JsonProperty("date")
	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Period withDate(LocalDateTime date) {
		this.date = date;
		return this;
	}

	@JsonProperty("parameter")
	public String getParameter() {
		return parameter;
	}

	@JsonProperty("parameter")
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public Period withParameter(String parameter) {
		this.parameter = parameter;
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
