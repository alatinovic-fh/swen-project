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

    @JsonProperty("Name")
    private String fullName;

    @JsonProperty("Bio")
    private String bio;

    @JsonProperty("Image")
    private String image;

    @JsonProperty("Stats")
    private int stats;

    public User() {
    }

    public User(String username, String password) {
        this.username = username; //unique
        this.password = password;
        this.coins = 20;
        this.stats = 100;
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

    public List<Card> getDeck() {
        return deck;
    }

    public String getFullName() {
        return fullName;
    }

    public int getStats() {
        return stats;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStack(List<Card> stack) {
        this.stack = stack;
    }

    public void setDeck(List<Card> deck) {
        this.deck = deck;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setStats(int stats) {
        this.stats = stats;
    }
}
