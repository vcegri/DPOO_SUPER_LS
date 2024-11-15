package Business;

public class Stat {

    private final String name;
    private final int gamesPlayed;
    private final int gamesWon;
    private final int KoDone;
    private final int KoReceived;

    public Stat(String name, int gamesPlayed, int gamesWon, int koDone, int koReceived) {
        this.name = name;
        this.gamesPlayed = gamesPlayed;
        this.gamesWon = gamesWon;
        KoDone = koDone;
        KoReceived = koReceived;
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
