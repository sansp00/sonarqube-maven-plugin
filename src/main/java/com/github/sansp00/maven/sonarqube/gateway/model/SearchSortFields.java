package com.github.sansp00.maven.sonarqube.gateway.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum SearchSortFields {
    
    @JsonProperty("CREATION_DATE")
    CREATION_DATE("CREATION_DATE"),
    @JsonProperty("UPDATE_DATE")
    UPDATE_DATE("UPDATE_DATE"),
    @JsonProperty("CLOSE_DATE")
    CLOSE_DATE("CLOSE_DATE"),
    @JsonProperty("ASSIGNEE")
    ASSIGNEE("ASSIGNEE"),
    @JsonProperty("SEVERITY")
    SEVERITY("SEVERITY"),
    @JsonProperty("STATUS")
    STATUS("STATUS"),
    @JsonProperty("FILE_LINE")
    FILE_LINE("FILE_LINE"),
    @JsonProperty("")
    UNDEFINED("");

    private final String code;

    private SearchSortFields(final String code) {
        this.code = code;
    }

    public static SearchSortFields valueOfCode(final String value) {
        SearchSortFields v = UNDEFINED;
        switch (value) {
        case "CREATION_DATE":
            v = CREATION_DATE;
            break;
        case "UPDATE_DATE":
            v = UPDATE_DATE;
            break;
        case "CLOSE_DATE":
            v = CLOSE_DATE;
            break;
        case "ASSIGNEE":
            v = ASSIGNEE;
            break;
        case "SEVERITY":
            v = SEVERITY;
            break;
        case "STATUS":
            v = STATUS;
            break;
        case "FILE_LINE":
            v = FILE_LINE;
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
