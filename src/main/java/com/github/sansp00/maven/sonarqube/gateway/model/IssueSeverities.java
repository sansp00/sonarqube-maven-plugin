package com.github.sansp00.maven.sonarqube.gateway.model;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum IssueSeverities {
    
    @JsonProperty("INFO")
    INFO("INFO"),
    @JsonProperty("MINOR")
    MINOR("MINOR"),
    @JsonProperty("MAJOR")
    MAJOR("MAJOR"),
    @JsonProperty("CRITICAL")
    CRITICAL("CRITICAL"),
    @JsonProperty("BLOCKER")
    BLOCKER("BLOCKER"),
    @JsonProperty("")
    UNDEFINED("");

    private final String code;

    private IssueSeverities(final String code) {
        this.code = code;
    }

    public static IssueSeverities valueOfCode(final String value) {
        IssueSeverities v = UNDEFINED;
        switch (StringUtils.upperCase(value)) {
        case "INFO":
            v = INFO;
            break;
        case "MINOR":
            v = MINOR;
            break;
        case "MAJOR":
            v = MAJOR;
            break;
        case "CRITICAL":
            v = CRITICAL;
            break;
        case "BLOCKER":
            v = BLOCKER;
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
