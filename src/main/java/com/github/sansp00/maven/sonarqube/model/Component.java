package com.github.sansp00.maven.sonarqube.model;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

public class Component {

    @JsonProperty("key")
    private String key;

    @JsonProperty("name")
    private String name;

    @JsonProperty("uri")
    private String uri;

    @JsonProperty("nutshellMeasures")
    private NutshellMeasures nutshellMeasures;

    @JsonProperty("extractionDate")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime extractionDate;

    @JsonProperty("historyDateRange")
    private DateRange historyDateRange;

    @JsonProperty("historyMeasures")
    private List<HistoryMeasures> historyMeasures;

    public DateRange getHistoryDateRange() {
        return historyDateRange;
    }

    public void setHistoryDateRange(DateRange historyDateRange) {
        this.historyDateRange = historyDateRange;
    }

    public Component withHistoryDateRange(DateRange historyDateRange) {
        this.historyDateRange = historyDateRange;
        return this;
    }

    @JsonIgnore
    public boolean isSetHistoryDateRange() {
        return historyDateRange != null;
    }

    public LocalDateTime getExtractionDate() {
        return extractionDate;
    }

    public void setExtractionDate(LocalDateTime extractionDate) {
        this.extractionDate = extractionDate;
    }

    public Component withExtractionDate(LocalDateTime extractionDate) {
        this.extractionDate = extractionDate;
        return this;
    }

    @JsonIgnore
    public boolean isSetExtractionDate() {
        return extractionDate != null;
    }

    public String getKey() {
        return key;
    }

    @JsonIgnore
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

    public String getUri() {
        return uri;
    }

    @JsonIgnore
    public void setUri(String uri) {
        this.uri = uri;
    }

    public Component withUri(String uri) {
        this.uri = uri;
        return this;
    }

    public boolean isSetUri() {
        return uri != null;
    }

    public String getName() {
        return name;
    }

    @JsonIgnore
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

    public List<HistoryMeasures> getHistoryMeasures() {
        return historyMeasures;
    }

    @JsonIgnore
    public void setHistoryMeasures(List<HistoryMeasures> historyMeasures) {
        this.historyMeasures = historyMeasures;
    }

    public Component withHistoryMeasures(List<HistoryMeasures> historyMeasures) {
        this.historyMeasures = historyMeasures;
        return this;
    }

    public boolean isSetHistoryMeasures() {
        return historyMeasures != null && !historyMeasures.isEmpty();
    }

    public NutshellMeasures getNutshellMeasures() {
        return nutshellMeasures;
    }

    @JsonIgnore
    public void setNutshellMeasures(NutshellMeasures nutshellMeasures) {
        this.nutshellMeasures = nutshellMeasures;
    }

    public Component withNutshellMeasures(NutshellMeasures nutshellMeasures) {
        this.nutshellMeasures = nutshellMeasures;
        return this;
    }

    public boolean isSetNutshellMeasures() {
        return nutshellMeasures != null;
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
