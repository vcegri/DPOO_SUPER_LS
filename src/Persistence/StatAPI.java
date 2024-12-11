package Persistence;

import Business.Stat;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.salle.url.api.ApiHelper;
import edu.salle.url.api.exception.ApiException;

import java.io.IOException;
import java.util.ArrayList;

public class StatAPI implements StatDAO{
    private static final String FILE_PATH = "https://balandrau.salle.url.edu/dpoo";
    private final ApiHelper apiHelper;
    private final String ID = "P1-G11";
    /**
     * Constructor para crear un DAO para la gestión de productos.
     */
    public StatAPI() {
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
    public ArrayList<Stat> readAll() throws ApiException {
        ArrayList<Stat> resultProducts = new ArrayList<>();
        try{
            Gson gson = new GsonBuilder().registerTypeAdapter(Stat[].class, new StatDeserializer()).create();

            String getUrl = apiHelper.getFromUrl(FILE_PATH + "/" + ID + "/stats");

            String stringJson = getUrl.substring(1,getUrl .length()-1);
            Stat[] products = gson.fromJson(stringJson, Stat[].class);

            if ( products != null){
                for (Stat p: products){
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
     * @param statList Llista de productes a guardar.
     */
    @Override
    public void saveStatList(ArrayList<Stat> statList) {
        Gson gson = new GsonBuilder().registerTypeAdapter(Stat[].class, new StatSerializer()).create();
        try {
            if ( statList.size() > 1) {
                apiHelper.deleteFromUrl(FILE_PATH + "/" + ID + "/stats");
            }
            Stat[] p = statList.toArray(new Stat[0]);
            String jsonBody = gson.toJson(p);

            apiHelper.postToUrl(FILE_PATH + "/" + ID + "/stats", jsonBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
