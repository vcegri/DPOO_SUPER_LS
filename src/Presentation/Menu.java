package Presentation;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages the presentation layer of the application by displaying menus and requesting user input.
 * This class provides various methods for displaying information and interacting with the user via the console.
 */
public class Menu {

    /**
     * Default constructor for Menu class.
     */
    public Menu() {}

    /**
     * Prompts the user for a string input.
     *
     * @return the string input from the user
     */
    public String askString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Prompts the user for an integer input.
     * If the user does not provide a valid integer, it will prompt again.
     *
     * @return the integer input from the user
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
     * Prints a message to the console.
     *
     * @param string the message to print
     */
    public void println(String string) {
        System.out.println(string);
    }

    /**
     * Prints a message to the console without a newline.
     *
     * @param string the message to print
     */
    public void print(String string) {
        System.out.print(string);
    }

    /**
     * Displays the initial welcome and file verification message.
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
     * Displays a message indicating the files are OK and the program is starting.
     */
    public void correctFile() {
        println("Files OK.");
        println("Starting program...");
    }

    /**
     * Displays an error message if a file cannot be accessed.
     *
     * @param file the name of the file that could not be accessed
     */
    public void incorrectFile(String file) {
        println("Error: The " + file + " can't be accessed.");
        println("Shutting down...");
    }

    /**
     * Displays the main menu with available options.
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
     * Displays a message indicating the user has selected an invalid option.
     */
    public void invalidOption() {
        println("Invalid Option, please choose a correct one (you have the options above)");
    }

    /**
     * Displays the character's information.
     *
     * @param characterId      the character's ID
     * @param characterName    the character's name
     * @param characterWeight  the character's weight
     * @param characterTeamList the list of teams the character is part of
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
     * Displays the menu for managing teams.
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
     * Prompts the user to enter the name of a new team.
     */
    public void createTeam() {
        print("\nPlease enter the team's name: ");
    }

    /**
     * Prints the list of names, providing an index for each entry.
     *
     * @param nameList the list of names to display
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
     * Displays information about a team.
     *
     * @param teamName         the team's name
     * @param characterNameList the list of character names in the team
     * @param combatPlayed     the number of combats played by the team
     * @param combatWon        the number of combats won by the team
     * @param koDone           the number of KOs done by the team
     * @param koReceived       the number of KOs received by the team
     * @param winrate          the team's win rate percentage
     */
    public void teamInfo(String teamName, ArrayList<String> characterNameList, int combatPlayed, int combatWon, int koDone, int koReceived, int winrate) {
        println("\tTeam name: " + teamName + "\n");
        for (int i = 0; i < characterNameList.size(); i++) {
            int j = i + 1;
            String name = characterNameList.get(i);
            println("\tCharacter #" + j + ": " + name + "\t\t(BALANCED)");
        }
        println("\n\tCombats played:\t" + combatPlayed);
        println("\tCombats won:\t" + combatWon);
        println("\tWin rate:\t\t" + winrate + "%");
        println("\tKo's done:\t\t" + koDone);
        println("\tKo's received:\t" + koReceived);
    }

    /**
     * Prompts the user to enter the name of a team to delete.
     */
    public void deleteTeam() {
        println("\n\tEnter the name of the team to remove: ");
    }

    /**
     * Displays information about an item.
     *
     * @param itemID     the item's ID
     * @param name       the item's name
     * @param classe     the item's class
     * @param power      the item's power
     * @param durability the item's durability
     */
    public void itemInfo(long itemID, String name, String classe, int power, int durability) {
        println("\n\tID: " + itemID);
        println("\tNAME: " + name);
        println("\tCLASS: " + classe);
        println("\tPOWER: " + power);
        println("\tDURABILITY: " + durability);
    }

    /**
     * Displays the information of teams for combat, including team members, weapons, and armor.
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
     * Displays the details of a combat round, including each team's members and their status.
     *
     * @param roundNum            the round number
     * @param teamNumber          the team number
     * @param teamName            the team name
     * @param teamMemberNameList  the list of team member names
     * @param teamWeaponList      the list of team member weapons
     * @param teamArmorList       the list of team member armors
     * @param damageTakenList     the list of damage taken by each team member
     * @param koList              the list of KO statuses for each team member
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
     * Displays information about a combat attack between two players.
     *
     * @param attacker     the name of the attacker
     * @param defender     the name of the defender
     * @param weapon       the weapon used by the attacker
     * @param damage       the damage dealt by the attacker
     * @param damageTaken  the damage received by the defender
     */
    public void combatAttack(String attacker, String defender, String weapon, double damage, double damageTaken) {
        println(attacker + " ATTACKS " + defender + " WITH " + weapon + " FOR " + damage + " DAMAGE!");
        println(defender + " RECEIVES " + damageTaken + " DAMAGE!");
    }

    /**
     * Displays a message when an item breaks during combat.
     *
     * @param memberName   the name of the team member whose item broke
     * @param weaponName   the name of the broken weapon
     */
    public void itemBreak(String memberName, String weaponName) {
        println("Oh no! " + memberName + "'s " + weaponName + " breaks!");
    }

    /**
     * Displays a message when a character is knocked out (KO).
     *
     * @param memberName the name of the character who was KO'd
     */
    public void charcterKO(String memberName) {
        println(memberName + " flies away! It's a KO!");
    }

    /**
     * Displays the result of a combat, announcing the winner.
     *
     * @param winner the name of the winning team
     */
    public void endCombat(String winner) {
        println("--- END OF COMBAT ---\n");
        println("...and " + winner + " wins!\n");
    }

    /**
     * Displays the final round information for a team, including the damage taken by each member.
     *
     * @param teamNumber          the team number
     * @param teamName            the team name
     * @param teamMemberNameList  the list of team member names
     * @param damageTakenList     the list of damage taken by each team member
     */
    public void finalRound(int teamNumber, String teamName, ArrayList<String> teamMemberNameList, ArrayList<String> damageTakenList) {
        println("\tTeam #" + teamNumber + " - " + teamName);
        for (int i = 0; i < teamMemberNameList.size(); i++) {
            String name = teamMemberNameList.get(i);
            String damageTaken = damageTakenList.get(i);
            println("\t- " + name + "(" + damageTaken + ")");
        }
    }
}
