package Persistence;

import Business.Character;
import com.google.gson.*;

import java.io.*;
import java.util.ArrayList;

public class CharacterJSON {

    private static final String FILE_PATH = "data/characters.json";

    public ArrayList<Character> readAll() throws FileNotFoundException {
        FileReader reader = new FileReader(FILE_PATH);
        Gson gson = new Gson();
        JsonArray elements = JsonParser.parseReader(reader).getAsJsonArray();
        ArrayList<Character> resultCharacters = new ArrayList<>();

        for (JsonElement jsonElement : elements) {
            Character character = gson.fromJson(jsonElement, Character.class);
            resultCharacters.add(character);
        }
        return resultCharacters;
    }

    public boolean fileOK() {
        File characterFile = new File(FILE_PATH);
        return characterFile.exists() && characterFile.isFile();
    }

}
