package org.example.application.entity;


/**
 * This enum contains all Card varations (name element and if the card is a spell)
 *
 * @author Aleksandar Latinovic
 * */
public enum CreatureType {
    // TODO vllt element Name
    WATER_GOBLIN("goblin", "water", false),
    FIRE_GOBLIN("goblin", "fire", false),
    REGULAR_GOBLIN("goblin", "normal", false),
    WATER_TROLL("troll", "water", false),
    FIRE_TROLL("troll", "fire", false),
    REGULAR_TROLL("troll", "normal", false),
    WATER_ELF("elf", "water", false),
    FIRE_ELF("elf", "fire", false),
    REGULAR_ELF("elf", "normal", false),
    WATER_SPELL("spell", "water", true),
    FIRE_SPELL("spell", "fire", true),
    REGULAR_SPELL("spell", "normal", true),
    KNIGHT("knight", "normal", false),
    DRAGON("dragon", "fire", false),
    ORK("ork", "Earth", false),
    KRAKEN("kraken", "water", false),
    WIZZARD("wizzard", "normal", false);

    private final String category;
    private final String element;
    private final boolean isSpell;

    CreatureType(String category, String element, boolean isSpell) {
        this.category = category;
        this.element = element;
        this.isSpell = isSpell;
    }

    public String getCategory() {
        return category;
    }

    public String getElement() {
        return element;
    }

    public boolean isSpell() {
        return isSpell;
    }
}