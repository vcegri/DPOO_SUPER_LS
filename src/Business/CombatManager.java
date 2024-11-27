package Business;

import java.util.ArrayList;

public class CombatManager {

    private final Combat combat;

    public CombatManager(Combat combat) {
        this.combat = combat;
    }

    public int simulateCombat(ArrayList<Team> teamList) {
        int winner = 0;

        return winner;
    }

    public ArrayList<Team> selectTeamList() {
        ArrayList<Team> teamList = new ArrayList<>();

        return teamList;
    }

    public boolean checkTeams() {
        boolean exist = false;

        return exist;
    }

    public Item setRandomArmor() {
        Item armor = null;

        return armor;
    }

    public Item setRandomWeapon() {
        Item weapon = null;

        return weapon;
    }

    public boolean checkKnockOut(double damage) {
        boolean knocked = false;

        return knocked;
    }

    public double calculateDamage() {
        double damage = 0;

        return damage;
    }

    public void updateStats() {}

    public void endCombat() {}

    public void setCombat(ArrayList<Team> teamFight, ArrayList<Item> weaponList, ArrayList<Item> armorList, ArrayList<Character> characterList) {
        int teamSize = 4;

        for (int i = 0; i < teamSize; i++){
            combat.createCombatMember(characterList.get(i), "Balanced", weaponList.get(i), armorList.get(i));
        }
        combat.setTeamList(teamFight);
    }
}