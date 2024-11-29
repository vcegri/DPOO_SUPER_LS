package Business;

/**
 * Represents an item with unique properties such as ID, name, power, durability, and class.
 */
public class Item {

    /** The unique identifier of the item. */
    private final long id;

    /** The name of the item. */
    private final String name;

    /** The power level of the item. */
    private final int power;

    /** The durability of the item, which decreases with use. */
    private int durability;

    /** The class or type of the item (e.g., weapon, armor, etc.). */
    private final String classe;

    /**
     * Constructs a new Item with the specified properties.
     *
     * @param id         the unique identifier of the item
     * @param name       the name of the item
     * @param power      the power level of the item
     * @param durability the initial durability of the item
     * @param classe     the class or type of the item
     */
    public Item(int id, String name, int power, int durability, String classe) {
        this.id = id;
        this.name = name;
        this.power = power;
        this.durability = durability;
        this.classe = classe;
    }

    /**
     * Returns the unique identifier of the item.
     *
     * @return the item's ID
     */
    public long getId() {
        return id;
    }

    /**
     * Returns the name of the item.
     *
     * @return the item's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the power level of the item.
     *
     * @return the item's power
     */
    public int getPower() {
        return power;
    }

    /**
     * Returns the current durability of the item.
     *
     * @return the item's durability
     */
    public int getDurability() {
        return durability;
    }

    /**
     * Returns the class or type of the item.
     *
     * @return the item's class
     */
    public String getClasse() {
        return classe;
    }

    /**
     * Decreases the item's durability by one. Should be called when the item is used.
     */
    public void updateDurability() {
        this.durability--;
    }
}
