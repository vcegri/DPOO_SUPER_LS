package Business;

import Persistence.StatJSON;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Manages the interaction with statistical data related to teams, including creating statistics,
 * updating stats, and retrieving information such as games played, games won, knockouts done, and received.
 */
public class StatManager {

    /** The StatJSON instance used to read and save statistical data. */
    private final StatJSON statJson;

    /**
     * Constructs a StatManager with the specified StatJSON instance.
     *
     * @param statJson the StatJSON instance to interact with for statistical data
     */
    public StatManager(StatJSON statJson) {
        this.statJson = statJson;
    }

    /**
     * Creates a new Stat object for a team with the given name. This method initializes
     * the statistics for the team with default values.
     *
     * @param name the name of the team for which to create statistics
     */
    public void createStat(String name) throws FileNotFoundException {
        ArrayList<Stat> statList = statJson.readAll();

        Stat stat = new Stat(name);
        statList.add(stat);
        statJson.saveStatList(statList);
    }

    /**
     * Retrieves the statistics of a team by name. The statistics include the number of games played,
     * games won, knockouts done, knockouts received, and the win rate.
     *
     * @param name the name of the team whose statistics to retrieve
     * @return a list of integers representing the team's statistics in the order:
     *         games played, games won, knockouts done, knockouts received, and win rate
     * @throws FileNotFoundException if the stat data cannot be read from storage
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
     * Updates the statistics for a team after a game. The statistics are updated based on whether the team won.
     * If the team won, the number of games played, games won, knockouts done, and knockouts received are incremented.
     *
     * @param winner  a boolean indicating if the team won (true if won, false otherwise)
     * @param teamName the name of the team whose statistics are to be updated
     * @throws FileNotFoundException if the stat data cannot be read or written to storage
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