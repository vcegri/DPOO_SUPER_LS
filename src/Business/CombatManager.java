package Business;

import java.util.ArrayList;

public class CombatManager {

    private Combat combat;

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
}
