package Business;

/**
 * Represents a CombatMember with sniper strategy.
 */
public class CombatMemberSniper extends CombatMember {
    private static final String ATTACK = "ATTACK";

    /**
     * Constructs a new CombatMemberSniper object with the specified parameters.
     *
     * @param character  character assigned to the member.
     * @param strategy strategy assigned.
     * @param weapon weapon assigned.
     * @param armor armor assigned.
     */
    public CombatMemberSniper(Character character, String strategy, Item weapon, Item armor) {
        super(character, strategy, weapon, armor);
    }

    /**
     * Determinate the action that the member make.
     *
     * @return A string representing the chosen action.
     */
    @Override
    public String chooseAction(){
        return ATTACK;
    }
}
