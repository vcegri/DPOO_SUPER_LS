package Business;

/**
 * Represents an item with properties such as ID, name, power, durability, and class.
 */
public abstract class Item {

    /** Identifier of the item. */
    private final long id;

    /** Name of the item. */
    private final String name;

    /** Power level of the item. */
    private final int power;

    /** Durability of the item */
    private int durability;

    /** Type of the item */
    private final String classe;

    /**
     * Constructs a new Item with the specified properties.
     *
     * @param id         identifier of the item
     * @param name       name of the item
     * @param power      power level of the item
     * @param durability initial durability of the item
     * @param classe     type of the item
     */
    public Item(long id, String name, int power, int durability, String classe) {
        this.id = id;
        this.name = name;
        this.power = power;
        this.durability = durability;
        this.classe = classe;
    }

    /**
     * Returns the identifier of the item.
     *
     * @return item ID
     */
    public long getId() {
        return id;
    }

    /**
     * Returns the name of the item.
     *
     * @return item name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the power of the item.
     *
     * @return item power
     */
    public int getPower() {
        return power;
    }

    /**
     * Returns the current durability of the item.
     *
     * @return item durability
     */
    public int getDurability() {
        return durability;
    }

    /**
     * Returns the class of the item.
     *
     * @return item class
     */
    public String getClasse() {
        return classe;
    }

    /**
     * Decreases the item durability by one when the item is used.
     */
    public void updateDurability() {
        this.durability--;
    }

    public abstract double getItemUtilityPower(int weight);
}
