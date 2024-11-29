package Business;

import Persistence.ItemJSON;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Manages the interactions with item data, including retrieving item details such as ID, power, durability,
 * and class, as well as reading all items from the storage.
 */
public class ItemManager {

    /** The ItemJSON instance used to read item data. */
    private final ItemJSON itemJson;

    /**
     * Constructs an ItemManager with the specified ItemJSON instance.
     *
     * @param itemJson the ItemJSON instance to interact with for item data
     */
    public ItemManager(ItemJSON itemJson) {
        this.itemJson = itemJson;
    }

    /**
     * Retrieves the ID of an item based on its name.
     *
     * @param name the name of the item
     * @return the ID of the item if found, or 0 if not found
     * @throws FileNotFoundException if the item data cannot be read from storage
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
     * Retrieves the power of an item based on its name.
     *
     * @param name the name of the item
     * @return the power of the item if found, or 0 if not found
     * @throws FileNotFoundException if the item data cannot be read from storage
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
     * Retrieves the durability of an item based on its name.
     *
     * @param name the name of the item
     * @return the durability of the item if found, or 0 if not found
     * @throws FileNotFoundException if the item data cannot be read from storage
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
     * Retrieves the class of an item based on its name.
     *
     * @param name the name of the item
     * @return the class of the item if found, or an empty string if not found
     * @throws FileNotFoundException if the item data cannot be read from storage
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
     * Checks if the item data file is accessible and valid.
     *
     * @return true if the file is accessible and valid, false otherwise
     */
    public boolean fileOK() {
        return itemJson.fileOK();
    }

    /**
     * Reads and returns all items from the item data source.
     *
     * @return a list of all items
     * @throws FileNotFoundException if the item data cannot be read from storage
     */
    public ArrayList<Item> readAllItems() throws FileNotFoundException {
        return itemJson.readAll();
    }

    /**
     * Retrieves the names of all items.
     *
     * @return a list of item names
     * @throws FileNotFoundException if the item data cannot be read from storage
     */
    public ArrayList<String> getNameOfItems() throws FileNotFoundException {
        ArrayList<String> nameList = new ArrayList<>();
        ArrayList<Item> itemList = itemJson.readAll();

        for (Item item : itemList) {
            nameList.add(item.getName());
        }
        return nameList;
    }
}
