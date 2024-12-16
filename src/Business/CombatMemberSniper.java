package Business;

public class CombatMemberSniper extends CombatMember {
    private static final String ATTACK = "ATTACK";

    public CombatMemberSniper(Character character, String strategy, Item weapon, Item armor) {
        super(character, strategy, weapon, armor);
    }

    @Override
    public String chooseAction(){
        return ATTACK;
    }
}
