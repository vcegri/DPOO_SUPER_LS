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

public class TeamAPI implements TeamDAO{
    private static final String FILE_PATH = "https://balandrau.salle.url.edu/dpoo";
    private final ApiHelper apiHelper;
    private final String ID = "P1-G11";
    /**
     * Constructor para crear un DAO para la gestión de productos.
     */
    public TeamAPI() {
        try{
            this.apiHelper = new ApiHelper();
        }catch (ApiException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Llegeix tots els productes des d'un fitxer JSON.
     *
     * @return Una llista de productes llegits des del fitxer.
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
     * Guarda una llista de productes en un fitxer JSON.
     *
     * @param teamList Llista de productes a guardar.
     */
    @Override
    public void saveTeams(ArrayList<Team> teamList) {
        Gson gson = new GsonBuilder().registerTypeAdapter(Team.class, new TeamSerializer()).create();
        try {
            if (teamList.size() < 1) {
                apiHelper.deleteFromUrl(FILE_PATH + "/" + ID + "/teams");
            }

            for (Team team : teamList) {
                String jsonBody = gson.toJson(team);
                apiHelper.postToUrl(FILE_PATH + "/" + ID + "/teams", jsonBody);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
