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
}