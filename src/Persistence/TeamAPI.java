package Persistence;

import Business.Team;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.salle.url.api.ApiHelper;
import edu.salle.url.api.exception.ApiException;

import java.io.IOException;
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
            Gson gson = new GsonBuilder().registerTypeAdapter(Team[].class, new TeamDeserializer()).create();

            //ArrayList<Product> resultProducts = new ArrayList<>();
            //JsonArray jsonArray = JsonParser.parseString(jsonResponse).getAsJsonArray();
            String getUrl = apiHelper.getFromUrl(FILE_PATH + "/" + ID + "/products");

            String stringJson = getUrl.substring(1,getUrl .length()-1);
            Team[] products = gson.fromJson(stringJson, Team[].class);

            if ( products != null){
                for ( Team p: products){
                    resultProducts.add(p);
                }
            }
        }catch (ApiException ignored){
        }
        return resultProducts;
    }




    /**
     * Guarda una llista de productes en un fitxer JSON.
     *
     * @param products Llista de productes a guardar.
     */
    @Override
    public void saveTeams(ArrayList<Team> products) {
        Gson gson = new GsonBuilder().registerTypeAdapter(Team[].class, new TeamSerializer()).create();
        try {
            if ( products.size() > 1) {
                apiHelper.deleteFromUrl(FILE_PATH + "/" + ID + "/products");
            }
            Team[] p = products.toArray(new Team[0]);
            String jsonBody = gson.toJson(p);

            apiHelper.postToUrl(FILE_PATH + "/" + ID + "/products", jsonBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
