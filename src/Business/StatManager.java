package Business;

import Persistence.StatJSON;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class StatManager {
    
    private final StatJSON statJson;

    public StatManager(StatJSON statJson) {
        this.statJson = statJson;
    }

    public void createStat(String name) {
        Stat stat = new Stat(name);
    }

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

        return (statInfoList);
    }

    public void updateStats(boolean winner, String teamName) throws FileNotFoundException {
        ArrayList<Stat> statList = statJson.readAll();
        int gamesPlayed, gamesWon, KO_done, KO_received;

        for (int i = 0; i < statJson.readAll().size(); i++){
            if (statList.get(i).getName().equals(teamName)){
                if (winner){
                    gamesPlayed =  statList.get(i).getGamesPlayed();
                    gamesPlayed++;
                    gamesWon = statList.get(i).getGamesWon();
                    gamesWon++;
                    KO_done = statList.get(i).getKoDone();
                    KO_done++;
                    KO_received = statList.get(i).getKoReceived();
                    KO_received++;
                    statList.remove(i);
                    Stat stat = new Stat(teamName, gamesPlayed, gamesWon, KO_done, KO_received);
                    statList.add(stat);
                    statJson.saveStatList(statList);
                }
            }
        }
    }
}
