package com.github.sansp00.maven.sonarqube.gateway.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum SearchAdditionnalFields {
	@JsonProperty("_all")
	ALL("_all"),
	@JsonProperty("comments")
	COMMENTS("comments"),
    @JsonProperty("languages")
    LANGUAGES("languages"),
    @JsonProperty("actionPlans")
    ACTION_PLANS("actionPlans"),
    @JsonProperty("rules")
    RULES("rules"),
    @JsonProperty("transitions")
    TRANSITIONS("transitions"),
    @JsonProperty("actions")
    ACTIONS("actions"),
    @JsonProperty("users")
    USERS("users"),
	@JsonProperty("")
	UNDEFINED("");

	private final String code;

	private SearchAdditionnalFields(final String code) {
		this.code = code;
	}

	public static SearchAdditionnalFields valueOfCode(final String value) {
		SearchAdditionnalFields v = UNDEFINED;
		switch (value) {
		case "_all":
			v = ALL;
			break;
		case "comments":
			v = COMMENTS;
			break;
        case "languages":
            v = LANGUAGES;
            break;
        case "actionPlans":
            v = ACTION_PLANS;
            break;
        case "rules":
            v = RULES;
            break;
        case "transitions":
            v = TRANSITIONS;
            break;
        case "actions":
            v = ACTIONS;
            break;
        case "users":
            v = USERS;
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
