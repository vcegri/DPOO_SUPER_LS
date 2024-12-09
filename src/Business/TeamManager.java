package Business;

import Persistence.TeamJSON;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Manages the interactions with team data using the teamJson class.
 */
public class TeamManager {

    /** Class to manage the teams.json file. */
    private final TeamJSON teamJson;

    /**
     * Constructs a TeamManager with the TeamJSON class.
     *
     * @param teamJson class to manage the teams.json file
     */
    public TeamManager(TeamJSON teamJson) {
        this.teamJson = teamJson;
    }

    /**
     * Creates a new Team with the name and list of team members.
     *
     * @param name name of the new team
     * @param teamMemberList list of team members
     * @throws FileNotFoundException if the team data can't be written
     */
    public void createTeam(String name, ArrayList<TeamMember> teamMemberList) throws FileNotFoundException {
        ArrayList<Team> teamList = teamJson.readAll();
        Team team = new Team(name, teamMemberList);
        teamList.add(team);
        teamJson.saveTeams(teamList);
    }

    /**
     * Get a list of all team names.
     *
     * @return list of names of all the teams
     * @throws FileNotFoundException if the team data can't be read
     */
    public ArrayList<String> getNameOfTeams() throws FileNotFoundException {
        ArrayList<String> nameList = new ArrayList<>();
        ArrayList<Team> teamList = teamJson.readAll();

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
     * @throws FileNotFoundException if the team data can't be read
     */
    public ArrayList<String> searchTeamsOfCharacter(long idCharacter) throws FileNotFoundException {
        ArrayList<String> teamNameList = new ArrayList<>();
        ArrayList<Team> teamList = teamJson.readAll();

        for (Team team : teamList) {
            for (int j = 0; j < 4; j++) {
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
     * @throws FileNotFoundException if the team data can't be read
     */
    public boolean comproveIfTeamExist(String newName) throws FileNotFoundException {
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
     * @throws FileNotFoundException if the team data can't be read or written
     */
    public void deleteTeam(String name) throws FileNotFoundException {
        ArrayList<Team> teamList = teamJson.readAll();

        Team teamFound = null;
        for (Team team : teamList) {
            if (team.getName().equals(name)) {
                teamFound = team;
                break;
            }
        }

        if (teamFound != null) {
            teamList.remove(teamFound);
            teamJson.saveTeams(teamList);
        }
    }

    /**
     * Get a team by its name.
     *
     * @param name name of the team
     * @return Team with the specified name
     * @throws FileNotFoundException if the team data can't be read
     */
    public Team getTeamByName(String name) throws FileNotFoundException {
        ArrayList<Team> teamList = teamJson.readAll();

        Team teamFound = null;
        for (Team team : teamList) {
            if (team.getName().equals(name)) {
                teamFound = team;
                break;
            }
        }

        return teamFound;
    }

    public ArrayList<Team> getTeamList() throws FileNotFoundException {
        return teamJson.readAll();
    }
}
