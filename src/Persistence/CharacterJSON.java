package Persistence;

import Business.Character;
import com.google.gson.*;

import java.io.*;
import java.util.ArrayList;

/**
 * Manages the reading of character data from a JSON file and checks the file's existence.
 */
public class CharacterJSON {

    /** Path to the JSON file containing character data. */
    private static final String FILE_PATH = "data/characters.json";

    /**
     * Reads all character data from the JSON file and converts it into a list of Character objects.
     *
     * @return a list of Character objects read from the JSON file
     * @throws FileNotFoundException if the character data file is not found or cannot be accessed
     */
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

    /**
     * Checks if the character data file exists and is a valid file.
     *
     * @return true if the character data file exists and is a valid file, false otherwise
     */
    public boolean fileOK() {
        File characterFile = new File(FILE_PATH);
        return characterFile.exists() && characterFile.isFile();
    }
}
