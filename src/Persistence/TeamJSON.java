package Persistence;

import Business.Team;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.ArrayList;

/**
 * Is used to read and write team data at the teams.json file.
 */
public class TeamJSON {

    /** Path to the teams.json file that contains all the team data. */
    private static final String FILE_PATH = "data/teams.json";

    /**
     * Reads all the team data at the teams.json file and converts it into a list of Teams.
     *
     * @return a list of all the Teams that the teams.json file contains
     * @throws FileNotFoundException if the teams.json file is not found or can't be accessed
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
     * Update the list of teams at the teams.json file.
     *
     * @param teamList the list of Teams to update
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
