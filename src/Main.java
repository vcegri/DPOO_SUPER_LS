import Business.*;
import Persistence.*;
import Presentation.*;

import java.io.IOException;

/**
 * Main class to start the program.
 */
public class Main {
    /**
     * Starts the program and does the principals configurations.
     *
     * @param args arguments from the command line.
     * @throws IOException if an I/O error happens during file operations.
     */
    public static void main(String[] args) throws IOException {

        Menu menu = new Menu();
        CharacterManager characterManager = new CharacterManager();
        ItemManager itemManager = new ItemManager();
        StatManager statManager = new StatManager();
        TeamManager teamManager = new TeamManager();
        CombatManager combatManager = new CombatManager();

        Controller controller = new Controller(menu,characterManager, teamManager, itemManager, statManager, combatManager);
        controller.run();

    }
}