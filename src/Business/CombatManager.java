package Business;

import java.util.ArrayList;
import java.util.Random;

/**
 * Manages the simulation and logic for a combat between teams, including the calculation of damage,
 * handling of combat stats, and determining the outcome of the battle.
 */
public class CombatManager {

    /** The combat instance that this manager will control. */
    private Combat combat;

    /**
     * Constructs a CombatManager with the specified Combat instance.
     *
     * @param combat the combat instance to manage
     */
    public CombatManager(Combat combat) {
        this.combat = combat;
    }

    /**
     * Returns the current combat instance.
     *
     * @return the combat instance being managed
     */
    public Combat getCombat() {
        return combat;
    }

    /**
     * Simulates the combat between the teams and determines the winner.
     *
     * @param teamList the list of teams participating in the combat
     * @return the ID of the winning team (0 for no winner)
     */
    public int simulateCombat(ArrayList<Team> teamList) {
        int winner = 0;

        // Simulate the combat and determine the winner
        return winner;
    }

    /**
     * Selects the teams for the combat.
     *
     * @return a list of teams selected for the combat
     */
    public ArrayList<Team> selectTeamList() {
        ArrayList<Team> teamList = new ArrayList<>();

        // Select teams for the combat
        return teamList;
    }

    /**
     * Checks if the teams are properly set up for combat.
     *
     * @return true if the teams are valid, false otherwise
     */
    public boolean checkTeams() {
        boolean exist = false;

        // Check if the teams exist
        return exist;
    }

    /**
     * Determines if a combat member has been knocked out based on their damage value.
     *
     * @param i the damage value of the combat member
     * @return true if the combat member is knocked out, false otherwise
     */
    public boolean checkKnockOut(int i) {
        boolean knocked = false;
        Random random = new Random();
        double randomKnockOut;

        double damage = combat.getCombatMemberList().get(i).getDamage();
        randomKnockOut = random.nextInt(199) + 1;
        randomKnockOut = randomKnockOut/100;

        if (randomKnockOut > damage) {
            knocked = true;
        }

        return knocked;
    }

    /**
     * Calculates the damage dealt by a combat member during an attack.
     *
     * @param attacker the combat member who is attacking
     * @return the calculated damage value
     */
    public double calculateDamage(CombatMember attacker) {
        double damage;
        int attackerWeight;
        double attackerDamage;
        int weaponPower;

        attackerWeight = attacker.getCharacter().getWeight();
        weaponPower = attacker.getWeapon().getPower();
        attackerDamage = attacker.getDamage();

        damage = attackerWeight * (1 - attackerDamage);
        damage = damage / 10;
        damage = damage + ((double) weaponPower / 20) + 18;

        return damage;
    }

    /**
     * Calculates the reduced damage for a defender based on their armor and other stats.
     *
     * @param damage   the initial damage dealt to the defender
     * @param defender the combat member who is defending
     * @return the reduced damage value after considering the defender's stats
     */
    public double calculateReducedDamage(double damage, CombatMember defender) {
        double finalDamage;
        double defenderDamage;
        int defenderWeight;
        int armor;

        defenderDamage = defender.getDamage();
        defenderWeight = defender.getCharacter().getWeight();
        if (defender.getArmor() != null){
            armor = defender.getArmor().getPower();
        }
        else {
            armor = 0;
        }

        finalDamage = 200 * (1 - defenderDamage);
        finalDamage = finalDamage / defenderWeight;
        finalDamage = finalDamage + ((double) armor / 20);
        finalDamage = finalDamage * 1.4;
        finalDamage = damage - finalDamage;
        finalDamage = finalDamage / 100;

        return finalDamage;
    }

    public double calculateDamageReduction(CombatMember defender){
        double damageReduction;

        damageReduction = (double) (defender.getCharacter().getWeight()) /400;

        return damageReduction;
    }

    /**
     * Updates the stats of the attacker after their turn in combat, such as durability of the weapon.
     *
     * @param combatMember the combat member whose stats are being updated
     */
    public void updateStatsAttacker(CombatMember combatMember) {
        for (int i = 0; i < combat.getCombatMemberList().size(); i++) {
            if (combat.getCombatMemberList().get(i).getCharacter().getName().equals(combatMember.getCharacter().getName())) {
                this.combat.getCombatMemberList().get(i).getWeapon().updateDurability();
            }
        }
    }

    /**
     * Updates the stats of the defender after taking damage, such as durability of the armor and updating the damage.
     *
     * @param combatMember the combat member whose stats are being updated
     * @param damage       the damage taken by the defender
     */
    public void updateStatsDefender(CombatMember combatMember, double damage) {
        for (int i = 0; i < combat.getCombatMemberList().size(); i++) {
            if (combat.getCombatMemberList().get(i).getCharacter().getName().equals(combatMember.getCharacter().getName())) {
                this.combat.getCombatMemberList().get(i).getArmor().updateDurability();
                this.combat.getCombatMemberList().get(i).updateDamage(damage);
            }
        }
    }

    /**
     * Sets up the combat with the specified teams, weapons, armor, and characters.
     *
     * @param teamFight      the list of teams participating in the combat
     * @param weaponList     the list of weapons available for combat members
     * @param armorList      the list of armor available for combat members
     * @param characterList  the list of characters participating in the combat
     */
    public void setCombat(ArrayList<Team> teamFight, ArrayList<Item> weaponList, ArrayList<Item> armorList, ArrayList<Character> characterList) {
        int teamSize = 4;

        for (int i = 0; i < teamSize; i++) {
            combat.createCombatMember(characterList.get(i), "Balanced", weaponList.get(i), armorList.get(i));
        }
        combat.setTeamList(teamFight);
    }

    /**
     * Returns the list of weapon names currently assigned to combat members.
     *
     * @return a list of weapon names
     */
    public ArrayList<String> getWeaponList(int k) {
        ArrayList<String> weaponNameList = new ArrayList<>();
        int teamSize = 4;

        for (int i = 0; i < teamSize; i++) {
            if (k == 0) {
                if (combat.getCombatMemberList().get(i).getWeapon() == null) {
                    weaponNameList.add("null");
                } else {
                    weaponNameList.add(combat.getCombatMemberList().get(i).getWeapon().getName());
                }
            }
            else {
                k = i + 4;
                if (combat.getCombatMemberList().get(k).getWeapon() == null) {
                    weaponNameList.add("null");
                } else {
                    weaponNameList.add(combat.getCombatMemberList().get(k).getWeapon().getName());
                }
            }
        }

        return weaponNameList;
    }

    /**
     * Returns the list of armor names currently assigned to combat members.
     *
     * @return a list of armor names
     */
    public ArrayList<String> getArmorList(int k) {
        ArrayList<String> armorNameList = new ArrayList<>();
        int teamSize = 4;

        for (int i = 0; i < teamSize; i++) {
            if (k == 0) {
                if (combat.getCombatMemberList().get(i).getArmor() == null) {
                    armorNameList.add("null");
                } else {
                    armorNameList.add(combat.getCombatMemberList().get(i).getArmor().getName());
                }
            }
            else {
                k = i + 4;
                if (combat.getCombatMemberList().get(k).getArmor() == null) {
                    armorNameList.add("null");
                } else {
                    armorNameList.add(combat.getCombatMemberList().get(k).getArmor().getName());
                }
            }
        }

        return armorNameList;
    }

    /**
     * Returns the list of damage values for all combat members.
     *
     * @return a list of damage values
     */
    public ArrayList<Double> getDamageList(int k) {
        ArrayList<Double> damageNameList = new ArrayList<>();
        int teamSize = 4;

        for (int i = 0; i < teamSize; i++) {
            if (k == 0) {
                damageNameList.add(combat.getCombatMemberList().get(i).getDamage());
            }
            else {
                k = i + 4;
                damageNameList.add(combat.getCombatMemberList().get(k).getDamage());
            }
        }

        return damageNameList;
    }

    /**
     * Checks if a combat member has a weapon assigned.
     *
     * @param i the index of the combat member to check
     * @return true if the combat member has a weapon, false otherwise
     */
    public boolean hasWeapon(int i) {
        return combat.getCombatMemberList().get(i).getWeapon() != null;
    }

    /**
     * Checks if a combat member has armor assigned.
     *
     * @param i the index of the combat member to check
     * @return true if the combat member has armor, false otherwise
     */
    public boolean hasArmor(int i) {
        return combat.getCombatMemberList().get(i).getArmor() != null;
    }

    /**
     * Checks if a combat member has high damage based on their damage stat.
     *
     * @param i the index of the combat member to check
     * @return true if the combat member has high damage, false otherwise
     */
    public boolean hasHighDamage(int i) {
        return combat.getCombatMemberList().get(i).getDamage() >= 0.5 && combat.getCombatMemberList().get(i).getDamage() <= 1.0;
    }

    public void setKo (int i) {
        if (checkKnockOut(i)) {
            this.combat.getCombatMemberList().get(i).setKo();
        }
    }
}
