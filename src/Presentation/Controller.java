package Presentation;

import Business.*;
import Business.Character;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import java.io.IOException;

public class Controller {
    private final Menu menu;
    private final CharacterManager characterManager;
    private final TeamManager teamManager;
    private final ItemManager itemManager;
    private final StatManager statManager;
    private final CombatManager combatManager;

    public Controller(Menu menu, CharacterManager characterManager, TeamManager teamManager, ItemManager itemManager, StatManager statManager, CombatManager combatManager) {
        this.menu = menu;
        this.characterManager = characterManager;
        this.teamManager = teamManager;
        this.itemManager = itemManager;
        this.statManager = statManager;
        this.combatManager = combatManager;
    }

    public void run() throws IOException {
        int option;
        boolean startProgram;

        menu.initialMenu();
        startProgram =  startProgram();

        if (startProgram) {
            do {
                menu.principalMenu();
                option = selectOption(1, 5);
                switch (option) {
                    case 1 -> listCharacters();
                    case 2 -> manageTeams();
                    case 3 -> listItems();
                    case 4 -> simulateCombat();
                }
            } while (option != 5);
            menu.println("See you soon!");
        }
    }

    private int selectOption(int min, int max) {
        int option;
        int flag;
        do {
            option = menu.askInt();
            if (option > max || option < min) {
                menu.invalidOption();
                flag = 0;
            }
            else {
                flag = 1;
            }
        }while (flag != 1);
        return (option);
    }

    private boolean startProgram() {
        boolean startProgram;
        boolean characterFileOk = characterManager.fileOK();
        boolean itemFileOk = itemManager.fileOK();

        if(characterFileOk && itemFileOk) {
            menu.correctFile();
            startProgram = true;
        }
        else {
            startProgram = false;
            if (!characterFileOk && !itemFileOk) {
                menu.incorrectFile("charaters.json y items.json files");
            }
            else if (!characterFileOk) {
                menu.incorrectFile("characters.json file");
            }
            else {
                menu.incorrectFile("items.json file");
            }
        }
        return (startProgram);
    }

    private void listCharacters() throws IOException {
        ArrayList<String> characterNameList;
        ArrayList<String> teamList;

        characterNameList = characterManager.getNameOfCharacters();
        menu.printList(characterNameList);
        menu.print("Choose an option: ");

        int max = characterNameList.size();
        int character = selectOption(0, max);
        character--;

        if (character != -1) {
            String name = characterNameList.get(character);
            long id = characterManager.getIdByName(name);
            int weight = characterManager.getWeightByName(name);
            teamList = teamManager.searchTeamsOfCharacter(id);
            menu.characterInfo(id, name, weight, teamList);
            pressEnter();
        }
    }
    private void manageTeams() throws FileNotFoundException {
        menu.manageTeamsMenu();
        int option = selectOption(1, 4);
        switch (option) {
            case 1 -> createTeam();
            case 2 -> listTeams();
            case 3 -> deleteTeam();
        }

    }
    private void listItems() throws IOException {
        ArrayList<String> itemNameList;

        itemNameList = itemManager.getNameOfItems();
        menu.printList(itemNameList);
        menu.print("Choose an option: ");

        int max = itemNameList.size();
        int item = selectOption(0, max);
        item--;

        if (item != -1) {
            String name = itemNameList.get(item);
            long id = itemManager.getIdByName(name);
            int power = itemManager.getPowerByName(name);
            int durability = itemManager.getDurabilityByName(name);
            String classe = itemManager.getClasseByName(name);
            menu.itemInfo(id, name, classe, power, durability);
            pressEnter();
        }
    }

    private void createTeam() throws FileNotFoundException {
        int teamSize = 4;
        ArrayList<TeamMember> teamMemberList = new ArrayList<>();

        menu.createTeam();
        String name = menu.askString();
        boolean exist = teamManager.comproveIfTeamExist(name);
        if (exist) {
            menu.println("We are sorry " + name + " is taken.");
        }
        else {
            for (int i = 0; i < teamSize; i++) {
                int j = i +1;
                menu.print("Please enter name or id for character #" + j + ": ");
                String characterName = menu.askString();
                TeamMember teamMember = getTeamMember(characterName, j);
                if (teamMember != null){
                    teamMemberList.add(teamMember);
                }
                else {
                    menu.println("That character doesn't exist");
                    i--;
                }

            }
            menu.println("Team " + name + " has been successfully created!");

            teamManager.createTeam(name, teamMemberList);
            statManager.createStat(name);
        }
    }

    private TeamMember getTeamMember(String characterName, int j) throws FileNotFoundException {
        boolean characterExist;
        boolean isLong = false;
        long id = 0;
        TeamMember teamMember = null;
        try {
            id = Long.parseLong(characterName);
            characterExist = characterManager.comproveIfCharacterExistById(id);
            isLong = true;
        } catch (NumberFormatException e) {
            characterExist = characterManager.comproveIfCharacterExistByName(characterName);
        }

        if (characterExist){
            menu.println("Game strategy for character #" + j + "?");
            menu.println("\t1) Balanced");
            selectOption(1,1);
            if (isLong){
                teamMember = new TeamMember(id, "Balanced");
            }
            else {
                teamMember = new TeamMember(characterManager.getIdByName(characterName), "Balanced");
            }
        }

        return teamMember;
    }
    private void listTeams() throws FileNotFoundException {
        ArrayList<String> teamNameList;
        ArrayList<Long> memberIdList = new ArrayList<>();
        ArrayList<String> memberNameList;
        ArrayList<Integer> statList;

        teamNameList = teamManager.getNameOfTeams();
        menu.printList(teamNameList);
        menu.print("Choose an option: ");
        int max = teamNameList.size();
        int teamName = selectOption(0, max);

        if (teamName != 0) {
            teamName--;
            String name = teamNameList.get(teamName);
            memberNameList = characterManager.getNameById(memberIdList);
            statList = statManager.getStatList(name);
            menu.teamInfo(name, memberNameList, statList.get(0), statList.get(1), statList.get(2), statList.get(3), statList.get(4));
            pressEnter();
        }
    }
    private void deleteTeam() throws FileNotFoundException {
        menu.deleteTeam();
        String name = menu.askString();
        boolean exist = teamManager.comproveIfTeamExist(name);
        if (exist) {
            menu.print("Are you sure you want to remove \"" + name + "\"?");
            String confirmation = menu.askString();
            if (confirmation.equals("Yes")) {
                teamManager.deleteTeam(name);
                menu.println("\"" + name + "\" has been removed from the system.");
            }
        }
    }

    private void simulateCombat() throws FileNotFoundException {
        boolean existTeam;

        menu.println("Starting simulation...");
        existTeam = chooseTeam();
        if (existTeam){
            executeCombat();
            endCombat();
        }
    }
    private boolean chooseTeam() throws FileNotFoundException {
        ArrayList<Team> teamFight;
        ArrayList<String> teamNames;
        boolean existTeam;

        teamNames = teamManager.getNameOfTeams();
        menu.println("Looking for available teams...");
        if (!teamNames.isEmpty()){
            teamFight = chooseTeamForCombat();
            int numTeam = 0;
            showTeamInfoForCombat(numTeam, teamFight);
            numTeam = 1;
            showTeamInfoForCombat(numTeam, teamFight);
            menu.println("\nCombat ready!");
            pressEnter();
            existTeam = true;
        }
        else {
            menu.println("There aren't teams to simulate the combat");
            existTeam = false;
        }

        return existTeam;
    }

    private ArrayList<Team> chooseTeamForCombat() throws FileNotFoundException {
        ArrayList<String> teamNameList;
        ArrayList<Team> teamFight = new ArrayList<>();
        String teamName;
        Team team;

        teamNameList = teamManager.getNameOfTeams();
        menu.printList(teamNameList);

        menu.print("\nChoose team #1: ");
        int max = teamNameList.size() + 1;
        int option = selectOption(1, max);
        teamName = teamNameList.get(option);
        team = teamManager.getTeamByName(teamName);
        teamFight.add(team);

        menu.print("\nChoose team #2: ");
        option = selectOption(1, max);
        teamName = teamNameList.get(option);
        team = teamManager.getTeamByName(teamName);
        teamFight.add(team);

        return teamFight;
    }

    private void showTeamInfoForCombat(int numTeam, ArrayList<Team> teamFight) throws FileNotFoundException {
        int teamSize = 4;
        ArrayList<String> teamMemberNameList = new ArrayList<>();
        ArrayList<Long> teamMemberIdList = new ArrayList<>();
        ArrayList<String> armorNameList = new ArrayList<>();
        ArrayList<String> weaponNameList = new ArrayList<>();
        ArrayList<Item> weaponList = new ArrayList<>();
        ArrayList<Item> armorList = new ArrayList<>();
        ArrayList<Character> characterList;

        for (int i = 0; i < teamSize; i++){
            teamMemberIdList.add(teamFight.get(numTeam).getMemberList().get(i).getId());
            weaponList.add(combatManager.setRandomWeapon());
            weaponNameList.add(weaponList.get(i).getName());
            armorList.add(combatManager.setRandomArmor());
            armorNameList.add(armorList.get(i).getName());
            teamMemberNameList = characterManager.getNameById(teamMemberIdList);
        }
        int showNumTeam = numTeam + 1;
        menu.showTeamInfoForCombat(showNumTeam, teamFight.get(numTeam).getName(), teamMemberNameList, weaponNameList, armorNameList);
        characterList = characterManager.getCharacterListByIdList();
        combatManager.setCombat(teamFight, weaponList, armorList, characterList);

    }
    private void executeCombat(){
        atack();
        defend();
        newWeapon();
    }

    private void atack(){}

    private void defend(){}

    private void newWeapon(){
        Item weapon = combatManager.setRandomWeapon();
    }
    private void endCombat(){}

    
    private void pressEnter() {
        menu.print("<Press enter to continue...>");
        menu.askString();
    }
}