package org.example.application.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class User {
    @JsonProperty("Username")
    private String username;

    @JsonProperty("Password")
    private String password;

    private List<Card> cards;


    public User() {
    }

    public User(String username, String password) {
        this.username = username; //unique
        this.password = password;
        this.cards = new ArrayList<>();
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
