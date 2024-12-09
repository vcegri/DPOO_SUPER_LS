package Business;

public class CombatMemberOffensive extends CombatMember{

    public CombatMemberOffensive(Character character, String strategy, Item weapon, Item armor) {
        super(character, strategy, weapon, armor);
    }

    @Override
    public String chooseAction(){
        String action = "";

        if (!isKo()) {
            if (!hasWeapon()) {
                action = "NEW_WEAPON";
            }
            else {
                action = "ATACK";
            }
        }

        return action;
    }
}
