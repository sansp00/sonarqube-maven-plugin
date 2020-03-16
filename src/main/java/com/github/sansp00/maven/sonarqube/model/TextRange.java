package com.github.sansp00.maven.sonarqube.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TextRange {

	@JsonProperty("startLine")
	private Integer startLine;
	@JsonProperty("endLine")
	private Integer endLine;
	@JsonProperty("startOffset")
	private Integer startOffset;
	@JsonProperty("endOffset")
	private Integer endOffset;

	@JsonProperty("startLine")
	public Integer getStartLine() {
		return startLine;
	}

	@JsonProperty("startLine")
	public void setStartLine(Integer startLine) {
		this.startLine = startLine;
	}

	@JsonProperty("endLine")
	public Integer getEndLine() {
		return endLine;
	}

	@JsonProperty("endLine")
	public void setEndLine(Integer endLine) {
		this.endLine = endLine;
	}

	@JsonProperty("startOffset")
	public Integer getStartOffset() {
		return startOffset;
	}

	@JsonProperty("startOffset")
	public void setStartOffset(Integer startOffset) {
		this.startOffset = startOffset;
	}

	@JsonProperty("endOffset")
	public Integer getEndOffset() {
		return endOffset;
	}

	@JsonProperty("endOffset")
	public void setEndOffset(Integer endOffset) {
		this.endOffset = endOffset;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object other) {
		return EqualsBuilder.reflectionEquals(this, other);
	}
}
