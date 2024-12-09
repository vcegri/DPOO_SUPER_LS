package Business;

public class SuperItem extends Item{

    public SuperItem(int id, String name, int power, int durability, String classe) {
        super(id, name, power, durability, classe);
    }

    public double getItemUtilityPower(int weight){

        return getPower() * weight;
    }
}
