package org.example.application.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    @JsonProperty("Username")
    private String username;

    @JsonProperty("Password")
    private String password;

    public User() {

    }

    public User(String username, String password) {
        this.username = username; //unique
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
