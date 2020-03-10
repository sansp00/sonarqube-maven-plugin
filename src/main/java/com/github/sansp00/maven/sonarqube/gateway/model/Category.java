package com.github.sansp00.maven.sonarqube.gateway.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Category {
    @JsonProperty("VERSION")
    VERSION("VERSION"), //
    @JsonProperty("OTHER")
    OTHER("OTHER"), //
    @JsonProperty("QUALITY_PROFILE")
    QUALITY_PROFILE("QUALITY_PROFILE"), //
    @JsonProperty("QUALITY_GATE")
    QUALITY_GATE("QUALITY_GATE"), //
    @JsonProperty("DEFINITION_CHANGE")
    DEFINITION_CHANGE("DEFINITION_CHANGE"), //
    @JsonProperty("")
    UNDEFINED("");

    private final String code;

    private Category(final String code) {
        this.code = code;
    }

    public static Category valueOfCode(final String value) {
        Category v = UNDEFINED;
        switch (value) {
            case "VERSION":
                v = VERSION;
                break;
            case "OTHER":
                v = OTHER;
                break;
            case "QUALITY_PROFILE":
                v = QUALITY_PROFILE;
                break;
            case "QUALITY_GATE":
                v = QUALITY_GATE;
                break;
            case "DEFINITION_CHANGE":
                v = DEFINITION_CHANGE;
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
