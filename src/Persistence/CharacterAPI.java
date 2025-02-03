package Persistence;

import Business.Character;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import edu.salle.url.api.ApiHelper;
import edu.salle.url.api.exception.ApiException;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Is used to read and write character data at the characters API.
 */
public class CharacterAPI implements CharacterDAO{
    private static final String FILE_PATH = "https://balandrau.salle.url.edu/dpoo";
    private final ApiHelper apiHelper;
    /**
     * Constructor to create a DAO for managing characters.
     */
    public CharacterAPI() {
        try{
            this.apiHelper = new ApiHelper();
        }catch (ApiException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Reads all characters from an API.
     *
     * @return A list of characters read from the API.
     */

    @Override
    public ArrayList<Character> readAll() throws ApiException {
        ArrayList<Character> resultProducts = new ArrayList<>();
        try{
            Gson gson = new GsonBuilder().registerTypeAdapter(Character[].class, new CharacterDeserializer()).create();
            String getUrl = apiHelper.getFromUrl(FILE_PATH + "/shared/characters");

            Type listType = new TypeToken<ArrayList<Character>>(){}.getType();
            ArrayList<Character> characters = gson.fromJson(getUrl, listType);

            if ( characters != null){
                for (Character character: characters){
                    resultProducts.add(character);
                }
            }
        }catch (ApiException ignored){
        }
        return resultProducts;
    }

    /**
     * Checks if the character file exists and is valid.
     *
     * @return True if the file is valid, false otherwise.
     */
    @Override
    public int fileOK() {
        try {
            apiHelper.getFromUrl(FILE_PATH + "/shared/characters");
            return 1;
        } catch (ApiException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
