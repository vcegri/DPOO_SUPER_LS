package Business;

import Persistence.CharacterJSON;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Manages character data by interfacing with a JSON storage system.
 */
public class CharacterManager {

    /** The JSON handler for character data. */
    private final CharacterJSON characterJson;

    /**
     * Constructs a CharacterManager with the specified JSON handler.
     *
     * @param characterJson the JSON handler for character data
     */
    public CharacterManager(CharacterJSON characterJson) {
        this.characterJson = characterJson;
    }

    /**
     * Retrieves the IDs of all characters.
     *
     * @return a list of character IDs
     * @throws FileNotFoundException if the JSON file cannot be found
     */
    public ArrayList<Long> getIdOfCharacters() throws FileNotFoundException {
        ArrayList<Long> idList = new ArrayList<>();
        ArrayList<Character> characterList = characterJson.readAll();

        for (int i = 0; i < characterList.size(); i++) {
            idList.set(i, characterList.get(i).getId());
        }
        return idList;
    }

    /**
     * Retrieves the names of all characters.
     *
     * @return a list of character names
     * @throws FileNotFoundException if the JSON file cannot be found
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
     * Retrieves the weights of all characters.
     *
     * @return a list of character weights
     * @throws FileNotFoundException if the JSON file cannot be found
     */
    public ArrayList<Long> getWeightOfCharacters() throws FileNotFoundException {
        ArrayList<Long> weightList = new ArrayList<>();
        ArrayList<Character> characterList = characterJson.readAll();

        for (Character character : characterList) {
            weightList.add(character.getId());
        }
        return weightList;
    }

    /**
     * Checks if a character with the specified name exists.
     *
     * @param name the name to check
     * @return true if the character exists, false otherwise
     * @throws FileNotFoundException if the JSON file cannot be found
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
     * Checks if a character with the specified ID exists.
     *
     * @param id the ID to check
     * @return true if the character exists, false otherwise
     * @throws FileNotFoundException if the JSON file cannot be found
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
     * Retrieves the ID of a character by its name.
     *
     * @param name the name of the character
     * @return the ID of the character
     * @throws FileNotFoundException if the JSON file cannot be found
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
     * Retrieves the weight of a character by its name.
     *
     * @param name the name of the character
     * @return the weight of the character
     * @throws FileNotFoundException if the JSON file cannot be found
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
     * Retrieves the names of characters based on a list of IDs.
     *
     * @param idList the list of character IDs
     * @return a list of character names corresponding to the IDs
     * @throws FileNotFoundException if the JSON file cannot be found
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
     * Retrieves a list of Character objects based on their IDs.
     *
     * @return a list of Character objects
     */
    public ArrayList<Character> getCharacterListByIdList() {
        return new ArrayList<>();
    }

    /**
     * Verifies if the JSON file is available and valid.
     *
     * @return true if the file is valid, false otherwise
     */
    public boolean fileOK() {
        return characterJson.fileOK();
    }
}
