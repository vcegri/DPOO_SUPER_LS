package Business;

import Persistence.CharacterJSON;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class CharacterManager {
    
    private final CharacterJSON characterJson;

    public CharacterManager(CharacterJSON characterJson) {
        this.characterJson = characterJson;
    }

    public ArrayList<Long> getIdOfCharacters() throws FileNotFoundException {
        ArrayList<Long> idList = new ArrayList<>();
        ArrayList<Character> characterList = characterJson.readAll();

        for (int i = 0; i < characterList.size(); i++) {
            idList.set(i, characterList.get(i).getId());
        }
        return (idList);
    }

    public ArrayList<String> getNameOfCharacters() throws FileNotFoundException {
        ArrayList<String> nameList = new ArrayList<>();
        ArrayList<Character> characterList = characterJson.readAll();

        for (Character character : characterList) {
            nameList.add(character.getName());
        }
        return (nameList);
    }

    public ArrayList<Long> getWeightOfCharacters() throws FileNotFoundException {
        ArrayList<Long> weightList = new ArrayList<>();
        ArrayList<Character> characterList = characterJson.readAll();

        for (Character character : characterList) {
            weightList.add(character.getId());
        }
        return (weightList);
    }

    public boolean comproveIfCharacterExistByName(String Name) throws FileNotFoundException {
        boolean exist = false;
        ArrayList<String> characterNameList;

        characterNameList = getNameOfCharacters();
        for (String string : characterNameList) {
            if (Name.equals(string)) {
                exist = true;
                break;
            }
        }
        return (exist);
    }

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
        return (exist);
    }

    public long getIdByName(String name) throws FileNotFoundException {
        long id = 0;
        ArrayList<Character> characterList = characterJson.readAll();

        for (Character character : characterList) {
            String characterName = character.getName();
            if (characterName.equals(name)) {
                id = character.getId();
            }
        }

        return (id);
    }

    public int getWeightByName(String name) throws FileNotFoundException {
        int weight = 0;
        ArrayList<Character> characterList = characterJson.readAll();

        for (Character character : characterList) {
            String characterName = character.getName();
            if (characterName.equals(name)) {
                weight = character.getWeight();
            }
        }

        return (weight);
    }

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

        return (nameList);
    }

    public boolean fileOK(){
        return characterJson.fileOK();
    }
}
