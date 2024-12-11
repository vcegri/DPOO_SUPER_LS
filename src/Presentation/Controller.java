package Presentation;

import Business.*;
import edu.salle.url.api.exception.ApiException;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import java.io.IOException;
import java.util.Random;

/**
 * Controller class that manages the logic and interactions
 * between the user interface and managers.
 */

public class Controller {
    private final Menu menu;
    private final CharacterManager characterManager;
    private final TeamManager teamManager;
    private final ItemManager itemManager;
    private final StatManager statManager;
    private final CombatManager combatManager;

    /**
     * Constructs a Controller with the given managers and menu interface.
     *
     * @param menu             menu interface for user interaction.
     * @param characterManager manager of character data and operations.
     * @param teamManager      manager of team data and operations.
     * @param itemManager      manager of item data and operations.
     * @param statManager      manager of team stats data and operations.
     * @param combatManager    manager that controls combat simulations.
     */
    public Controller(Menu menu, CharacterManager characterManager, TeamManager teamManager, ItemManager itemManager, StatManager statManager, CombatManager combatManager) {
        this.menu = menu;
        this.characterManager = characterManager;
        this.teamManager = teamManager;
        this.itemManager = itemManager;
        this.statManager = statManager;
        this.combatManager = combatManager;
    }

    /**
     * Runs the main application loop, controlling the program functions.
     *
     * @throws IOException if an I/O error happens during file operations.
     */
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

    /**
     * Ask the user to select an option.
     *
     * @param min minimum option.
     * @param max maximum option.
     * @return selected option.
     */
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

    /**
     * Starts the program after verifying the state of required files.
     *
     * @return true if the program can start, false if not.
     */
    private boolean startProgram() {
        boolean startProgram = false;
        int characterFileOk = characterManager.fileOK();
        int itemFileOk = itemManager.fileOK();

        if (characterFileOk == 1 && itemFileOk == 1) {
            menu.correctFile();
            startProgram = true;
        }
        else {
            menu.println("Api failed...");
            if(characterFileOk == 3 && itemFileOk == 3) {
                menu.correctFile();
                startProgram = true;
            }
            else {

                if (characterFileOk == 2 && itemFileOk == 2) {
                    menu.incorrectFile("charaters.json y items.json files");
                }
                else if (characterFileOk == 2) {
                    menu.incorrectFile("characters.json file");
                }
                else {
                    menu.incorrectFile("items.json file");
                }
            }
        }
        return (startProgram);
    }

    /**
     * Show a list of characters and their details.
     *
     * @throws IOException if an I/O error happens while accessing character data.
     */
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

    /**
     * Manages team operations such as creation, listing, and deletion.
     *
     * @throws FileNotFoundException if the JSON file can't be found.
     */
    private void manageTeams() throws FileNotFoundException, ApiException {
        menu.manageTeamsMenu();
        int option = selectOption(1, 4);
        switch (option) {
            case 1 -> createTeam();
            case 2 -> listTeams();
            case 3 -> deleteTeam();
        }

    }

    /**
     * Creates a new team asking the user.
     *
     * @throws FileNotFoundException if the JSON file can't be found.
     */
    private void createTeam() throws FileNotFoundException, ApiException {
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

    /**
     * Takes a TeamMember based on a character name or ID.
     *
     * @param characterName name or ID of the character.
     * @param j             index of the character in the team.
     * @return corresponding TeamMember.
     * @throws FileNotFoundException if the JSON file can't be found.
     */
    private TeamMember getTeamMember(String characterName, int j) throws FileNotFoundException, ApiException {
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
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }

        if (characterExist){
            menu.println("Game strategy for character #" + j + "?");
            menu.println("\t1) Balanced");
            menu.println("\t2) Offensive");
            menu.println("\t3) Defensive");
            menu.println("\t4) Sniper");
            int opcio = selectOption(1,4);
            String strategy = whichStrategy(opcio);
            if (isLong){
                teamMember = new TeamMember(id, strategy);
            }
            else {
                teamMember = new TeamMember(characterManager.getIdByName(characterName), strategy);
            }
        }

        return teamMember;
    }

    private String whichStrategy(int opcio) {
        String strategy = "";

        if (opcio == 1) {
            strategy = "Balanced";
        }
        if (opcio == 2) {
            strategy = "Offensive";
        }
        if (opcio == 3) {
            strategy = "Defensive";
        }
        if (opcio == 4) {
            strategy = "Sniper";
        }

        return strategy;
    }

    /**
     * Show a list of teams and their details.
     *
     * @throws FileNotFoundException if the JSON file can't be found.
     */
    private void listTeams() throws FileNotFoundException, ApiException {
        ArrayList<String> teamNameList;
        ArrayList<Long> memberIdList = new ArrayList<>();
        ArrayList<String> memberNameList;
        ArrayList<Integer> statList;
        ArrayList<String> strategy = new ArrayList<>();
        ArrayList<Team> teamList = teamManager.getTeamList();

        teamNameList = teamManager.getNameOfTeams();
        menu.printList(teamNameList);
        menu.print("Choose an option: ");
        int max = teamNameList.size();
        int teamName = selectOption(0, max);

        if (teamName != 0) {
            teamName--;
            for (int i = 0; i < 4; i++) {
                strategy.add(teamList.get(teamName).getMemberList().get(i).getStrategy());
            }
            String name = teamNameList.get(teamName);
            memberNameList = characterManager.getNameById(memberIdList);
            statList = statManager.getStatList(name);
            menu.teamInfo(name, memberNameList, statList.get(0), statList.get(1), statList.get(2), statList.get(3), statList.get(4), strategy);
            pressEnter();
        }
    }

    /**
     * Deletes a team asking the user.
     *
     * @throws FileNotFoundException if the JSON file can't be found.
     */
    private void deleteTeam() throws FileNotFoundException, ApiException {
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

    /**
     * Show a list of items and their details.
     *
     * @throws IOException if an I/O error happens while accessing item data.
     */
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

    /**
     * Simulates a combat between two teams.
     *
     * @throws FileNotFoundException if the JSON file can't be found.
     */
    private void simulateCombat() throws FileNotFoundException, ApiException {
        boolean existTeam;

        menu.println("Starting simulation...");
        existTeam = chooseTeam();
        if (existTeam){
            executeCombat();
        }
    }

    /**
     * Show which teams are available for combat and prepares them for the combat.
     *
     * @return true if teams are available for combat, false if not.
     * @throws FileNotFoundException if the JSON file can't be found.
     */
    private boolean chooseTeam() throws FileNotFoundException, ApiException {
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

    /**
     * Ask the user to select two teams for combat.
     *
     * @return list of the two selected teams.
     * @throws FileNotFoundException if the JSON file can't be found.
     */
    private ArrayList<Team> chooseTeamForCombat() throws FileNotFoundException, ApiException {
        ArrayList<String> teamNameList;
        ArrayList<Team> teamFight = new ArrayList<>();

        teamNameList = teamManager.getNameOfTeams();
        menu.printList(teamNameList);

        teamFight.add(chooseSingleTeam(teamNameList, 1));
        teamFight.add(chooseSingleTeam(teamNameList, 2));

        return teamFight;
    }

    /**
     * Ask the user to select a single team for combat.
     *
     * @param teamNameList list of team names that can fight.
     * @param numTeam team number being selected (1 or 2).
     * @return selected team.
     * @throws FileNotFoundException if the JSON file can't be found.
     */
    private Team chooseSingleTeam(ArrayList<String> teamNameList, int numTeam) throws FileNotFoundException, ApiException {
        String teamName;
        Team team;
        
        menu.print("\nChoose team #" + numTeam + ": ");
        int max = teamNameList.size();
        int option = selectOption(1, max) - 1;
        teamName = teamNameList.get(option);
        team = teamManager.getTeamByName(teamName);
        
        return team;
    }

    /**
     * Show the details of a team for combat simulation.
     *
     * @param numTeam  team number in the combat.
     * @param teamFight list of teams in the combat.
     * @throws FileNotFoundException if the JSON file can't be found.
     */
    private void showTeamInfoForCombat(int numTeam, ArrayList<Team> teamFight) throws FileNotFoundException, ApiException {
        int teamSize = 4;
        ArrayList<String> teamMemberNameList = new ArrayList<>();
        ArrayList<Long> teamMemberIdList = new ArrayList<>();
        ArrayList<String> armorNameList = new ArrayList<>();
        ArrayList<String> weaponNameList = new ArrayList<>();
        ArrayList<Item> weaponList = new ArrayList<>();
        ArrayList<Item> armorList = new ArrayList<>();

        for (int i = 0; i < teamSize; i++){
            teamMemberIdList.add(teamFight.get(numTeam).getMemberList().get(i).getId());
            weaponList.add(itemManager.setRandomWeapon());
            weaponNameList.add(weaponList.get(i).getName());
            armorList.add(itemManager.setRandomArmor());
            armorNameList.add(armorList.get(i).getName());
            teamMemberNameList = characterManager.getNameById(teamMemberIdList);
        }
        int showNumTeam = numTeam + 1;
        menu.showTeamInfoForCombat(showNumTeam, teamFight.get(numTeam).getName(), teamMemberNameList, weaponNameList, armorNameList);

        this.combatManager.setCombat(teamFight, weaponList, armorList, characterManager.getCharacterListByIdList(teamMemberIdList));

    }

    /**
     * Executes all rounds of a combat by simulating each CombatMember action.
     *
     * @throws FileNotFoundException if the JSON file can't be found.
     */
    private void executeCombat() throws FileNotFoundException, ApiException {
        int roundNum = 1;
        ArrayList<Boolean> koList = new ArrayList<>();
        ArrayList<Double> damageTakenList = new ArrayList<>();
        ArrayList<String> memberNameList = new ArrayList<>();

        do {
            combatManager.checkDefenders();
            menu.println("--- ROUND " + roundNum + " ---");
            roundTeamInfo();
            executeAction();
            menu.println("");
            brokenWeapon();
            brokenArmor();
            menu.println("");
            combatManager.calculateKo();
            deadCombatMembers();
            roundNum++;
        } while (!combatManager.checkEndCombat());
        int teamNumber = combatManager.checkWinner();
        String winner;
        if (teamNumber != 2) {
            winner = combatManager.getTeamList().get(teamNumber).getName();
        }
        else {
            winner = "";
        }
        for (int i = 0; i < this.combatManager.getCombatMemberList().size(); i++) {
            koList.add(combatManager.getCombatMemberList().get(i).isKo());
            damageTakenList.add(this.combatManager.getCombatMemberList().get(i).getDamage());
            memberNameList.add(this.combatManager.getCombatMemberList().get(i).getCharacter().getName());
        }

        menu.endCombat(winner);
        menu.finalRound(memberNameList, damageTakenList, koList);
        updateStats(winner, koList);
        this.combatManager.endCombat();
    }

    /**
     * Show the state of teams after each combat round, including CombatMember details.
     *
     * @throws FileNotFoundException if the JSON file can't be found.
     */
    private void roundTeamInfo() throws FileNotFoundException, ApiException {
        int teamNumber;
        ArrayList<String> nameList;
        ArrayList<String> weaponList;
        ArrayList<String> armorList;
        ArrayList<Double> damageList;
        ArrayList<Long> idList = new ArrayList<>();
        ArrayList<Boolean> koList = new ArrayList<>();
        ArrayList<Team> teamList = combatManager.getTeamList();

        for (int k = 0; k < teamList.size(); k++) {
            teamNumber = k +1;
            idList.clear();
            koList.clear();
            for (int i = 0; i < teamList.get(k).getMemberList().size(); i++) {
                idList.add(teamList.get(k).getMemberList().get(i).getId());
                if (k == 0){
                    koList.add(combatManager.getCombatMemberList().get(i).isKo());
                }
                else {
                    int j = i +4;
                    koList.add(combatManager.getCombatMemberList().get(j).isKo());
                }
            }
            nameList = characterManager.getNameById(idList);
            weaponList = combatManager.getWeaponList(k);
            armorList = combatManager.getArmorList(k);
            damageList = combatManager.getDamageList(k);

            menu.roundinfo(teamNumber, teamList.get(k).getName(), nameList, weaponList, armorList, damageList, koList);
        }
    }

    /**
     * Executes actions of all CombatMembers.
     *
     * @throws FileNotFoundException if the JSON file can't be found.
     */
    private void executeAction() throws FileNotFoundException, ApiException {

        for (int i = 0; i < combatManager.getCombatMemberList().size(); i++){
            CombatMember combatMember = combatManager.getCombatMemberList().get(i);

            String action = combatManager.getCombatMemberList().get(i).chooseAction();
            if (action.equals("ATACK")) {
                atack(i);
            }
            if (action.equals("DEFEND") && combatMember instanceof CombatMemberBalanced){
                ((CombatMemberBalanced) combatMember).setDefendingStatus(true);
            }
            if (action.equals("DEFEND") && combatMember instanceof CombatMemberDeffensive){
                ((CombatMemberDeffensive) combatMember).setDefendingStatus(true);
            }
            if (action.equals("NEW_WEAPON")){
                newWeapon(i);
            }
        }
    }

    /**
     * Simulates an attack from a CombatMember to a random opponent.
     *
     * @param i index of the attacking CombatMember at the combat list.
     */
    private void atack(int i){

        String  attackerName = this.combatManager.getCombatMemberList().get(i).getCharacter().getName();

        int objective = selectObjective(i);

        String weaponName;
        String defenderName = this.combatManager.getCombatMemberList().get(objective).getCharacter().getName();
        if (this.combatManager.getCombatMemberList().get(objective).getWeapon() != null) {
            weaponName = this.combatManager.getCombatMemberList().get(objective).getWeapon().getName();
        }
        else {
            weaponName = "null";
        }
        double damage = combatManager.calculateDamage(this.combatManager.getCombatMemberList().get(i));
        double finalDamage = combatManager.calculateReducedDamage(damage, this.combatManager.getCombatMemberList().get(objective));

        CombatMember combatMember = combatManager.getCombatMemberList().get(objective);

        if (combatMember instanceof CombatMemberDeffensive) {
            if (((CombatMemberDeffensive) combatMember).isDefending()) {
                finalDamage = finalDamage - combatManager.calculateDamageReduction(this.combatManager.getCombatMemberList().get(objective));
            }
        }
        if (combatMember instanceof CombatMemberBalanced) {
            if (((CombatMemberBalanced) combatMember).isDefending()) {
                finalDamage = finalDamage - combatManager.calculateDamageReduction(this.combatManager.getCombatMemberList().get(objective));
            }
        }

        this.combatManager.getCombatMemberList().get(objective).updateDamage(finalDamage);
        this.combatManager.getCombatMemberList().get(objective).setAtacked(true);
        menu.combatAttack(attackerName, defenderName, weaponName, damage, finalDamage);
        combatManager.updateItemDurability(i, objective);
    }

    private int selectObjective(int i){
        Random random = new Random();
        int index = 0;
        int maxDamage = 0;

        if (combatManager.getCombatMemberList().get(i).getStrategy().equals("Sniper")) {
            if (i < 4) {
                do {
                    for (int j = 0; j < 4; j++) {
                        if (combatManager.getCombatMemberList().get(j).getDamage() > maxDamage) {
                            index = j;
                        }
                    }
                } while (combatManager.getCombatMemberList().get(index).isKo());
            } else {
                do {
                    for (int j = 4; j < 8; j++) {
                        if (combatManager.getCombatMemberList().get(j).getDamage() > maxDamage) {
                            index = j;
                        }
                    }
                } while (combatManager.getCombatMemberList().get(index).isKo());
            }
        }
        else {
            if (i < 4) {
                do {
                    index = random.nextInt(4) + 4;
                } while (combatManager.getCombatMemberList().get(index).isKo());
            } else {
                do {
                    index = random.nextInt(4);
                } while (combatManager.getCombatMemberList().get(index).isKo());
            }
        }

        return index;
    }

    /**
     * Set a new weapon to a CombatMember if they don't have one.
     *
     * @param i index of the CombatMember at the list.
     * @throws FileNotFoundException if the JSON file can't be found.
     */
    private void newWeapon(int i) throws FileNotFoundException, ApiException {
        this.combatManager.getCombatMemberList().get(i).setWeapon(itemManager.setRandomWeapon());
        menu.println(combatManager.getCombatMemberList().get(i).getCharacter().getName() + " GOT A NEW WEAPON");
    }

    /**
     * Checks for CombatMembers broken weapons and updates their status.
     */
    private void brokenWeapon(){
        
        for (int i = 0; i < combatManager.getCombatMemberList().size(); i++) {
            if (combatManager.getCombatMemberList().get(i).getWeapon() != null) {
                if (combatManager.getCombatMemberList().get(i).getWeapon().getDurability() == 0) {
                    String characterName = combatManager.getCombatMemberList().get(i).getCharacter().getName();
                    String weaponName = combatManager.getCombatMemberList().get(i).getWeapon().getName();
                    combatManager.getCombatMemberList().get(i).setWeapon(null);
                    menu.itemBreak(characterName, weaponName);
                }
            }
        }
        menu.println("");
    }

    /**
     * Checks for CombatMembers broken armor and updates their status.
     */
    private void brokenArmor(){

        for (int i = 0; i < combatManager.getCombatMemberList().size(); i++) {
            if (combatManager.getCombatMemberList().get(i).getArmor() != null) {
                if (combatManager.getCombatMemberList().get(i).getArmor().getDurability() == 0) {
                    String characterName = combatManager.getCombatMemberList().get(i).getCharacter().getName();
                    String armorName = combatManager.getCombatMemberList().get(i).getArmor().getName();
                    combatManager.getCombatMemberList().get(i).setArmor(null);
                    menu.itemBreak(characterName, armorName);
                }
            }
        }
    }

    /**
     * Show all the CombatMembers who have been KO during the combat.
     */
    private void deadCombatMembers(){

        for (int i = 0; i < combatManager.getCombatMemberList().size(); i++) {
            if (combatManager.getCombatMemberList().get(i).isKo()) {
                menu.charcterKO(combatManager.getCombatMemberList().get(i).getCharacter().getName());
            }
        }
        menu.println("");
    }

    /**
     * Updates the stats for teams and characters after a combat.
     *
     * @param winner name of the winning team.
     * @param koList a list indicating KO variable for each character.
     * @throws FileNotFoundException if the JSON file can't be found.
     */
    private void updateStats(String winner, ArrayList<Boolean> koList) throws FileNotFoundException, ApiException {
        String teamName;
        int teamNum = 2;

        for (int i = 0; i < teamNum; i++) {
            teamName = combatManager.getTeamList().get(i).getName();
            statManager.updateStats(winner, teamName, koList, i);
        }
    }

    /**
     * Waits for the user to press Enter.
     */
    private void pressEnter() {
        menu.print("<Press enter to continue...>");
        menu.askString();
    }
}