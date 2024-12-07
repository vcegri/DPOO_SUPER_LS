package Business;

import Persistence.StatJSON;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Manages the interactions with stat data using the statJson class.
 */
public class StatManager {

    /** Class to manage the stats.json file. */
    private final StatJSON statJson;

    /**
     * Constructs a StatManager with the StatJSON class.
     *
     * @param statJson class to manage the stats.json file
     */
    public StatManager(StatJSON statJson) {
        this.statJson = statJson;
    }

    /**
     * Creates a new Stat for a team with the given name.
     *
     * @param name name of the team
     */
    public void createStat(String name) throws FileNotFoundException {
        ArrayList<Stat> statList = statJson.readAll();

        Stat stat = new Stat(name);
        statList.add(stat);
        statJson.saveStatList(statList);
    }

    /**
     * Get the stats of a team by its name.
     *
     * @param name name of the team
     * @return list of integers representing the teams stats
     * @throws FileNotFoundException if the stat data can't be read
     */
    public ArrayList<Integer> getStatList(String name) throws FileNotFoundException {
        ArrayList<Integer> statInfoList = new ArrayList<>();
        ArrayList<Stat> statList = statJson.readAll();

        for (Stat stat : statList) {
            if (name.equals(stat.getName())) {
                statInfoList.add(stat.getGamesPlayed());
                statInfoList.add(stat.getGamesWon());
                statInfoList.add(stat.getKoDone());
                statInfoList.add(stat.getKoReceived());
                double winrate;
                int WinRate = 0;
                if (stat.getGamesPlayed() != 0) {
                    winrate = (((double) stat.getGamesWon() / stat.getGamesPlayed()) * 100);
                    WinRate = (int) winrate;
                }
                statInfoList.add(WinRate);
            }
        }

        return statInfoList;
    }

    /**
     * Updates the stats for a team after a game.
     *
     * @param winner  boolean indicating if the team won
     * @param teamName name of the team
     * @throws FileNotFoundException if the stat data can't be read or written
     */
    public void updateStats(String winner, String teamName, ArrayList<Boolean> koList, int teamNum) throws FileNotFoundException {
        ArrayList<Stat> statList = statJson.readAll();
        int gamesPlayed, gamesWon, KO_done, KO_received;

        for (int i = 0; i < statList.size(); i++){
            if (statList.get(i).getName().equals(teamName)){
                KO_received = statList.get(i).getKoReceived();
                KO_done = statList.get(i).getKoDone();
                gamesPlayed = statList.get(i).getGamesPlayed();
                gamesPlayed++;
                gamesWon = statList.get(i).getGamesWon();
                if (winner.equals(teamName)) {
                    gamesWon++;
                    KO_done = KO_done + 4;

                    int cont = 0;
                    if (teamNum == 0) {
                        for (int j = 0; j < 4; j++) {
                            if (koList.get(j)) {
                                cont++;
                            }
                        }
                    }
                    else {
                        for (int j = 4; j < 8; j++) {
                            if (koList.get(j)) {
                                cont++;
                            }
                        }
                    }
                    KO_received = KO_received + cont;
                }
                else {
                    KO_received = KO_received + 4;

                    int cont = 0;
                    if (teamNum == 1) {
                        for (int j = 0; j < 4; j++) {
                            if (koList.get(j)) {
                                cont++;
                            }
                        }
                    }
                    else {
                        for (int j = 4; j < 8; j++) {
                            if (koList.get(j)) {
                                cont++;
                            }
                        }
                    }
                    KO_done = KO_done + cont;
                }

                statList.get(i).updateStats(gamesPlayed, gamesWon, KO_done, KO_received);
                statJson.saveStatList(statList);
            }
        }
    }

    /**
     * Deletes the stats of a team by its name.
     *
     * @param name name of the team
     * @throws FileNotFoundException if the statistics file can't be found.
     */
    public void deleteStat(String name) throws FileNotFoundException {
        ArrayList<Stat> statList = statJson.readAll();

        Stat statFound = null;
        for (Stat stat : statList) {
            if (stat.getName().equals(name)) {
                statFound = stat;
                break;
            }
        }

        if (statFound != null) {
            statList.remove(statFound);
            statJson.saveStatList(statList);
        }
    }
}