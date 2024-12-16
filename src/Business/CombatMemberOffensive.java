package Business;

public class CombatMemberOffensive extends CombatMember{

    private static final String ATTACK = "ATTACK";
    private static final String NEW_WEAPON = "NEW_WEAPON";

    public CombatMemberOffensive(Character character, String strategy, Item weapon, Item armor) {
        super(character, strategy, weapon, armor);
    }

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
