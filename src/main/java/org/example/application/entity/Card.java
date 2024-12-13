package org.example.application.entity;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.lang.annotation.ElementType;

/**
 *
 * This is the Card entity
 *
 * @author Aleksandar Latinovic
 */

public class Card {

    @JsonProperty("Id")
    private String id;

    @JsonProperty("name")
    private CreatureType name;

    private final int damage;


    public Card(int damage) {
        this.damage = damage;
    }
}
