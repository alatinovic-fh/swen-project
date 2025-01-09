package org.example.application.entity;


/**
 * This enum contains all Card varations (name element and if the card is a spell)
 *
 * @author Aleksandar Latinovic
 * */
public enum CreatureType {
    // TODO vllt element Name
    WATER_GOBLIN("WaterGoblin", "goblin", "water", false),
    FIRE_GOBLIN("FireGoblin","goblin", "fire", false),
    REGULAR_GOBLIN("RegularGoblin","goblin", "normal", false),
    WATER_TROLL("WaterTroll","troll", "water", false),
    FIRE_TROLL("FireTroll","troll", "fire", false),
    REGULAR_TROLL("RegularTroll","troll", "normal", false),
    WATER_ELF("WaterElf","elf", "water", false),
    FIRE_ELF("FireElf","elf", "fire", false),
    REGULAR_ELF("RegularElf","elf", "normal", false),
    WATER_SPELL("WaterSpell","spell", "water", true),
    FIRE_SPELL("FireSpell","spell", "fire", true),
    REGULAR_SPELL("RegularSpell","spell", "normal", true),
    KNIGHT("Knight","knight", "normal", false),
    DRAGON("Dragon","dragon", "fire", false),
    ORK("Ork","ork", "normal", false),
    KRAKEN("Kraken","kraken", "water", false),
    WIZZARD("Wizzard","wizzard", "normal", false);

    private final String name;
    private final String category;
    private final String element;
    private final boolean isSpell;

    CreatureType(String name, String category, String element, boolean isSpell) {
        this.name = name;
        this.category = category;
        this.element = element;
        this.isSpell = isSpell;
    }

    public String getName() {
        return name;
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