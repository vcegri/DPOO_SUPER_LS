package Business;

import java.util.ArrayList;

/**
 * Represents a team consisting of a name and a list of members.
 */
public class Team {

    /** The name of the team. */
    private final String name;

    /** The list of members in the team. */
    private final ArrayList<TeamMember> members;

    /**
     * Constructs a new Team with the specified name and list of members.
     *
     * @param name       the name of the team
     * @param memberList the list of {@code TeamMember} objects representing the members of the team
     */
    public Team(String name, ArrayList<TeamMember> memberList) {
        this.name = name;
        this.members = memberList;
    }

    /**
     * Returns the name of the team.
     *
     * @return the team's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the list of members in the team.
     *
     * @return a list of TeamMember objects
     */
    public ArrayList<TeamMember> getMemberList() {
        return members;
    }
}
