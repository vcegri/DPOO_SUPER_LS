package Business;

import Persistence.ItemJSON;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Manages the interactions with item data using the characterJson class.
 */
public class ItemManager {

    /** Class to manage the items.json file. */
    private final ItemJSON itemJson;

    /**
     * Constructs a ItemManager with the ItemJSON class.
     *
     * @param itemJson class to manage the items.json file
     */
    public ItemManager(ItemJSON itemJson) {
        this.itemJson = itemJson;
    }

    /**
     * Get the ID of an item by its name.
     *
     * @param name name of the item
     * @return ID of the item
     * @throws FileNotFoundException if the item data can't be read
     */
    public Long getIdByName(String name) throws FileNotFoundException {
        long id = 0;
        ArrayList<Item> itemList = itemJson.readAll();

        for (Item item : itemList) {
            String characterName = item.getName();
            if (characterName.equals(name)) {
                id = item.getId();
            }
        }

        return id;
    }

    /**
     * Get the power of an item by its name.
     *
     * @param name name of the item
     * @return power of the item
     * @throws FileNotFoundException if the item data can't be read
     */
    public int getPowerByName(String name) throws FileNotFoundException {
        int power = 0;

        ArrayList<Item> itemList = itemJson.readAll();

        for (Item item : itemList) {
            String characterName = item.getName();
            if (characterName.equals(name)) {
                power = item.getPower();
            }
        }

        return power;
    }

    /**
     * Get the durability of an item by its name.
     *
     * @param name name of the item
     * @return durability of the item
     * @throws FileNotFoundException if the item data can't be read
     */
    public int getDurabilityByName(String name) throws FileNotFoundException {
        int durability = 0;

        ArrayList<Item> itemList = itemJson.readAll();

        for (Item item : itemList) {
            String characterName = item.getName();
            if (characterName.equals(name)) {
                durability = item.getDurability();
            }
        }

        return durability;
    }

    /**
     * Get the class of an item by its name.
     *
     * @param name name of the item
     * @return class of the item
     * @throws FileNotFoundException if the item data can't be read
     */
    public String getClasseByName(String name) throws FileNotFoundException {
        String classe = "";

        ArrayList<Item> itemList = itemJson.readAll();

        for (Item item : itemList) {
            String characterName = item.getName();
            if (characterName.equals(name)) {
                classe = item.getClasse();
            }
        }

        return classe;
    }

    /**
     * Get the names of all items.
     *
     * @return list of item names
     * @throws FileNotFoundException if the item data can't be read
     */
    public ArrayList<String> getNameOfItems() throws FileNotFoundException {
        ArrayList<String> nameList = new ArrayList<>();
        ArrayList<Item> itemList = itemJson.readAll();

        for (Item item : itemList) {
            nameList.add(item.getName());
        }
        return nameList;
    }

    /**
     * Sets a random weapon for a CombatMember.
     *
     * @return random Item representing a weapon
     */
    public Item setRandomWeapon() throws FileNotFoundException {
        Random random = new Random();
        ArrayList<Item> itemList = itemJson.readAll();
        int randomWeapon = random.nextInt(itemList.size());
        do {
            if (itemList.get(randomWeapon).getClasse().equals("Armor")) {
                randomWeapon = random.nextInt(itemList.size());
            }
        } while (itemList.get(randomWeapon).getClasse().equals("Armor"));

        return itemList.get(randomWeapon);
    }

    /**
     * Sets a random armor for a CombatMember.
     *
     * @return random Item representing armor
     */
    public Item setRandomArmor() throws FileNotFoundException {
        Random random = new Random();
        ArrayList<Item> itemList = itemJson.readAll();
        int randomArmor = random.nextInt(itemList.size());
        do {
            if (itemList.get(randomArmor).getClasse().equals("Weapon")) {
                randomArmor = random.nextInt(itemList.size());
            }
        } while (itemList.get(randomArmor).getClasse().equals("Weapon"));

        return itemList.get(randomArmor);
    }

    /**
     * Checks if the items.json file is accessible.
     *
     * @return true if the file is accessible, false if not
     */
    public boolean fileOK() {
        return itemJson.fileOK();
    }
}
