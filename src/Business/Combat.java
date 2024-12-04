package Business;

import java.util.ArrayList;

/**
 * Represents a combat scenario involving teams and combat members.
 */
public class Combat {

    /** The list of combat members participating in the combat. */
    private final ArrayList<CombatMember> combatMemberList;

    /** The list of teams involved in the combat. */
    private ArrayList<Team> teamList;

    /**
     * Constructs a new Combat instance, initializing empty lists for combat members and teams.
     */
    public Combat() {
        this.combatMemberList = new ArrayList<>();
        this.teamList = new ArrayList<>();
    }

    /**
     * Returns the list of combat members participating in the combat.
     *
     * @return a list of CombatMember
     */
    public ArrayList<CombatMember> getCombatMemberList() {
        return combatMemberList;
    }

    /**
     * Returns the list of teams involved in the combat.
     *
     * @return a list of Team
     */
    public ArrayList<Team> getTeamList() {
        return teamList;
    }

    /**
     * Sets the list of teams involved in the combat.
     *
     * @param teamList the list of Team to set
     */
    public void setTeamList(ArrayList<Team> teamList) {
        this.teamList = teamList;
    }

    /**
     * Creates a new combat member and adds it to the combat member list.
     *
     * @param character the character associated with the combat member
     * @param strategy  the strategy employed by the combat member
     * @param weapon    the weapon equipped by the combat member
     * @param armor     the armor equipped by the combat member
     */
    public void createCombatMember(Character character, String strategy, Item weapon, Item armor) {
        CombatMember combatMember = new CombatMember(character, strategy, weapon, armor);
        this.combatMemberList.add(combatMember);
    }

    /**
     * Ends the combat by clearing the team and combat member lists.
     */
    public void endCombat() {
        this.teamList.clear();
        this.combatMemberList.clear();
    }
}
