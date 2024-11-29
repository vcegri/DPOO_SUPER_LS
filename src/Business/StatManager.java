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
    public void createStat(String name) {
        Stat stat = new Stat(name);
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
                int winrate;
                if (stat.getGamesPlayed() != 0) {
                    winrate = ((stat.getGamesWon() / stat.getGamesPlayed()) * 100);
                } else {
                    winrate = 0;
                }
                statInfoList.add(winrate);
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
    public void updateStats(boolean winner, String teamName) throws FileNotFoundException {
        ArrayList<Stat> statList = statJson.readAll();
        int gamesPlayed, gamesWon, KO_done, KO_received;

        for (int i = 0; i < statList.size(); i++){
            if (statList.get(i).getName().equals(teamName)){
                gamesPlayed = statList.get(i).getGamesPlayed();
                gamesPlayed++;
                gamesWon = statList.get(i).getGamesWon();
                if (winner) {
                    gamesWon++;
                }
                KO_done = statList.get(i).getKoDone();
                KO_received = statList.get(i).getKoReceived();

                // Updating the stats
                statList.remove(i);
                Stat stat = new Stat(teamName, gamesPlayed, gamesWon, KO_done, KO_received);
                statList.add(stat);
                statJson.saveStatList(statList);
            }
        }
    }
}
