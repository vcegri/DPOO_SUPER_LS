package Business;

import Persistence.TeamJSON;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class TeamManager {
    
    private final TeamJSON teamJson;

    public TeamManager(TeamJSON teamJson) {
        this.teamJson = teamJson;
    }

    public ArrayList<String> getNameOfTeams() throws FileNotFoundException {
        ArrayList<String> nameList = new ArrayList<>();
        ArrayList<Team> teamList = teamJson.readAll();

        for (Team team : teamList) {
            nameList.add(team.getName());
        }
        return (nameList);
    }

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

        return (teamNameList);
    }

    public boolean comproveIfTeamExist(String newName) throws FileNotFoundException {
        boolean exist = false;
        ArrayList<String> teamNameList;

        teamNameList = getNameOfTeams();
        for (int i = 0; i < teamNameList.size(); i++) {
            if (newName.equals(teamNameList.get(i))) {
                exist = true;
                i = teamNameList.size();
            }
        }
        return (exist);
    }

    public void createTeam(String name, ArrayList<TeamMember> teamMemberList) throws FileNotFoundException {
        ArrayList<Team> teamList = teamJson.readAll();
        Team team = new Team(name, teamMemberList);
        teamList.add(team);
        teamJson.saveTeams(teamList);
    }

    public ArrayList<Long> getIdListOfATeam(String name) throws FileNotFoundException {
        ArrayList<Long> memberList = new ArrayList<>();
        ArrayList<Team> teamList = teamJson.readAll();

        for (Team team : teamList) {
            String teamName = team.getName();
            if (name.equals(teamName)) {
                for (int j = 0; j < 4; j++) {
                    memberList.add(team.getMemberList().get(j).getId());
                }
            }
        }

        return (memberList);
    }

    public void deleteTeam(String name) throws FileNotFoundException {
        ArrayList<Team> teamList = teamJson.readAll();

        Team teamFound = null;
        for (int i = 0; i < teamList.size(); i++) {
            if (teamList.get(i).getName().equals(name)) {
                teamFound = teamList.get(i);
                i = teamList.size();
            }
        }

        teamList.remove(teamFound);
        teamJson.saveTeams(teamList);
    }

    public Team getTeamByName(String name) throws FileNotFoundException {
        ArrayList<Team> teamList = teamJson.readAll();

        Team teamFound = null;
        for (int i = 0; i < teamList.size(); i++) {
            if (teamList.get(i).getName().equals(name)) {
                teamFound = teamList.get(i);
                i = teamList.size();
            }
        }

        return teamFound;
    }
}
