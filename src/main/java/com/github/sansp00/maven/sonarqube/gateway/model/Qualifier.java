package com.github.sansp00.maven.sonarqube.gateway.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Qualifier {
    @JsonProperty("VIEW")
	VIEW("VIEW"), //
	@JsonProperty("SVW")
	SUBVIEW("SVW"), //
	@JsonProperty("APP")
	APP("APP"), //
	@JsonProperty("TRK")
	PROJECT("TRK"), //
	@JsonProperty("LIB")
	LIBRARY("LIB"), //
	@JsonProperty("BRC")
	MODULE("BRC"), //
	@JsonProperty("DIR")
	DIRECTORY("DIR"), //
	@JsonProperty("FIL")
	FILE("FIL"), //
	@JsonProperty("DIR")
	UNIT_TEST_FILE("DIR"), //
	@JsonProperty("")
	UNDEFINED("");

	private final String code;

	private Qualifier(final String code) {
		this.code = code;
	}

	public static Qualifier valueOfCode(final String value) {
		Qualifier v = UNDEFINED;
		switch (value) {
		case "VIEW":
			v = VIEW;
			break;
		case "SVW":
			v = SUBVIEW;
			break;
		case "APP":
			v = APP;
			break;
		case "TRK":
			v = PROJECT;
			break;
		case "LIB":
			v = LIBRARY;
			break;
		case "BRC":
			v = MODULE;
			break;
		case "DIR":
			v = DIRECTORY;
			break;
		case "FILE":
			v = FILE;
			break;
		case "UTS":
			v = UNIT_TEST_FILE;
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
