package com.github.sansp00.maven.sonarqube.model;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NutshellMeasures {

    @JsonProperty("bugs")
    private BigInteger bugs;

    @JsonProperty("reliabilityRating")
    private Rating reliabilityRating;

    @JsonProperty("vulnerabilities")
    private BigInteger vulnerabilities;

    @JsonProperty("securityRating")
    private Rating securityRating;

    @JsonProperty("codeSmells")
    private BigInteger codeSmells;

    @JsonProperty("sqaleRating")
    private Rating sqaleRating;

    @JsonProperty("coverage")
    private BigDecimal coverage;

    @JsonProperty("duplicatedLinesDensity")
    private BigDecimal duplicatedLinesDensity;

    @JsonProperty("linesOfCode")
    private BigInteger linesOfCode;

    public BigInteger getBugs() {
        return bugs;
    }

    public void setBugs(BigInteger bugs) {
        this.bugs = bugs;
    }

    public NutshellMeasures withBugs(BigInteger bugs) {
        this.bugs = bugs;
        return this;
    }

    @JsonIgnore
    public boolean isSetBugs() {
        return bugs != null;
    }

    public Rating getReliabilityRating() {
        return reliabilityRating;
    }

    public void setReliabilityRating(Rating reliabilityRating) {
        this.reliabilityRating = reliabilityRating;
    }

    public NutshellMeasures withReliabilityRating(Rating reliabilityRating) {
        this.reliabilityRating = reliabilityRating;
        return this;
    }

    @JsonIgnore
    public boolean isSetReliabilityRating() {
        return reliabilityRating != null && reliabilityRating != Rating.UNDEFINED;
    }

    public BigInteger getVulnerabilities() {
        return vulnerabilities;
    }

    public void setVulnerabilities(BigInteger vulnerabilities) {
        this.vulnerabilities = vulnerabilities;
    }

    public NutshellMeasures withVulnerabilities(BigInteger vulnerabilities) {
        this.vulnerabilities = vulnerabilities;
        return this;
    }

    @JsonIgnore
    public boolean isSetVulnerabilities() {
        return vulnerabilities != null;
    }

    public Rating getSecurityRating() {
        return securityRating;
    }

    public void setSecurityRating(Rating securityRating) {
        this.securityRating = securityRating;
    }

    public NutshellMeasures withSecurityRating(Rating securityRating) {
        this.securityRating = securityRating;
        return this;
    }

    @JsonIgnore
    public boolean isSetSecurityRating() {
        return securityRating != null && securityRating != Rating.UNDEFINED;
    }

    public BigInteger getCodeSmells() {
        return codeSmells;
    }

    public void setCodeSmells(BigInteger codeSmells) {
        this.codeSmells = codeSmells;
    }

    public NutshellMeasures withCodeSmells(BigInteger codeSmells) {
        this.codeSmells = codeSmells;
        return this;
    }

    @JsonIgnore
    public boolean isSetCodeSmells() {
        return codeSmells != null;
    }

    public Rating getSqaleRating() {
        return sqaleRating;
    }

    public void setSqaleRating(Rating sqaleRating) {
        this.sqaleRating = sqaleRating;
    }

    public NutshellMeasures withSqaleRating(Rating sqaleRating) {
        this.sqaleRating = sqaleRating;
        return this;
    }

    @JsonIgnore
    public boolean isSetSqaleRating() {
        return sqaleRating != null && sqaleRating != Rating.UNDEFINED;
    }

    public BigDecimal getCoverage() {
        return coverage;
    }

    public void setCoverage(BigDecimal coverage) {
        this.coverage = coverage;
    }

    public NutshellMeasures withCoverage(BigDecimal coverage) {
        this.coverage = coverage;
        return this;
    }

    @JsonIgnore
    public boolean isSetCoverage() {
        return coverage != null;
    }

    public BigDecimal getDuplicatedLinesDensity() {
        return duplicatedLinesDensity;
    }

    public void setDuplicatedLinesDensity(BigDecimal duplicatedLinesDensity) {
        this.duplicatedLinesDensity = duplicatedLinesDensity;
    }

    public NutshellMeasures withDuplicatedLinesDensity(BigDecimal duplicatedLinesDensity) {
        this.duplicatedLinesDensity = duplicatedLinesDensity;
        return this;
    }

    @JsonIgnore
    public boolean isSetDuplicatedLinesDensity() {
        return duplicatedLinesDensity != null;
    }

    public BigInteger getLinesOfCode() {
        return linesOfCode;
    }

    public void setLinesOfCode(BigInteger linesOfCode) {
        this.linesOfCode = linesOfCode;
    }

    public NutshellMeasures withLinesOfCode(BigInteger linesOfCode) {
        this.linesOfCode = linesOfCode;
        return this;
    }

    @JsonIgnore
    public boolean isSetLinesOfCode() {
        return linesOfCode != null;
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
