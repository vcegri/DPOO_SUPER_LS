package Business;

import java.util.ArrayList;

public class Combat {
    private ArrayList<CombatMember> combatMemberList;
    private ArrayList<Team> teamList;

    public Combat() {
        this.combatMemberList = new ArrayList<>();
        this.teamList = new ArrayList<>();
    }

    public ArrayList<CombatMember> getCombatMemberList() {
        return combatMemberList;
    }

    public ArrayList<Team> getTeamList() {
        return teamList;
    }

    public void setTeamList(ArrayList<Team> teamList) {
        this.teamList = teamList;
    }

    public void createCombatMember(Character character,String strategy, Item weapon, Item armor) {
        CombatMember combatMember = new CombatMember(character,strategy, weapon, armor);
        this.combatMemberList.add(combatMember);
    }

    public void endCombat() {
        this.teamList.clear();
        this.combatMemberList.clear();
    }
}