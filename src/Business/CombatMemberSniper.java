package Business;

public class CombatMemberSniper extends CombatMember {

    public CombatMemberSniper(Character character, String strategy, Item weapon, Item armor) {
        super(character, strategy, weapon, armor);
    }

    @Override
    public String chooseAction(){
        String string = "";
        return string;
    }
}
