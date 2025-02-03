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

/**
 * Is used to read and write item data at the items API.
 */
public class ItemAPI implements ItemDAO{

    private static final String FILE_PATH = "https://balandrau.salle.url.edu/dpoo";
    private final ApiHelper apiHelper;

    /**
     * Constructor to create a DAO for managing items.
     */
    public ItemAPI() {
        try{
            this.apiHelper = new ApiHelper();
        }catch (ApiException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Reads all items from an API.
     *
     * @return A list of characters read from the API.
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
     * Checks if the item file exists and is valid.
     *
     * @return True if the file is valid, false otherwise.
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
