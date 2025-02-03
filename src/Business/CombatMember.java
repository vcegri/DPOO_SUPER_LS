package Business;

/**
 * Represents a member of a combat, with an associate character, strategy, equipment, and combat stats.
 */
public abstract class CombatMember {

    private static final int DEFAULT_VALUE = 0;

    /** Character associated with the CombatMember. */
    private final Character character;

    /** Strategy employed by the CombatMember. */
    private final String strategy;

    /** Total damage dealt by the CombatMember. */
    private double damage;

    /** Weapon equipped by the CombatMember. */
    private Item weapon;

    /** Armor equipped by the CombatMember. */
    private Item armor;

    /** Indicates when this CombatMember is KO. */
    private boolean ko;

    /** Indicates when this CombatMember is attacking */
    private boolean attacked;

    /**
     * Constructs a new CombatMember with the specified character, strategy, weapon, and armor.
     *
     * @param character character associated with the CombatMember
     * @param strategy  strategy employed by the CombatMember
     * @param weapon    weapon equipped by the CombatMember
     * @param armor     armor equipped by the CombatMember
     */
    public CombatMember(Character character, String strategy, Item weapon, Item armor) {
        this.character = character;
        this.strategy = strategy;
        this.damage = DEFAULT_VALUE;
        this.weapon = weapon;
        this.armor = armor;
        this.ko = false;
        this.attacked = false;
    }

    /**
     * Returns the strategy of the CombatMember.
     *
     * @return strategy of the CombatMember
     */
    public String getStrategy() {
        return strategy;
    }

    /**
     * Returns the character associated with the CombatMember.
     *
     * @return character of the CombatMember
     */
    public Character getCharacter() {
        return character;
    }

    /**
     * Returns the total damage received by the CombatMember.
     *
     * @return total damage
     */
    public double getDamage() {
        return damage;
    }

    /**
     * Returns the weapon equipped by the CombatMember.
     *
     * @return using weapon
     */
    public Item getWeapon() {
        return weapon;
    }

    /**
     * Returns the armor equipped by the CombatMember.
     *
     * @return using armor
     */
    public Item getArmor() {
        return armor;
    }

    /**
     * Indicates when the CombatMember is KO.
     *
     * @return true if the CombatMember is KO, false if not
     */
    public boolean isKo() {
        return ko;
    }

    /**
     * Checks if the CombatMember has been attacked in the current round.
     *
     * @return true if the CombatMember has been attacked, false if not.
     */
    public boolean isAttacked() {
        return attacked;
    }

    /**
     * Sets the CombatMember as KO.
     */
    public void setKo(){
        this.ko = true;
    }

    /**
     * Assigns a weapon to the CombatMember.
     *
     * @param weapon weapon item to be assigned.
     */
    public void setWeapon(Item weapon) {
        this.weapon = weapon;
    }

    /**
     * Assigns armor to the CombatMember.
     *
     * @param armor armor item to be assigned.
     */
    public void setArmor(Item armor) {
        this.armor = armor;
    }

    /**
     * Sets the attacked state of the CombatMember.
     *
     * @param attacked true if the CombatMember has been attacked, false if not.
     */
    public void setAttacked(boolean attacked) {
        this.attacked = attacked;
    }

    /**
     * Updates the total damage received by the CombatMember.
     *
     * @param newDamage damage to add
     */
    public void updateDamage(double newDamage) {
        this.damage = this.damage + newDamage;
    }

    /**
     * Checks if a CombatMember has a weapon assigned.
     *
     * @return true if the CombatMember has a weapon, false if not
     */
    public boolean hasWeapon() {
        return getWeapon() != null;
    }

    /**
     * Checks if a CombatMember has armor assigned.
     *
     * @return true if the CombatMember has armor, false if not
     */
    public boolean hasArmor() {
        return getArmor() != null;
    }

    /**
     * Determinate the action that the member make.
     *
     * @return A string representing the chosen action.
     */
    public abstract String chooseAction();
}