package Presentation;

import Business.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import java.io.IOException;
import java.util.Random;

public class Controller {
    private final Menu menu;
    private final CharacterManager characterManager;
    private final TeamManager teamManager;
    private final ItemManager itemManager;
    private final StatManager statManager;
    private CombatManager combatManager;

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
        boolean correctConfirm = true;

        menu.deleteTeam();
        String name = menu.askString();
        boolean exist = teamManager.comproveIfTeamExist(name);
        if (exist) {
            menu.print("Are you sure you want to remove \"" + name + "\"?");
            do {
                String confirmation = menu.askString();
                if (confirmation.equals("Yes")) {
                    teamManager.deleteTeam(name);
                    statManager.deleteStat(name);
                    menu.println("\"" + name + "\" has been removed from the system.");
                }
                else if (confirmation.equals("No")){
                    menu.println("Ok, team " + name + "is not canceled");
                    manageTeams();
                }
                else {
                    menu.println("You might write 'Yes' or 'No'");
                    correctConfirm = false;
                }
            }while (!correctConfirm);
        }
        else {
            menu.println("Doesn't exist " + name + " team.");
            manageTeams();
        }
    }

    private void simulateCombat() throws FileNotFoundException {
        boolean existTeam;

        menu.println("Starting simulation...");
        existTeam = chooseTeam();
        if (existTeam){
            executeCombat();
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

        this.combatManager.setCombat(teamFight, weaponList, armorList, characterManager.getCharacterListByIdList());

    }
    private void executeCombat() throws FileNotFoundException {
        int roundNum = 1;

        do {
            roundTeamInfo(roundNum);
            executeAction();
            brokenItems();
            deadCombatMembers();
            roundNum++;
        } while (!endCombat());
        this.combatManager.getCombat().endCombat();
    }

    private void roundTeamInfo(int roundNum) throws FileNotFoundException {
        int teamNumber;
        ArrayList<String> nameList;
        ArrayList<String> weaponList;
        ArrayList<String> armorList;
        ArrayList<Double> damageList;
        ArrayList<Long> idList = new ArrayList<>();
        ArrayList<Boolean> koList = new ArrayList<>();
        ArrayList<Team> teamList = combatManager.getCombat().getTeamList();

        for (int k = 0; k < teamList.size(); k++) {
            teamNumber = k +1;

            for (int i = 0; i < teamList.get(i).getMemberList().size(); i++) {
                idList.add(teamList.get(i).getMemberList().get(i).getId());
                if (i == 0){
                    koList.add(combatManager.getCombat().getCombatMemberList().get(i).isKo());
                }
                else {
                    int j = i +4;
                    koList.add(combatManager.getCombat().getCombatMemberList().get(j).isKo());
                }
            }
            nameList = characterManager.getNameById(idList);
            weaponList = combatManager.getWeaponList();
            armorList = combatManager.getArmorList();
            damageList = combatManager.getDamageList();

            menu.roundinfo(roundNum, teamNumber, teamList.get(k).getName(), nameList, weaponList, armorList, damageList, koList);
        }
    }

    private void executeAction(){
        boolean hasWeapon;
        boolean hasArmor;
        boolean hasHighDamage;

        for (int i = 0; i < combatManager.getCombat().getCombatMemberList().size(); i++){

            hasWeapon = combatManager.hasWeapon(i);
            hasArmor = combatManager.hasArmor(i);
            hasHighDamage = combatManager.hasHighDamage(i);

            if (!hasWeapon){
                newWeapon();
            }
            else if (hasArmor){
                if (hasHighDamage){
                    defend(i);
                }
                else {
                    atack(i);
                }
            }
            else {
                atack(i);
            }
        }
    }

    private void atack(int i){
        Combat combat = this.combatManager.getCombat();
        Random random = new Random();
        int randomIndex;

        String  attackerName = combat.getCombatMemberList().get(i).getCharacter().getName();

        if (i < 4) {
            randomIndex = random.nextInt(4) + 4;
        }
        else {
            randomIndex = random.nextInt(4);
        }

        String defenderName = combat.getCombatMemberList().get(randomIndex).getCharacter().getName();
        String weaponName = combat.getCombatMemberList().get(randomIndex).getWeapon().getName();
        double damage = combatManager.calculateDamage(combat.getCombatMemberList().get(i));
        double finalDamage = combatManager.calculateReducedDamage(damage, combat.getCombatMemberList().get(randomIndex));

        if (combat.getCombatMemberList().get(randomIndex).isDefending()){
            finalDamage = finalDamage - combatManager.calculateDamageReduction(combat.getCombatMemberList().get(randomIndex));
        }

        menu.println(attackerName + "ATTACKS" + defenderName + "WITH" + weaponName + "FOR" + damage + "DAMAGE!");
        menu.println(defenderName + "RECEIVES" + finalDamage + "DAMAGE.");
    }

    private void defend(int i){
        this.combatManager.getCombat().getCombatMemberList().get(i).isDefending();
    }

    private void newWeapon() {}

    private void brokenItems(){}

    private void deadCombatMembers(){
        Combat combat = combatManager.getCombat();

        for (int i = 0; i < combat.getCombatMemberList().size(); i++) {
            if (combat.getCombatMemberList().get(i).isKo()) {
                menu.println(combat.getCombatMemberList().get(i).getCharacter().getName() +  " flies away! It’s a KO!");
            }
        }
    }

    private boolean endCombat(){
        boolean combatEnded = false;
        boolean teamOneFinish = true;
        boolean teamTwoFinish = true;

        for (int i = 0; i < 4; i++){
            if (!combatManager.getCombat().getCombatMemberList().get(i).isKo()) {
                teamOneFinish = false;
                break;
            }
        }

        for (int i = 0; i < 4; i++){
            int j = i + 4;
            if (!combatManager.getCombat().getCombatMemberList().get(j).isKo()) {
                teamTwoFinish = false;
                break;
            }
        }

        if (teamOneFinish || teamTwoFinish){
            combatEnded = true;
        }

        return combatEnded;
    }
    
    private void pressEnter() {
        menu.print("<Press enter to continue...>");
        menu.askString();
    }
}

//defensa