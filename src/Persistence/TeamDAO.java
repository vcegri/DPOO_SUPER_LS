package Persistence;

import Business.Team;
import edu.salle.url.api.exception.ApiException;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Is used to read and write team data.
 */
public interface TeamDAO {

    /**
     * Reads all teams.
     *
     * @return A list of teams read.
     */
    ArrayList<Team> readAll() throws FileNotFoundException, ApiException;
    void saveTeams(ArrayList<Team> teamList) throws ApiException;

}
