package Persistence;

import Business.Item;
import com.google.gson.*;

import java.io.*;
import java.util.ArrayList;

/**
 * Is used to read and write team data at the items.json file.
 */
public class ItemJSON implements ItemDAO{
    /** Path to the items.json file that contains all the item data. */
    private static final String FILE_PATH = "data/items.json";

    /**
     * Reads all the item data at the items.json file and converts it into a list of Items.
     *
     * @return a list of all the Items that the items.json file contains
     * @throws FileNotFoundException if the items.json file is not found or can't be accessed
     */
    public ArrayList<Item> readAll() throws FileNotFoundException {
        FileReader reader = new FileReader(FILE_PATH);
        Gson gson = new GsonBuilder().registerTypeAdapter(Item.class, new ItemDeserializer()).create();
        JsonArray elements = JsonParser.parseReader(reader).getAsJsonArray();
        ArrayList<Item> resultItems = new ArrayList<>();

        for (JsonElement jsonElement : elements) {
            Item item = gson.fromJson(jsonElement, Item.class);
            resultItems.add(item);
        }
        return resultItems;
    }

    /**
     * Checks if the items.json file exists and is a valid file.
     *
     * @return true if all is okay
     */
    public int fileOK() {
        File itemFile = new File(FILE_PATH);
        int fileState = 2;
        if (itemFile.exists() && itemFile.isFile()) {
            fileState = 3;
        }
        return fileState;
    }
}
