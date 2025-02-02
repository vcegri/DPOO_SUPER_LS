package Business;

/**
 * Represents a super item.
 */

public class SuperItem extends Item{

    /**
     * Constructs a new SuperItem object with the specified parameters.
     *
     * @param id  identifier of the item.
     * @param name name of the item.
     * @param power power of the item.
     * @param durability durability of the item.
     * @param classe class of the item.
     */

    public SuperItem(long id, String name, int power, int durability, String classe) {
        super(id, name, power, durability, classe);
    }

    /**
     * Returns the power of the item.
     *
     * @param weight weight of the character.
     * @return power of the item.
     */
    @Override
    public  double getItemUtilityPower(int weight){

        return getPower() * weight;
    }
}
