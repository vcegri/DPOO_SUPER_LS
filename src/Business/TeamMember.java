package Business;

/**
 * Represents a member of a team, with an ID and a strategy.
 */
public class TeamMember {

    /** Identifier of the team member. */
    private final long id;

    /** Strategy assigned to the team member. */
    private final String strategy;

    /**
     * Constructs a new TeamMember with their ID and strategy.
     *
     * @param id       identifier of the team member
     * @param strategy strategy assigned to the team member
     */
    public TeamMember(long id, String strategy) {
        this.id = id;
        this.strategy = strategy;
    }

    /**
     * Returns the identifier of the team member.
     *
     * @return team member ID
     */
    public long getId() {
        return id;
    }

    /**
     * Returns the strategy assigned to the team member.
     *
     * @return team member strategy
     */
    public String getStrategy() {
        return strategy;
    }
}
