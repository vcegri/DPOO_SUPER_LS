import Business.*;
import Persistence.CharacterJSON;
import Persistence.ItemJSON;
import Persistence.StatJSON;
import Persistence.TeamJSON;
import Presentation.Controller;
import Presentation.Menu;

import java.io.IOException;

public class Main {
    /**
     * Inicia el programa configurant les clasess i el controlador.
     *
     * @param args arguments de la línia de comandaments (no utilitzats).
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
        Combat combat = new Combat();
        CombatManager combatManager = new CombatManager(combat);

        Controller controller = new Controller(menu,characterManager, teamManager, itemManager, statManager, combatManager);
        controller.run();

    }
}