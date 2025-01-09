package org.example.application.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonProperty("Name")
    private String name;

    @JsonIgnore
    private CreatureType creatureType;

    @JsonProperty("Damage")
    private int damage;


    public Card() {
        this.setCreatureType();
    }

    public Card(int damage) {
        this.damage = damage;

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CreatureType getCreatureType() {
        return creatureType;
    }

    public int getDamage() {
        return damage;
    }

    private void setCreatureType() {
        for(CreatureType creatureType : CreatureType.values()) {
            if(creatureType.name().equals(this.name)) {
                this.creatureType = creatureType;
            }
        }
    }
}
