package Persistence;

import Business.Character;
import Business.Team;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.ArrayList;

public class TeamJSON {
    private static final String FILE_PATH = "data/teams.json";


    public ArrayList<Team> readAll() throws FileNotFoundException {
        FileReader reader = new FileReader(FILE_PATH);
        Gson gson = new Gson();
        JsonArray elements = JsonParser.parseReader(reader).getAsJsonArray();
        ArrayList<Team> resultTeams = new ArrayList<>();

        for (JsonElement jsonElement : elements) {
            Team team = gson.fromJson(jsonElement, Team.class);
            resultTeams.add(team);
        }
        return resultTeams;
    }

    public void saveTeams(ArrayList<Team> teamList) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            Gson gson = new Gson();
            gson.toJson(teamList, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
