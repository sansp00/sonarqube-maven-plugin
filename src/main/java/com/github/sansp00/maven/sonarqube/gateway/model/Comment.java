package com.github.sansp00.maven.sonarqube.gateway.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
