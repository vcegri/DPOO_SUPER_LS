package Business;

import Persistence.ItemJSON;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ItemManager {
    
    private ItemJSON itemJson;

    public ItemManager(ItemJSON itemJson) {
        this.itemJson = itemJson;
    }

    public int getIdByName(String name) throws FileNotFoundException {
        int id = 0;
        ArrayList<Item> itemList = itemJson.readAll();

        for (int i = 0; i < itemList.size(); i++) {
            String characterName = itemList.get(i).getName();
            if (characterName.equals(name)){
                id = itemList.get(i).getId();
            }
        }

        return (id);
    }

    public int getPowerByName(String name) throws FileNotFoundException {
        int power = 0;

        ArrayList<Item> itemList = itemJson.readAll();

        for (int i = 0; i < itemList.size(); i++) {
            String characterName = itemList.get(i).getName();
            if (characterName.equals(name)){
                power = itemList.get(i).getPower();
            }
        }

        return (power);
    }

    public int getDurabilityByName(String name) throws FileNotFoundException {
        int power = 0;

        ArrayList<Item> itemList = itemJson.readAll();

        for (int i = 0; i < itemList.size(); i++) {
            String characterName = itemList.get(i).getName();
            if (characterName.equals(name)){
                power = itemList.get(i).getDurability();
            }
        }

        return (power);
    }

    public String getClasseByName(String name) throws FileNotFoundException {
        String classe = "";

        ArrayList<Item> itemList = itemJson.readAll();

        for (int i = 0; i < itemList.size(); i++) {
            String characterName = itemList.get(i).getName();
            if (characterName.equals(name)){
                classe = itemList.get(i).getClasse();
            }
        }

        return (classe);
    }

    public boolean fileOK(){
        return itemJson.fileOK();
    }
}
