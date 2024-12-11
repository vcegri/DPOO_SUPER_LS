package Persistence;

import Business.Character;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.salle.url.api.ApiHelper;
import edu.salle.url.api.exception.ApiException;

import java.util.ArrayList;

public class CharacterAPI implements CharacterDAO{
    private static final String FILE_PATH = "https://balandrau.salle.url.edu/dpoo";
    private final ApiHelper apiHelper;
    private final String ID = "P1-G11";
    /**
     * Constructor para crear un DAO para la gestión de productos.
     */
    public CharacterAPI() {
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
    public ArrayList<Character> readAll() throws ApiException {
        ArrayList<Character> resultProducts = new ArrayList<>();
        try{
            Gson gson = new GsonBuilder().registerTypeAdapter(Character.class, new CharacterDeserializer()).create();
            String getUrl = apiHelper.getFromUrl(FILE_PATH + "/" + ID + "/products");

            String stringJson = getUrl.substring(1,getUrl .length()-1);
            Character[] products = gson.fromJson(stringJson, Character[].class);

            if ( products != null){
                for (Character p: products){
                    resultProducts.add(p);
                }
            }
        }catch (ApiException ignored){
        }
        return resultProducts;
    }

    /**
     * Comprova si el fitxer de productes existeix i és un fitxer vàlid.
     *
     * @return True si el fitxer és vàlid, false en cas contrari.
     */
    @Override
    public boolean fileOK() {
        try {
            String response = apiHelper.getFromUrl(FILE_PATH + "/" + ID + "/products");
            return true;
        } catch (ApiException e) {
            e.printStackTrace();
            return false;
        }
    }
}
