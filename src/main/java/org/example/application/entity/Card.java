package org.example.application.entity;


import java.lang.annotation.ElementType;

/**
 *
 * This is the Card entity
 *
 * @author Aleksandar Latinovic
 */

public class Card {

    private String id;

    private CreatureType name;

    private final int damage;


    public Card(int damage) {
        this.damage = damage;
    }
}
