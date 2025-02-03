package Business;

import Persistence.*;
import edu.salle.url.api.ApiHelper;
import edu.salle.url.api.exception.ApiException;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Manages the interactions with stat data using the statJson class.
 */
public class StatManager {

    public static final int TEAM_SIZE = 4;
    public static final int FIRST_TEAM = 0;
    public static final int SECOND_TEAM = 1;
    public static final int DEFAULT_VALUE = 0;

    /** Class to manage the stats.json file. */
    private StatDAO statDao;

    /**
     * Constructs a StatManager with the StatJSON class.
     */
    public StatManager() {
        try {
            ApiHelper api = new ApiHelper();
            statDao = new StatAPI();
        }catch (ApiException e){
            statDao = new StatJSON();
        }    }

    /**
     * Creates a new Stat for a team with the given name.
     *
     * @param name name of the team
     * @throws FileNotFoundException if the JSON file can't be found
     * @throws ApiException if there is an error with the API
     */
    public void createStat(String name) throws FileNotFoundException, ApiException {
        ArrayList<Stat> statList = statDao.readAll();

        Stat stat = new Stat(name);
        statList.add(stat);
        statDao.saveStatList(statList);
    }

    /**
     * Get the stats of a team by its name.
     *
     * @param name name of the team
     * @return list of integers representing the teams stats
     * @throws FileNotFoundException if the JSON file can't be found
     * @throws ApiException if there is an error with the API
     */
    public ArrayList<Integer> getStatList(String name) throws FileNotFoundException, ApiException {
        ArrayList<Integer> statInfoList = new ArrayList<>();
        ArrayList<Stat> statList = statDao.readAll();

        for (Stat stat : statList) {

            if (name.equals(stat.getName())) {
                statInfoList.add(stat.getGamesPlayed());
                statInfoList.add(stat.getGamesWon());
                statInfoList.add(stat.getKoDone());
                statInfoList.add(stat.getKoReceived());
                double winrate;
                int WinRate = DEFAULT_VALUE;
                if (stat.getGamesPlayed() != DEFAULT_VALUE) {
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
     * @param teamNum  number of the team
     * @throws FileNotFoundException if the JSON file can't be found
     * @throws ApiException if there is an error with the API
     */
    public void updateStats(String winner, String teamName, ArrayList<Boolean> koList, int teamNum) throws FileNotFoundException, ApiException {
        ArrayList<Stat> statList = statDao.readAll();
        int gamesPlayed, gamesWon, KO_done, KO_received;

        for (int i = DEFAULT_VALUE; i < statList.size(); i++){
            if (statList.get(i).getName().equals(teamName)){
                KO_received = statList.get(i).getKoReceived();
                KO_done = statList.get(i).getKoDone();
                gamesPlayed = statList.get(i).getGamesPlayed();
                gamesPlayed++;
                gamesWon = statList.get(i).getGamesWon();
                if (winner.equals(teamName)) {
                    gamesWon++;
                    KO_done = KO_done + TEAM_SIZE;

                    int cont = DEFAULT_VALUE;
                    if (teamNum == FIRST_TEAM) {
                        for (int j = DEFAULT_VALUE; j < TEAM_SIZE; j++) {
                            if (koList.get(j)) {
                                cont++;
                            }
                        }
                    }
                    else {
                        for (int j = TEAM_SIZE; j < (2* TEAM_SIZE); j++) {
                            if (koList.get(j)) {
                                cont++;
                            }
                        }
                    }
                    KO_received = KO_received + cont;
                }
                else {
                    KO_received = KO_received + TEAM_SIZE;

                    int cont = DEFAULT_VALUE;
                    if (teamNum == SECOND_TEAM) {
                        for (int j = DEFAULT_VALUE; j < TEAM_SIZE; j++) {
                            if (koList.get(j)) {
                                cont++;
                            }
                        }
                    }
                    else {
                        for (int j = TEAM_SIZE; j < (2* TEAM_SIZE); j++) {
                            if (koList.get(j)) {
                                cont++;
                            }
                        }
                    }
                    KO_done = KO_done + cont;
                }

                statList.get(i).updateStats(gamesPlayed, gamesWon, KO_done, KO_received);
                statDao.saveStatList(statList);
            }
        }
    }

    /**
     * Deletes the stats of a team by its name.
     *
     * @param name name of the team
     * @throws FileNotFoundException if the JSON file can't be found
     * @throws ApiException if there is an error with the API
     */
    public void deleteStat(String name) throws FileNotFoundException, ApiException {
        ArrayList<Stat> statList = statDao.readAll();

        Stat statFound = null;
        for (Stat stat : statList) {
            if (stat.getName().equals(name)) {
                statFound = stat;
                break;
            }
        }

        if (statFound != null) {
            statList.remove(statFound);
            statDao.saveStatList(statList);
        }
    }
}