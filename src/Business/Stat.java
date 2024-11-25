package Business;

public class Stat {

    private final String name;
    private final int games_played;
    private final int games_won;
    private final int KO_done;
    private final int KO_received;

    public Stat(String name) {
        this.name = name;
        this.games_played = 0;
        this.games_won = 0;
        this.KO_done = 0;
        this.KO_received = 0;
    }

    public String getName() {
        return name;
    }

    public int getGamesPlayed() {
        return games_played;
    }

    public int getGamesWon() {
        return games_won;
    }

    public int getKoDone() {
        return KO_done;
    }

    public int getKoReceived() {
        return KO_received;
    }
}
