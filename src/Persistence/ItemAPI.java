package Persistence;

import Business.Character;
import Business.Item;
import Business.SuperItem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import edu.salle.url.api.ApiHelper;
import edu.salle.url.api.exception.ApiException;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ItemAPI implements ItemDAO{

    private static final String FILE_PATH = "https://balandrau.salle.url.edu/dpoo";
    private final ApiHelper apiHelper;
    /**
     * Constructor para crear un DAO para la gestión de productos.
     */
    public ItemAPI() {
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
    public ArrayList<Item> readAll() throws ApiException {
        ArrayList<Item> resultProducts = new ArrayList<>();
        try{
            Gson gson = new GsonBuilder().registerTypeAdapter(Item.class, new ItemDeserializer()).create();
            String getUrl = apiHelper.getFromUrl(FILE_PATH + "/shared/items");

            Type listType = new TypeToken<ArrayList<Item>>(){}.getType();
            ArrayList<Item> items = gson.fromJson(getUrl, listType);

            if ( items != null){
                for (Item item: items){
                    resultProducts.add(item);
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
    public int fileOK() {
        try {
            apiHelper.getFromUrl(FILE_PATH + "/shared/items");
            return 1;
        } catch (ApiException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
