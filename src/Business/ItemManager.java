package Business;

import Persistence.ItemJSON;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ItemManager {
    
    private final ItemJSON itemJson;

    public ItemManager(ItemJSON itemJson) {
        this.itemJson = itemJson;
    }

    public Long getIdByName(String name) throws FileNotFoundException {
        long id = 0;
        ArrayList<Item> itemList = itemJson.readAll();

        for (Item item : itemList) {
            String characterName = item.getName();
            if (characterName.equals(name)) {
                id = item.getId();
            }
        }

        return (id);
    }

    public int getPowerByName(String name) throws FileNotFoundException {
        int power = 0;

        ArrayList<Item> itemList = itemJson.readAll();

        for (Item item : itemList) {
            String characterName = item.getName();
            if (characterName.equals(name)) {
                power = item.getPower();
            }
        }

        return (power);
    }

    public int getDurabilityByName(String name) throws FileNotFoundException {
        int power = 0;

        ArrayList<Item> itemList = itemJson.readAll();

        for (Item item : itemList) {
            String characterName = item.getName();
            if (characterName.equals(name)) {
                power = item.getDurability();
            }
        }

        return (power);
    }

    public String getClasseByName(String name) throws FileNotFoundException {
        String classe = "";

        ArrayList<Item> itemList = itemJson.readAll();

        for (Item item : itemList) {
            String characterName = item.getName();
            if (characterName.equals(name)) {
                classe = item.getClasse();
            }
        }

        return (classe);
    }

    public boolean fileOK(){
        return itemJson.fileOK();
    }

    public ArrayList<Item> readAllItems() throws FileNotFoundException {
        return itemJson.readAll();
    }

    public ArrayList<String> getNameOfItems() throws FileNotFoundException {
        ArrayList<String> nameList = new ArrayList<>();
        ArrayList<Item> itemList = itemJson.readAll();

        for (Item item : itemList) {
            nameList.add(item.getName());
        }
        return (nameList);
    }
}
