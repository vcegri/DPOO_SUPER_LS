package Business;

import java.util.ArrayList;

public class Combat {
    private final ArrayList<CombatMember> combatMemberList;
    private final ArrayList<Team> teamList;

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