package Business;

/**
 * Represents the stats of a Team with the games played, games won, knockouts performed, and knockouts received.
 */
public class Stat {

    public static final int DEFAULT_VALUE = 0;

    /** Team name associated with the stats */
    private final String name;

    /** Total number of games played. */
    private int games_played;

    /** Total number of games won. */
    private int games_won;

    /** Total number of KO done by the Team. */
    private int KO_done;

    /** Total number of KO received by the Team. */
    private int KO_received;

    /**
     * Constructs a new Stat with a name and default values for all statistics.
     *
     * @param name the name associated with the statistics
     */
    public Stat(String name) {
        this.name = name;
        this.games_played = DEFAULT_VALUE;
        this.games_won = DEFAULT_VALUE;
        this.KO_done = DEFAULT_VALUE;
        this.KO_received = DEFAULT_VALUE;
    }

    /**
     * Returns the Team name.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the total number of games played.
     *
     * @return number of games played
     */
    public int getGamesPlayed() {
        return games_played;
    }

    /**
     * Returns the total number of games won.
     *
     * @return number of games won
     */
    public int getGamesWon() {
        return games_won;
    }

    /**
     * Returns the total number of KOs done.
     *
     * @return number of KOs done
     */
    public int getKoDone() {
        return KO_done;
    }

    /**
     * Returns the total number of KOs received.
     *
     * @return number of KOs received
     */
    public int getKoReceived() {
        return KO_received;
    }

    /**
     * Updates the stats of a Team.
     *
     * @param games_played total number of games played.
     * @param games_won total number of games won.
     * @param KO_done total number of KOs done.
     * @param KO_received total number of KOs received.
     */
    public void updateStats(int games_played, int games_won, int KO_done, int KO_received) {
        this.games_played = games_played;
        this.games_won = games_won;
        this.KO_done = KO_done;
        this.KO_received = KO_received;
    }
}
