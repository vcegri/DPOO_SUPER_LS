package Business;

/**
 * Represents a character with an ID, name, and weight.
 */
public class Character {

    /** The unique identifier of the character. */
    private final long id;

    /** The name of the character. */
    private final String name;

    /** The weight of the character. */
    private final int weight;

    /**
     * Constructs a new Character with the specified ID, name, and weight.
     *
     * @param id    the unique identifier of the character
     * @param name  the name of the character
     * @param weight the weight of the character
     */
    public Character(int id, String name, int weight) {
        this.id = id;
        this.name = name;
        this.weight = weight;
    }

    /**
     * Returns the unique identifier of the character.
     *
     * @return the character's ID
     */
    public long getId() {
        return id;
    }

    /**
     * Returns the name of the character.
     *
     * @return the character's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the weight of the character.
     *
     * @return the character's weight
     */
    public int getWeight() {
        return weight;
    }
}
