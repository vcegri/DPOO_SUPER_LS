package Business;

import java.util.ArrayList;
import java.util.Random;

/**
 * Manages the simulation and logic for a combat between teams, including the calculation of damage,
 * handling of combat stats, and determining the outcome of the battle.
 */
public class CombatManager {

    /** The list of combat members participating in the  */
    private final ArrayList<CombatMember> combatMemberList;

    /** The list of teams involved in the  */
    private ArrayList<Team> teamList;

    /**
     * Constructs a CombatManager with the specified Combat instance.
     */
    public CombatManager() {
        this.combatMemberList = new ArrayList<>();
        this.teamList = new ArrayList<>();
    }

    /**
     * Returns the list of combat members participating in the
     *
     * @return a list of CombatMember
     */
    public ArrayList<CombatMember> getCombatMemberList() {
        return this.combatMemberList;
    }

    /**
     * Returns the list of teams involved in the
     *
     * @return a list of Team
     */
    public ArrayList<Team> getTeamList() {
        return teamList;
    }

    /**
     * Sets the list of teams involved in the
     *
     * @param teamList the list of Team to set
     */
    public void setTeamList(ArrayList<Team> teamList) {
        this.teamList = teamList;
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
            createCombatMember(characterList.get(i), "Balanced", weaponList.get(i), armorList.get(i));
        }
        setTeamList(teamFight);
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
     * Returns the list of armor names currently assigned to combat members.
     *
     * @return a list of armor names
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
     * Returns the list of damage values for all combat members.
     *
     * @return a list of damage values
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
     * Checks if a combat member has a weapon assigned.
     *
     * @param i the index of the combat member to check
     * @return true if the combat member has a weapon, false otherwise
     */
    public boolean hasWeapon(int i) {
        return getCombatMemberList().get(i).getWeapon() != null;
    }

    /**
     * Checks if a combat member has armor assigned.
     *
     * @param i the index of the combat member to check
     * @return true if the combat member has armor, false otherwise
     */
    public boolean hasArmor(int i) {
        return getCombatMemberList().get(i).getArmor() != null;
    }

    /**
     * Checks if a combat member has high damage based on their damage stat.
     *
     * @param i the index of the combat member to check
     * @return true if the combat member has high damage, false otherwise
     */
    public boolean hasHighDamage(int i) {
        return getCombatMemberList().get(i).getDamage() >= 0.5 && getCombatMemberList().get(i).getDamage() <= 1.0;
    }

    public void setKo (int i) {
        if (knocked(i)) {
            this.getCombatMemberList().get(i).setKo();
        }
    }

    public void checkDefenders(){

        for (int i = 0; i < getCombatMemberList().size(); i++) {
            this.getCombatMemberList().get(i).setDefending(getCombatMemberList().get(i).isDefendRequest());
            this.getCombatMemberList().get(i).setDefendingStatus(false);
        }
    }
    
    public void updateItemDurability(int i,int randomIndex) {
        this.getCombatMemberList().get(i).getWeapon().updateDurability();
        if (this.getCombatMemberList().get(randomIndex).getArmor() != null) {
            this.getCombatMemberList().get(randomIndex).getArmor().updateDurability();
        }
    }

    public void calculateKo() {
        for (int i = 0; i < getCombatMemberList().size(); i++) {
            this.setKo(i);
        }
    }

    /**
     * Creates a new combat member and adds it to the combat member list.
     *
     * @param character the character associated with the combat member
     * @param strategy  the strategy employed by the combat member
     * @param weapon    the weapon equipped by the combat member
     * @param armor     the armor equipped by the combat member
     */
    public void createCombatMember(Character character, String strategy, Item weapon, Item armor) {
        CombatMember combatMember = new CombatMember(character, strategy, weapon, armor);
        this.combatMemberList.add(combatMember);
    }

    public boolean knocked (int i) {
        boolean knocked = false;
        Random random = new Random();
        double randomKnockOut;

        double damage = getCombatMemberList().get(i).getDamage();
        randomKnockOut = random.nextInt(199) + 1;
        randomKnockOut = randomKnockOut/100;

        if (randomKnockOut > damage) {
            knocked = true;
        }

        return knocked;
    }

    public double calculateDamage (CombatMember attacker) {
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
     * Ends the combat by clearing the team and combat member lists.
     */
    public void endCombat() {
        this.teamList.clear();
        this.combatMemberList.clear();
    }

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
