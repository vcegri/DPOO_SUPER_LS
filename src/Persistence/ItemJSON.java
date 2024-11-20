package Persistence;

import Business.Item;
import Business.Team;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.ArrayList;

public class ItemJSON {
    private static final String FILE_PATH = "data/items.json";

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

    public boolean fileOK() {
        File itemFile = new File(FILE_PATH);
        return itemFile.exists() && itemFile.isFile();
    }

}
