package Business;
/**
 * Represents a CombatMember with offensive strategy.
 */
public class CombatMemberOffensive extends CombatMember{

    private static final String ATTACK = "ATTACK";
    private static final String NEW_WEAPON = "NEW_WEAPON";
    /**
     * Constructs a new CombatMemberOffensive object with the specified parameters.
     *
     * @param character  character assigned to the member.
     * @param strategy strategy assigned.
     * @param weapon weapon assigned.
     * @param armor armor assigned.
     */
    public CombatMemberOffensive(Character character, String strategy, Item weapon, Item armor) {
        super(character, strategy, weapon, armor);
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
            }
            else {
                action = ATTACK;
            }
        }

        return action;
    }
}
