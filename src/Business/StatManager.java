package Business;

import Persistence.StatJSON;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class StatManager {
    
    private StatJSON statJson;

    public StatManager(StatJSON statJson) {
        this.statJson = statJson;
    }

    public void createStat(String name) {
        Stat stat = new Stat(name);
    }

    public ArrayList<Integer> getStatList(String name) throws FileNotFoundException {
        ArrayList<Integer> statInfoList = new ArrayList<>();
        ArrayList<Stat> statList = statJson.readAll();

        for (int i = 0; i < statList.size(); i++) {
            Stat stat = statList.get(i);
            if (name.equals(stat.getName())) {
                statInfoList.add(stat.getGamesPlayed());
                statInfoList.add(stat.getGamesWon());
                statInfoList.add(stat.getKoDone());
                statInfoList.add(stat.getKoReceived());
                int winrate =  ((stat.getGamesWon()/stat.getGamesPlayed())*100);
                statInfoList.add(winrate);
            }
        }

        return (statInfoList);
    }
}
