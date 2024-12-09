package Business;

import java.util.ArrayList;
import java.util.Random;

/**
 * Manages the simulation and logic for a combat between two teams.
 */
public class CombatManager {

    /** List of CombatMembers that participates in the combat*/
    private final ArrayList<CombatMember> combatMemberList;

    /** List of teams that participates in the combat*/
    private ArrayList<Team> teamList;

    /**
     * Constructs a CombatManager with the specified combat team and members.
     */
    public CombatManager() {
        this.combatMemberList = new ArrayList<>();
        this.teamList = new ArrayList<>();
    }

    /**
     * Returns the list of CombatMembers participating in the combat
     *
     * @return list of CombatMember
     */
    public ArrayList<CombatMember> getCombatMemberList() {
        return this.combatMemberList;
    }

    /**
     * Returns the list of teams participating in the combat
     *
     * @return list of Teams
     */
    public ArrayList<Team> getTeamList() {
        return teamList;
    }

    /**
     * Sets the list of teams participating in the combat
     *
     * @param teamList list of Teams
     */
    public void setTeamList(ArrayList<Team> teamList) {
        this.teamList = teamList;
    }

    /**
     * Creates all the CombatMembers and set them to their team combat.
     *
     * @param teamFight      list of teams participating in the combat
     * @param weaponList     list of weapons available for CombatMembers
     * @param armorList      list of armor available for CombatMembers
     * @param characterList  list of characters participating at the combat
     */
    public void setCombat(ArrayList<Team> teamFight, ArrayList<Item> weaponList, ArrayList<Item> armorList, ArrayList<Character> characterList) {
        int teamSize = 4;

        for (int i = 0; i < teamSize; i++) {
            createCombatMember(characterList.get(i), "Balanced", weaponList.get(i), armorList.get(i));
        }
        setTeamList(teamFight);
    }

    /**
     * Returns the list of weapon names assigned to each CombatMember.
     *
     * @return list of weapon names
     */
    public ArrayList<String> getWeaponList(int k) {
        ArrayList<String> weaponNameList = new ArrayList<>();
        int teamSize = 4;

        for (int i = 0; i < teamSize; i++) {
            if (k == 0) {
                if (getCombatMemberList().get(i).getWeapon() == null) {
                    weaponNameList.add("null");
                } else {
                    weaponNameList.add(getCombatMemberList().get(i).getWeapon().getName());
                }
            }
            else {
                k = i + 4;
                if (getCombatMemberList().get(k).getWeapon() == null) {
                    weaponNameList.add("null");
                } else {
                    weaponNameList.add(getCombatMemberList().get(k).getWeapon().getName());
                }
            }
        }

        return weaponNameList;
    }

    /**
     * Returns the list of armor names assigned to each CombatMember.
     *
     * @return list of armor names
     */
    public ArrayList<String> getArmorList(int k) {
        ArrayList<String> armorNameList = new ArrayList<>();
        int teamSize = 4;

        for (int i = 0; i < teamSize; i++) {
            if (k == 0) {
                if (getCombatMemberList().get(i).getArmor() == null) {
                    armorNameList.add("null");
                } else {
                    armorNameList.add(getCombatMemberList().get(i).getArmor().getName());
                }
            }
            else {
                k = i + 4;
                if (getCombatMemberList().get(k).getArmor() == null) {
                    armorNameList.add("null");
                } else {
                    armorNameList.add(getCombatMemberList().get(k).getArmor().getName());
                }
            }
        }

        return armorNameList;
    }

    /**
     * Returns the list of damage for all CombatMembers.
     *
     * @return list of damage
     */
    public ArrayList<Double> getDamageList(int k) {
        ArrayList<Double> damageNameList = new ArrayList<>();
        int teamSize = 4;

        for (int i = 0; i < teamSize; i++) {
            if (k == 0) {
                damageNameList.add(getCombatMemberList().get(i).getDamage());
            }
            else {
                k = i + 4;
                damageNameList.add(getCombatMemberList().get(k).getDamage());
            }
        }

        return damageNameList;
    }

    /**
     * Checks if a CombatMember has a weapon assigned.
     *
     * @param i index of the CombatMember to check
     * @return true if the CombatMember has a weapon, false if not
     */
    public boolean hasWeapon(int i) {
        return getCombatMemberList().get(i).getWeapon() != null;
    }

    /**
     * Checks if a CombatMember has armor assigned.
     *
     * @param i index of the CombatMember to check
     * @return true if the CombatMember has armor, false if not
     */
    public boolean hasArmor(int i) {
        return getCombatMemberList().get(i).getArmor() != null;
    }

    /**
     * Checks if a CombatMember has high damage based on their damage stat and need to defend.
     *
     * @param i index of the CombatMember to check
     * @return true if the CombatMember has high damage, false if not
     */
    public boolean hasHighDamage(int i) {
        return getCombatMemberList().get(i).getDamage() >= 0.5 && getCombatMemberList().get(i).getDamage() <= 1.0;
    }

    /**
     * Updates the defending variable of all the CombatMembers.
     */
    public void checkDefenders(){

        for (int i = 0; i < getCombatMemberList().size(); i++) {
            this.getCombatMemberList().get(i).setDefending(getCombatMemberList().get(i).isDefendRequest());
            this.getCombatMemberList().get(i).setDefendingStatus(false);
        }
    }

    /**
     * Updates the durability of the attacker weapon and the defender armor in a combat round.
     *
     * @param i index of the attacking CombatMember.
     * @param randomIndex index of the defending CombatMember.
     */
    public void updateItemDurability(int i,int randomIndex) {
        this.getCombatMemberList().get(i).getWeapon().updateDurability();
        if (this.getCombatMemberList().get(randomIndex).getArmor() != null) {
            this.getCombatMemberList().get(randomIndex).getArmor().updateDurability();
        }
    }

    /**
     * Calculates the KO state of each CombatMember based on their damage taken and
     * resets the attacked state of all combat members.
     */
    public void calculateKo() {
        for (int i = 0; i < getCombatMemberList().size(); i++) {
            this.knocked(i);
            this.combatMemberList.get(i).setAtacked(false);
        }
    }

    /**
     * Creates a new CombatMember and adds it to the CombatMember list.
     *
     * @param character character associated with the CombatMember
     * @param strategy  strategy employed by the CombatMember
     * @param weapon    weapon equipped by the CombatMember
     * @param armor     armor equipped by the CombatMember
     */
    public void createCombatMember(Character character, String strategy, Item weapon, Item armor) {
        CombatMember combatMember = new CombatMember(character, strategy, weapon, armor);
        this.combatMemberList.add(combatMember);
    }

    /**
     * Determines if a CombatMember is KO based on a random parameter and their current damage.
     *
     * @param i index of the CombatMember.
     */
    public void knocked (int i) {
        Random random = new Random();
        double randomKnockOut;

        double damage = getCombatMemberList().get(i).getDamage();
        randomKnockOut = random.nextInt(199) + 1;
        randomKnockOut = randomKnockOut/100;

        if (getCombatMemberList().get(i).isAttacked()) {
            if (randomKnockOut > damage) {
                this.getCombatMemberList().get(i).setKo();
            }
        }
    }

    /**
     * Calculates the initial damage done by an attacker in a combat round.
     *
     * @param attacker CombatMember that attacks.
     * @return calculated damage value.
     */
    public double calculateDamage (CombatMember attacker) {
        double damage;
        int attackerWeight;
        double attackerDamage;
        double weaponPower;

        attackerWeight = attacker.getCharacter().getWeight();
        weaponPower = attacker.getWeapon().getItemUtilityPower(attackerWeight);
        attackerDamage = attacker.getDamage();

        damage = attackerWeight * (1 - attackerDamage);
        damage = damage / 10;
        damage = damage + (weaponPower / 20) + 18;

        return damage;
    }

    /**
     * Calculates the reduced damage taken by a defender.
     *
     * @param damage initial damage done by the attacker.
     * @param defender CombatMember defending.
     * @return final reduced damage.
     */
    public double calculateReducedDamage(double damage, CombatMember defender) {
        double finalDamage;
        double defenderDamage;
        int defenderWeight;
        double armor;

        defenderDamage = defender.getDamage();
        defenderWeight = defender.getCharacter().getWeight();
        if (defender.getArmor() != null){
            armor = defender.getArmor().getItemUtilityPower(defenderWeight);
        }
        else {
            armor = 0;
        }

        finalDamage = 200 * (1 - defenderDamage);
        finalDamage = finalDamage / defenderWeight;
        finalDamage = finalDamage + (armor / 20);
        finalDamage = finalDamage * 1.4;
        finalDamage = damage - finalDamage;
        finalDamage = finalDamage / 100;

        return finalDamage;
    }

    /**
     * Calculates the damage reduction value for a defender.
     *
     * @param defender CombatMember defending against an attack.
     * @return calculated damage reduction value.
     */
    public double calculateDamageReduction(CombatMember defender){
        double damageReduction;

        damageReduction = (double) (defender.getCharacter().getWeight()) /400;

        return damageReduction;
    }

    /**
     * Ends the combat by clearing the Team list and CombatMember list.
     */
    public void endCombat() {
        this.teamList.clear();
        this.combatMemberList.clear();
    }

    /**
     * Checks if the combat has ended, if all members of a team are KO.
     *
     * @return true if the combat has ended; false if not.
     */
    public boolean checkEndCombat(){
        boolean combatEnded = false;
        boolean teamOneFinish = true;
        boolean teamTwoFinish = true;

        for (int i = 0; i < 4; i++){
            if (!getCombatMemberList().get(i).isKo()) {
                teamOneFinish = false;
                break;
            }
        }

        for (int i = 0; i < 4; i++){
            int j = i + 4;
            if (!getCombatMemberList().get(j).isKo()) {
                teamTwoFinish = false;
                break;
            }
        }

        if (teamOneFinish || teamTwoFinish){
            combatEnded = true;
        }

        return combatEnded;
    }

    /**
     * Determines the winning team.
     *
     * @return an integer depending on the final result.
     */
    public Integer checkWinner() {
        int winner = 2;

        for (int i = 0; i < 4; i++){
            if (!getCombatMemberList().get(i).isKo()) {
                winner = 0;
                break;
            }
        }

        for (int i = 0; i < 4; i++){
            int j = i + 4;
            if (!getCombatMemberList().get(j).isKo()) {
                winner = 1;
                break;
            }
        }

        return winner;
    }
}
