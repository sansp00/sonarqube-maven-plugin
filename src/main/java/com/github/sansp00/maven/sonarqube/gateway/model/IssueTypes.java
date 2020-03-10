package com.github.sansp00.maven.sonarqube.gateway.model;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum IssueTypes {
    
    @JsonProperty("BUG")
    BUG("BUG"),
    @JsonProperty("VULNERABILITY")
    VULNERABILITY("VULNERABILITY"),
    @JsonProperty("CODE_SMELL")
    CODE_SMELL("CODE_SMELL"),
    @JsonProperty("")
    UNDEFINED("");

    private final String code;

    private IssueTypes(final String code) {
        this.code = code;
    }

    public static IssueTypes valueOfCode(final String value) {
        IssueTypes v = UNDEFINED;
        switch (StringUtils.upperCase(value)) {
        case "BUG":
            v = BUG;
            break;
        case "VULNERABILITY":
            v = VULNERABILITY;
            break;
        case "CODE_SMELL":
            v = CODE_SMELL;
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
