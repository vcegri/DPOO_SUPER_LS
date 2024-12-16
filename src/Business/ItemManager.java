package Business;

import Persistence.*;
import edu.salle.url.api.ApiHelper;
import edu.salle.url.api.exception.ApiException;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Manages the interactions with item data using the characterJson class.
 */
public class ItemManager {

    public static final String WEAPON = "Weapon";
    public static final String ARMOR = "Armor";
    public static final String SUPER_ARMOR = "Superarmor";
    public static final String SUPER_WEAPON = "Superweapon";

    /** Class to manage the items.json file. */
    private ItemDAO itemDao;

    /**
     * Constructs a ItemManager with the ItemJSON class.
     */
    public ItemManager() {
        try {
            ApiHelper api = new ApiHelper();
            itemDao = new ItemAPI();
        }catch (ApiException e){
            itemDao = new ItemAPI();
        }
    }

    /**
     * Get the ID of an item by its name.
     *
     * @param name name of the item
     * @return ID of the item
     * @throws FileNotFoundException if the item data can't be read
     */
    public Long getIdByName(String name) throws FileNotFoundException, ApiException {
        long id = 0;
        ArrayList<Item> itemList = itemDao.readAll();

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
    public int getPowerByName(String name) throws FileNotFoundException, ApiException {
        int power = 0;

        ArrayList<Item> itemList = itemDao.readAll();

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
    public int getDurabilityByName(String name) throws FileNotFoundException, ApiException {
        int durability = 0;

        ArrayList<Item> itemList = itemDao.readAll();

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
    public String getClasseByName(String name) throws FileNotFoundException, ApiException {
        String classe = "";

        ArrayList<Item> itemList = itemDao.readAll();

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
    public ArrayList<String> getNameOfItems() throws FileNotFoundException, ApiException {
        ArrayList<String> nameList = new ArrayList<>();
        ArrayList<Item> itemList = itemDao.readAll();

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
    public Item setRandomWeapon() throws FileNotFoundException, ApiException {
        boolean found = true;
        Random random = new Random();
        ArrayList<Item> itemList = itemDao.readAll();
        int randomWeapon = random.nextInt(itemList.size());
        do {
            if (itemList.get(randomWeapon).getClasse().equals(ARMOR)) {
                randomWeapon = random.nextInt(itemList.size());
                found = false;
            }
            if (itemList.get(randomWeapon).getClasse().equals(SUPER_ARMOR)) {
                randomWeapon = random.nextInt(itemList.size());
                found = false;
            }
        } while (!found);

        return itemList.get(randomWeapon);
    }

    /**
     * Sets a random armor for a CombatMember.
     *
     * @return random Item representing armor
     */
    public Item setRandomArmor() throws FileNotFoundException, ApiException {
        boolean found = true;
        Random random = new Random();
        ArrayList<Item> itemList = itemDao.readAll();
        int randomArmor = random.nextInt(itemList.size());
        do {
            if (itemList.get(randomArmor).getClasse().equals(WEAPON)) {
                randomArmor = random.nextInt(itemList.size());
                found = false;
            }
            if (itemList.get(randomArmor).getClasse().equals(SUPER_WEAPON)) {
                randomArmor = random.nextInt(itemList.size());
                found = false;
            }
        } while (!found);

        return itemList.get(randomArmor);
    }

    /**
     * Checks if the items.json file is accessible.
     *
     * @return true if the file is accessible, false if not
     */
    public int fileOK() {
        return itemDao.fileOK();
    }
}
