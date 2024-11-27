package Business;

import Persistence.CharacterJSON;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class CharacterManager {
    
    private CharacterJSON characterJson;

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

        for (int i = 0; i < characterList.size(); i++) {
            nameList.add(characterList.get(i).getName());
        }
        return (nameList);
    }

    public ArrayList<Long> getWeightOfCharacters() throws FileNotFoundException {
        ArrayList<Long> weightList = new ArrayList<>();
        ArrayList<Character> characterList = characterJson.readAll();

        for (int i = 0; i < characterList.size(); i++) {
            weightList.add(characterList.get(i).getId());
        }
        return (weightList);
    }

    public boolean comproveIfCharacterExistByName(String Name) throws FileNotFoundException {
        boolean exist = false;
        ArrayList<String> characterNameList;

        characterNameList = getNameOfCharacters();
        for (int i = 0; i < characterNameList.size(); i++) {
            if (Name.equals(characterNameList.get(i))) {
                exist = true;
            }
        }
        return (exist);
    }

    public boolean comproveIfCharacterExistById(long id) throws FileNotFoundException {
        boolean exist = false;
        ArrayList<Character> characterList;

        characterList = characterJson.readAll();
        for (int i = 0; i < characterList.size(); i++) {
            if (id == characterList.get(i).getId()) {
                exist = true;
            }
        }
        return (exist);
    }

    public long getIdByName(String name) throws FileNotFoundException {
        long id = 0;
        ArrayList<Character> characterList = characterJson.readAll();

        for (int i = 0; i < characterList.size(); i++) {
            String characterName = characterList.get(i).getName();
            if (characterName.equals(name)){
                id = characterList.get(i).getId();
            }
        }

        return (id);
    }

    public int getWeightByName(String name) throws FileNotFoundException {
        int weight = 0;
        ArrayList<Character> characterList = characterJson.readAll();

        for (int i = 0; i < characterList.size(); i++) {
            String characterName = characterList.get(i).getName();
            if (characterName.equals(name)){
                weight = characterList.get(i).getWeight();
            }
        }

        return (weight);
    }

    public ArrayList<String> getNameById(ArrayList<Long> idList) throws FileNotFoundException {
        ArrayList<String> nameList = new ArrayList<>();
        ArrayList<Character> characterList = characterJson.readAll();

        for (int i = 0; i < characterList.size(); i++) {
            for (int j = 0; j < idList.size(); j++) {
                if (characterList.get(i).getId() == idList.get(j)) {
                    nameList.add(characterList.get(i).getName());
                }
            }
        }

        return (nameList);
    }

    public boolean fileOK(){
        return characterJson.fileOK();
    }
}
