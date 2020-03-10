
package com.github.sansp00.maven.sonarqube.gateway.model;

import java.time.LocalDateTime;
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
public class Component {

    
    @JsonProperty("organization")
    private String organization;

    
    @JsonProperty("id")
    private String id;

    
    @JsonProperty("key")
    private String key;

    
    @JsonProperty("name")
    private String name;

    
    @JsonProperty("version")
    private String version;

    
    @JsonProperty("project")
    private String project;

    
    @JsonProperty("language")
    private String language;

    
    @JsonProperty("path")
    private String path;

    
    @JsonProperty("qualifier")
    private String qualifier;

    
    @JsonProperty("visibility")
    private Visibility visibility;

    
    @JsonProperty("lastAnalysisDate")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private LocalDateTime lastAnalysisDate;

    
    @JsonProperty("leakPeriodDate")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private LocalDateTime leakPeriodDate;

    
    @JsonProperty("analysisDate")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private LocalDateTime analysisDate;

    
    @JsonProperty("measures")
    private List<Measure> measures = null;

    
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("organization")
    public String getOrganization() {
        return organization;
    }

    @JsonProperty("organization")
    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public Component withOrganization(String organization) {
        this.organization = organization;
        return this;
    }

    public boolean isSetOrganization() {
        return organization != null;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public Component withId(String id) {
        this.id = id;
        return this;
    }

    public boolean isSetId() {
        return id != null;
    }

    @JsonProperty("key")
    public String getKey() {
        return key;
    }

    @JsonProperty("key")
    public void setKey(String key) {
        this.key = key;
    }

    public Component withKey(String key) {
        this.key = key;
        return this;
    }

    public boolean isSetKey() {
        return key != null;
    }

    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(String version) {
        this.version = version;
    }

    public Component withVersion(String version) {
        this.name = version;
        return this;
    }

    public boolean isSetVersion() {
        return version != null;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public Component withName(String name) {
        this.name = name;
        return this;
    }

    public boolean isSetName() {
        return name != null;
    }

    @JsonProperty("path")
    public String getPath() {
        return name;
    }

    @JsonProperty("path")
    public void setPath(String path) {
        this.path = path;
    }

    public Component withPath(String path) {
        this.path = path;
        return this;
    }

    public boolean isSetPath() {
        return path != null;
    }

    @JsonProperty("language")
    public String getLanguage() {
        return language;
    }

    @JsonProperty("language")
    public void setLanguage(String language) {
        this.language = language;
    }

    public Component withLanguage(String language) {
        this.language = language;
        return this;
    }

    public boolean isSetLanguage() {
        return language != null;
    }

    @JsonProperty("project")
    public String getProject() {
        return project;
    }

    @JsonProperty("project")
    public void setProject(String project) {
        this.project = project;
    }

    public Component withProject(String project) {
        this.project = project;
        return this;
    }

    public boolean isSetProject() {
        return project != null;
    }

    @JsonProperty("qualifier")
    public String getQualifier() {
        return qualifier;
    }

    @JsonProperty("qualifier")
    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }

    public Component withQualifier(String qualifier) {
        this.qualifier = qualifier;
        return this;
    }

    public boolean isSetQualifier() {
        return qualifier != null;
    }

    @JsonProperty("visibility")
    public Visibility getVisibility() {
        return visibility;
    }

    @JsonProperty("visibility")
    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public Component withVisibility(Visibility visibility) {
        this.visibility = visibility;
        return this;
    }

    public boolean isSetVisibility() {
        return visibility != null;
    }

    @JsonProperty("lastAnalysisDate")
    public LocalDateTime getLastAnalysisDate() {
        return lastAnalysisDate;
    }

    @JsonProperty("lastAnalysisDate")
    public void setLastAnalysisDate(LocalDateTime lastAnalysisDate) {
        this.lastAnalysisDate = lastAnalysisDate;
    }

    public Component withLastAnalysisDate(LocalDateTime lastAnalysisDate) {
        this.lastAnalysisDate = lastAnalysisDate;
        return this;
    }

    public boolean isSetLastAnalysisDate() {
        return lastAnalysisDate != null;
    }

    @JsonProperty("analysisDate")
    public LocalDateTime getAnalysisDate() {
        return analysisDate;
    }

    @JsonProperty("analysisDate")
    public void setAnalysisDate(LocalDateTime analysisDate) {
        this.analysisDate = analysisDate;
    }

    public Component withAnalysisDate(LocalDateTime analysisDate) {
        this.analysisDate = analysisDate;
        return this;
    }

    public boolean isSetAnalysisDate() {
        return analysisDate != null;
    }

    @JsonProperty("leakPeriodDate")
    public LocalDateTime getLeakPeriodDate() {
        return leakPeriodDate;
    }

    @JsonProperty("leakPeriodDate")
    public void setLeakPeriodDate(LocalDateTime leakPeriodDate) {
        this.leakPeriodDate = leakPeriodDate;
    }

    public Component withLeakPeriodDate(LocalDateTime leakPeriodDate) {
        this.leakPeriodDate = leakPeriodDate;
        return this;
    }

    public boolean isSetLeakPeriodDate() {
        return leakPeriodDate != null;
    }

    @JsonProperty("measures")
    public List<Measure> getMeasures() {
        return measures;
    }

    @JsonProperty("measures")
    public void setMeasures(List<Measure> measures) {
        this.measures = measures;
    }

    public Component withMeasures(List<Measure> measures) {
        this.measures = measures;
        return this;
    }

    public boolean isSetMeasures() {
        return measures != null && !measures.isEmpty();
    }

    
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Component withAdditionalProperty(String name, Object value) {
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
