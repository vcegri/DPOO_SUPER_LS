package Persistence;

import Business.Character;
import com.google.gson.*;

import java.io.*;
import java.util.ArrayList;

public class CharacterJSON {

    private static final String FILE_PATH = "data/characters.json";

    @Override
    public ArrayList<Character> readAll() throws FileNotFoundException {
        FileReader reader = new FileReader(FILE_PATH);
        Gson gson = new Gson();
        JsonArray elements = JsonParser.parseReader(reader).getAsJsonArray();
        ArrayList<Character> resultProducts = new ArrayList<>();

        for (JsonElement jsonElement : elements) {
            Character character = gson.fromJson(jsonElement, Character.class);
            resultProducts.add(character);
        }
        return resultProducts;
    }

    @Override
    public void saveShops(ArrayList<Character> shops) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            Gson gson = new Gson();
            gson.toJson(shops.toArray(), writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeProduct(Character character,ArrayList<Character> characters) {
        characters.remove(character);
        saveShops(characters);
    }

    @Override
    public boolean fileOK(){
        File characterFile = new File(FILE_PATH);
        return characterFile.exists() && characterFile.isFile();
    }

}
