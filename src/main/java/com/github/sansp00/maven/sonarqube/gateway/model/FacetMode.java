package com.github.sansp00.maven.sonarqube.gateway.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum FacetMode {
    @JsonProperty("count")
    COUNT("count"), @JsonProperty("effort")
    EFFORT("effort"), @JsonProperty("debt")
    DEBT("debt"), @JsonProperty("")
    UNDEFINED("");

    private final String code;

    private FacetMode(final String code) {
        this.code = code;
    }

    public static FacetMode valueOfCode(final String value) {
        FacetMode v = UNDEFINED;
        switch (value) {
            case "count":
                v = COUNT;
                break;
            case "effort":
                v = EFFORT;
                break;
            case "debt":
                v = DEBT;
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
