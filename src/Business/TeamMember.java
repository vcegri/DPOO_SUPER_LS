package Business;

/**
 * Represents a member of a team, identified by an ID and a strategy.
 */
public class TeamMember {

    /** The unique identifier of the team member. */
    private final long id;

    /** The strategy assigned to the team member. */
    private final String strategy;

    /**
     * Constructs a new {@code TeamMember} with the specified ID and strategy.
     *
     * @param id       the unique identifier of the team member
     * @param strategy the strategy assigned to the team member
     */
    public TeamMember(long id, String strategy) {
        this.id = id;
        this.strategy = strategy;
    }

    /**
     * Returns the unique identifier of the team member.
     *
     * @return the team member's ID
     */
    public long getId() {
        return id;
    }

    /**
     * Returns the strategy assigned to the team member.
     *
     * @return the team member's strategy
     */
    public String getStrategy() {
        return strategy;
    }
}
