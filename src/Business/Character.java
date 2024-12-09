package Business;

/**
 * Represents a character with an ID, name, and weight.
 */
public class Character {

    /** Identifier of the character. */
    private final long id;

    /** Name of the character. */
    private final String name;

    /** Weight of the character. */
    private final int weight;

    /**
     * Constructs a new Character with the ID, name, and weight.
     *
     * @param id    identifier of the character
     * @param name  name of the character
     * @param weight weight of the character
     */
    public Character(int id, String name, int weight) {
        this.id = id;
        this.name = name;
        this.weight = weight;
    }

    /**
     * Returns the character identifier.
     *
     * @return character ID
     */
    public long getId() {
        return id;
    }

    /**
     * Returns the character name.
     *
     * @return character name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the character weight.
     *
     * @return character weight
     */
    public int getWeight() {
        return weight;
    }
}
