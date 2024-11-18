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
        boolean startProgram;

        menu.initialMenu();
       startProgram =  startProgram(characterFileOk, itemFileOk);

        if (startProgram){
            do {
                menu.principalMenu();
                option = selectOption(1, 5);
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

    public int selectOption(int min, int max){
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
                menu.incorrectFile("characters.json file");
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
        int character = selectOption(0, max);
        character--;

        String name = characterNameList.get(character);
        int id = characterManager.getIdByName(name);
        int weight = characterManager.getWeightByName(name);
        teamList = teamManager.searchTeamsOfCharacter(id);
        menu.characterInfo(id, name, weight, teamList);
    }
    public void manageTeams(){
        menu.manageTeamsMenu();
        int option = selectOption(1, 4);
        switch (option){
            case 1 -> createTeam();
            case 2 -> listTeams();
            case 3 -> deleteTeam();
        }

    }
    public void listItems(){
        ArrayList<String> itemNameList;

        itemNameList = characterManager.getNameOfCharacters();
        menu.characterList(itemNameList);

        int max = itemNameList.size();
        int item = selectOption(0, max);
        item--;

        String name = itemNameList.get(item);
        int id = itemManager.getIdByName(name);
        int power = itemManager.getPowerByName(name);
        int durability = itemManager.getDurabilityByName(name);
        String classe = itemManager.getClasseByName(name);
        menu.itemInfo(id, name, classe, power, durability);
    }
    public void simulateCombat(){}

    public void createTeam(){
        int teamSize = 4;
        ArrayList<Integer> characterIdList = new ArrayList<>();

        menu.createTeam();
        String name = menu.askString();
        boolean exist = teamManager.comproveIfTeamExist(name);
        if (!exist){
            menu.print("We are sorry " + name + " is taken.");
        }
        else {
            menu.print("Please enter name or id for character #1: ");
            for (int i = 0; i < teamSize; i++) {
                String characterName = menu.askString();
                int j = i +1;
                menu.print("Game strategy for character #" + j + "?");
                menu.print("\t1) Balanced");
                selectOption(1,1);
                characterIdList.add(characterManager.getIdByName(characterName));
            }
            menu.print("Team " + name + " has been successfully created!");

            teamManager.createTeam(name, characterIdList);
        }
    }
    public void listTeams(){
        ArrayList<String> teamNameList;
        ArrayList<Integer> memberIdList = new ArrayList<>();
        ArrayList<String> memberNameList;
        ArrayList<Integer> statList;

        teamNameList = teamManager.getNameOfTeams();
        menu.teamList(teamNameList);
        int max = teamNameList.size();
        int teamName = selectOption(0, max);

        String name = teamNameList.get(teamName);
        teamManager.getIdListOfATeam(name);

        memberNameList = characterManager.getNameById(memberIdList);

        statList = statManager.getStatList(name);

        menu.teamInfo(name, memberNameList, statList.get(0), statList.get(1), statList.get(2), statList.get(3), statList.get(4));
    }
    public void deleteTeam(){
        menu.deleteTeam();
        String name = menu.askString();
        boolean exist = teamManager.comproveIfTeamExist(name);
        if (exist){
            menu.print("Are you sure you want to remove \"" + name + "\" ?");
            String confirmation = menu.askString();
            if (confirmation.equals("Yes")){
                teamManager.deleteTeam(name);
                menu.print("\"" + name + "\" has been removed from the system.");
            }
        }
    }
}