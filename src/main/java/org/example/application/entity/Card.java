package org.example.application.entity;


import java.lang.annotation.ElementType;

public class Card {

    private String name;

    private final int damage;

    private Element element;

    private boolean isMonster;

    public Card(int damage) {
        this.damage = damage;
    }
}
