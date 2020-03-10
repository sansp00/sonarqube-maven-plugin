package com.github.sansp00.maven.sonarqube.gateway.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Location {
    @JsonProperty("textRange")
    private TextRange textRange;
    @JsonProperty("msg")
    private String msg;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("textRange")
    public TextRange getTextRange() {
        return textRange;
    }

    @JsonProperty("textRange")
    public void setTextRange(TextRange textRange) {
        this.textRange = textRange;
    }

    @JsonProperty("msg")
    public String getMsg() {
        return msg;
    }

    @JsonProperty("msg")
    public void setMsg(String msg) {
        this.msg = msg;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
