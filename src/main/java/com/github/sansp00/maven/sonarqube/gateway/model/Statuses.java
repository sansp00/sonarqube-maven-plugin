package com.github.sansp00.maven.sonarqube.gateway.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Statuses {
    
    @JsonProperty("CODE_SMELL")
    CODE_SMELL("CODE_SMELL"),
    @JsonProperty("BUG")
    BUG("BUG"),
    @JsonProperty("VULNERABILITY")
    VULNERABILITY("VULNERABILITY"),
    @JsonProperty("")
    UNDEFINED("");

    private final String code;

    private Statuses(final String code) {
        this.code = code;
    }

    public static Statuses valueOfCode(final String value) {
        Statuses v = UNDEFINED;
        switch (value) {
        case "CODE_SMELL":
            v = CODE_SMELL;
            break;
        case "BUG":
            v = BUG;
            break;
        case "VULNERABILITY":
            v = VULNERABILITY;
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
