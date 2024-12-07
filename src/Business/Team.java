package Business;

import java.util.ArrayList;

/**
 * Represents a team with a name and a list of TeamMembers.
 */
public class Team {

    /** Name of the team. */
    private final String name;

    /** List of members in the team. */
    private final ArrayList<TeamMember> members;

    /**
     * Constructs a new Team with its name and list of members.
     *
     * @param name name of the team
     * @param memberList list of TeamMembers
     */
    public Team(String name, ArrayList<TeamMember> memberList) {
        this.name = name;
        this.members = memberList;
    }

    /**
     * Returns the name of the team.
     *
     * @return team name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the list of members in the team.
     *
     * @return list of TeamMember
     */
    public ArrayList<TeamMember> getMemberList() {
        return members;
    }
}
