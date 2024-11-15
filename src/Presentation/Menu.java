package Presentation;
import Business.Character;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    public Menu() {
    }
    public String askString(){
        Scanner scanner = new Scanner(System.in);
        String string;

        string = scanner.nextLine();

        return (string);
    }

    public int askInt(){
        Scanner scanner = new Scanner(System.in);
        int integer = 0;

        if (scanner.hasNextInt()) {
            integer = scanner.nextInt();
            scanner.nextLine();
        } else {
            scanner.nextLine();
            print("Yoy may write an integer");
            System.out.print("\nPlease choose one option: ");
        }

        return (integer);
    }

    public void print(String string){
        System.out.println(string);
    }

    public void initialMenu(){
        print("    / __   __   ___    ___     ___   ___   __    __   ");
        print("   | |__) /__\\ | | \\  | | \\   / __| / __|  \\ \\  / /   ");
        print("   | |   /    \\| | |  | |  \\  \\__ \\ \\__ \\   \\ \\/ /    ");
        print("   |_|  /__/\\__\\_| |__|_| \\_\\ |___/ |___/    \\__/     ");
        print("                                                     ");
        print("      /\\      ___     ___   __    __   __   __       ");
        print("     /  \\    / _ \\   | | \\  | |  | |  | |\\  | |       ");
        print("    / /\\ \\  / / \\ \\  | |  \\ | |  | |  | | \\ | |       ");
        print("   / /__\\ \\/ /   \\ \\ | | \\  | |__| |  | |  \\| |       ");
        print("  /_/    \\_/    |_| |_|  \\_|____/    |_|   |_|        ");
        print("\nWelcome to Super LS, Bro! Simulator.\n");
        print("Verifying local files...");
    }

    public void correctFile(){
        print("Files OK.");
        print("Starting program...");
    }

    public void incorrectFile(String file){
        print("Error: The " + file + "can't be accessed.");
        print("Shutting down...");
    }

    public void principalMenu(){
        print("\n\t1) List Characters");
        print("\t2) Manage Teams");
        print("\t3) List Items");
        print("\t4) Simulate Combat\n");
        print("\t5) Exit\n");
        print("Choose an option: ");
    }

    public void invalidOption(){
        print("Invalid Option, please choose a correct one (you have the options above)");
    }

    public void characterList(ArrayList<String> characterNameList){
        print("\n");
        for (int i = 0; i < characterNameList.size(); i++) {
            int j = i+1;
            String name = characterNameList.get(i);
            print("\t" + j + ") " + name);
        }
        print("\n\t0) Back");
        print("Choose an option: ");
    }

    public void characterInfo(int characterId, String characterName, int characterWeight, ArrayList<String> characterTeamList){
        print("\n\tID:\t" + characterId);
        print("\tNAME:\t" + characterName);
        print("\tWEIGHT:\t" + characterWeight + "kg");
        print("\tTEAMS:");
        for (String team : characterTeamList) {
            print("\t\t - " + team);
        }
    }

    public void manageTeamsMenu(){
        print("\nTeam management.");
        print("\t1) Create a Team");
        print("\t2) List Teams");
        print("\t3) Delete Team\n");
        print("\t4) Back\n");
        print("Choose an option: ");
    }

    public void createTeam(){
        print("\nPlease enter the team's name: ");
    }

    public void teamList(ArrayList<String> teamNameList){
        print("\n");
        for (int i = 0; i < teamNameList.size(); i++) {
            int j = i+1;
            String name = teamNameList.get(i);
            print("\t" + j + ") " + name);
        }
        print("\n\t0) Back");
        print("Choose an option: ");
    }

    public void teamInfo(String teamName, ArrayList<String> characterNameList, int combatPlayed, int combatWon, int winRate, int KoDone, int KoReceived){
        print("\tTeam name: " + teamName + "\n");
        for (int i = 0; i < characterNameList.size(); i++) {
            int j = i+1;
            String name = characterNameList.get(i);
            print("\tCharacter #" + j + ": " + name + "\t\t(BALANCED)");
        }
        print("\n\tCombats played:\t" + combatPlayed);
        print("\tCombats won:\t" + combatWon);
        print("\tWin rate:\t\t" + winRate + "%");
        print("\tKo's done:\t\t" + KoDone);
        print("\tKo's received:\t" + KoReceived);
    }

    public void deleteTeam(){
        print("\n\tEnter the name of the team to remove: ");
    }

    public void itemList(ArrayList<String> itemNameList){
        print("\n");
        for (int i = 0; i < itemNameList.size(); i++) {
            int j = i+1;
            String name = itemNameList.get(i);
            print("\t" + j + ") " + name);
        }
        print("\n\t0) Back");
        print("Choose an option: ");
    }

    public void itemInfo(int itemID, String name, String classe, int power, int durability){
        print("\n\tID:\t\t" + itemID);
        print("\tNAME:\t\t" + name);
        print("\n\tCLASS:\t\t" + classe);
        print("\n\tPOWER:\t\t" + power);
        print("\n\tDURABILITY:\t" + durability);
    }

    public ArrayList<Integer> startCombat(ArrayList<String> teamNameList){
        ArrayList<Integer> teamFight = new ArrayList<>();
        print("\nStarting simulation...");
        print("\nLooking for available team...\n");
        for (int i = 0; i < teamNameList.size(); i++) {
            int j = i+1;
            String name = teamNameList.get(i);
            print("\t" + j + ") " + name);
        }
        print("\nChoose team #1: ");
        int max = teamNameList.size() + 1;
        teamFight.add(escollirOpcio(1, max));

        print("Choose team #2: ");
        max = teamNameList.size() + 1;
        teamFight.add(escollirOpcio(1, max));

        print("Initializing teams...");

        return (teamFight);
    }

    public void memberTeamList(int teamNumber, String teamName, ArrayList<String> teamMemberNameList, ArrayList<String> teamWeaponList, ArrayList<String> teamArmorList){
        print("\tTeam #" + teamNumber + " - " + teamName);
        for (int i = 0; i < teamMemberNameList.size(); i++) {
            String name = teamMemberNameList.get(i);
            String weapon = teamWeaponList.get(i);
            String armor = teamArmorList.get(i);
            print("\t- " + name);
            print("\t\tWeapon: " + weapon);
            print("\t\tArmor: " + armor);
        }
    }

    public void roundinfo(int teamNumber, String teamName, ArrayList<String> teamMemberNameList, ArrayList<String> teamWeaponList, ArrayList<String> teamArmorList, ArrayList<String> damageTakenList){
        print("\tTeam #" + teamNumber + " - " + teamName);
        for (int i = 0; i < teamMemberNameList.size(); i++) {
            String name = teamMemberNameList.get(i);
            String weapon = teamWeaponList.get(i);
            String armor = teamArmorList.get(i);
            String damageTaken = damageTakenList.get(i);
            print("\t- " + name + "(" + damageTaken + ") " + weapon + " - " + armor);
        }
    }

    public void combatAttack(String attacker, String defender, String weapon, double damage, double damageTaken){
        print(attacker + "ATTACKS" + defender + "WITH" + weapon + "FOR" + damage + "DAMAGE!");
        print(defender + "RECEIVES" + damageTaken + "DAMAGE!");
    }

    public void itemBreak(String memberName, String weaponName){
        print("Oh no! " + memberName + "'s " + weaponName + "breaks!");
    }

    public void charcterKO(String memberName){
        print(memberName + "flies away! It's a KO!");
    }

    public void endCombat(String winner){
        print("--- END OF COMBAT ---\n");
        print("...and" + winner + "wins!\n");
    }

    public void finalRound(int teamNumber, String teamName, ArrayList<String> teamMemberNameList, ArrayList<String> damageTakenList){
        print("\tTeam #" + teamNumber + " - " + teamName);
        for (int i = 0; i < teamMemberNameList.size(); i++) {
            String name = teamMemberNameList.get(i);
            String damageTaken = damageTakenList.get(i);
            print("\t- " + name + "(" + damageTaken + ")");
        }
    }
}