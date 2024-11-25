package Presentation;
import Business.Character;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {


    public Menu() {
    }

    public String askString() {
        Scanner scanner = new Scanner(System.in);
        String string;

        string = scanner.nextLine();

        return (string);
    }

    public int askInt() {
        Scanner scanner = new Scanner(System.in);
        int integer = 0;
        int flag;

        do {
            if (scanner.hasNextInt()) {
                integer = scanner.nextInt();
                scanner.nextLine();
                flag = 1;
            } else {
                scanner.nextLine();
                println("Yoy may write an integer");
                flag = 0;
            }
        }while (flag !=1);

        return (integer);
    }

    public void println(String string) {
        System.out.println(string);
    }

    public void print(String string) {
        System.out.print(string);
    }

    public void initialMenu() {
        System.out.println("   ___                      _    ___     ___         _   ");
        System.out.println("  / __|_  _ _ __  ___ _ _  | |  / __|   | _ )_ _ ___| |");
        System.out.println("  \\__ \\ || | '_ \\/ -_) '_| | |__\\__ \\_  | _ \\ '_/ _ \\_|");
        System.out.println("  |___/\\_,_| .__/\\___|_|   |____|___( ) |___/_| \\___(_)");
        System.out.println("           |_|                      |/");
        println("\nWelcome to Super LS, Bro! Simulator.\n");
        println("Verifying local files...");
    }

    public void correctFile() {
        println("Files OK.");
        println("Starting program...");
    }

    public void incorrectFile(String file) {
        println("Error: The " + file + "can't be accessed.");
        println("Shutting down...");
    }

    public void principalMenu() {
        println("\n\t1) List Characters");
        println("\t2) Manage Teams");
        println("\t3) List Items");
        println("\t4) Simulate Combat\n");
        println("\t5) Exit\n");
        print("Choose an option: ");
    }

    public void invalidOption() {
        println("Invalid Option, please choose a correct one (you have the options above)");
    }

    public void characterList(ArrayList<String> characterNameList) {
        println("\n");
        for (int i = 0; i < characterNameList.size(); i++) {
            int j = i+1;
            String name = characterNameList.get(i);
            println("\t" + j + ") " + name);
        }
        println("\n\t0) Back");
        print("Choose an option: ");
    }

    public void characterInfo(long characterId, String characterName, int characterWeight, ArrayList<String> characterTeamList) {
        println("\n\tID: " + characterId);
        println("\tNAME: " + characterName);
        println("\tWEIGHT: " + characterWeight + "kg");
        println("\tTEAMS: ");
        for (String team : characterTeamList) {
            println("\t\t - " + team);
        }
    }

    public void manageTeamsMenu() {
        println("\nTeam management.");
        println("\t1) Create a Team");
        println("\t2) List Teams");
        println("\t3) Delete Team\n");
        println("\t4) Back\n");
        print("Choose an option: ");
    }

    public void createTeam() {
        print("\nPlease enter the team's name: ");
    }

    public void teamList(ArrayList<String> teamNameList) {
        println("\n");
        for (int i = 0; i < teamNameList.size(); i++) {
            int j = i+1;
            String name = teamNameList.get(i);
            println("\t" + j + ") " + name);
        }
        println("\n\t0) Back");
        print("Choose an option: ");
    }

    public void teamInfo(String teamName, ArrayList<String> characterNameList, int combatPlayed, int combatWon, int koDone, int koReceived, int winrate) {
        println("\tTeam name: " + teamName + "\n");
        for (int i = 0; i < characterNameList.size(); i++) {
            int j = i+1;
            String name = characterNameList.get(i);
            println("\tCharacter #" + j + ": " + name + "\t\t(BALANCED)");
        }
        println("\n\tCombats played:\t" + combatPlayed);
        println("\tCombats won:\t" + combatWon);
        println("\tWin rate:\t\t" + winrate + "%");
        println("\tKo's done:\t\t" + koDone);
        println("\tKo's received:\t" + koReceived);
    }

    public void deleteTeam() {
        println("\n\tEnter the name of the team to remove: ");
    }

    public void itemList(ArrayList<String> itemNameList) {
        println("\n");
        for (int i = 0; i < itemNameList.size(); i++) {
            int j = i+1;
            String name = itemNameList.get(i);
            println("\t" + j + ") " + name);
        }
        println("\n\t0) Back");
        print("Choose an option: ");
    }

    public void itemInfo(long itemID, String name, String classe, int power, int durability) {
        println("\n\tID: " + itemID);
        println("\tNAME: " + name);
        println("\tCLASS: " + classe);
        println("\tPOWER: " + power);
        println("\tDURABILITY: " + durability);
    }

    public void startCombat(ArrayList<String> teamNameList) {
        println("\nStarting simulation...");
        println("\nLooking for available team...\n");
        for (int i = 0; i < teamNameList.size(); i++) {
            int j = i+1;
            String name = teamNameList.get(i);
            println("\t" + j + ") " + name);
        }
    }

    public void memberTeamList(int teamNumber, String teamName, ArrayList<String> teamMemberNameList, ArrayList<String> teamWeaponList, ArrayList<String> teamArmorList) {
        println("\tTeam #" + teamNumber + " - " + teamName);
        for (int i = 0; i < teamMemberNameList.size(); i++) {
            String name = teamMemberNameList.get(i);
            String weapon = teamWeaponList.get(i);
            String armor = teamArmorList.get(i);
            println("\t- " + name);
            println("\t\tWeapon: " + weapon);
            println("\t\tArmor: " + armor);
        }
    }

    public void roundinfo(int teamNumber, String teamName, ArrayList<String> teamMemberNameList, ArrayList<String> teamWeaponList, ArrayList<String> teamArmorList, ArrayList<String> damageTakenList) {
        println("\tTeam #" + teamNumber + " - " + teamName);
        for (int i = 0; i < teamMemberNameList.size(); i++) {
            String name = teamMemberNameList.get(i);
            String weapon = teamWeaponList.get(i);
            String armor = teamArmorList.get(i);
            String damageTaken = damageTakenList.get(i);
            println("\t- " + name + "(" + damageTaken + ") " + weapon + " - " + armor);
        }
    }

    public void combatAttack(String attacker, String defender, String weapon, double damage, double damageTaken) {
        println(attacker + "ATTACKS" + defender + "WITH" + weapon + "FOR" + damage + "DAMAGE!");
        println(defender + "RECEIVES" + damageTaken + "DAMAGE!");
    }

    public void itemBreak(String memberName, String weaponName) {
        println("Oh no! " + memberName + "'s " + weaponName + "breaks!");
    }

    public void charcterKO(String memberName) {
        println(memberName + "flies away! It's a KO!");
    }

    public void endCombat(String winner) {
        println("--- END OF COMBAT ---\n");
        println("...and" + winner + "wins!\n");
    }

    public void finalRound(int teamNumber, String teamName, ArrayList<String> teamMemberNameList, ArrayList<String> damageTakenList) {
        println("\tTeam #" + teamNumber + " - " + teamName);
        for (int i = 0; i < teamMemberNameList.size(); i++) {
            String name = teamMemberNameList.get(i);
            String damageTaken = damageTakenList.get(i);
            println("\t- " + name + "(" + damageTaken + ")");
        }
    }
}