package Business;

public class TeamMember {
    private int id;
    private String strategy;

    public TeamMember(int id, String strategy) {
        this.id = id;
        this.strategy = strategy;
    }

    public int getId() {
        return id;
    }

    public String getStrategy() {
        return strategy;
    }
}
