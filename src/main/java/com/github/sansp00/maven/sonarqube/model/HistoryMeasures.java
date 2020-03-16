package com.github.sansp00.maven.sonarqube.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

public class HistoryMeasures {

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

	@JsonProperty("maintainabilityRating")
	private Rating maintainabilityRating;

	@JsonProperty("coverage")
	private BigDecimal coverage;

	@JsonProperty("duplicatedLinesDensity")
	private BigDecimal duplicatedLinesDensity;

	@JsonProperty("linesOfCode")
	private BigInteger linesOfCode;

	@JsonProperty("analysisDate")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime analysisDate;

	public LocalDateTime getAnalysisDate() {
		return analysisDate;
	}

	public void setAnalysisDate(LocalDateTime analysisDate) {
		this.analysisDate = analysisDate;
	}

	public HistoryMeasures withAnalysisDate(LocalDateTime analysisDate) {
		this.analysisDate = analysisDate;
		return this;
	}

	public BigInteger getBugs() {
		return bugs;
	}

	public void setBugs(BigInteger bigInteger) {
		this.bugs = bigInteger;
	}

	public HistoryMeasures withBugs(BigInteger bugs) {
		this.bugs = bugs;
		return this;
	}

	public Rating getReliabilityRating() {
		return reliabilityRating;
	}

	public void setReliabilityRating(Rating reliabilityRating) {
		this.reliabilityRating = reliabilityRating;
	}

	public HistoryMeasures withReliabilityRating(Rating reliabilityRating) {
		this.reliabilityRating = reliabilityRating;
		return this;
	}

	public BigInteger getVulnerabilities() {
		return vulnerabilities;
	}

	public void setVulnerabilities(BigInteger vulnerabilities) {
		this.vulnerabilities = vulnerabilities;
	}

	public HistoryMeasures withVulnerabilities(BigInteger vulnerabilities) {
		this.vulnerabilities = vulnerabilities;
		return this;
	}

	public Rating getSecurityRating() {
		return securityRating;
	}

	public void setSecurityRating(Rating securityRating) {
		this.securityRating = securityRating;
	}

	public HistoryMeasures withSecurityRating(Rating securityRating) {
		this.securityRating = securityRating;
		return this;
	}

	public BigInteger getCodeSmells() {
		return codeSmells;
	}

	public void setCodeSmells(BigInteger codeSmells) {
		this.codeSmells = codeSmells;
	}

	public HistoryMeasures withCodeSmells(BigInteger codeSmells) {
		this.codeSmells = codeSmells;
		return this;
	}

	public Rating getMaintainabilityRating() {
		return maintainabilityRating;
	}

	public void setMaintainabilityRating(Rating maintainabilityRating) {
		this.maintainabilityRating = maintainabilityRating;
	}

	public HistoryMeasures withMaintainabilityRating(Rating maintainabilityRating) {
		this.maintainabilityRating = maintainabilityRating;
		return this;
	}

	public BigDecimal getCoverage() {
		return coverage;
	}

	public void setCoverage(BigDecimal coverage) {
		this.coverage = coverage;
	}

	public HistoryMeasures withCoverage(BigDecimal coverage) {
		this.coverage = coverage;
		return this;
	}

	public BigDecimal getDuplicatedLinesDensity() {
		return duplicatedLinesDensity;
	}

	public void setDuplicatedLinesDensity(BigDecimal duplicatedLinesDensity) {
		this.duplicatedLinesDensity = duplicatedLinesDensity;
	}

	public HistoryMeasures withDuplicatedLinesDensity(BigDecimal duplicatedLinesDensity) {
		this.duplicatedLinesDensity = duplicatedLinesDensity;
		return this;
	}

	public BigInteger getLinesOfCode() {
		return linesOfCode;
	}

	public void setLinesOfCode(BigInteger linesOfCode) {
		this.linesOfCode = linesOfCode;
	}

	public HistoryMeasures withLinesOfCode(BigInteger linesOfCode) {
		this.linesOfCode = linesOfCode;
		return this;
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
