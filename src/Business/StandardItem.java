package Business;

public class StandardItem extends Item {

    public StandardItem(long id, String name, int power, int durability, String classe) {
        super(id, name, power, durability, classe);
    }

    @Override
    public  double getItemUtilityPower(int weight){

        return getPower();
    }
}
