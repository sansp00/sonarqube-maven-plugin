package com.github.sansp00.maven.sonarqube.gateway.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Resolutions {
    
    @JsonProperty("FALSE-POSITIVE")
    FALSE_POSITIVE("FALSE-POSITIVE"),
    @JsonProperty("WONTFIX")
    WONTFIX("WONTFIX"),
    @JsonProperty("FIXED")
    FIXED("FIXED"),
    @JsonProperty("REMOVED")
    REMOVED("REMOVED"),
    @JsonProperty("")
    UNDEFINED("");

    private final String code;

    private Resolutions(final String code) {
        this.code = code;
    }

    public static Resolutions valueOfCode(final String value) {
        Resolutions v = UNDEFINED;
        switch (value) {
        case "FALSE-POSITIVE":
            v = FALSE_POSITIVE;
            break;
        case "WONTFIX":
            v = WONTFIX;
            break;
        case "FIXED":
            v = FIXED;
            break;
        case "REMOVED":
            v = REMOVED;
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
