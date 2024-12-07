package Business;

/**
 * Represents a member of a combat, with an associate character, strategy, equipment, and combat stats.
 */
public class CombatMember {

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

    /** Indicates when this CombatMember is defending */
    private boolean defending;

    /** Indicates when this CombatMember is going to defend */
    private boolean defendRequest;

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
        this.damage = 0;
        this.weapon = weapon;
        this.armor = armor;
        this.ko = false;
        this.defending = false;
        this.defendRequest = false;
        this.attacked = false;
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
     * Checks if the CombatMember is currently in a defending state.
     *
     * @return true if the CombatMember is defending, false if not.
     */
    public boolean isDefending() {
        return defending;
    }

    /**
     * Checks if the CombatMember has requested to defend in the current round.
     *
     * @return true if a defend request is active, false if not.
     */
    public boolean isDefendRequest() {
        return defendRequest;
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
     * Updates the defending request for the CombatMember.
     *
     * @param defendingStatus true if the CombatMember requests to defend, false if not.
     */
    public void setDefendingStatus(boolean defendingStatus) {
        this.defendRequest = defendingStatus;
    }

    /**
     * Sets the defending state of the CombatMember.
     *
     * @param defendingStatus true if the CombatMember is defending, false if not.
     */
    public void setDefending(boolean defendingStatus) {
        this.defending = defendingStatus;
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
    public void setAtacked(boolean attacked) {
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
}
