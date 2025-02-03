package Business;

import Persistence.TeamAPI;
import Persistence.TeamDAO;
import Persistence.TeamJSON;
import edu.salle.url.api.ApiHelper;
import edu.salle.url.api.exception.ApiException;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Manages the interactions with team data using the teamJson class.
 */
public class TeamManager {

    public static final int DEFAULT_VALUE = 0;
    public static final int TEAM_SIZE = 4;

    /** Class to manage the teams.json file. */
    private TeamDAO teamDao;

    /**
     * Constructs a TeamManager with the TeamJSON class.
     */
    public TeamManager() {
        try {
            ApiHelper api = new ApiHelper();
            teamDao = new TeamAPI();
        }catch (ApiException e){
            teamDao = new TeamJSON();
        }

    }

    /**
     * Creates a new Team with the name and list of team members.
     *
     * @param name name of the new team
     * @param teamMemberList list of team members
     * @throws FileNotFoundException if the JSON file can't be found
     * @throws ApiException if there is an error with the API
     */
    public void createTeam(String name, ArrayList<TeamMember> teamMemberList) throws FileNotFoundException, ApiException {
        ArrayList<Team> teamList = teamDao.readAll();
        Team team = new Team(name, teamMemberList);
        teamList.add(team);
        teamDao.saveTeams(teamList);
    }

    /**
     * Get a list of all team names.
     *
     * @return list of names of all the teams
     * @throws FileNotFoundException if the JSON file can't be found
     * @throws ApiException if there is an error with the API
     */
    public ArrayList<String> getNameOfTeams() throws FileNotFoundException, ApiException {
        ArrayList<String> nameList = new ArrayList<>();
        ArrayList<Team> teamList = teamDao.readAll();

        for (Team team : teamList) {
            nameList.add(team.getName());
        }
        return nameList;
    }

    /**
     * Search for teams with a specific member based on the character's ID.
     *
     * @param idCharacter ID of the character
     * @return list of team names the character is a part of
     * @throws FileNotFoundException if the JSON file can't be found
     * @throws ApiException if there is an error with the API
     */
    public ArrayList<String> searchTeamsOfCharacter(long idCharacter) throws FileNotFoundException, ApiException {
        ArrayList<String> teamNameList = new ArrayList<>();
        ArrayList<Team> teamList = teamDao.readAll();

        for (Team team : teamList) {
            for (int j = DEFAULT_VALUE; j < TEAM_SIZE; j++) {
                if (team.getMemberList().get(j).getId() == idCharacter) {
                    teamNameList.add(team.getName());
                    break;
                }
            }
        }

        return teamNameList;
    }

    /**
     * Verifies if a team with a specified name exists.
     *
     * @param newName name of the team
     * @return true if the team exists, false if not
     * @throws FileNotFoundException if the JSON file can't be found
     * @throws ApiException if there is an error with the API
     */
    public boolean comproveIfTeamExist(String newName) throws FileNotFoundException, ApiException {
        boolean exist = false;
        ArrayList<String> teamNameList = getNameOfTeams();

        for (String teamName : teamNameList) {
            if (newName.equals(teamName)) {
                exist = true;
                break;
            }
        }
        return exist;
    }

    /**
     * Deletes a team based on the team name.
     *
     * @param name name of the team
     * @throws FileNotFoundException if the JSON file can't be found
     * @throws ApiException if there is an error with the API
     */
    public void deleteTeam(String name) throws FileNotFoundException, ApiException {
        ArrayList<Team> teamList = teamDao.readAll();

        Team teamFound = null;
        for (Team team : teamList) {
            if (team.getName().equals(name)) {
                teamFound = team;
                break;
            }
        }

        if (teamFound != null) {
            teamList.remove(teamFound);
            teamDao.saveTeams(teamList);
        }
    }

    /**
     * Get a team by its name.
     *
     * @param name name of the team
     * @return Team with the specified name
     * @throws FileNotFoundException if the JSON file can't be found
     * @throws ApiException if there is an error with the API
     */
    public Team getTeamByName(String name) throws FileNotFoundException, ApiException {
        ArrayList<Team> teamList = teamDao.readAll();

        Team teamFound = null;
        for (Team team : teamList) {
            if (team.getName().equals(name)) {
                teamFound = team;
                break;
            }
        }

        return teamFound;
    }

    /**
     * Get all the teams.
     *
     * @return list of all the teams
     * @throws FileNotFoundException if the JSON file can't be found
     * @throws ApiException if there is an error with the API
     */
    public ArrayList<Team> getTeamList() throws FileNotFoundException, ApiException {
        return teamDao.readAll();
    }
}
