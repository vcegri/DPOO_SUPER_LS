package Business;

import java.util.ArrayList;

public class Combat {
    private ArrayList<CombatMember> combatMemberList;
    private ArrayList<Team> teamList;

    public Combat(ArrayList<CombatMember> combatMemberList, ArrayList<Team> teamList) {
        this.combatMemberList = combatMemberList;
        this.teamList = teamList;
    }

    public ArrayList<CombatMember> getCombatMemberList() {
        return combatMemberList;
    }

    public ArrayList<Team> getTeamList() {
        return teamList;
    }
}