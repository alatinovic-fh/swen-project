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

    private int coins;

    private String bio;

    private String image;

    public User() {
    }

    public User(String username, String password) {
        this.username = username; //unique
        this.password = password;
        this.coins = 20;
        this.cards = new ArrayList<>();
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getCoins() {
        return coins;
    }

    public String getBio() {
        return bio;
    }

    public String getImage() {
        return image;
    }
}
