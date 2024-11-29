package Persistence;

import Business.Stat;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Manages the reading, writing, and deletion of stat data from/to a JSON file.
 */
public class StatJSON {

    /** Path to the JSON file containing stat data. */
    private static final String FILE_PATH = "data/teams.json";

    /**
     * Reads all stat data from the JSON file and converts it into a list of Stat objects.
     *
     * @return a list of Stat objects read from the JSON file
     * @throws FileNotFoundException if the stat data file is not found or cannot be accessed
     */
    public ArrayList<Stat> readAll() throws FileNotFoundException {
        FileReader reader = new FileReader(FILE_PATH);
        Gson gson = new Gson();
        JsonArray elements = JsonParser.parseReader(reader).getAsJsonArray();
        ArrayList<Stat> resultStats = new ArrayList<>();

        for (JsonElement jsonElement : elements) {
            Stat stat = gson.fromJson(jsonElement, Stat.class);
            resultStats.add(stat);
        }
        return resultStats;
    }

    /**
     * Saves the updated list of stats back to the stat data JSON file.
     *
     * @param statList the list of Stat objects to save to the file
     */
    public void saveStatList(ArrayList<Stat> statList) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            Gson gson = new Gson();
            gson.toJson(statList.toArray(), writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a specific stat from the list and updates the stat data file.
     *
     * @param stat the stat to delete
     * @param statList the list of Stat objects to remove the stat from
     */
    public void deleteStat(Stat stat, ArrayList<Stat> statList) {
        statList.remove(stat);
        saveStatList(statList);
    }
}
