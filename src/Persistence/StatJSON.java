package Persistence;

import Business.Stat;
import Business.Team;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class StatJSON {
    private static final String FILE_PATH = "data/teams.json";

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

    public void saveTeams(ArrayList<Stat> statList) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            Gson gson = new Gson();
            gson.toJson(statList.toArray(), writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteCharacter(Stat stat, ArrayList<Stat> statList) {
        statList.remove(stat);
        saveTeams(statList);
    }
}
