package com.github.sansp00.maven.sonarqube.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Comment {
	@JsonProperty("key")
	private String key;
	@JsonProperty("login")
	private String login;
	@JsonProperty("htmlText")
	private String htmlText;
	@JsonProperty("markdown")
	private String markdown;
	@JsonProperty("updatable")
	private Boolean updatable;
	@JsonProperty("createdAt")
	private String createdAt;

	@JsonProperty("key")
	public String getKey() {
		return key;
	}

	@JsonProperty("key")
	public void setKey(String key) {
		this.key = key;
	}

	@JsonProperty("login")
	public String getLogin() {
		return login;
	}

	@JsonProperty("login")
	public void setLogin(String login) {
		this.login = login;
	}

	@JsonProperty("htmlText")
	public String getHtmlText() {
		return htmlText;
	}

	@JsonProperty("htmlText")
	public void setHtmlText(String htmlText) {
		this.htmlText = htmlText;
	}

	@JsonProperty("markdown")
	public String getMarkdown() {
		return markdown;
	}

	@JsonProperty("markdown")
	public void setMarkdown(String markdown) {
		this.markdown = markdown;
	}

	@JsonProperty("updatable")
	public Boolean getUpdatable() {
		return updatable;
	}

	@JsonProperty("updatable")
	public void setUpdatable(Boolean updatable) {
		this.updatable = updatable;
	}

	@JsonProperty("createdAt")
	public String getCreatedAt() {
		return createdAt;
	}

	@JsonProperty("createdAt")
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
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
