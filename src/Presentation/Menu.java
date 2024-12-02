package Presentation;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages the presentation layer of the application by displaying menus and requesting user input.
 * This class provides methods for displaying information and interacting with the user by the console.
 */
public class Menu {

    /**
     * Constructor for Menu class.
     */
    public Menu() {}

    /**
     * Ask the user a string.
     *
     * @return the string that the user provide.
     */
    public String askString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Ask the user an integer.
     * If the user doesn't provide a correct integer, it will ask again for a new integer.
     *
     * @return the integer the user provide
     */
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
                println("You may write an integer");
                flag = 0;
            }
        } while (flag != 1);

        return integer;
    }

    /**
     * Prints a message with an enter.
     *
     * @param string the message to print
     */
    public void println(String string) {
        System.out.println(string);
    }

    /**
     * Prints a message without an enter.
     *
     * @param string the message to print
     */
    public void print(String string) {
        System.out.print(string);
    }

    /**
     * Show the initial welcome message and the file verification message.
     */
    public void initialMenu() {
        System.out.println("   ___                      _    ___     ___         _   ");
        System.out.println("  / __|_  _ _ __  ___ _ _  | |  / __|   | _ )_ _ ___| |");
        System.out.println("  \\__ \\ || | '_ \\/ -_) '_| | |__\\__ \\_  | _ \\ '_/ _ \\_|");
        System.out.println("  |___/\\_,_| .__/\\___|_|   |____|___( ) |___/_| \\___(_)");
        System.out.println("           |_|                      |/");
        println("\nWelcome to Super LS, Bro! Simulator.\n");
        println("Verifying local files...");
    }

    /**
     * Show a message indicating the files are okay and the program is starting.
     */
    public void correctFile() {
        println("Files OK.");
        println("Starting program...");
    }

    /**
     * Show an error message if a file can't be accessed.
     *
     * @param file the name of the file
     */
    public void incorrectFile(String file) {
        println("Error: The " + file + " can't be accessed.");
        println("Shutting down...");
    }

    /**
     * Prints the main menu.
     */
    public void principalMenu() {
        println("\n\t1) List Characters");
        println("\t2) Manage Teams");
        println("\t3) List Items");
        println("\t4) Simulate Combat\n");
        println("\t5) Exit\n");
        print("Choose an option: ");
    }

    /**
     * Prints a message showing that the user provided an invalid option.
     */
    public void invalidOption() {
        println("Invalid Option, please choose a correct one (you have the options above)");
    }

    /**
     * Prints the character information.
     *
     * @param characterId      the character ID
     * @param characterName    the character name
     * @param characterWeight  the character weight
     * @param characterTeamList ArrayList with all the teams that the character is part of
     */
    public void characterInfo(long characterId, String characterName, int characterWeight, ArrayList<String> characterTeamList) {
        println("\n\tID: " + characterId);
        println("\tNAME: " + characterName);
        println("\tWEIGHT: " + characterWeight + "kg");
        println("\tTEAMS: ");
        for (String team : characterTeamList) {
            println("\t\t - " + team);
        }
    }

    /**
     * Prints the menu of the managing teams option.
     */
    public void manageTeamsMenu() {
        println("\nTeam management.");
        println("\t1) Create a Team");
        println("\t2) List Teams");
        println("\t3) Delete Team\n");
        println("\t4) Back\n");
        print("Choose an option: ");
    }

    /**
     * Ask the user a name of a new team.
     */
    public void createTeam() {
        print("\nPlease enter the team's name: ");
    }

    /**
     * Prints the list of names, with an index for each name.
     *
     * @param nameList the list of the names
     */
    public void printList(ArrayList<String> nameList) {
        println("\n");
        for (int i = 0; i < nameList.size(); i++) {
            int j = i + 1;
            String name = nameList.get(i);
            println("\t" + j + ") " + name);
        }
        println("\n\t0) Back");
    }

    /**
     * Show information about a team.
     *
     * @param teamName         the team name
     * @param characterNameList the list of all character names in the team
     * @param combatPlayed     the number of combats played
     * @param combatWon        the number of combats won
     * @param koDone           the number of KOs done
     * @param koReceived       the number of KOs received
     * @param winRate          the team win rate
     */
    public void teamInfo(String teamName, ArrayList<String> characterNameList, int combatPlayed, int combatWon, int koDone, int koReceived, int winRate) {
        println("\tTeam name: " + teamName + "\n");
        for (int i = 0; i < characterNameList.size(); i++) {
            int j = i + 1;
            String name = characterNameList.get(i);
            println("\tCharacter #" + j + ": " + name + "\t\t(BALANCED)");
        }
        println("\n\tCombats played:\t" + combatPlayed);
        println("\tCombats won:\t" + combatWon);
        println("\tWin rate:\t\t" + winRate + "%");
        println("\tKo's done:\t\t" + koDone);
        println("\tKo's received:\t" + koReceived);
    }

    /**
     * Ask the user a name to delete a team.
     */
    public void deleteTeam() {
        println("\n\tEnter the name of the team to remove: ");
    }

    /**
     * Show information of an item.
     *
     * @param itemID     the item ID
     * @param name       the item name
     * @param classe     the item type
     * @param power      the item power
     * @param durability the item durability
     */
    public void itemInfo(long itemID, String name, String classe, int power, int durability) {
        println("\n\tID: " + itemID);
        println("\tNAME: " + name);
        println("\tCLASS: " + classe);
        println("\tPOWER: " + power);
        println("\tDURABILITY: " + durability);
    }

    /**
     * Show the information of teams for combat.
     *
     * @param teamNumber        the team number
     * @param teamName          the team name
     * @param teamMemberNameList the list of team member names
     * @param teamWeaponList    the list of team member weapons
     * @param teamArmorList     the list of team member armors
     */
    public void showTeamInfoForCombat(int teamNumber, String teamName, ArrayList<String> teamMemberNameList, ArrayList<String> teamWeaponList, ArrayList<String> teamArmorList) {
        println("Initializing teams...\n");
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

    /**
     * Prints the details of a combat round.
     *
     * @param roundNum            the round number
     * @param teamNumber          the team number
     * @param teamName            the team name
     * @param teamMemberNameList  the list of team member names
     * @param teamWeaponList      the list of team member weapons
     * @param teamArmorList       the list of team member armors
     * @param damageTakenList     the list of damage received by each team member
     * @param koList              the list of KO value for each team member
     */
    public void roundinfo(int roundNum, int teamNumber, String teamName, ArrayList<String> teamMemberNameList, ArrayList<String> teamWeaponList, ArrayList<String> teamArmorList, ArrayList<Double> damageTakenList, ArrayList<Boolean> koList) {
        println("--- ROUND " + roundNum + " ---");
        println("\tTeam #" + teamNumber + " - " + teamName);
        for (int i = 0; i < teamMemberNameList.size(); i++) {
            String name = teamMemberNameList.get(i);
            String weapon = teamWeaponList.get(i);
            String armor = teamArmorList.get(i);
            double damageTaken = damageTakenList.get(i);
            if (!koList.get(i)) {
                println("\t- " + name + "(" + damageTaken + " %) " + weapon + " - " + armor);
            } else {
                println("\t- " + name + "(KO) " + weapon + " - " + armor);
            }
        }
    }

    /**
     * Show what is happening in a combat attack between two players.
     *
     * @param attacker     the name of the attacker
     * @param defender     the name of the defender
     * @param weapon       the weapon used by the attacker
     * @param damage       the damage done by the attacker
     * @param damageTaken  the damage received by the defender
     */
    public void combatAttack(String attacker, String defender, String weapon, double damage, double damageTaken) {
        println(attacker + " ATTACKS " + defender + " WITH " + weapon + " FOR " + damage + " DAMAGE!");
        println(defender + " RECEIVES " + damageTaken + " DAMAGE!");
    }

    /**
     * Prints a message when an item breaks during a combat.
     *
     * @param memberName   the name of the team member whose item broke
     * @param itemName   the name of the broken item
     */
    public void itemBreak(String memberName, String itemName) {
        println("Oh no! " + memberName + "'s " + itemName + " breaks!");
    }

    /**
     * Show a message when a character is KO.
     *
     * @param memberName the name of the character
     */
    public void charcterKO(String memberName) {
        println(memberName + " flies away! It's a KO!");
    }

    /**
     * Prints the result of a combat.
     *
     * @param winner the name of the winning team
     */
    public void endCombat(String winner) {
        println("--- END OF COMBAT ---\n");
        println("...and " + winner + " wins!\n");
    }

    /**
     * Show the final round information of a team.
     *
     * @param teamNumber          the team number
     * @param teamName            the team name
     * @param teamMemberNameList  the list of team member names
     * @param damageTakenList     the list of damage received by each team member
     */
    public void finalRound(int teamNumber, String teamName, ArrayList<String> teamMemberNameList, ArrayList<Double> damageTakenList, ArrayList<Boolean> koList) {
        println("\tTeam #" + teamNumber + " - " + teamName);
        for (int i = 0; i < teamMemberNameList.size(); i++) {
            String name = teamMemberNameList.get(i);
            double damageTaken = damageTakenList.get(i);
            if (!koList.get(i)) {
                println("\t- " + name + "(" + damageTaken + " %) ");
            } else {
                println("\t- " + name + "(KO) ");
            }
        }
    }
}
