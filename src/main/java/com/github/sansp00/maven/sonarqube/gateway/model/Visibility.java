package com.github.sansp00.maven.sonarqube.gateway.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Visibility {
    
    @JsonProperty("private")
    PRIVATE("private"),
    @JsonProperty("public")
    PUBLIC("public"),
    @JsonProperty("")
    UNDEFINED("");

    private final String code;

    private Visibility(final String code) {
        this.code = code;
    }

    public static Visibility valueOfCode(final String value) {
        Visibility v = UNDEFINED;
        switch (value) {
        case "private":
            v = PRIVATE;
            break;
        case "public":
            v = PUBLIC;
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
