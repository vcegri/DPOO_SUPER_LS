package Business;

public class TeamMember {
    private final long id;
    private final String strategy;

    public TeamMember(long id, String strategy) {
        this.id = id;
        this.strategy = strategy;
    }

    public long getId() {
        return id;
    }

    public String getStrategy() {
        return strategy;
    }
}
