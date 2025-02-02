package Business;

/**
 * Represents a CombatMember with balanced strategy.
 */

public class CombatMemberBalanced extends CombatMember {

    private static final double MIN_DAMAGE = 0.5;
    private static final double MAX_DAMAGE = 1.0;
    private static final String ATTACK = "ATTACK";
    private static final String DEFEND = "DEFEND";
    private static final String NEW_WEAPON = "NEW_WEAPON";

    /** Indicates when this CombatMember is defending */
    private boolean defending;

    /** Indicates when this CombatMember is going to defend */
    private boolean defendRequest;
    /**
     * Constructs a new CombatMemberBalanced object with the specified parameters.
     *
     * @param character  character assigned to the member.
     * @param strategy strategy assigned.
     * @param weapon weapon assigned.
     * @param armor armor assigned.
     */
    public CombatMemberBalanced(Character character, String strategy, Item weapon, Item armor) {
        super(character, strategy, weapon, armor);
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
     * Checks if a CombatMember has high damage based on their damage stat and need to defend.
     *
     * @return true if the CombatMember has high damage, false if not
     */
    public boolean hasHighDamage() {
        return getDamage() >= MIN_DAMAGE && getDamage() <= MAX_DAMAGE;
    }

    /**
     * Determinate the action that the member make.
     *
     * @return A string representing the chosen action.
     */
    @Override
    public String chooseAction(){
        String action = "";

        if (!isKo()) {
            if (!hasWeapon()) {
                action = NEW_WEAPON;
            } else {
                if (hasArmor() && hasHighDamage()) {
                    this.setDefendingStatus(true);
                    action = DEFEND;
                } else {
                    action = ATTACK;
                }
            }
        }

        return action;
    }
}
