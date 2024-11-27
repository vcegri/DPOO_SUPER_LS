package Business;

public class CombatMember {

    private final Character character;
    private final String strategy;
    private final double damage;
    private final Item weapon;
    private final Item armor;

    public CombatMember(Character character, String strategy, Item weapon, Item armor) {
        this.character = character;
        this.strategy = strategy;
        this.damage = 0;
        this.weapon = weapon;
        this.armor = armor;
    }

    public Character getCharacter() {
        return character;
    }

    public String getStrategy() {
        return strategy;
    }

    public double getDamage() {
        return damage;
    }

    public Item getWeapon() {
        return weapon;
    }

    public Item getArmor() {
        return armor;
    }
}
