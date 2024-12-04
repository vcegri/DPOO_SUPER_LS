package Business;

import Persistence.TeamJSON;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Manages the interaction with team data, including retrieving team information,
 * creating teams, deleting teams, and verifying the existence of a team.
 */
public class TeamManager {

    /** The TeamJSON instance used to read and save team data. */
    private final TeamJSON teamJson;

    /**
     * Constructs a TeamManager with the specified TeamJSON instance.
     *
     * @param teamJson the TeamJSON instance to interact with for team data
     */
    public TeamManager(TeamJSON teamJson) {
        this.teamJson = teamJson;
    }

    /**
     * Creates a new team with the specified name and list of team members.
     *
     * @param name the name of the new team
     * @param teamMemberList the list of team members
     * @throws FileNotFoundException if the team data cannot be written to storage
     */
    public void createTeam(String name, ArrayList<TeamMember> teamMemberList) throws FileNotFoundException {
        ArrayList<Team> teamList = teamJson.readAll();
        Team team = new Team(name, teamMemberList);
        teamList.add(team);
        teamJson.saveTeams(teamList);
    }

    /**
     * Retrieves a list of all team names.
     *
     * @return a list of names of all the teams
     * @throws FileNotFoundException if the team data cannot be read from storage
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
     * Searches for teams that a specific character is a member of, based on the character's ID.
     *
     * @param idCharacter the ID of the character to search for
     * @return a list of team names the character is a part of
     * @throws FileNotFoundException if the team data cannot be read from storage
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
     * Verifies if a team with the specified name already exists.
     *
     * @param newName the name of the team to check for existence
     * @return true if the team exists, false otherwise
     * @throws FileNotFoundException if the team data cannot be read from storage
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
     * Deletes a team based on the team's name.
     *
     * @param name the name of the team to be deleted
     * @throws FileNotFoundException if the team data cannot be read or written to storage
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
     * Retrieves a team object by its name.
     *
     * @param name the name of the team to retrieve
     * @return the Team object with the specified name
     * @throws FileNotFoundException if the team data cannot be read from storage
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
}
