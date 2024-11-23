package Business;

import Persistence.TeamJSON;

import java.util.ArrayList;

public class TeamManager {
    
    private TeamJSON teamJson;

    public TeamManager(TeamJSON teamJson) {
        this.teamJson = teamJson;
    }

    public ArrayList<String> getNameOfTeams() {
        ArrayList<String> nameList = new ArrayList<>();

        for (int i = 0; i < teamList.size(); i++) {
            nameList.add(teamList.get(i).getName());
        }
        return (nameList);
    }

    public ArrayList<String> searchTeamsOfCharacter(int character) {
        ArrayList<String> teamNameList = new ArrayList<>();
        ArrayList<Integer> memberList;


        return (teamNameList);
    }

    public boolean comproveIfTeamExist(String newName) {
        boolean exist = false;
        ArrayList<String> teamNameList;

        teamNameList = getNameOfTeams(teamList);
        for (int i = 0; i < teamNameList.size(); i++) {
            if (newName.equals(teamNameList.get(i))) {
                exist = true;
            }
        }
        return (exist);
    }

    public void createTeam(String name, ArrayList<TeamMember> teamMemberList) {
    }

    public ArrayList<Integer> getIdListOfATeam(String name) {
        ArrayList<Integer> memberList = new ArrayList<>();

        for (int i = 0; i < teamList.size(); i++) {
            teamName = teamList.get(i).getName();
            if (name.equals(teamName)) {
                memberList = teamList.get(i).getMemberList();
            }
        }

        return (memberList);
    }

    public void deleteTeam(String name) {}

    public Team getTeamByName(String name){
        Team team;

        return team;
    }
}
