package Business;

import Persistence.CharacterJSON;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Manages the interactions with character data using the characterJson class.
 */
public class CharacterManager {

    /** Class to manage the characters.json file. */
    private final CharacterJSON characterJson;

    /**
     * Constructs a CharacterManager with the CharacterJSON class.
     *
     * @param characterJson class to manage the characters.json file
     */
    public CharacterManager(CharacterJSON characterJson) {
        this.characterJson = characterJson;
    }

    /**
     * Checks if a character with a specified name exists.
     *
     * @param name name to check
     * @return true if the character exists, false if not
     * @throws FileNotFoundException if the JSON file can't be found
     */
    public boolean comproveIfCharacterExistByName(String name) throws FileNotFoundException {
        boolean exist = false;
        ArrayList<String> characterNameList;

        characterNameList = getNameOfCharacters();
        for (String string : characterNameList) {
            if (name.equals(string)) {
                exist = true;
                break;
            }
        }
        return exist;
    }

    /**
     * Checks if a character with a specified ID exists.
     *
     * @param id ID to check
     * @return true if the character exists, false if not
     * @throws FileNotFoundException if the JSON file can't be found
     */
    public boolean comproveIfCharacterExistById(long id) throws FileNotFoundException {
        boolean exist = false;
        ArrayList<Character> characterList;

        characterList = characterJson.readAll();
        for (Character character : characterList) {
            if (id == character.getId()) {
                exist = true;
                break;
            }
        }
        return exist;
    }

    /**
     * Creates a list with the names of all characters.
     *
     * @return list of all the character names
     * @throws FileNotFoundException if the JSON file can't be found
     */
    public ArrayList<String> getNameOfCharacters() throws FileNotFoundException {
        ArrayList<String> nameList = new ArrayList<>();
        ArrayList<Character> characterList = characterJson.readAll();

        for (Character character : characterList) {
            nameList.add(character.getName());
        }
        return nameList;
    }

    /**
     * Get the ID of a character by its name.
     *
     * @param name name of the character
     * @return ID of the character
     * @throws FileNotFoundException if the JSON file can't be found.
     */
    public long getIdByName(String name) throws FileNotFoundException {
        long id = 0;
        ArrayList<Character> characterList = characterJson.readAll();

        for (Character character : characterList) {
            String characterName = character.getName();
            if (characterName.equals(name)) {
                id = character.getId();
            }
        }

        return id;
    }

    /**
     * Get the weight of a character by its name.
     *
     * @param name name of the character
     * @return weight of the character
     * @throws FileNotFoundException if the JSON file can't be found
     */
    public int getWeightByName(String name) throws FileNotFoundException {
        int weight = 0;
        ArrayList<Character> characterList = characterJson.readAll();

        for (Character character : characterList) {
            String characterName = character.getName();
            if (characterName.equals(name)) {
                weight = character.getWeight();
            }
        }

        return weight;
    }

    /**
     * Get the names of characters by a list of IDs.
     *
     * @param idList list of character IDs
     * @return list of character names
     * @throws FileNotFoundException if the JSON file can't be found
     */
    public ArrayList<String> getNameById(ArrayList<Long> idList) throws FileNotFoundException {
        ArrayList<String> nameList = new ArrayList<>();
        ArrayList<Character> characterList = characterJson.readAll();

        for (Character character : characterList) {
            for (Long aLong : idList) {
                if (character.getId() == aLong) {
                    nameList.add(character.getName());
                }
            }
        }

        return nameList;
    }

    /**
     * Get a list of Characters by their IDs.
     *
     * @return list of Characters
     */
    public ArrayList<Character> getCharacterListByIdList(ArrayList<Long> teamMemberIdList) throws FileNotFoundException {
        ArrayList<Character> characterList = characterJson.readAll();
        ArrayList<Character> characterCombat = new ArrayList<>();
        for (Character character : characterList) {
            for (Long id : teamMemberIdList) {
                if (character.getId() == id) {
                    characterCombat.add(character);
                }
            }

        }

        return characterCombat;
    }

    /**
     * Verifies if the JSON file is available.
     *
     * @return true if the file is available, false if not
     */
    public boolean fileOK() {
        return characterJson.fileOK();
    }
}
