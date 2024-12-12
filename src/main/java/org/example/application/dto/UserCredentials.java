package org.example.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserCredentials {

    @JsonProperty("Username")
    private String username;

    @JsonProperty("Password")
    private String password;

    public UserCredentials() {

    }

    public UserCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
