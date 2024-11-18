package Business;

import java.util.ArrayList;

public class CharacterManager {
    public ArrayList<Integer> getIdOfCharacters(){
        ArrayList<Integer> idList = new ArrayList<>();

        for (int i = 0; i < characterList.size(); i++) {
            idList.set(i, characterList.get(i).getId());
        }
        return (idList);
    }

    public ArrayList<String> getNameOfCharacters(){
        ArrayList<String> nameList = new ArrayList<>();

        for (int i = 0; i < characterList.size(); i++) {
            nameList.add(characterList.get(i).getName());
        }
        return (nameList);
    }

    public ArrayList<Integer> getWeightOfCharacters(){
        ArrayList<Integer> weightList = new ArrayList<>();

        for (int i = 0; i < characterList.size(); i++) {
            weightList.add(characterList.get(i).getId());
        }
        return (weightList);
    }

    public boolean comproveIfCharacterExist(String newName){
        boolean exist = false;
        ArrayList<String> characterNameList = new ArrayList<>();

        characterNameList = getNameOfCharacters(teamList);
        for (int i = 0; i < characterNameList.size(); i++) {
            if (newName.equals(characterNameList.get(i))){
                exist = true;
            }
        }
        return(exist);
    }

    public int getIdByName(String name) {
        int id;

        return(id);
    }

    public int getWeightByName(String name) {
        int weight;

        return(weight);
    }

    public ArrayList<String> getNameById(ArrayList<Integer> idList){
        ArrayList<String> nameList = new ArrayList<>();

        for (int i = 0; i < characterList.size(); i++){
            for (int j = 0; j < idList.size(); j++) {
                if (characterList.get(i).getId() == idList.get(j)) {
                    nameList.add(characterList.get(i).getName);
                }
            }
        }

        return(nameList);
    }
}
