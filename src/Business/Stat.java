package Business;

/**
 * Represents the statistics of a character or player, including games played, games won, knockouts performed, and knockouts received.
 */
public class Stat {

    /** The name associated with the statistics (e.g., character or player name). */
    private final String name;

    /** The total number of games played. */
    private int games_played;

    /** The total number of games won. */
    private int games_won;

    /** The total number of knockouts (KO) performed by this entity. */
    private int KO_done;

    /** The total number of knockouts (KO) received by this entity. */
    private int KO_received;

    /**
     * Constructs a new Stat instance with a name and default values for all statistics.
     * All statistics are initialized to zero.
     *
     * @param name the name associated with the statistics
     */
    public Stat(String name) {
        this.name = name;
        this.games_played = 0;
        this.games_won = 0;
        this.KO_done = 0;
        this.KO_received = 0;
    }

    /**
     * Constructs a new Stat instance with the specified values for all attributes.
     *
     * @param name         the name associated with the statistics
     * @param games_played the total number of games played
     * @param games_won    the total number of games won
     * @param KO_done      the total number of knockouts performed
     * @param KO_received  the total number of knockouts received
     */
    public Stat(String name, int games_played, int games_won, int KO_done, int KO_received) {
        this.name = name;
        this.games_played = games_played;
        this.games_won = games_won;
        this.KO_done = KO_done;
        this.KO_received = KO_received;
    }

    /**
     * Returns the name associated with the statistics.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the total number of games played.
     *
     * @return the number of games played
     */
    public int getGamesPlayed() {
        return games_played;
    }

    /**
     * Returns the total number of games won.
     *
     * @return the number of games won
     */
    public int getGamesWon() {
        return games_won;
    }

    /**
     * Returns the total number of knockouts performed.
     *
     * @return the number of knockouts performed
     */
    public int getKoDone() {
        return KO_done;
    }

    /**
     * Returns the total number of knockouts received.
     *
     * @return the number of knockouts received
     */
    public int getKoReceived() {
        return KO_received;
    }

    public void updateStats(int games_played, int games_won, int KO_done, int KO_received) {
        this.games_played = games_played;
        this.games_won = games_won;
        this.KO_done = KO_done;
        this.KO_received = KO_received;
    }
}
