package Business;

/**
 * Represents a member participating in a combat, with a character, strategy, equipment, and combat status.
 */
public class CombatMember {

    /** The character associated with this combat member. */
    private final Character character;

    /** The strategy employed by this combat member. */
    private final String strategy;

    /** The total damage dealt by this combat member. */
    private double damage;

    /** The weapon equipped by this combat member. */
    private Item weapon;

    /** The armor equipped by this combat member. */
    private Item armor;

    /** Indicates whether this combat member is knocked out (KO). */
    private boolean ko;

    private boolean defending;

    private boolean defendRequest;

    /**
     * Constructs a new CombatMember with the specified character, strategy, weapon, and armor.
     *
     * @param character the character associated with the combat member
     * @param strategy  the strategy employed by the combat member
     * @param weapon    the weapon equipped by the combat member
     * @param armor     the armor equipped by the combat member
     */
    public CombatMember(Character character, String strategy, Item weapon, Item armor) {
        this.character = character;
        this.strategy = strategy;
        this.damage = 0;
        this.weapon = weapon;
        this.armor = armor;
        this.ko = false;
        this.defending = false;
        this.defendRequest = false;
    }

    /**
     * Returns the character associated with this combat member.
     *
     * @return the character
     */
    public Character getCharacter() {
        return character;
    }

    /**
     * Returns the total damage dealt by this combat member.
     *
     * @return the total damage
     */
    public double getDamage() {
        return damage;
    }

    /**
     * Returns the weapon equipped by this combat member.
     *
     * @return the weapon
     */
    public Item getWeapon() {
        return weapon;
    }

    /**
     * Returns the armor equipped by this combat member.
     *
     * @return the armor
     */
    public Item getArmor() {
        return armor;
    }

    /**
     * Indicates whether this combat member is knocked out (KO).
     *
     * @return true if the combat member is KO; {@code false} otherwise
     */
    public boolean isKo() {
        return ko;
    }

    public boolean isDefending() {
        return defending;
    }

    public boolean isDefendRequest() {
        return defendRequest;
    }

    /**
     * Updates the total damage dealt by this combat member.
     *
     * @param newDamage the additional damage to add
     */
    public void updateDamage(double newDamage) {
        this.damage = this.damage + newDamage;
    }

    public void setKo(){
        this.ko = true;
    }

    public void setDefendingStatus(boolean defendingStatus) {
        this.defendRequest = defendingStatus;
    }

    public void setDefending(boolean defendingStatus) {
        this.defending = defendingStatus;
    }

    public void setWeapon(Item weapon) {
        this.weapon = weapon;
    }

    public void setArmor(Item armor) {
        this.armor = armor;
    }
}
