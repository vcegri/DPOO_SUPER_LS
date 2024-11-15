package Business;

public class Item {

    private final int id;
    private final String name;
    private final int power;
    private final int durability;
    private final String classe;

    public Item(int id, String name, int power, int durability, String classe) {
        this.id = id;
        this.name = name;
        this.power = power;
        this.durability = durability;
        this.classe = classe;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPower() {
        return power;
    }

    public int getDurability() {
        return durability;
    }

    public String getClasse() {
        return classe;
    }
}
