package Persistence;

import Business.Item;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.ArrayList;

/**
 * Manages the reading of item data from a JSON file and checks the file's existence.
 */
public class ItemJSON {
    /** Path to the JSON file containing item data. */
    private static final String FILE_PATH = "data/items.json";

    /**
     * Reads all item data from the JSON file and converts it into a list of Item objects.
     *
     * @return a list of Item objects read from the JSON file
     * @throws FileNotFoundException if the item data file is not found or cannot be accessed
     */
    public ArrayList<Item> readAll() throws FileNotFoundException {
        FileReader reader = new FileReader(FILE_PATH);
        Gson gson = new Gson();
        JsonArray elements = JsonParser.parseReader(reader).getAsJsonArray();
        ArrayList<Item> resultItems = new ArrayList<>();

        for (JsonElement jsonElement : elements) {
            Item item = gson.fromJson(jsonElement, Item.class);
            resultItems.add(item);
        }
        return resultItems;
    }

    /**
     * Checks if the item data file exists and is a valid file.
     *
     * @return true if the item data file exists and is a valid file, false otherwise
     */
    public boolean fileOK() {
        File itemFile = new File(FILE_PATH);
        return itemFile.exists() && itemFile.isFile();
    }
}
