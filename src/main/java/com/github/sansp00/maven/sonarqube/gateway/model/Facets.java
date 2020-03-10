package com.github.sansp00.maven.sonarqube.gateway.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Facets {
    @JsonProperty("severities")
    SEVERITIES("severities"),
    @JsonProperty("statuses")
    STATUSES("statuses"),
    @JsonProperty("resolutions")
    RESOLUTIONS("resolutions"),
    @JsonProperty("actionPlans")
    ACTIONS_PLANS("actionPlans"),
    @JsonProperty("projectUuids")
    PROJECT_UUIDS("projectUuids"),
    @JsonProperty("rules")
    RULES("rules"),
    @JsonProperty("assignees")
    ASSIGNEES("assignees"),
    @JsonProperty("assigned_to_me")
    ASSIGNED_TO_ME("assigned_to_me"),
    @JsonProperty("reporters")
    REPORTERS("reporters"),
    @JsonProperty("authors")
    AUTHORS("authors"),
    @JsonProperty("moduleUuids")
    MODULE_UUIDS("moduleUuids"),
    @JsonProperty("fileUuids")
    FILE_UUIDS("fileUuids"),
    @JsonProperty("directories")
    DIRECTORIES("directories"),
    @JsonProperty("languages")
    LANGUAGES("languages"),
    @JsonProperty("tags")
    TAGS("tags"),
    @JsonProperty("types")
    TYPES("types"),
    @JsonProperty("createdAt")
    CREATED_AT("createdAt"),
    @JsonProperty("")
    UNDEFINED("");

    private final String code;

    private Facets(final String code) {
        this.code = code;
    }

    public static Facets valueOfCode(final String value) {
        Facets v = UNDEFINED;
        switch (value) {
        case "severities":
            v = SEVERITIES;
            break;
        case "statuses":
            v = STATUSES;
            break;
        case "resolutions":
            v = RESOLUTIONS;
            break;
        case "actionPlans":
            v = ACTIONS_PLANS;
            break;
        case "projectUuids":
            v = PROJECT_UUIDS;
            break;
        case "rules":
            v = RULES;
            break;
        case "assignees":
            v = ASSIGNEES;
            break;
        case "assigned_to_me":
            v = ASSIGNED_TO_ME;
            break;
        case "reporters":
            v = REPORTERS;
            break;
        case "authors":
            v = AUTHORS;
            break;
        case "moduleUuids":
            v = MODULE_UUIDS;
            break;
        case "fileUuids":
            v = FILE_UUIDS;
            break;
        case "directories":
            v = DIRECTORIES;
            break;
        case "languages":
            v = LANGUAGES;
            break;
        case "tags":
            v = TAGS;
            break;
        case "types":
            v = TYPES;
            break;
        case "createdAt":
            v = CREATED_AT;
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
