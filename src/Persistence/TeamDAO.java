package Persistence;

import Business.Team;
import edu.salle.url.api.exception.ApiException;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface TeamDAO {
    ArrayList<Team> readAll() throws FileNotFoundException, ApiException;
    void saveTeams(ArrayList<Team> teamList);
}
