package Persistence;

import Business.Character;
import com.google.gson.*;

import java.io.*;
import java.util.ArrayList;

/**
 * Is used to read and write team data at the characters.json file.
 */
public class CharacterJSON implements CharacterDAO{

    /** Path to the items.json file that contains all the item data. */
    private static final String FILE_PATH = "data/characters.json";

    /**
     * Reads all the character data at the characters.json file and converts it into a list of Characters.
     *
     * @return a list of all the Characters that the characters.json file contains
     * @throws FileNotFoundException if the characters.json file is not found or can't be accessed
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
     * Checks if the characters.json file exists and is a valid file.
     *
     * @return true if all is okay
     */
    public boolean fileOK() {
        File characterFile = new File(FILE_PATH);
        return characterFile.exists() && characterFile.isFile();
    }
}
