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
 * Is used to read and write team data at the stats.json file.
 */
public class StatJSON {

    /** Path to the stats.json file that contains all the stat data. */
    private static final String FILE_PATH = "data/teams.json";

    /**
     * Reads all the stat data at the stats.json file and converts it into a list of Stats.
     *
     * @return a list of all the Stats that the stats.json file contains
     * @throws FileNotFoundException if the stats.json file is not found or can't be accessed
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
     * Update the list of stats at the stats.json file.
     *
     * @param statList the list of Stats to update
     */
    public void saveStatList(ArrayList<Stat> statList) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            Gson gson = new Gson();
            gson.toJson(statList.toArray(), writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
