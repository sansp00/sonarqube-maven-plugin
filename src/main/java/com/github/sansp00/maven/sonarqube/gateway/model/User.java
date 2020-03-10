package com.github.sansp00.maven.sonarqube.gateway.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    @JsonProperty("login")
    private String login;
    @JsonProperty("name")
    private String name;
    @JsonProperty("active")
    private Boolean active;
    @JsonProperty("avatar")
    private String avatar;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("login")
    public String getLogin() {
        return login;
    }

    @JsonProperty("login")
    public void setLogin(String login) {
        this.login = login;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("active")
    public Boolean getActive() {
        return active;
    }

    @JsonProperty("active")
    public void setActive(Boolean active) {
        this.active = active;
    }

    @JsonProperty("avatar")
    public String getAvatar() {
        return avatar;
    }

    @JsonProperty("avatar")
    public void setAvatar(String avatar) {
        this.avatar = avatar;
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
