package Business;

public class Character {
    private final long id;
    private final String name;
    private final int weight;

    public Character(int id, String name, int weight) {
        this.id = id;
        this.name = name;
        this.weight = weight;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }
}
