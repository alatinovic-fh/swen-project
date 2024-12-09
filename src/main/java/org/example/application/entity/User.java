package org.example.application.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;


/**
 * This is the User entity for the Table "users"
 *
 * @author Aleksandar Latinovic
 * */
public class User {
    @JsonProperty("Username")
    private String username;

    @JsonProperty("Password")
    private String password;

    @JsonProperty("Stack")
    private List<Card> stack;

    @JsonProperty("Deck")
    private List<Card> deck;

    @JsonProperty("Coins")
    private int coins;

    @JsonProperty("Bio")
    private String bio;

    @JsonProperty("Image")
    private String image;

    public User() {
    }

    public User(String username, String password) {
        this.username = username; //unique
        this.password = password;
        this.coins = 20;
        this.stack = new ArrayList<>();
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public List<Card> getStack() {
        return stack;
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
