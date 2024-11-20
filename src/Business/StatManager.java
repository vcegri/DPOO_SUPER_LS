package Business;

import java.util.ArrayList;

public class StatManager {
    public ArrayList<Integer> getStatList(String name) {
        ArrayList<Integer> statInfoList = new ArrayList<>();

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

        return(statInfoList);
    }
}
