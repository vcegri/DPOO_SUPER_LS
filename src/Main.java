import Business.*;
import Persistence.*;
import Presentation.*;

import java.io.IOException;

public class Main {
    /**
     * Starts the program and does the principals configurations.
     *
     * @param args arguments from the command line.
     */
    public static void main(String[] args) throws IOException {

        Menu menu = new Menu();
        CharacterJSON characterJson = new CharacterJSON();
        ItemJSON itemJson = new ItemJSON();
        StatJSON statJson = new StatJSON();
        TeamJSON teamJson = new TeamJSON();
        CharacterManager characterManager = new CharacterManager(characterJson);
        ItemManager itemManager = new ItemManager(itemJson);
        StatManager statManager = new StatManager(statJson);
        TeamManager teamManager = new TeamManager(teamJson);
        CombatManager combatManager = new CombatManager();

        Controller controller = new Controller(menu,characterManager, teamManager, itemManager, statManager, combatManager);
        controller.run();

    }
}