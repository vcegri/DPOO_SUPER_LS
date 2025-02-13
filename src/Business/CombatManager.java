package Business;

import java.util.ArrayList;
import java.util.Random;

/**
 * Manages the simulation and logic for a combat between two teams.
 */
public class CombatManager {

    private static final int TEAM_SIZE = 4;
    private static final int DEFAULT_VALUE = 0;
    private static final String BALANCED = "balanced";
    private static final String SNIPER = "sniper";
    private static final String DEFENSIVE = "defensive";
    private static final String OFFENSIVE = "offensive";
    private static final String NULL = "null";

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
     * @param strategyList   list of strategy of the CombatMembers
     */
    public void setCombat(ArrayList<Team> teamFight, ArrayList<Item> weaponList, ArrayList<Item> armorList, ArrayList<Character> characterList, ArrayList<String> strategyList) {
        for (int i = DEFAULT_VALUE; i < TEAM_SIZE; i++) {
            createCombatMember(characterList.get(i), strategyList.get(i), weaponList.get(i), armorList.get(i));
        }
        setTeamList(teamFight);
    }

    /**
     * Returns the list of weapon names assigned to each CombatMember.
     * @param k team number
     *
     * @return list of weapon names
     */
    public ArrayList<String> getWeaponList(int k) {
        ArrayList<String> weaponNameList = new ArrayList<>();
        for (int i = DEFAULT_VALUE; i < TEAM_SIZE; i++) {
            if (k == DEFAULT_VALUE) {
                if (getCombatMemberList().get(i).getWeapon() == null) {
                    weaponNameList.add(NULL);
                } else {
                    weaponNameList.add(getCombatMemberList().get(i).getWeapon().getName());
                }
            }
            else {
                k = i + TEAM_SIZE;
                if (getCombatMemberList().get(k).getWeapon() == null) {
                    weaponNameList.add(NULL);
                } else {
                    weaponNameList.add(getCombatMemberList().get(k).getWeapon().getName());
                }
            }
        }

        return weaponNameList;
    }

    /**
     * Returns the list of armor names assigned to each CombatMember.
     * @param k team number
     *
     * @return list of armor names
     */
    public ArrayList<String> getArmorList(int k) {
        ArrayList<String> armorNameList = new ArrayList<>();
        for (int i = DEFAULT_VALUE; i < TEAM_SIZE; i++) {
            if (k == DEFAULT_VALUE) {
                if (getCombatMemberList().get(i).getArmor() == null) {
                    armorNameList.add(NULL);
                } else {
                    armorNameList.add(getCombatMemberList().get(i).getArmor().getName());
                }
            }
            else {
                k = i + TEAM_SIZE;
                if (getCombatMemberList().get(k).getArmor() == null) {
                    armorNameList.add(NULL);
                } else {
                    armorNameList.add(getCombatMemberList().get(k).getArmor().getName());
                }
            }
        }

        return armorNameList;
    }

    /**
     * Returns the list of damage for all CombatMembers.
     * @param k team number
     *
     * @return list of damage
     */
    public ArrayList<Double> getDamageList(int k) {
        ArrayList<Double> damageNameList = new ArrayList<>();
        for (int i = DEFAULT_VALUE; i < TEAM_SIZE; i++) {
            if (k == DEFAULT_VALUE) {
                damageNameList.add(getCombatMemberList().get(i).getDamage());
            }
            else {
                k = i + TEAM_SIZE;
                damageNameList.add(getCombatMemberList().get(k).getDamage());
            }
        }

        return damageNameList;
    }

    /**
     * Updates the defending variable of all the CombatMembers.
     */
    public void checkDefenders(){

        for (int i = DEFAULT_VALUE; i < getCombatMemberList().size(); i++) {
            CombatMember combatMember = this.getCombatMemberList().get(i);
            if (combatMember instanceof CombatMemberDeffensive) {
                ((CombatMemberDeffensive) combatMember).setDefending(((CombatMemberDeffensive) getCombatMemberList().get(i)).isDefendRequest());
                ((CombatMemberDeffensive) combatMember).setDefendingStatus(false);
            }

            if (combatMember instanceof CombatMemberBalanced) {
                ((CombatMemberBalanced) combatMember).setDefending(((CombatMemberBalanced) getCombatMemberList().get(i)).isDefendRequest());
                ((CombatMemberBalanced) combatMember).setDefendingStatus(false);
            }
        }
    }

    /**
     * Updates the durability of the attacker weapon and the defender armor in a combat round.
     *
     * @param i index of the attacking CombatMember.
     * @param randomIndex index of the defending CombatMember.
     */
    public void updateItemDurability(int i,int randomIndex) {
        if (this.getCombatMemberList().get(i).getWeapon() != null) {
            this.getCombatMemberList().get(i).getWeapon().updateDurability();
        }
        if (this.getCombatMemberList().get(randomIndex).getArmor() != null) {
            this.getCombatMemberList().get(randomIndex).getArmor().updateDurability();
        }
    }

    /**
     * Calculates the KO state of each CombatMember based on their damage taken and
     * resets the attacked state of all combat members.
     */
    public void calculateKo() {
        for (int i = DEFAULT_VALUE; i < getCombatMemberList().size(); i++) {
            this.knocked(i);
            this.combatMemberList.get(i).setAttacked(false);
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
        if (strategy.equals(BALANCED)) {
            CombatMember combatMember = new CombatMemberBalanced(character, strategy, weapon, armor);
            this.combatMemberList.add(combatMember);
        }
        if (strategy.equals(SNIPER)) {
            CombatMember combatMember = new CombatMemberSniper(character, strategy, weapon, armor);
            this.combatMemberList.add(combatMember);
        }
        if (strategy.equals(DEFENSIVE)) {
            CombatMember combatMember = new CombatMemberDeffensive(character, strategy, weapon, armor);
            this.combatMemberList.add(combatMember);
        }
        if (strategy.equals(OFFENSIVE)) {
            CombatMember combatMember = new CombatMemberOffensive(character, strategy, weapon, armor);
            this.combatMemberList.add(combatMember);
        }
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
        double weaponPower = DEFAULT_VALUE;

        attackerWeight = attacker.getCharacter().getWeight();
        attackerDamage = attacker.getDamage();
        if (attacker.getWeapon() != null){
            weaponPower = attacker.getWeapon().getItemUtilityPower(attackerWeight);
        }

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
            armor = DEFAULT_VALUE;
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

        for (int i = DEFAULT_VALUE; i < TEAM_SIZE; i++){
            if (!getCombatMemberList().get(i).isKo()) {
                teamOneFinish = false;
                break;
            }
        }

        for (int i = 0; i < TEAM_SIZE; i++){
            int j = i + TEAM_SIZE;
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

        for (int i = DEFAULT_VALUE; i < TEAM_SIZE; i++){
            if (!getCombatMemberList().get(i).isKo()) {
                winner = 0;
                break;
            }
        }

        for (int i = DEFAULT_VALUE; i < TEAM_SIZE; i++){
            int j = i + TEAM_SIZE;
            if (!getCombatMemberList().get(j).isKo()) {
                winner = 1;
                break;
            }
        }

        return winner;
    }
}
