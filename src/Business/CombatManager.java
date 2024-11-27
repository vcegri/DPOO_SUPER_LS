package Business;

import java.util.ArrayList;
import java.util.Random;

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
        Random random = new Random();
        double randomKnockOut;

        randomKnockOut = random.nextInt(199) + 1;
        if (randomKnockOut < damage){
            knocked = true;
        }

        return knocked;
    }

    public double calculateDamage(CombatMember attacker) {
        double damage;
        int attackerWeight;
        double attackerDamage;
        int weaponPower;


        attackerWeight = attacker.getCharacter().getWeight();
        weaponPower = attacker.getWeapon().getPower();
        attackerDamage = attacker.getDamage();

        damage = attackerWeight * (1 - attackerDamage);
        damage = damage/10;
        damage = damage + (weaponPower/20) + 18;

        return damage;
    }

    public double calculateReducedDamage(double damage, CombatMember defender) {
        double finalDamage;
        double defenderDamage;
        int defenderWeight;
        int armor;

        defenderDamage = defender.getDamage();
        defenderWeight = defender.getCharacter().getWeight();
        armor = defender.getArmor().getPower();

        finalDamage = 200 * (1 - defenderDamage);
        finalDamage = finalDamage/defenderWeight;
        finalDamage = finalDamage + (armor/20);
        finalDamage = finalDamage * 1.4;
        finalDamage = damage - finalDamage;
        finalDamage = finalDamage/100;

        return finalDamage;
    }


    public void updateStatsAttacker(CombatMember combatMember) {
        for (int i = 0; i < combat.getCombatMemberList().size(); i++){
            if (combat.getCombatMemberList().get(i).getCharacter().getName().equals(combatMember.getCharacter().getName())) {
                this.combat.getCombatMemberList().get(i).getWeapon().updateDurability();
            }
        }
    }

    public void updateStatsDefender(CombatMember combatMember, double damage) {
        for (int i = 0; i < combat.getCombatMemberList().size(); i++){
            if (combat.getCombatMemberList().get(i).getCharacter().getName().equals(combatMember.getCharacter().getName())) {
                this.combat.getCombatMemberList().get(i).getArmor().updateDurability();
                this.combat.getCombatMemberList().get(i).updateDamage(damage);
            }
        }
    }

    public void setCombat(ArrayList<Team> teamFight, ArrayList<Item> weaponList, ArrayList<Item> armorList, ArrayList<Character> characterList) {
        int teamSize = 4;

        for (int i = 0; i < teamSize; i++){
            combat.createCombatMember(characterList.get(i), "Balanced", weaponList.get(i), armorList.get(i));
        }
        combat.setTeamList(teamFight);
    }

    public ArrayList<String> getWeaponList() {
        ArrayList<String> weaponNameList = new ArrayList<>();

        for (int i = 0; i < combat.getCombatMemberList().size(); i++){
            weaponNameList.add(combat.getCombatMemberList().get(i).getWeapon().getName());
        }

        return weaponNameList;
    }

    public ArrayList<String> getArmorList() {
        ArrayList<String> armorNameList = new ArrayList<>();

        for (int i = 0; i < combat.getCombatMemberList().size(); i++){
            armorNameList.add(combat.getCombatMemberList().get(i).getArmor().getName());
        }

        return armorNameList;
    }

    public ArrayList<Double> getDamageList() {
        ArrayList<Double> damageNameList = new ArrayList<>();

        for (int i = 0; i < combat.getCombatMemberList().size(); i++){
            damageNameList.add(combat.getCombatMemberList().get(i).getDamage());
        }

        return damageNameList;
    }
}