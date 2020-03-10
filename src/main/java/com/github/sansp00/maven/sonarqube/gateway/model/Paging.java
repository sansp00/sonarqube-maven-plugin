
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
public class Paging {

	@JsonProperty("pageIndex")
	private Integer pageIndex;

	@JsonProperty("pageSize")
	private Integer pageSize;

	@JsonProperty("total")
	private Integer total;

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("pageIndex")
	public Integer getPageIndex() {
		return pageIndex;
	}

	@JsonProperty("pageIndex")
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Paging withPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
		return this;
	}

	@JsonProperty("pageSize")
	public Integer getPageSize() {
		return pageSize;
	}

	@JsonProperty("pageSize")
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Paging withPageSize(Integer pageSize) {
		this.pageSize = pageSize;
		return this;
	}

	@JsonProperty("total")
	public Integer getTotal() {
		return total;
	}

	@JsonProperty("total")
	public void setTotal(Integer total) {
		this.total = total;
	}

	public Paging withTotal(Integer total) {
		this.total = total;
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

	public Paging withAdditionalProperty(String name, Object value) {
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
