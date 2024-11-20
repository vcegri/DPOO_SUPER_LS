package Business;

public class Stat {

    private final String name;
    private final int gamesPlayed;
    private final int gamesWon;
    private final int KoDone;
    private final int KoReceived;

    public Stat(String name) {
        this.name = name;
        this.gamesPlayed = 0;
        this.gamesWon = 0;
        KoDone = 0;
        KoReceived = 0;
    }

    public String getName() {
        return name;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public int getKoDone() {
        return KoDone;
    }

    public int getKoReceived() {
        return KoReceived;
    }
}
