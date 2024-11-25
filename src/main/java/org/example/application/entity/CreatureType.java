package org.example.application.entity;


/**
 * This enum contains all Card varations (name element and if the card is a spell)
 *
 * @author Aleksandar Latinovic
 * */
public enum CreatureType {
    WATER_GOBLIN("Goblin", "Water", false),
    FIRE_GOBLIN("Goblin", "Fire", false),
    REGULAR_GOBLIN("Goblin", "Regular", false),
    WATER_TROLL("Troll", "Water", false),
    FIRE_TROLL("Troll", "Fire", false),
    REGULAR_TROLL("Troll", "Regular", false),
    WATER_ELF("Elf", "Water", false),
    FIRE_ELF("Elf", "Fire", false),
    REGULAR_ELF("Elf", "Regular", false),
    WATER_SPELL("Spell", "Water", true),
    FIRE_SPELL("Spell", "Fire", true),
    REGULAR_SPELL("Spell", "Regular", true),
    KNIGHT("Warrior", "Neutral", false),
    DRAGON("Beast", "Fire", false),
    ORK("Warrior", "Earth", false),
    KRAKEN("Beast", "Water", false),
    WIZZARD("Mage", "Neutral", false);

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