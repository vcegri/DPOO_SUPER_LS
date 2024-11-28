package Business;

public class CombatMember {

    private final Character character;
    private final String strategy;
    private double damage;
    private final Item weapon;
    private final Item armor;
    private final boolean ko;

    public CombatMember(Character character, String strategy, Item weapon, Item armor) {
        this.character = character;
        this.strategy = strategy;
        this.damage = 0;
        this.weapon = weapon;
        this.armor = armor;
        this.ko = false;
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

    public boolean isKo() {
        return ko;
    }

    public void updateDamage(double newDamage) {
        this.damage = this.damage + newDamage;
    }
}
