package Persistence;

import Business.Character;
import Business.Stat;
import Business.Team;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import edu.salle.url.api.ApiHelper;
import edu.salle.url.api.exception.ApiException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Is used to read and write team data at the teams API.
 */
public class TeamAPI implements TeamDAO{
    private static final String FILE_PATH = "https://balandrau.salle.url.edu/dpoo";
    private final ApiHelper apiHelper;
    private final String ID = "P1-G11";

    /**
     * Constructor to create a DAO for managing teams.
     */
    public TeamAPI() {
        try{
            this.apiHelper = new ApiHelper();
        }catch (ApiException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Reads all teams from an API.
     *
     * @return A list of characters read from the API.
     */

    @Override
    public ArrayList<Team> readAll() throws ApiException {
        ArrayList<Team> resultProducts = new ArrayList<>();
        try{
            Gson gson = new GsonBuilder().registerTypeAdapter(Team.class, new TeamDeserializer()).create();

            String getUrl = apiHelper.getFromUrl(FILE_PATH + "/" + ID + "/teams");

            Type listType = new TypeToken<ArrayList<Team>>(){}.getType();
            ArrayList<Team> teams = gson.fromJson(getUrl, listType);

            if ( teams != null){
                for ( Team team: teams){
                    resultProducts.add(team);
                }
            }
        }catch (ApiException ignored){
        }
        return resultProducts;
    }




    /**
     * Saves a list of teams to an API.
     *
     * @param teamList List of stats to save.
     */
    @Override
    public void saveTeams (ArrayList<Team> teamList) throws ApiException {
        Gson gson = new GsonBuilder().registerTypeAdapter(Team.class, new TeamSerializer()).create();
        if (!readAll().isEmpty()) {
            apiHelper.deleteFromUrl(FILE_PATH + "/" + ID + "/teams");
        }
        try {
            for (Team team : teamList) {
                String jsonBody = gson.toJson(team);
                apiHelper.postToUrl(FILE_PATH + "/" + ID + "/teams", jsonBody);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
