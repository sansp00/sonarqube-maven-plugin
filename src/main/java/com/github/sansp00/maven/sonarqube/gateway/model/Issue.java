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
import com.fasterxml.jackson.annotation.JsonProperty;

public class Issue {
    @JsonProperty("key")
    private String key;
    @JsonProperty("component")
    private String component;
    @JsonProperty("project")
    private String project;
    @JsonProperty("rule")
    private String rule;
    @JsonProperty("status")
    private String status;
    @JsonProperty("resolution")
    private String resolution;
    @JsonProperty("severity")
    private String severity;
    @JsonProperty("message")
    private String message;
    @JsonProperty("line")
    private Integer line;
    @JsonProperty("hash")
    private String hash;
    @JsonProperty("author")
    private String author;
    @JsonProperty("effort")
    private String effort;
    @JsonProperty("creationDate")
    private String creationDate;
    @JsonProperty("updateDate")
    private String updateDate;
    @JsonProperty("tags")
    private List<String> tags = null;
    @JsonProperty("type")
    private String type;
    @JsonProperty("comments")
    private List<Comment> comments = null;
    @JsonProperty("attr")
    private Attr attr;
    @JsonProperty("transitions")
    private List<String> transitions = null;
    @JsonProperty("actions")
    private List<String> actions = null;
    @JsonProperty("textRange")
    private TextRange textRange;
    @JsonProperty("flows")
    private List<Flow> flows = null;
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

    @JsonProperty("component")
    public String getComponent() {
        return component;
    }

    @JsonProperty("component")
    public void setComponent(String component) {
        this.component = component;
    }

    @JsonProperty("project")
    public String getProject() {
        return project;
    }

    @JsonProperty("project")
    public void setProject(String project) {
        this.project = project;
    }

    @JsonProperty("rule")
    public String getRule() {
        return rule;
    }

    @JsonProperty("rule")
    public void setRule(String rule) {
        this.rule = rule;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("resolution")
    public String getResolution() {
        return resolution;
    }

    @JsonProperty("resolution")
    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    @JsonProperty("severity")
    public String getSeverity() {
        return severity;
    }

    @JsonProperty("severity")
    public void setSeverity(String severity) {
        this.severity = severity;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("line")
    public Integer getLine() {
        return line;
    }

    @JsonProperty("line")
    public void setLine(Integer line) {
        this.line = line;
    }

    @JsonProperty("hash")
    public String getHash() {
        return hash;
    }

    @JsonProperty("hash")
    public void setHash(String hash) {
        this.hash = hash;
    }

    @JsonProperty("author")
    public String getAuthor() {
        return author;
    }

    @JsonProperty("author")
    public void setAuthor(String author) {
        this.author = author;
    }

    @JsonProperty("effort")
    public String getEffort() {
        return effort;
    }

    @JsonProperty("effort")
    public void setEffort(String effort) {
        this.effort = effort;
    }

    @JsonProperty("creationDate")
    public String getCreationDate() {
        return creationDate;
    }

    @JsonProperty("creationDate")
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    @JsonProperty("updateDate")
    public String getUpdateDate() {
        return updateDate;
    }

    @JsonProperty("updateDate")
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    @JsonProperty("tags")
    public List<String> getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("comments")
    public List<Comment> getComments() {
        return comments;
    }

    @JsonProperty("comments")
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @JsonProperty("attr")
    public Attr getAttr() {
        return attr;
    }

    @JsonProperty("attr")
    public void setAttr(Attr attr) {
        this.attr = attr;
    }

    @JsonProperty("transitions")
    public List<String> getTransitions() {
        return transitions;
    }

    @JsonProperty("transitions")
    public void setTransitions(List<String> transitions) {
        this.transitions = transitions;
    }

    @JsonProperty("actions")
    public List<String> getActions() {
        return actions;
    }

    @JsonProperty("actions")
    public void setActions(List<String> actions) {
        this.actions = actions;
    }

    @JsonProperty("textRange")
    public TextRange getTextRange() {
        return textRange;
    }

    @JsonProperty("textRange")
    public void setTextRange(TextRange textRange) {
        this.textRange = textRange;
    }

    @JsonProperty("flows")
    public List<Flow> getFlows() {
        return flows;
    }

    @JsonProperty("flows")
    public void setFlows(List<Flow> flows) {
        this.flows = flows;
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
