package Persistence;

import Business.Team;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.ArrayList;

/**
 * Manages the reading and writing of team data from/to a JSON file.
 */
public class TeamJSON {

    /** Path to the JSON file containing team data. */
    private static final String FILE_PATH = "data/teams.json";

    /**
     * Reads all team data from the JSON file and converts it into a list of Team objects.
     *
     * @return a list of Team objects read from the JSON file
     * @throws FileNotFoundException if the team data file is not found or cannot be accessed
     */
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

    /**
     * Saves the updated list of teams back to the team data JSON file.
     *
     * @param teamList the list of Team objects to save to the file
     */
    public void saveTeams(ArrayList<Team> teamList) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            Gson gson = new Gson();
            gson.toJson(teamList, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
