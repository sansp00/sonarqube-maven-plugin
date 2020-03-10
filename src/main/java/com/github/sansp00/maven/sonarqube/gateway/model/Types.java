package com.github.sansp00.maven.sonarqube.gateway.model;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Types {
    
    @JsonProperty("OPEN")
    OPEN("OPEN"),
    @JsonProperty("CONFIRMED")
    CONFIRMED("CONFIRMED"),
    @JsonProperty("REOPENED")
    REOPENED("REOPENED"),
    @JsonProperty("RESOLVED")
    RESOLVED("RESOLVED"),
    @JsonProperty("CLOSED")
    CLOSED("CLOSED"),
    @JsonProperty("")
    UNDEFINED("");

    private final String code;

    private Types(final String code) {
        this.code = code;
    }

    public static Types valueOfCode(final String value) {
        Types v = UNDEFINED;
        switch (StringUtils.upperCase(value)) {
        case "OPEN":
            v = OPEN;
            break;
        case "CONFIRMED":
            v = CONFIRMED;
            break;
        case "REOPENED":
            v = REOPENED;
            break;
        case "RESOLVED":
            v = RESOLVED;
            break;
        case "CLOSED":
            v = CLOSED;
            break;
        default:
            break;
        }
        return v;
    }

	public String getCode() {
		return this.code;
	}
}
