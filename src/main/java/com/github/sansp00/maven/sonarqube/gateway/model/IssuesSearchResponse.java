
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
public class IssuesSearchResponse implements PagingResponse {

    @JsonProperty("paging")
    private Paging paging;
    @JsonProperty("issues")
    private List<Issue> issues = new ArrayList<>();
    @JsonProperty("components")
    private List<Component> components = new ArrayList<>();
    @JsonProperty("rules")
    private List<Rule> rules = new ArrayList<>();
    @JsonProperty("users")
    private List<User> users = new ArrayList<>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("paging")
    public Paging getPaging() {
        return paging;
    }

    @JsonProperty("paging")
    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    @JsonProperty("issues")
    public List<Issue> getIssues() {
        return issues;
    }

    @JsonProperty("issues")
    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }

    @JsonProperty("components")
    public List<Component> getComponents() {
        return components;
    }

    @JsonProperty("components")
    public void setComponents(List<Component> components) {
        this.components = components;
    }

    @JsonProperty("rules")
    public List<Rule> getRules() {
        return rules;
    }

    @JsonProperty("rules")
    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    @JsonProperty("users")
    public List<User> getUsers() {
        return users;
    }

    @JsonProperty("users")
    public void setUsers(List<User> users) {
        this.users = users;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public IssuesSearchResponse withPaging(Paging paging) {
        this.paging = paging;
        return this;
    }

    public IssuesSearchResponse withAdditionalProperty(String name, Object value) {
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
