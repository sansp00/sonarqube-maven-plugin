
package com.github.sansp00.maven.sonarqube.gateway.model;

import java.time.LocalDateTime;
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
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Analysis {

	@JsonProperty("key")
	private String key;

	@JsonProperty("date")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
	private LocalDateTime date;

	@JsonProperty("events")
	private List<Event> events = new ArrayList<Event>();

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

	public Analysis withKey(String key) {
		this.key = key;
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

	public Analysis withDate(LocalDateTime date) {
		this.date = date;
		return this;
	}

	@JsonProperty("events")
	public List<Event> getEvents() {
		return events;
	}

	@JsonProperty("events")
	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public Analysis withEvents(List<Event> events) {
		this.events = events;
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

	public Analysis withAdditionalProperty(String name, Object value) {
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
