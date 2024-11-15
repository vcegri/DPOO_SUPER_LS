package Presentation;

import Business.*;

import java.util.ArrayList;

public class Controller {
    private final Menu menu;
    private final CharacterManager characterManager;
    private final TeamManager teamManager;
    private final ItemManager itemManager;
    private final StatManager statManager;

    public Controller(Menu menu, CharacterManager characterManager, TeamManager teamManager, ItemManager itemManager, StatManager statManager) {
        this.menu = menu;
        this.characterManager = characterManager;
        this.teamManager = teamManager;
        this.itemManager = itemManager;
        this.statManager = statManager;
    }

    public void run() {
        int option;
        boolean characterFileOk = false;
        boolean itemFileOk = false;
        boolean startProgram = false;

        menu.initialMenu();
       startProgram =  startProgram(characterFileOk, itemFileOk);

        if (startProgram){
            do {
                menu.principalMenu();
                option = escollirOpcio(1, 5);
                switch (option){
                    case 1 -> listCharacters();
                    case 2 -> manageTeams();
                    case 3 -> listItems();
                    case 4 -> simulateCombat();
                }
            } while (option != 5);
            menu.print("See you soon!");
        }
    }

    public int escollirOpcio(int min, int max){
        int option;
        int flag = 1;
        do {
            option = menu.askInt();
            if (option > max || option < min){
                menu.invalidOption();
                flag = 0;
            }
        }while (flag != 1);
        return(option);
    }

    public boolean startProgram(boolean characterFileOk, boolean itemFileOk){
        boolean startProgram;

        if(characterFileOk && itemFileOk){
            menu.correctFile();
            startProgram = true;
        }
        else {
            startProgram = false;
            if (!characterFileOk && !itemFileOk) {
                menu.incorrectFile("charaters.json y items.json files");
            }
            else if (!characterFileOk){
                menu.incorrectFile("character.json file");
            }
            else {
                menu.incorrectFile("items.json file");
            }
        }
        return (startProgram);
    }

    public void listCharacters(){
        ArrayList<String> characterNameList;
        ArrayList<String> teamList;

        characterNameList = characterManager.getNameOfCharacters();
        menu.characterList(characterNameList);

        int max = characterNameList.size();
        int character = escollirOpcio(0, max);
        character--;

        String name = characterNameList.get(character);
        int id = characterManager.getIdByName(name);
        int weight = characterManager.getWeightByName(name);
        teamList = teamManager.searchTeamsOfCharacter(id);
        menu.characterInfo(id, name, weight, teamList);
    }
    public void manageTeams(){
        menu.manageTeamsMenu();
    }
    public void listItems(){
        ArrayList<String> itemNameList;

        itemNameList = characterManager.getNameOfCharacters();
        menu.characterList(itemNameList);

        int max = itemNameList.size();
        int item = escollirOpcio(0, max);
        item--;

        String name = itemNameList.get(item);
        int id = itemManager.getIdByName(name);
        int power = itemManager.getPowerByName(name);
        int durability = itemManager.getDurabilityByName(name);
        String classe = itemManager.getClasseByName(name);
        menu.itemInfo(id, name, classe, power, durability);
    }
    public void simulateCombat(){}
}